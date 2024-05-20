package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

/**
 * 极值数据
 *
 * @author zoudingyun
 * 2024/5/16 9:38
 */
public class MaxMinData {

    /**
     * 最高电压电池子系统号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private Object maxVoltageBatterySubsystemId;


    /**
     * 最高电压电池单体代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private Object maxVoltageBatteryCellId;


    /**
     * 电池单体电压最高值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～15000(表示0V～15 V) <br>
     * 最小计量单元：0.001 V <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private Object maxVoltage;


    /**
     * 最低电压电池子系统号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private Object minVoltageBatterySubsystemId;


    /**
     * 最低电压电池单体代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private Object minVoltageBatteryCellId;


    /**
     * 电池单体电压最低值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～15000(表示0V～15 V) <br>
     * 最小计量单元：0.001 V <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private Object minVoltage;


    /**
     * 最高温度电池子系统号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private Object maxTemperatureSubsystemId;


    /**
     * 最高温度电池单体代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private Object maxTemperatureProbesId;


    /**
     * 温度最高值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～250(数值偏移量40 ℃,表示 —40 ℃ ~ +210 ℃) <br>
     * 最小计量单元：1℃ <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private Object maxTemperature;


    /**
     * 最低温度电池子系统号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private Object minTemperatureSubsystemId;


    /**
     * 最低温度电池单体代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private Object minTemperatureProbesId;


    /**
     * 温度最低值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～250(数值偏移量40 ℃,表示 —40 ℃ ~ +210 ℃) <br>
     * 最小计量单元：1℃ <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private Object minTemperature;


    public Object getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(Object minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Object getMinTemperatureProbesId() {
        return minTemperatureProbesId;
    }

    public void setMinTemperatureProbesId(Object minTemperatureProbesId) {
        this.minTemperatureProbesId = minTemperatureProbesId;
    }

    public Object getMaxVoltageBatterySubsystemId() {
        return maxVoltageBatterySubsystemId;
    }

    public void setMaxVoltageBatterySubsystemId(Object maxVoltageBatterySubsystemId) {
        this.maxVoltageBatterySubsystemId = maxVoltageBatterySubsystemId;
    }

    public Object getMaxVoltageBatteryCellId() {
        return maxVoltageBatteryCellId;
    }

    public void setMaxVoltageBatteryCellId(Object maxVoltageBatteryCellId) {
        this.maxVoltageBatteryCellId = maxVoltageBatteryCellId;
    }

    public Object getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(Object maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public Object getMinVoltageBatterySubsystemId() {
        return minVoltageBatterySubsystemId;
    }

    public void setMinVoltageBatterySubsystemId(Object minVoltageBatterySubsystemId) {
        this.minVoltageBatterySubsystemId = minVoltageBatterySubsystemId;
    }

    public Object getMinVoltageBatteryCellId() {
        return minVoltageBatteryCellId;
    }

    public void setMinVoltageBatteryCellId(Object minVoltageBatteryCellId) {
        this.minVoltageBatteryCellId = minVoltageBatteryCellId;
    }

    public Object getMinVoltage() {
        return minVoltage;
    }

    public void setMinVoltage(Object minVoltage) {
        this.minVoltage = minVoltage;
    }

    public Object getMaxTemperatureSubsystemId() {
        return maxTemperatureSubsystemId;
    }

    public void setMaxTemperatureSubsystemId(Object maxTemperatureSubsystemId) {
        this.maxTemperatureSubsystemId = maxTemperatureSubsystemId;
    }

    public Object getMaxTemperatureProbesId() {
        return maxTemperatureProbesId;
    }

    public void setMaxTemperatureProbesId(Object maxTemperatureProbesId) {
        this.maxTemperatureProbesId = maxTemperatureProbesId;
    }

    public Object getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(Object maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public Object getMinTemperatureSubsystemId() {
        return minTemperatureSubsystemId;
    }

    public void setMinTemperatureSubsystemId(Object minTemperatureSubsystemId) {
        this.minTemperatureSubsystemId = minTemperatureSubsystemId;
    }
}
