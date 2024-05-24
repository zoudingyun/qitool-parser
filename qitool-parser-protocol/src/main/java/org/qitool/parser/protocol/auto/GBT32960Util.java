package org.qitool.parser.protocol.auto;

import org.qitool.parser.general.BCCUtil;
import org.qitool.parser.general.BaseDataTypeUtil;
import org.qitool.parser.gis.CoordinateUtil;
import org.qitool.parser.gis.domain.LatLongCoordinate;
import org.qitool.parser.protocol.auto.GBT32960.domain.AutoData;
import org.qitool.parser.protocol.auto.GBT32960.domain.CommandUnit;
import org.qitool.parser.protocol.auto.GBT32960.domain.DataUnit;
import org.qitool.parser.protocol.auto.GBT32960.domain.DataUnitDataRealTimeData;
import org.qitool.parser.protocol.auto.GBT32960.domain.RTData.*;
import org.qitool.parser.protocol.auto.GBT32960.domain.dto.CheckedValue;
import org.qitool.parser.protocol.auto.GBT32960.enums.*;
import org.qitool.parser.protocol.auto.GBT32960.exceptions.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * GB-T 32960.1-2016 报文工具
 *
 * @author zoudingyun
 * 2024/5/14 14:26
 */
public class GBT32960Util {

    private GBT32960Util() {}

    /**
     * 0
     * */
    private static final BigDecimal VALUE_0 = new BigDecimal(0);


    /**
     * 1
     * */
    private static final BigDecimal VALUE_1 = new BigDecimal(1);

    /**
     * 1
     * */
    private static final BigDecimal VALUE_2 = new BigDecimal(2);


    /**
     * 4
     * */
    private static final BigDecimal VALUE_4 = new BigDecimal(4);

    /**
     * 40
     * */
    private static final BigDecimal VALUE_40 = new BigDecimal(40);

    /**
     * 1000
     * */
    private static final BigDecimal VALUE_1000 = new BigDecimal(1000);


    /**
     * 2000
     * */
    private static final BigDecimal VALUE_2000 = new BigDecimal(2000);

    /**
     * 20000
     * */
    private static final BigDecimal VALUE_20000 = new BigDecimal(20000);


    /**
     * 0.001
     * */
    private static final BigDecimal VALUE_0_001 = new BigDecimal("0.001");

    /**
     * 0.1
     * */
    private static final BigDecimal VALUE_0_1 = new BigDecimal("0.1");

    /**
     * 异常描述
     * */
    private static final String EXCEPTIONAL = "异常";

    /**
     * 无效描述
     * */
    private static final String INVALID = "无效";



    /**
     * 解析GB-T 32960原始报文数据
     * @param gbt32960Hex hex数据
     * @return 解析后的数据
     * */
    public static AutoData format(String gbt32960Hex) {
        return  format(BaseDataTypeUtil.hexStringToBytes(gbt32960Hex));
    }


    /**
     * 解析GB-T 32960原始报文数据
     * @param gbt32960Bytes 报文数据
     * @return 解析后的数据
     * */
    public static AutoData format(byte[] gbt32960Bytes) {

        AutoData autoData = new AutoData();
        // 数据校验
        autoData.setBcc(checkData(gbt32960Bytes));
        // 解析命令单元
        autoData.setCommandUnit(formatCommandUnit(gbt32960Bytes));
        // 解析VIN码
        autoData.setVin(formatVin(gbt32960Bytes));
        // 解析数据加密类型
        autoData.setEncryptionType(formatEncryptionType(gbt32960Bytes));
        // 数据单元长度
        autoData.setDataUnitLength(formatDataUnitLength(gbt32960Bytes));
        // 数据单元
        autoData.setDataUnit(formatDataUnit(gbt32960Bytes,autoData.getDataUnitLength(),autoData.getCommandUnit(),new DataUnit(),autoData.getEncryptionType()));
        return autoData;
    }


    /**
     * 格式化：命令单元数据
     * @param gbt32960Bytes 报文数据
     * @return 命令单元数据
     * */
    private static CommandUnit formatCommandUnit(byte[] gbt32960Bytes) {
        CommandUnit commandUnit = new CommandUnit();
        // 命令单元部分
        byte[] commandUnitBytes = Arrays.copyOfRange(gbt32960Bytes, 2, 4);
        // 命令标识(byte转无符号位后进行比较)
        int commandValue = commandUnitBytes[0] & 0xFF;
        switch (commandValue) {
            case 0x01:{
                // 车辆登入
                commandUnit.setCommandIdentification(CommandIdentification.UP_AUTO_LOGIN);
                break;
            }
            case 0x02:{
                // 实时信息上报
                commandUnit.setCommandIdentification(CommandIdentification.UP_REAL_TIME_DATA_REPORT);
                break;
            }
            case 0x03:{
                // 补发信息上报
                commandUnit.setCommandIdentification(CommandIdentification.UP_REAL_TIME_DATA_COMPLETE);
                break;
            }
            case 0x04:{
                // 车辆登出
                commandUnit.setCommandIdentification(CommandIdentification.UP_AUTO_LOGOUT);
                break;
            }
            case 0x05:
            case 0x06:{
                // 平台传输数据占用
                commandUnit.setCommandIdentification(CommandIdentification.CUSTOM_DATA_OCCUPY);
                break;
            }
            case 0x07:{
                // 心跳
                commandUnit.setCommandIdentification(CommandIdentification.UP_HEARTBEAT);
                break;
            }
            case 0x08:{
                // 终端校时
                commandUnit.setCommandIdentification(CommandIdentification.UP_TIMING);
                break;
            }
            case 0x80:{
                // 查询命令
                commandUnit.setCommandIdentification(CommandIdentification.DOWN_COMMAND_QUERY);
                break;
            }
            case 0x81:{
                // 设置命令
                commandUnit.setCommandIdentification(CommandIdentification.DOWN_COMMAND_SETUP);
                break;
            }
            case 0x82:{
                // 车载终端控制命令
                commandUnit.setCommandIdentification(CommandIdentification.DOWN_COMMAND_CONTROL);
                break;
            }
            default:{
                if (commandValue>=0x09 && commandValue<=0x7F) {
                    // 上行数据系统预留
                    commandUnit.setCommandIdentification(CommandIdentification.UP_DATA_SYSTEM_RESERVATION);
                    break;
                }else if (commandValue>=0x83 && commandValue<=0xBF){
                    // 下行数据系统预留
                    commandUnit.setCommandIdentification(CommandIdentification.DOWN_DATA_SYSTEM_RESERVATION);
                    break;
                }else if (commandValue>=0xC0 && commandValue<=0xFE){
                    // 平台交换自定义数据
                    commandUnit.setCommandIdentification(CommandIdentification.CUSTOM_DATA_EXCHANGE);
                    break;
                }else {
                    // 匹配不上任何标志
                    throw new UnknowCommandIdentification(BaseDataTypeUtil.bytesToHexString(commandUnitBytes[0]));
                }
            }

        }
        // 应答标志(byte转无符号位后进行比较)
        int responseFlagValue = commandUnitBytes[1] & 0xFF;
        switch (responseFlagValue) {
            case 0x01:{
                // 成功
                commandUnit.setResponseFlag(ResponseFlag.SUCCESS);
                break;
            }
            case 0x02:{
                // 错误
                commandUnit.setResponseFlag(ResponseFlag.ERROR);
                break;
            }
            case 0x03:{
                // VIN重复
                commandUnit.setResponseFlag(ResponseFlag.VIN_REPEAT);
                break;
            }
            case 0xFE:{
                // 命令
                commandUnit.setResponseFlag(ResponseFlag.COMMAND);
                break;
            }
            default:{
                throw new UnknowResponseFlag(BaseDataTypeUtil.bytesToHexString(commandUnitBytes[1]));
            }
        }


        return commandUnit;
    }

