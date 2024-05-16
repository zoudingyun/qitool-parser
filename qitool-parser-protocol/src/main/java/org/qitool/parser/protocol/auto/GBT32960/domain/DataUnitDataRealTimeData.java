package org.qitool.parser.protocol.auto.GBT32960.domain;

import org.qitool.parser.protocol.auto.GBT32960.domain.RTData.AutoStatisticsData;
import org.qitool.parser.protocol.auto.GBT32960.domain.RTData.DriveMotorData;

import java.util.List;

/**
 * 数据单元-实时信息上报
 *
 * @author zoudingyun
 * 2024/5/14 15:18
 */
public class DataUnitDataRealTimeData extends DataUnitData {

    /**
     * 整车数据部分
     * */
    private AutoStatisticsData autoStatisticsData;

    /**
     * 驱动电机数量
     * */
    private int driveMotorCount;

    /**
     * 驱动电机数据
     * */
    private List<DriveMotorData> motorData;


    public AutoStatisticsData getAutoStatisticsData() {
        return autoStatisticsData;
    }

    public void setAutoStatisticsData(AutoStatisticsData autoStatisticsData) {
        this.autoStatisticsData = autoStatisticsData;
    }

    public int getDriveMotorCount() {
        return driveMotorCount;
    }

    public void setDriveMotorCount(int driveMotorCount) {
        this.driveMotorCount = driveMotorCount;
    }

    public List<DriveMotorData> getMotorData() {
        return motorData;
    }

    public void setMotorData(List<DriveMotorData> motorData) {
        this.motorData = motorData;
    }
}
