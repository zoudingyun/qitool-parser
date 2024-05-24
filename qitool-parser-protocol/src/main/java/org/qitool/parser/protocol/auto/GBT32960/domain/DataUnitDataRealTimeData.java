package org.qitool.parser.protocol.auto.GBT32960.domain;

import org.qitool.parser.protocol.auto.GBT32960.domain.RTData.*;

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

    /**
     * 车辆位置
     * */
    private PositionData position;

    /**
     * 极值数据
     * */
    private MaxMinData maxMinData;

    /**
     * 报警数据
     * */
    private AlarmData alarmData;

    /**
     * 可充电储能装置数据
     * */
    private BatterySubsystemData batterySubsystemData;


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

    public PositionData getPosition() {
        return position;
    }

    public void setPosition(PositionData position) {
        this.position = position;
    }

    public MaxMinData getMaxMinData() {
        return maxMinData;
    }

    public void setMaxMinData(MaxMinData maxMinData) {
        this.maxMinData = maxMinData;
    }

    public AlarmData getAlarmData() {
        return alarmData;
    }

    public void setAlarmData(AlarmData alarmData) {
        this.alarmData = alarmData;
    }

    public BatterySubsystemData getBatterySubsystemData() {
        return batterySubsystemData;
    }

    public void setBatterySubsystemData(BatterySubsystemData batterySubsystemData) {
        this.batterySubsystemData = batterySubsystemData;
    }
}