    /**
     * 格式化：VIN码
     * @param gbt32960Bytes 报文数据
     * @return VIN码
     * */
    private static String formatVin(byte[] gbt32960Bytes){
        // VIN码部分
        byte[] vinBytes = Arrays.copyOfRange(gbt32960Bytes, 4, 21);
        return new String(vinBytes);
    }


    /**
     * 格式化：加密方式
     * @param gbt32960Bytes 报文数据
     * @return 加密方式
     * */
    private static EncryptionType formatEncryptionType(byte[] gbt32960Bytes){
        // 加密方式部分
        byte[] encryptionTypeBytes = Arrays.copyOfRange(gbt32960Bytes, 21, 22);
        // 加密方式(byte转无符号位后进行比较)
        int encryptionTypeValue = encryptionTypeBytes[0] & 0xFF;
        switch (encryptionTypeValue){
            case 0x01:{
                return EncryptionType.RAW;
            }
            case 0x02:{
                return EncryptionType.RSA;
            }
            case 0x03:{
                return EncryptionType.AES128;
            }
            case 0xFE:{
                return EncryptionType.EXCEPTION;
            }
            case 0xFF:{
                return EncryptionType.INVALID;
            }
            default:{
                throw new UnknowEncryptionType(encryptionTypeBytes[0]);
            }
        }
    }


    /**
     * 格式化GB-T 32960原始报文中数据长度
     * @param gbt32960Bytes 报文数据
     * @return 数据长度
     * */
    private static int formatDataUnitLength(byte[] gbt32960Bytes){
        // 数据长度部分
        byte[] lengthBytes = Arrays.copyOfRange(gbt32960Bytes, 22, 24);
        // 数据长度(byte转无符号位后进行比较)
        return BaseDataTypeUtil.bytesToUnsignedBigInteger(lengthBytes).intValue();

    }


    /**
     * 格式化：数据单元
     * @param gbt32960Bytes 报文数据
     * @param commandUnit 命令单元
     * @param dataUnit 数据单元
     * @param commandUnitLength 命令单元长度
     * @param encryptionType 加密方式
     * @return 数据单元
     * */
    private static DataUnit formatDataUnit(byte[] gbt32960Bytes, int commandUnitLength, CommandUnit commandUnit, DataUnit dataUnit, EncryptionType encryptionType){
        switch (encryptionType){
            case RAW:{
                switch (commandUnit.getCommandIdentification()){
                    case UP_REAL_TIME_DATA_REPORT:{
                        // 实时信息上报
                        if (commandUnitLength == 0){
                            // 数据长度为0
                            return dataUnit;
                        }
                        // 数据单元部分
                        byte[] dataUnitBytes = Arrays.copyOfRange(gbt32960Bytes, 24, gbt32960Bytes.length-1);
                        if (dataUnitBytes.length!=commandUnitLength){
                            throw new DataUnitLengthMismatch(dataUnitBytes.length,commandUnitLength);
                        }
                        // 开始解析数据
                        DataUnitDataRealTimeData realTimeData = new DataUnitDataRealTimeData();
                        // ============================================时间部分(6字节)===================================//
                        String dateTimeStr = getTimeString(dataUnitBytes);
                        // 定义时间格式
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                        // 解析时间字符串
                        LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                        realTimeData.setDataTimeDesc(dateTimeStr);
                        realTimeData.setDataTime(java.util.Date.from(dateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()));

                        // ============================================实时信息上报===================================//
                        // 从第7个字节开始时数据位
                        analysistRealTimeData(dataUnitBytes,6,realTimeData);
                        dataUnit.setRealTimeData(realTimeData);
                    }
                    default:{
                        return dataUnit;
                    }
                }
            }
            default:{
                throw new UnsupportedEncryptionMethod(encryptionType);
            }
        }
    }


