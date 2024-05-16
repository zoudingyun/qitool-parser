package org.qitool.parser.protocol.auto.GBT32960.domain;

/**
 * 数据单元
 *
 * @author zoudingyun
 * 2024/5/14 15:18
 */
public class DataUnit {

    /**
     * 实时信息上报数据
     * */
    DataUnitDataRealTimeData realTimeData;

    public DataUnitDataRealTimeData getRealTimeData() {
        return realTimeData;
    }

    public void setRealTimeData(DataUnitDataRealTimeData realTimeData) {
        this.realTimeData = realTimeData;
    }
}
