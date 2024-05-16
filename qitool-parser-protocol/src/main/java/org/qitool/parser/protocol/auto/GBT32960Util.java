package org.qitool.parser.protocol.auto;

import org.qitool.parser.general.BCCUtil;
import org.qitool.parser.general.BaseDataTypeUtil;
import org.qitool.parser.protocol.auto.GBT32960.domain.AutoData;
import org.qitool.parser.protocol.auto.GBT32960.domain.CommandUnit;
import org.qitool.parser.protocol.auto.GBT32960.domain.DataUnit;
import org.qitool.parser.protocol.auto.GBT32960.domain.DataUnitDataRealTimeData;
import org.qitool.parser.protocol.auto.GBT32960.domain.RTData.AutoStatisticsData;
import org.qitool.parser.protocol.auto.GBT32960.domain.RTData.DriveMotorData;
import org.qitool.parser.protocol.auto.GBT32960.enums.*;
import org.qitool.parser.protocol.auto.GBT32960.exceptions.*;

import java.math.BigDecimal;
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
        checkData(gbt32960Bytes);
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

                        // ============================================整车数据===================================//
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
                dataUnit.setMotorData(analysistDriveMotorData(thisDataBytes,0,new ArrayList<>(driverCount)));
            }
            case 0x03:
            case 0x04:
            case 0x05:
            case 0x06:
            case 0x07:
            default:{
                // 整车数据
                return dataUnit;
            }
        }
    }

    /**
     * 分析驱动电机报文
     * @param dataBytes 整车数据部分的数据报文
     * */
    private static List<DriveMotorData> analysistDriveMotorData(byte[] dataBytes, int startBytesIndex, List<DriveMotorData> driveMotorDataList){
        DriveMotorData driveMotorData = new DriveMotorData();
        // 驱动电机序号
        driveMotorData.setOrderNumber(BaseDataTypeUtil.byteToUnsignedBigInteger(dataBytes[startBytesIndex]).intValue());
        // 驱动电机状态
        int stateValue = BaseDataTypeUtil.byteToUnsignedBigInteger(dataBytes[startBytesIndex+1]).intValue();
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
        driveMotorData.setControllerTemperature(BaseDataTypeUtil.byteToUnsignedBigInteger(dataBytes[startBytesIndex+2]).intValue()-40);
        // 驱动电机转速（偏移量-20000）
        driveMotorData.setSpeed(BaseDataTypeUtil.bytesToUnsignedBigInteger(
                Arrays.copyOfRange(dataBytes, startBytesIndex+3, startBytesIndex+5)
        ).intValue()-20000);
        // 驱动电机转矩（偏移量-2000）
        driveMotorData.setTorque(BaseDataTypeUtil.bytesToUnsignedBigDecimal(
                Arrays.copyOfRange(dataBytes, startBytesIndex+5, startBytesIndex+7)
                ,new BigDecimal("0.1")
        ).subtract(new BigDecimal(2000)).floatValue());
        // 驱动电机温度（偏移量-40）
        driveMotorData.setTemperature(BaseDataTypeUtil.byteToUnsignedBigInteger(dataBytes[startBytesIndex+7]).intValue()-40);
        // 驱动电机控制器输入电压
        driveMotorData.setControllerInputVoltage(BaseDataTypeUtil.bytesToUnsignedBigDecimal(
                Arrays.copyOfRange(dataBytes, startBytesIndex+8, startBytesIndex+10)
                ,new BigDecimal("0.1")
        ).floatValue());
        // 驱动电机直流母线输入电压（偏移量-1000）
        driveMotorData.setControllerBusCurrentDc(BaseDataTypeUtil.bytesToUnsignedBigDecimal(
                Arrays.copyOfRange(dataBytes, startBytesIndex+10, startBytesIndex+12)
                ,new BigDecimal("0.1")
        ).subtract(new BigDecimal(1000)).floatValue());

        driveMotorDataList.add(driveMotorData);
        int endIndex = startBytesIndex+12;
        if (endIndex<dataBytes.length){
            return analysistDriveMotorData(dataBytes,endIndex,driveMotorDataList);
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
        int autoStateValue = dataBytes[0] & 0xFF;
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
        int chargingStateValue = dataBytes[1] & 0xFF;
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
        int workModeValue = dataBytes[2] & 0xFF;
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
        autoStatisticsData.setSpeed(
                BaseDataTypeUtil.bytesToUnsignedBigDecimal(
                        Arrays.copyOfRange(dataBytes, 3, 5)
                        ,new BigDecimal("0.1")).floatValue()
        );
        // 累计里程
        autoStatisticsData.setMileage(
                BaseDataTypeUtil.bytesToUnsignedBigDecimal(
                        Arrays.copyOfRange(dataBytes, 5, 9)
                        ,new BigDecimal("0.1")).doubleValue()
        );
        // 总电压
        autoStatisticsData.setTotalVoltage(
                BaseDataTypeUtil.bytesToUnsignedBigDecimal(
                        Arrays.copyOfRange(dataBytes, 9, 11)
                        ,new BigDecimal("0.1")).floatValue()
        );
        // 总电流
        autoStatisticsData.setTotalCurrent(
                BaseDataTypeUtil.bytesToUnsignedBigDecimal(
                        Arrays.copyOfRange(dataBytes, 11, 13)
                        ,new BigDecimal("0.1")).subtract(new BigDecimal(1000)).floatValue()
        );
        // soc
        autoStatisticsData.setSoc(
                BaseDataTypeUtil.bytesToBigInteger(Arrays.copyOfRange(dataBytes, 13, 14)).intValue()
        );
        // DC/DC 状态
        int dcDcValue = dataBytes[14] & 0xFF;
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
        autoStatisticsData.setResistance(BaseDataTypeUtil.bytesToUnsignedBigInteger(Arrays.copyOfRange(dataBytes, 16, 18)).intValue());
        // 预留位
        autoStatisticsData.setReservedRawData(Arrays.copyOfRange(dataBytes, 18, 20));
        // 油门
        autoStatisticsData.setAcceleratorPedalState(BaseDataTypeUtil.byteToUnsignedBigInteger(dataBytes[18]).intValue());
        // 刹车
        autoStatisticsData.setBrakePedalState(BaseDataTypeUtil.byteToUnsignedBigInteger(dataBytes[19]).intValue());

        return autoStatisticsData;

    }

    /**
     * 根据时间部分的bytes格式化出时间字符串
     * @param dataUnitBytes 数据报文
     * @return 时间字符串
     * */
    private static String getTimeString(byte[] dataUnitBytes) {
        byte[] timeBytes = Arrays.copyOfRange(dataUnitBytes, 0, 6);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
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
     * */
    public static void checkData(byte[] gbt32960Bytes) {
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
    }

}