    /**
     * 分析实时信息上报报文
     * @param dataBytes 数据报文
     * @param dataUnit 待格式化的数据单元
     * @param startBytesIndex 单个信息体起始位置（信息类型标志的位置）
     * @return 格式化后的数据单元
     * */
    private static DataUnitDataRealTimeData analysistRealTimeData(byte[] dataBytes, int startBytesIndex, DataUnitDataRealTimeData dataUnit){
        // 判断数据类型
        byte[] dataTypeBytes = Arrays.copyOfRange(dataBytes, startBytesIndex, startBytesIndex+1);
        int dataTypeValue = dataTypeBytes[0] & 0xFF;
        // 数据起始位置
        int dataStartIndex = startBytesIndex+1;
        switch (dataTypeValue){
            case 0x01:{
                // 整车数据
                // 数据结束位置
                int dataEndIndex = dataStartIndex+20;
                // 获取当前数据包数据数组
                byte[] thisDataBytes = Arrays.copyOfRange(dataBytes, dataStartIndex, dataEndIndex);
                // 整车数据
                dataUnit.setAutoStatisticsData(analysistAutoStatisticsData(thisDataBytes));
                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }
            }
            case 0x02:{
                // 驱动电机数据
                // 驱动电机数量
                int driverCount = BaseDataTypeUtil.byteToUnsignedBigInteger(dataBytes[dataStartIndex]).intValue();
                dataUnit.setDriveMotorCount(driverCount);
                dataStartIndex++;
                // 数据结束位置(每个电机数据体长度为12)
                int dataEndIndex = dataStartIndex+(12*driverCount);
                // 获取当前数据包数据数组
                byte[] thisDataBytes = Arrays.copyOfRange(dataBytes, dataStartIndex, dataEndIndex);
                dataUnit.setMotorData(analysisDriveMotorData(thisDataBytes,0,new ArrayList<>(driverCount)));
                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }
            }
            case 0x03:{
                // 燃料电池数据
                // 温度探针数量
                int count = BaseDataTypeUtil.bytesToUnsignedBigInteger(Arrays.copyOfRange(dataBytes, dataStartIndex+6, dataStartIndex+8)).intValue();
                // 数据结束位置
                int dataEndIndex = dataStartIndex+2+2+2+2+count+2+1+2+1+2+1+1;

                //TODO: 实现燃料电池数据分析

                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }
            }
            case 0x04:{
                // 发动机数据
                // 数据结束位置
                int dataEndIndex = dataStartIndex+1+2+2;

                //TODO: 实现发动机数据分析

                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }
            }
            case 0x05:{
                // 车辆定位
                // 数据结束位置
                int dataEndIndex = dataStartIndex+1+4+4;
                // 获取当前数据包数据数组
                byte[] thisDataBytes = Arrays.copyOfRange(dataBytes, dataStartIndex, dataEndIndex);
                dataUnit.setPosition(analysisPositionData(thisDataBytes));

                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }
            }
            case 0x06:{
                // 极值数据
                // 数据结束位置
                int dataEndIndex = dataStartIndex+14;
                // 获取当前数据包数据数组
                byte[] thisDataBytes = Arrays.copyOfRange(dataBytes, dataStartIndex, dataEndIndex);
                dataUnit.setMaxMinData(analysistMaxMinData(thisDataBytes));

                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }
            }
            case 0x07:{
                // 报警数据
                // 获取当前数据包数据数组
                byte[] thisDataBytes = Arrays.copyOfRange(dataBytes, dataStartIndex, dataBytes.length);
                dataUnit.setAlarmData(analysisAlarmData(thisDataBytes));
                // 数据结束位置
                int dataEndIndex = dataStartIndex+dataUnit.getAlarmData().getAlarmBytesLength();

                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }

            }
            case 0x08:{
                // 分析可充电储能装置电压数据
                if (dataUnit.getBatterySubsystemData()==null){
                    dataUnit.setBatterySubsystemData(new BatterySubsystemData());
                }
                // 获取当前数据包数据数组
                byte[] thisDataBytes = Arrays.copyOfRange(dataBytes, dataStartIndex, dataBytes.length);
                analysisBatterySubsystemVoltageData(thisDataBytes,dataUnit.getBatterySubsystemData());

                // 数据结束位置
                int dataEndIndex = dataStartIndex+dataUnit.getBatterySubsystemData().getSubsystemCountForVoltageBytesLength();

                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }
            }
            case 0x09:{
                // 分析可充电储能装置温度数据
                if (dataUnit.getBatterySubsystemData()==null){
                    dataUnit.setBatterySubsystemData(new BatterySubsystemData());
                }
                // 获取当前数据包数据数组
                byte[] thisDataBytes = Arrays.copyOfRange(dataBytes, dataStartIndex, dataBytes.length);
                analysisBatterySubsystemTemperatureData(thisDataBytes,dataUnit.getBatterySubsystemData());

                // 数据结束位置
                int dataEndIndex = dataStartIndex+dataUnit.getBatterySubsystemData().getSubsystemCountForTemperatureBytesLength();

                // 递归查询下一波
                if (dataEndIndex>=dataBytes.length){
                    return dataUnit;
                }else {
                    return analysistRealTimeData(dataBytes,dataEndIndex,dataUnit);
                }
            }
            default:{
                // 整车数据
                return dataUnit;
            }
        }
    }


    /**
     * 分析可充电储能装置温度数据
     * @param dataBytes 温度数据报文
     * @param batterySubsystemData 电池子系统汇总数据
     * */
    private static void analysisBatterySubsystemTemperatureData(byte[] dataBytes,BatterySubsystemData batterySubsystemData){

        // 报文长度
        int realLength = 0;
        // 可充电储能电压数据子系统个数(1字节)
        realLength +=1;
        CheckedValue value = analysisByteData(dataBytes,VALUE_1.intValue());
        batterySubsystemData.setSubsystemCountForTemperature(value.getValue());

        // 温度数据
        List<BatterySubsystemTemperatureData> dataList = new ArrayList<>();

        // 子系统温度信息列表
        if (value.getValue() instanceof Number){
            // 如果子系统数量是个数字（说明有效）
            int count = (int)value.getValue();
            // 子系统电压数据
            for (int i=0;i<count;i++){
                BatterySubsystemTemperatureData temperatureData = new BatterySubsystemTemperatureData();
                // 可充电储能子系统号(1字节)
                realLength +=1;
                value = analysisByteDataWithoutDesc(value.getDataBytes(),VALUE_1.intValue());
                temperatureData.setSubsystemId(((BigInteger) value.getValue()).intValue());
                // 可充电储能温度探针个数(2字节)
                realLength +=2;
                value = analysisByteData(value.getDataBytes(),VALUE_2.intValue());
                temperatureData.setProbesCount(value.getValue());

                // 探针温度数据
                List<BatterySubsystemProbesTemperatureData> probesList = new ArrayList<>();
                if (temperatureData.getProbesCount() instanceof Number){
                    // 如果探针数量无误
                    for (int j=0;j<(int)temperatureData.getProbesCount();j++){
                        BatterySubsystemProbesTemperatureData probesTemperatureData = new BatterySubsystemProbesTemperatureData();
                        probesTemperatureData.setProbesId(j);

                        // 温度(每个1字节)
                        realLength +=1;
                        value = analysisByteData(value.getDataBytes(),VALUE_1.intValue(),VALUE_1,VALUE_40);
                        probesTemperatureData.setTemperature(value.getValue());

                        probesList.add(probesTemperatureData);
                    }
                }
                temperatureData.setProbesTemperatureData(probesList);
                dataList.add(temperatureData);
            }
        }
        batterySubsystemData.setSubsystemCountForTemperatureBytesLength(realLength);
        batterySubsystemData.setTemperatureData(dataList);
    }


    /**
     * 分析可充电储能装置电压数据
     * @param dataBytes 电压数据报文
     * @param batterySubsystemData 电池子系统汇总数据
     * */
    private static void analysisBatterySubsystemVoltageData(byte[] dataBytes,BatterySubsystemData batterySubsystemData){

        // 报文长度
        int realLength = 0;
        // 可充电储能电压数据子系统个数(1字节)
        realLength +=1;
        CheckedValue value = analysisByteData(dataBytes,VALUE_1.intValue());
        batterySubsystemData.setSubsystemCountForVoltage(value.getValue());

        // 电压数据
        List<BatterySubsystemVoltageData> dataList = new ArrayList<>();

        // 子系统电压信息列表
        if (value.getValue() instanceof Number){
            // 如果子系统数量是个数字（说明有效）
            int count = (int)value.getValue();
            // 子系统电压数据
            for (int i=0;i<count;i++){
                BatterySubsystemVoltageData batterySubsystemVoltageData = new BatterySubsystemVoltageData();
                // 可充电储能子系统号(1字节)
                realLength +=1;
                value = analysisByteDataWithoutDesc(value.getDataBytes(),VALUE_1.intValue());
                batterySubsystemVoltageData.setSubsystemId(((BigInteger) value.getValue()).intValue());
                // 可充电储能装置电压(2字节)
                realLength +=2;
                value = analysisByteData(value.getDataBytes(),VALUE_2.intValue(),VALUE_0_1,VALUE_0);
                batterySubsystemVoltageData.setVoltage(value.getValue());

                // 可充电储能装置电流(2字节)
                realLength +=2;
                value = analysisByteData(value.getDataBytes(),VALUE_2.intValue(),VALUE_0_1,VALUE_1000);
                batterySubsystemVoltageData.setCurrent(value.getValue());

                // 单体电池总数(2字节)
                realLength +=2;
                value = analysisByteData(value.getDataBytes(),VALUE_2.intValue());
                batterySubsystemVoltageData.setCellCount(value.getValue());

                // 本帧起始电池序号(2字节)
                realLength +=2;
                value = analysisByteDataWithoutDesc(value.getDataBytes(),VALUE_2.intValue());
                batterySubsystemVoltageData.setDatsStartCellId(((BigInteger) value.getValue()).intValue());

                // 本帧单体电池总数(1字节)
                realLength +=1;
                value = analysisByteDataWithoutDesc(value.getDataBytes(),VALUE_1.intValue());
                batterySubsystemVoltageData.setDataCellCount(((BigInteger) value.getValue()).intValue());

                // 单体电池电压(每个2字节)
                realLength += (2*batterySubsystemVoltageData.getDataCellCount());
                List<BatterySubsystemCellVoltageData> voltageDatas = new ArrayList<>(batterySubsystemVoltageData.getDataCellCount());
                for (int j=0;j<batterySubsystemVoltageData.getDataCellCount();j++){
                    BatterySubsystemCellVoltageData voltageData = new BatterySubsystemCellVoltageData();
                    // 单体电池序号
                    voltageData.setCellId(batterySubsystemVoltageData.getDatsStartCellId()+j);
                    // 电压
                    value = analysisByteData(value.getDataBytes(),VALUE_2.intValue(),VALUE_0_001,VALUE_0);
                    voltageData.setVoltage(value.getValue());

                    voltageDatas.add(voltageData);
                }
                batterySubsystemVoltageData.setCellVoltageData(voltageDatas);


                dataList.add(batterySubsystemVoltageData);
            }
        }
        batterySubsystemData.setSubsystemCountForVoltageBytesLength(realLength);
        batterySubsystemData.setVoltageData(dataList);
    }


    /**
     * 分析报警数据
     * @param dataBytes 报警数据报文
     * @return 报警数据
     * */
    private static AlarmData analysisAlarmData(byte[] dataBytes){
        // 报文长度
        int realLength = 0;
        // 告警数据
        AlarmData alarmData = new AlarmData();
        // 告警等级(1字节)
        realLength +=1;
        CheckedValue value = analysisByteData(dataBytes,VALUE_1.intValue());
        alarmData.setAlarmLevel(value.getValue());
        // 通用报警标志(4字节)
        realLength +=4;
        AlarmCommonFlag alarmCommonFlag = new AlarmCommonFlag(Arrays.copyOfRange(value.getDataBytes(), 0, VALUE_4.intValue()));
        value = analysisByteDataWithoutDesc(value.getDataBytes(),VALUE_4.intValue());
        alarmData.setAlarmCommonFlag(alarmCommonFlag);
        // 可充电储能装置故障总数N1(1字节)
        realLength +=1;
        value = analysisByteData(value.getDataBytes(),VALUE_1.intValue());
        alarmData.setEnergyStorageAlarmCount(value.getValue());
        // 可充电储能装置故障代码列表
        if (value.getValue() instanceof Number){
            // 如果故障数量是个数字（说明有效）
            int count = (int)value.getValue();
            realLength += (count*VALUE_4.intValue());
            alarmData.setEnergyStorageAlarmCodes(analysisAlarmCodes(value,count));
        }
        // 驱动电机故障总数N₂(1字节)
        realLength +=1;
        value = analysisByteData(value.getDataBytes(),VALUE_1.intValue());
        alarmData.setDriveMotorAlarmCount(value.getValue());
        // 驱动电机故障代码列表
        if (value.getValue() instanceof Number){
            // 如果故障数量是个数字（说明有效）
            int count = (int)value.getValue();
            realLength += (count*VALUE_4.intValue());
            alarmData.setDriveMotorAlarmCodes(analysisAlarmCodes(value,count));
        }
        // 发动机故障总数N₃(1字节)
        realLength +=1;
        value = analysisByteData(value.getDataBytes(),VALUE_1.intValue());
        alarmData.setEngineAlarmCount(value.getValue());
        // 发动机故障代码列表
        if (value.getValue() instanceof Number){
            // 如果故障数量是个数字（说明有效）
            int count = (int)value.getValue();
            realLength += (count*VALUE_4.intValue());
            alarmData.setEngineAlarmCodes(analysisAlarmCodes(value,count));
        }
        // 其他故障总数N₄(1字节)
        realLength +=1;
        value = analysisByteData(value.getDataBytes(),VALUE_1.intValue());
        alarmData.setOtherAlarmCount(value.getValue());
        // 其他故障代码列表
        if (value.getValue() instanceof Number){
            // 如果故障数量是个数字（说明有效）
            int count = (int)value.getValue();
            realLength += (count*VALUE_4.intValue());
            alarmData.setOtherAlarmCodes(analysisAlarmCodes(value,count));
        }
        alarmData.setAlarmBytesLength(realLength);
        return alarmData;
    }


    /**
     * 分析报警代码
     * @param value 检测报警数量后的value
     * @param count 报警数量
     * @return 报警代码
     * */
    private static List<BigInteger> analysisAlarmCodes(CheckedValue value,int count) {
        List<BigInteger> codes = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            value =  analysisByteDataWithoutDesc(value.getDataBytes(),VALUE_4.intValue());
            codes.add((BigInteger) value.getValue());
        }
        return codes;
    }


    /**
     * 分析极值数据
     * @param dataBytes 极值数据报文
     * @return 极值数据
     * */
    private static MaxMinData analysistMaxMinData(byte[] dataBytes){
        MaxMinData maxMinData = new MaxMinData();

        // 最高电压电池子系统号（1字节）
        CheckedValue value = analysisByteData(dataBytes,1);
        maxMinData.setMaxVoltageBatterySubsystemId(value.getValue());
        // 最高电压电池单体代号（1字节）
        value = analysisByteData(value.getDataBytes(),1);
        maxMinData.setMaxVoltageBatteryCellId(value.getValue());
        // 电池单体电压最高值（2字节 除以1000 保留3位小数）
        value = analysisByteMultiplyData(value.getDataBytes(),2,VALUE_0_001);
        maxMinData.setMaxVoltage(value.getValue());


        // 最低电压电池子系统号（1字节）
        value = analysisByteData(value.getDataBytes(),1);
        maxMinData.setMinVoltageBatterySubsystemId(value.getValue());
        // 最低电压电池单体代号（1字节）
        value = analysisByteData(value.getDataBytes(),1);
        maxMinData.setMinVoltageBatteryCellId(value.getValue());
        // 电池单体电压最低值（2字节 除以1000 保留3位小数）
        value = analysisByteMultiplyData(value.getDataBytes(),2,VALUE_0_001);
        maxMinData.setMinVoltage(value.getValue());

        // 最高温度子系统号
        value = analysisByteData(value.getDataBytes(),1);
        maxMinData.setMaxTemperatureSubsystemId(value.getValue());
        // 最高温度探针序号
        value = analysisByteData(value.getDataBytes(),1);
        maxMinData.setMaxTemperatureProbesId(value.getValue());
        // 最高温度值
        value = analysisByteSubtractOffsetData(value.getDataBytes(),1,VALUE_40);
        maxMinData.setMaxTemperature(value.getValue());

        // 最低温度子系统号
        value = analysisByteData(value.getDataBytes(),1);
        maxMinData.setMinTemperatureSubsystemId(value.getValue());
        // 最低温度探针序号
        value = analysisByteData(value.getDataBytes(),1);
        maxMinData.setMinTemperatureProbesId(value.getValue());
        // 最低温度值
        value = analysisByteSubtractOffsetData(value.getDataBytes(),1,VALUE_40);
        maxMinData.setMinTemperature(value.getValue());

        return maxMinData;
    }


    /**
     * 分析车辆位置
     * @param dataBytes 车辆位置报文
     * @return 车辆位置
     * */
    private static PositionData analysisPositionData(byte[] dataBytes){
        PositionData positionData = new PositionData();
        // 定位状态
        positionData.setEnabled(!BaseDataTypeUtil.checkBitValue(dataBytes[0],8));
        // 北纬还是南纬(true 为需要转为北纬)
        boolean needChangeN = BaseDataTypeUtil.checkBitValue(dataBytes[0],7);
        // 东经还是西经(true 为需要转为东经)
        boolean needChangeE = BaseDataTypeUtil.checkBitValue(dataBytes[0],6);
        // 大地坐标
        LatLongCoordinate wgs84 = new LatLongCoordinate();
        // 经度
        BigDecimal longitude = BaseDataTypeUtil.bytesToBigDecimal(
                Arrays.copyOfRange(dataBytes, 1, 5)
                ,new BigDecimal("0.000001")
        );
        wgs84.setLongitude(needChangeE?longitude.negate():longitude);
        // 纬度
        BigDecimal latitude = BaseDataTypeUtil.bytesToBigDecimal(
                Arrays.copyOfRange(dataBytes, 5, 9)
                ,new BigDecimal("0.000001")
        );
        wgs84.setLatitude(needChangeN?latitude.negate():latitude);
        // 地球坐标
        positionData.setWgs84(wgs84);
        // 火星坐标
        positionData.setGcj02(CoordinateUtil.wgs84ToGcj02(wgs84));

        return positionData;
    }


    /**
     * 分析驱动电机报文
     * @param dataBytes 驱动电机部分的数据报文
     * @return 驱动电机部分的数据
     * */
    private static List<DriveMotorData> analysisDriveMotorData(byte[] dataBytes, int startBytesIndex, List<DriveMotorData> driveMotorDataList){
        DriveMotorData driveMotorData = new DriveMotorData();
        // 驱动电机序号
        CheckedValue value = analysisByteData(dataBytes,1);
        driveMotorData.setOrderNumber((int)value.getValue());
        // 驱动电机状态
        value = analysisByteDataWithoutDesc(value.getDataBytes(),1);
        int stateValue = ((BigInteger) value.getValue()).intValue();
        switch (stateValue){
            case 0x01:{
                // 耗电
                driveMotorData.setMotorState(DriveMotorState.USING_ELECTRICITY);
                break;
            }
            case 0x02:{
                // 发电
                driveMotorData.setMotorState(DriveMotorState.GENERATING_ELECTRICITY);
                break;
            }
            case 0x03:{
                // 关闭
                driveMotorData.setMotorState(DriveMotorState.OFF);
                break;
            }
            case 0x04:{
                // 耗电
                driveMotorData.setMotorState(DriveMotorState.PREPARE);
                break;
            }
            case 0xFE:{
                // 异常
                driveMotorData.setMotorState(DriveMotorState.EXCEPTION);
                break;
            }
            case 0xFF:{
                // 无效
                driveMotorData.setMotorState(DriveMotorState.INVALID);
                break;
            }
        }
        // 驱动电机控制器温度（偏移量-40）
        value = analysisByteSubtractOffsetData(value.getDataBytes(),1,VALUE_40);
        driveMotorData.setControllerTemperature(value.getValue());
        // 驱动电机转速（偏移量-20000）
        value = analysisByteSubtractOffsetData(value.getDataBytes(),2,VALUE_20000);
        driveMotorData.setSpeed(value.getValue());
        // 驱动电机转矩（除以10 偏移量-2000）
        value = analysisByteData(value.getDataBytes(),2,VALUE_0_1,VALUE_2000);
        driveMotorData.setTorque(value.getValue());

        // 驱动电机温度（偏移量-40）
        value = analysisByteSubtractOffsetData(value.getDataBytes(),1,VALUE_40);
        driveMotorData.setTemperature(value.getValue());
        // 驱动电机控制器输入电压(缩放0.1)
        value = analysisByteMultiplyData(value.getDataBytes(),2,VALUE_0_1);
        driveMotorData.setControllerInputVoltage(value.getValue());

        // 驱动电机直流母线输入电压（缩放0.1 偏移量-1000）
        value = analysisByteData(value.getDataBytes(),2,VALUE_0_1,VALUE_1000);
        driveMotorData.setControllerBusCurrentDc(value.getValue());


        driveMotorDataList.add(driveMotorData);
        int endIndex = startBytesIndex+12;
        if (endIndex<dataBytes.length){
            return analysisDriveMotorData(dataBytes,endIndex,driveMotorDataList);
        }else {
            return driveMotorDataList;
        }
    }



    /**
     * 分析整车数据报文
     * @param dataBytes 整车数据部分的数据报文
     * */
    private static AutoStatisticsData analysistAutoStatisticsData(byte[] dataBytes){
        AutoStatisticsData autoStatisticsData = new AutoStatisticsData();
        // 汽车状态
        CheckedValue value = analysisByteDataWithoutDesc(dataBytes,1);
        int autoStateValue = ((BigInteger) value.getValue()).intValue();
        switch (autoStateValue){
            case 0x01:{
                // 车辆已启动
                autoStatisticsData.setAutoState(AutoState.ON);
                break;
            }
            case 0x02:{
                // 车辆已熄火
                autoStatisticsData.setAutoState(AutoState.OFF);
                break;
            }
            case 0x03:{
                // 其它状态
                autoStatisticsData.setAutoState(AutoState.OTHER);
                break;
            }
            case 0xFE:{
                // 异常
                autoStatisticsData.setAutoState(AutoState.EXCEPTION);
                break;
            }
            case 0xFF:{
                // 无效
                autoStatisticsData.setAutoState(AutoState.INVALID);
                break;
            }
        }
        // 充电状态
        value = analysisByteDataWithoutDesc(value.getDataBytes(),1);
        int chargingStateValue =((BigInteger) value.getValue()).intValue();
        switch (chargingStateValue){
            case 0x01:{
                // 停车充电
                autoStatisticsData.setChargingState(ChargingState.STOP_CHARGING);
                break;
            }
            case 0x02:{
                // 行驶充电
                autoStatisticsData.setChargingState(ChargingState.RUNNING_CHARGING);
                break;
            }
            case 0x03:{
                // 未充电
                autoStatisticsData.setChargingState(ChargingState.UNCHARGED);
                break;
            }
            case 0x04:{
                // 充电完成
                autoStatisticsData.setChargingState(ChargingState.COMPLETED_CHARGED);
                break;
            }
            case 0xFE:{
                // 异常
                autoStatisticsData.setChargingState(ChargingState.EXCEPTION);
                break;
            }
            case 0xFF:{
                // 无效
                autoStatisticsData.setChargingState(ChargingState.INVALID);
                break;
            }
        }
        // 运行模式
        value = analysisByteDataWithoutDesc(value.getDataBytes(),1);
        int workModeValue = ((BigInteger) value.getValue()).intValue();
        switch (workModeValue){
            case 0x01:{
                // 纯电
                autoStatisticsData.setWorkMode(WorkMode.ELECTRICITY);
                break;
            }
            case 0x02:{
                // 混动
                autoStatisticsData.setWorkMode(WorkMode.OIL_ELECTRICITY);
                break;
            }
            case 0x03:{
                // 燃油
                autoStatisticsData.setWorkMode(WorkMode.OIL);
                break;
            }
            case 0xFE:{
                // 异常
                autoStatisticsData.setWorkMode(WorkMode.EXCEPTION);
                break;
            }
            case 0xFF:{
                // 无效
                autoStatisticsData.setWorkMode(WorkMode.INVALID);
                break;
            }
        }
        // 速度
        value = analysisByteMultiplyData(value.getDataBytes(),2,VALUE_0_1);
        autoStatisticsData.setSpeed(value.getValue());
        // 累计里程
        value = analysisByteMultiplyData(value.getDataBytes(),4,VALUE_0_1);
        autoStatisticsData.setMileage(value.getValue());
        // 总电压
        value = analysisByteMultiplyData(value.getDataBytes(),2,VALUE_0_1);
        autoStatisticsData.setTotalVoltage(value.getValue());
        // 总电流
        value = analysisByteData(value.getDataBytes(),2,VALUE_0_1,VALUE_1000);
        autoStatisticsData.setTotalCurrent(value.getValue());
        // soc
        value = analysisByteData(value.getDataBytes(),1);
        autoStatisticsData.setSoc(value.getValue());
        // DC/DC 状态
        value = analysisByteDataWithoutDesc(value.getDataBytes(),1);
        int dcDcValue = ((BigInteger) value.getValue()).intValue();
        switch (dcDcValue){
            case 0x01:{
                // 工作
                autoStatisticsData.setDcDcState(DcDcState.ON);
                break;
            }
            case 0x02:{
                // 断开
                autoStatisticsData.setDcDcState(DcDcState.OFF);
                break;
            }
            case 0xFE:{
                // 异常
                autoStatisticsData.setDcDcState(DcDcState.EXCEPTION);
                break;
            }
            case 0xFF:{
                // 无效
                autoStatisticsData.setDcDcState(DcDcState.INVALID);
                break;
            }
        }
        // 有无驱动力
        value = analysisByteData(value.getDataBytes(),1);
        autoStatisticsData.setDriving(BaseDataTypeUtil.checkBitValue(dataBytes[15],3));
        // 有无制动力
        autoStatisticsData.setBraking(BaseDataTypeUtil.checkBitValue(dataBytes[15],4));
        // 档位
        int gearValue = (dataBytes[15]&0xFF) & 0x0F;
        if (gearValue == 13){
            autoStatisticsData.setGear("R");
        }else if (gearValue == 14){
            autoStatisticsData.setGear("D");
        }else if (gearValue == 15){
            autoStatisticsData.setGear("P");
        }else {
            autoStatisticsData.setGear(String.format("%s", gearValue));
        }
        // 绝缘电阻
        value = analysisByteData(value.getDataBytes(),2);
        autoStatisticsData.setResistance((int)value.getValue());
        // 油门
        value = analysisByteData(value.getDataBytes(),1);
        autoStatisticsData.setAcceleratorPedalState(value.getValue());
        // 刹车
        value = analysisByteData(value.getDataBytes(),1);
        autoStatisticsData.setBrakePedalState(value.getValue());
        // 预留位
        autoStatisticsData.setReservedRawData(Arrays.copyOfRange(dataBytes, 18, 20));

        return autoStatisticsData;

    }

    /**
     * 根据时间部分的bytes格式化出时间字符串
     * @param dataUnitBytes 数据报文
     * @return 时间字符串
     * */
    private static String getTimeString(byte[] dataUnitBytes) {
        byte[] timeBytes = Arrays.copyOfRange(dataUnitBytes, 0, 6);
        int year = Calendar.getInstance().get(Calendar.YEAR);
        // 获取年份的百位和千位数字
        int hundred = (year / 100) % 10;
        int thousand = (year / 1000) % 10;
        String yearStr = String.format("%s%s%s",thousand,hundred, String.format("%02d", BaseDataTypeUtil.byteToUnsignedBigInteger(timeBytes[0])));
        String monthStr = String.format("%02d",BaseDataTypeUtil.byteToUnsignedBigInteger(timeBytes[1]));
        String dayStr = String.format("%02d",BaseDataTypeUtil.byteToUnsignedBigInteger(timeBytes[2]));
        String hourStr = String.format("%02d",BaseDataTypeUtil.byteToUnsignedBigInteger(timeBytes[3]));
        String minStr = String.format("%02d",BaseDataTypeUtil.byteToUnsignedBigInteger(timeBytes[4]));
        String secStr = String.format("%02d",BaseDataTypeUtil.byteToUnsignedBigInteger(timeBytes[5]));
        return String.format("%s-%s-%s %s:%s:%s",yearStr,monthStr,dayStr,hourStr,minStr,secStr);
    }


    /**
     * 检查数据格式
     * @param gbt32960Hex 32960报文数据Hex
     * */
    public static void checkData(String gbt32960Hex){
        checkData(BaseDataTypeUtil.hexStringToBytes(gbt32960Hex));
    }

    /**
     * 检查数据格式
     * @param gbt32960Bytes 32960报文数据
     * @return 返回crc数字
     * */
    public static int checkData(byte[] gbt32960Bytes) {
        if (gbt32960Bytes == null) {
            throw new GBT32960FormatException("数据为空");
        }
        // 一个报文至少25字节
        if (gbt32960Bytes.length < 25) {
            throw new GBT32960FormatException(String.format("数据格式异常，至少需要25字节，当前数据长度：%s 字节",gbt32960Bytes.length));
        }
        // 起始符不对
        if (gbt32960Bytes[0] != 0x23 ||gbt32960Bytes[1] != 0x23) {
            byte[] head = new byte[2];
            head[0] = gbt32960Bytes[0];
            head[1] = gbt32960Bytes[1];
            throw new GBT32960FormatException(String.format("未知的起始标志符，期望的值是 '##'，当前起始字符为：'%s'",new String(head)));
        }
        // BCC校验
        byte bccByte = gbt32960Bytes[gbt32960Bytes.length - 1];
        // bcc校验的部分是头部2字节和尾部的bcc校验码以外的所有部分,截取从第三个元素开始到倒数第二个元素为止的数组
        byte[] newArray = Arrays.copyOfRange(gbt32960Bytes, 2, gbt32960Bytes.length - 1);
        byte calcBcc = BCCUtil.calculateBcc(newArray);
        if (calcBcc!=bccByte){
            throw new GBT32960FormatException(
                    String.format("BCC校验未通过，期望的值是 '0x%s'，报文校验码为：'0x%s'"
                            , BaseDataTypeUtil.bytesToHexString(calcBcc)
                            ,BaseDataTypeUtil.bytesToHexString(bccByte)
                    )
            );
        }
        return BaseDataTypeUtil.byteToUnsignedBigInteger(bccByte).intValue();
    }

    /**
     * 检查数据是否有效
     * @param bigInteger 转为10进制无符号整数的数据
     * @param byteCount 字节长度
     * @return 结果（包含是否有效，和转化后的数据）
     * */
    private static CheckedValue checkValueEnable(BigInteger bigInteger, int byteCount){
        CheckedValue checkedValue = new CheckedValue();
        if (byteCount == 1){
            checkedValue.setEffective(bigInteger.longValue() !=254&&bigInteger.longValue() !=255);
            if (checkedValue.getEffective()){
                checkedValue.setValue(bigInteger.longValue());
            }else if (bigInteger.longValue() == 254){
                checkedValue.setValue(EXCEPTIONAL);
            }else {
                checkedValue.setValue(INVALID);
            }
        }else if (byteCount == 2){
            checkedValue.setEffective(bigInteger.longValue() !=65534&&bigInteger.longValue() !=65535);
            if (checkedValue.getEffective()){
                checkedValue.setValue(bigInteger.longValue());
            }else if (bigInteger.longValue() == 65534){
                checkedValue.setValue(EXCEPTIONAL);
            }else {
                checkedValue.setValue(INVALID);
            }
        }else if (byteCount == 4){
            checkedValue.setEffective(bigInteger.longValue() !=4294967294L&&bigInteger.longValue() !=4294967295L);
            if (checkedValue.getEffective()){
                checkedValue.setValue(bigInteger.longValue());
            }else if (bigInteger.longValue() == 4294967294L){
                checkedValue.setValue(EXCEPTIONAL);
            }else {
                checkedValue.setValue(INVALID);
            }
        }
        else{
            checkedValue.setValue(bigInteger.longValue());
            checkedValue.setEffective(false);
        }
        return checkedValue;
    }

    /**
     * 检查数据是否有效
     * @param dataBytes 数据字节
     * @return 结果（包含是否有效，和转化后的数据）
     * */
    private static CheckedValue checkValueEnable(byte[] dataBytes){
        BigInteger value = BaseDataTypeUtil.bytesToUnsignedBigInteger(dataBytes);
        return checkValueEnable(value,dataBytes.length);
    }


    /**
     * 通过字节数分析数据值
     * @param dataBytes 数据数组
     * @param byteCount 本次数据截取长度
     * @param multiplyNum [×]系数 (先执行)
     * @param subtractOffset [-]偏移量 (后执行)
     * */
    private static CheckedValue analysisByteData(byte[] dataBytes,int byteCount,BigDecimal multiplyNum,BigDecimal subtractOffset){
        // 本数据报文部分
        byte[] data = Arrays.copyOfRange(dataBytes, 0, byteCount);
        // 截取后剩余部分
        byte[] leaveData =  Arrays.copyOfRange(dataBytes, byteCount, dataBytes.length);
        // 检查数据
        CheckedValue checkedValue = checkValueEnable(data);
        checkedValue.setDataBytes(leaveData);

        if (checkedValue.getEffective()){
            BigDecimal checkedBigDecimal = new BigDecimal((long)checkedValue.getValue()).multiply(multiplyNum).subtract(subtractOffset);
            // 根据数据类型设置值
            if (multiplyNum.compareTo(new BigDecimal(1))<0){
                checkedValue.setValue(checkedBigDecimal.floatValue());
            }else {
                checkedValue.setValue(checkedBigDecimal.intValue());
            }
        }
        return checkedValue;
    }


    /**
     * 通过字节数分析需要乘系数的数据值
     * @param dataBytes 数据数组
     * @param byteCount 本次数据截取长度
     * @param multiplyNum [×]系数
     * */
    private static CheckedValue analysisByteMultiplyData(byte[] dataBytes,int byteCount,BigDecimal multiplyNum){
        return analysisByteData(dataBytes,byteCount,multiplyNum,VALUE_0);
    }

    /**
     * 通过字节数分析需要减偏移量的数据值
     * @param dataBytes 数据数组
     * @param byteCount 本次数据截取长度
     * @param subtractOffset  [-]偏移量
     * */
    private static CheckedValue analysisByteSubtractOffsetData(byte[] dataBytes,int byteCount,BigDecimal subtractOffset){
        return analysisByteData(dataBytes,byteCount,VALUE_1,subtractOffset);
    }


    /**
     * 通过字节数分析整型数据值
     * @param dataBytes 数据数组
     * @param byteCount 本次数据截取长度
     * */
    private static CheckedValue analysisByteData(byte[] dataBytes,int byteCount){
        return analysisByteData(dataBytes,byteCount,VALUE_1,VALUE_0);
    }

    /**
     * 通过字节数分析整型数据值，只做基本数值转换，不分析是否有效
     * @param dataBytes 数据数组
     * @param byteCount 本次数据截取长度
     * @return 转换后的整型数据
     * */
    private static CheckedValue analysisByteDataWithoutDesc(byte[] dataBytes,int byteCount){
        // 本数据报文部分
        byte[] data = Arrays.copyOfRange(dataBytes, 0, byteCount);
        // 截取后剩余部分
        byte[] leaveData =  Arrays.copyOfRange(dataBytes, byteCount, dataBytes.length);
        // 检查数据
        CheckedValue checkedValue = checkValueEnable(data);
        checkedValue.setDataBytes(leaveData);
        checkedValue.setValue(BaseDataTypeUtil.bytesToUnsignedBigInteger(data));
        return checkedValue;
    }


}
