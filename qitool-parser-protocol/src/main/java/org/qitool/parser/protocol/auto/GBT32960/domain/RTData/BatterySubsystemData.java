package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import java.util.List;

/**
 * 可充电储能装置数据
 *
 * @author zoudingyun
 * 2024/5/23 17:11
 */
public class BatterySubsystemData {


    /**
     * 电压数据报文比特流长度
     * */
    public int subsystemCountForVoltageBytesLength;

    /**
     * 涉及到电压数据的子系统数
     * <br>
     * --------------------------------------------------------
     * <br>
     * N个可充电储能子系统，有效值范围：1～250,“0xFE”表示异常，“OxFF”表示无效
     * */
    private Object subsystemCountForVoltage;

    /**
     * 可充电储能子系统 电压信息列表
     * <br>
     * --------------------------------------------------------
     * <br>
     * 按可充电储能子系统序号依次排列
     * */
    private List<BatterySubsystemVoltageData> voltageData;


    public Object getSubsystemCountForVoltage() {
        return subsystemCountForVoltage;
    }

    public void setSubsystemCountForVoltage(Object subsystemCountForVoltage) {
        this.subsystemCountForVoltage = subsystemCountForVoltage;
    }

    public List<BatterySubsystemVoltageData> getVoltageData() {
        return voltageData;
    }

    public void setVoltageData(List<BatterySubsystemVoltageData> voltageData) {
        this.voltageData = voltageData;
    }

    public int getSubsystemCountForVoltageBytesLength() {
        return subsystemCountForVoltageBytesLength;
    }

    public void setSubsystemCountForVoltageBytesLength(int subsystemCountForVoltageBytesLength) {
        this.subsystemCountForVoltageBytesLength = subsystemCountForVoltageBytesLength;
    }
}
