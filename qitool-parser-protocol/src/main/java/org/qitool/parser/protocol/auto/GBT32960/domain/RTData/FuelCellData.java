package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import org.qitool.parser.protocol.auto.GBT32960.enums.DcDcState;

/**
 * 燃料电池数据
 *
 * @author zoudingyun
 * 2024/5/16 9:38
 */
public class FuelCellData {

    /**
     * 燃料电池电压
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～20000(表示0V～2000 V)<br>
     * 最小计量单元： 0.1 V <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float voltage;


    /**
     * 燃料电池电流
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～20000(表示 0A～2000 A)<br>
     * 最小计量单元： 0.1 A <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float current;


    /**
     * 燃料消耗率
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～60000(表示0 kg/100 km ~ 600 kg/100 km)<br>
     * 最小计量单元： 0.01 kg/100 km <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float fuelConsumptionRate;


    /**
     * 燃料电池温度探针总数
     * <br>
     * --------------------------------------------------------------
     * <br>
     * N个燃料电池温度探针 <br>
     * 有效值范围：0～65531 <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private int temperatureProbesCount;


    /**
     * 探针温度值
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～240(数值偏移量40 ℃,表示-40 ℃~ +200 ℃) <br>
     * 最小计量单元：1 ℃ <br>
     * 长度：燃料电池温度探针总数
     * */
    private int[] temperatures;


    /**
     * 氢系统中最高温度
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～2400(偏移量40 ℃,表示 -40℃ ~ 200℃) <br>
     * 最小计量单元：0.1 ℃ <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float maxTemperature;


    /**
     * 氢系统中最高温度探针代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～252 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int maxTemperatureProbesId;


    /**
     * 氢系统中最高浓度
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～60000(表示0mg/kg～50000 mg/kg) <br>
     * 最小计量单元：1 mg/kg <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float maxConcentration;


    /**
     * 氢系统中最高浓度传感器代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～252 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int maxConcentrationProbesId;


    /**
     * 氢系统中最高压力
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～1000(表示0MPa～100 MPa) <br>
     * 最小计量单元：0.1 MPa <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float maxPressure;


    /**
     * 氢系统中最高压力传感器代号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：1～252 <br>
     * “0xFE”(254)表示异常，“0xFF”(255)表示无效
     * */
    private int maxPressureProbesId;


    /**
     * 高压DC/DC状态
     * */
    private DcDcState highVoltageDcDcState;


    public float getVoltage() {
        return voltage;
    }

    public void setVoltage(float voltage) {
        this.voltage = voltage;
    }

    public float getCurrent() {
        return current;
    }

    public void setCurrent(float current) {
        this.current = current;
    }

    public float getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public void setFuelConsumptionRate(float fuelConsumptionRate) {
        this.fuelConsumptionRate = fuelConsumptionRate;
    }

    public int getTemperatureProbesCount() {
        return temperatureProbesCount;
    }

    public void setTemperatureProbesCount(int temperatureProbesCount) {
        this.temperatureProbesCount = temperatureProbesCount;
    }

    public int[] getTemperatures() {
        return temperatures;
    }

    public void setTemperatures(int[] temperatures) {
        this.temperatures = temperatures;
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

    public float getMaxConcentration() {
        return maxConcentration;
    }

    public void setMaxConcentration(float maxConcentration) {
        this.maxConcentration = maxConcentration;
    }

    public int getMaxConcentrationProbesId() {
        return maxConcentrationProbesId;
    }

    public void setMaxConcentrationProbesId(int maxConcentrationProbesId) {
        this.maxConcentrationProbesId = maxConcentrationProbesId;
    }

    public float getMaxPressure() {
        return maxPressure;
    }

    public void setMaxPressure(float maxPressure) {
        this.maxPressure = maxPressure;
    }

    public int getMaxPressureProbesId() {
        return maxPressureProbesId;
    }

    public void setMaxPressureProbesId(int maxPressureProbesId) {
        this.maxPressureProbesId = maxPressureProbesId;
    }

    public DcDcState getHighVoltageDcDcState() {
        return highVoltageDcDcState;
    }

    public void setHighVoltageDcDcState(DcDcState highVoltageDcDcState) {
        this.highVoltageDcDcState = highVoltageDcDcState;
    }
}
