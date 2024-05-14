package org.qitool.parser.protocol.auto.GBT32960.domain;

import org.qitool.parser.protocol.auto.GBT32960.domain.RTData.AutoStatisticsData;

/**
 * 数据单元-实时信息上报
 *
 * @author zoudingyun
 * 2024/5/14 15:18
 */
public class DataUnitRealTimeData extends DataUnit {

    /**
     * 整车数据部分
     * */
    private AutoStatisticsData autoStatisticsData;


    public AutoStatisticsData getAutoStatisticsData() {
        return autoStatisticsData;
    }

    public void setAutoStatisticsData(AutoStatisticsData autoStatisticsData) {
        this.autoStatisticsData = autoStatisticsData;
    }
}
