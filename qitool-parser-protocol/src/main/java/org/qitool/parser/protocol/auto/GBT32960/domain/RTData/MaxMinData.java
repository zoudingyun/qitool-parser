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
    private int maxVoltageBatterySubsystemId;


    /**
     * 最高电压电池单体代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int maxVoltageBatteryCellId;


    /**
     * 电池单体电压最高值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～15000(表示0V～15 V) <br>
     * 最小计量单元：0.001 V <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float maxVoltage;


    /**
     * 最低电压电池子系统号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int minVoltageBatterySubsystemId;


    /**
     * 最低电压电池单体代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int minVoltageBatteryCellId;


    /**
     * 电池单体电压最低值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～15000(表示0V～15 V) <br>
     * 最小计量单元：0.001 V <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float minVoltage;


    /**
     * 最高温度电池子系统号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int maxTemperatureSubsystemId;


    /**
     * 最高温度电池单体代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int maxTemperatureProbesId;


    /**
     * 温度最高值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～250(数值偏移量40 ℃,表示 —40 ℃ ~ +210 ℃) <br>
     * 最小计量单元：1℃ <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float maxTemperature;


    /**
     * 最低温度电池子系统号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int minTemperatureSubsystemId;


    /**
     * 最低温度电池单体代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～250 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int minTemperatureProbesId;


    /**
     * 温度最低值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～250(数值偏移量40 ℃,表示 —40 ℃ ~ +210 ℃) <br>
     * 最小计量单元：1℃ <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float minTemperature;


    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getMinTemperatureProbesId() {
        return minTemperatureProbesId;
    }

    public void setMinTemperatureProbesId(int minTemperatureProbesId) {
        this.minTemperatureProbesId = minTemperatureProbesId;
    }

    public int getMinTemperatureSubsystemId() {
        return minTemperatureSubsystemId;
    }

    public void setMinTemperatureSubsystemId(int minTemperatureSubsystemId) {
        this.minTemperatureSubsystemId = minTemperatureSubsystemId;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public int getMaxTemperatureProbesId() {
        return maxTemperatureProbesId;
    }

    public void setMaxTemperatureProbesId(int maxTemperatureProbesId) {
        this.maxTemperatureProbesId = maxTemperatureProbesId;
    }

    public int getMaxTemperatureSubsystemId() {
        return maxTemperatureSubsystemId;
    }

    public void setMaxTemperatureSubsystemId(int maxTemperatureSubsystemId) {
        this.maxTemperatureSubsystemId = maxTemperatureSubsystemId;
    }

    public float getMinVoltage() {
        return minVoltage;
    }

    public void setMinVoltage(float minVoltage) {
        this.minVoltage = minVoltage;
    }

    public int getMinVoltageBatteryCellId() {
        return minVoltageBatteryCellId;
    }

    public void setMinVoltageBatteryCellId(int minVoltageBatteryCellId) {
        this.minVoltageBatteryCellId = minVoltageBatteryCellId;
    }

    public int getMinVoltageBatterySubsystemId() {
        return minVoltageBatterySubsystemId;
    }

    public void setMinVoltageBatterySubsystemId(int minVoltageBatterySubsystemId) {
        this.minVoltageBatterySubsystemId = minVoltageBatterySubsystemId;
    }

    public float getMaxVoltage() {
        return maxVoltage;
    }

    public void setMaxVoltage(float maxVoltage) {
        this.maxVoltage = maxVoltage;
    }

    public int getMaxVoltageBatteryCellId() {
        return maxVoltageBatteryCellId;
    }

    public void setMaxVoltageBatteryCellId(int maxVoltageBatteryCellId) {
        this.maxVoltageBatteryCellId = maxVoltageBatteryCellId;
    }

    public int getMaxVoltageBatterySubsystemId() {
        return maxVoltageBatterySubsystemId;
    }

    public void setMaxVoltageBatterySubsystemId(int maxVoltageBatterySubsystemId) {
        this.maxVoltageBatterySubsystemId = maxVoltageBatterySubsystemId;
    }
}
