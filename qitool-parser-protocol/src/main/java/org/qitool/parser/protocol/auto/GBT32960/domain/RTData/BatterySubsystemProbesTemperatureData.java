package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

/**
 * 可充电储能装置子系统探针温度数据
 *
 * @author zoudingyun
 * 2024/5/23 17:18
 */
public class BatterySubsystemProbesTemperatureData {

    /**
     * 探针序号
     * <br>
     * --------------------------------------------------------
     * <br>
     * */
    private int probesId;


    /**
     * 温度探针检测到的温度值
     * <br>
     * --------------------------------------------------------
     * <br>
     * 有效值范围：0～250(数值偏移量40 ℃,表示-40 ℃ ~ +210 ℃)
     * 最小计量单元：1 ℃
     * “0xFE”表示异常，“0xFF”表示无效
     * */
    private Object temperature;

    public int getProbesId() {
        return probesId;
    }

    public void setProbesId(int probesId) {
        this.probesId = probesId;
    }

    public Object getTemperature() {
        return temperature;
    }

    public void setTemperature(Object temperature) {
        this.temperature = temperature;
    }
}
