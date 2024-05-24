package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import java.util.List;

/**
 * 可充电储能装置子系统温度数据
 *
 * @author zoudingyun
 * 2024/5/23 17:18
 */
public class BatterySubsystemTemperatureData {

    /**
     * 可充电储能子系统号
     * <br>
     * --------------------------------------------------------
     * <br>
     * */
    private int subsystemId;


    /**
     * 可充电储能温度探针个数
     * <br>
     * --------------------------------------------------------
     * <br>
     * N个温度探针，有效值范围：1～65531
     * “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效
     * */
    private Object probesCount;


    /**
     * 可充电储能子系统各温度探针检测到的温度值
     * */
    private List<BatterySubsystemProbesTemperatureData> probesTemperatureData;


    public int getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(int subsystemId) {
        this.subsystemId = subsystemId;
    }

    public Object getProbesCount() {
        return probesCount;
    }

    public void setProbesCount(Object probesCount) {
        this.probesCount = probesCount;
    }

    public List<BatterySubsystemProbesTemperatureData> getProbesTemperatureData() {
        return probesTemperatureData;
    }

    public void setProbesTemperatureData(List<BatterySubsystemProbesTemperatureData> probesTemperatureData) {
        this.probesTemperatureData = probesTemperatureData;
    }
}
