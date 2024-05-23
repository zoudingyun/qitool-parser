package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import org.qitool.parser.general.BaseDataTypeUtil;

/**
 * 通用报警标志位
 *
 * @author zoudingyun
 * 2024/5/23 14:32
 */
public class AlarmCommonFlag {

    /**
     * 温度差异报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
   private Boolean temperatureDifferenceAlarm;

    /**
     * 电池高温报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean batteryTemperatureAlarm;


    /**
     * 车载储能装置类型过压报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean overVoltageAlarm;


    /**
     * 车载储能装置类型欠压报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean underVoltageAlarm;


    /**
     * SOC低报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean socLowAlarm;


    /**
     * SOC过高报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean socHighAlarm;

    /**
     * SOC跳变报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean socBreakAlarm;

    /**
     * 单体电池过压报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean cellOverVoltageAlarm;


    /**
     * 单体电池欠压报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean cellUnderVoltageAlarm;


    /**
     * 可充电储能系统不匹配报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean batteryMismatchAlarm;


    /**
     * 电池单体一致性差报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean cellConsistencyAlarm;


    /**
     * 绝缘报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean insulationAlarm;


    /**
     * DC-DC温度报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean dcDcTemperatureAlarm;


    /**
     * 制动系统报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean brakingSystemAlarm;


    /**
     * DC-DC状态报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean dcDcStatusAlarm;


    /**
     * 驱动电机控制器温度报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean driveMotorControllerTemperatureAlarm;


    /**
     * 高压互锁状态报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean highVoltageInterlockStatusAlarm;


    /**
     * 驱动电机温度报警
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean driveMotorTemperatureAlarm;


    /**
     * 车载储能装置类型过充
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有报警
     * false：无报警
     * */
    private Boolean overChargingAlarm;



    /**
     * 解析每个标志位的状态并初始化
     * @param flag 标志字节
     * */
    public AlarmCommonFlag(byte[] flag){
        // 为空，默认全都不报警
        if (flag == null||flag.length != 4){
            this.temperatureDifferenceAlarm = false;
            this.batteryTemperatureAlarm = false;
            this.overVoltageAlarm = false;
            this.underVoltageAlarm = false;
            this.socLowAlarm = false;
            this.cellOverVoltageAlarm = false;
            this.cellUnderVoltageAlarm = false;
            this.socHighAlarm = false;
            this.socBreakAlarm = false;
            this.batteryMismatchAlarm = false;
            this.cellConsistencyAlarm = false;
            this.insulationAlarm = false;
            this.dcDcTemperatureAlarm = false;
            this.brakingSystemAlarm = false;
            this.dcDcStatusAlarm = false;
            this.driveMotorControllerTemperatureAlarm = false;
            this.highVoltageInterlockStatusAlarm = false;
            this.driveMotorTemperatureAlarm = false;
            this.overChargingAlarm = false;
        }else{
            this.temperatureDifferenceAlarm = BaseDataTypeUtil.checkBitValue(flag[3],8);
            this.batteryTemperatureAlarm = BaseDataTypeUtil.checkBitValue(flag[3],7);
            this.overVoltageAlarm = BaseDataTypeUtil.checkBitValue(flag[3],6);
            this.underVoltageAlarm = BaseDataTypeUtil.checkBitValue(flag[3],5);
            this.socLowAlarm = BaseDataTypeUtil.checkBitValue(flag[3],4);
            this.cellOverVoltageAlarm = BaseDataTypeUtil.checkBitValue(flag[3],3);
            this.cellUnderVoltageAlarm = BaseDataTypeUtil.checkBitValue(flag[3],2);
            this.socHighAlarm = BaseDataTypeUtil.checkBitValue(flag[3],1);

            this.socBreakAlarm = BaseDataTypeUtil.checkBitValue(flag[2],8);
            this.batteryMismatchAlarm = BaseDataTypeUtil.checkBitValue(flag[2],7);
            this.cellConsistencyAlarm = BaseDataTypeUtil.checkBitValue(flag[2],6);
            this.insulationAlarm = BaseDataTypeUtil.checkBitValue(flag[2],5);
            this.dcDcTemperatureAlarm = BaseDataTypeUtil.checkBitValue(flag[2],4);
            this.brakingSystemAlarm = BaseDataTypeUtil.checkBitValue(flag[2],3);
            this.dcDcStatusAlarm = BaseDataTypeUtil.checkBitValue(flag[2],2);
            this.driveMotorControllerTemperatureAlarm = BaseDataTypeUtil.checkBitValue(flag[2],1);

            this.highVoltageInterlockStatusAlarm = BaseDataTypeUtil.checkBitValue(flag[1],8);
            this.driveMotorTemperatureAlarm = BaseDataTypeUtil.checkBitValue(flag[1],7);
            this.overChargingAlarm = BaseDataTypeUtil.checkBitValue(flag[1],6);
        }

    }



    public Boolean getTemperatureDifferenceAlarm() {
        return temperatureDifferenceAlarm;
    }

    public void setTemperatureDifferenceAlarm(Boolean temperatureDifferenceAlarm) {
        this.temperatureDifferenceAlarm = temperatureDifferenceAlarm;
    }

    public Boolean getBatteryTemperatureAlarm() {
        return batteryTemperatureAlarm;
    }

    public void setBatteryTemperatureAlarm(Boolean batteryTemperatureAlarm) {
        this.batteryTemperatureAlarm = batteryTemperatureAlarm;
    }

    public Boolean getOverVoltageAlarm() {
        return overVoltageAlarm;
    }

    public void setOverVoltageAlarm(Boolean overVoltageAlarm) {
        this.overVoltageAlarm = overVoltageAlarm;
    }

    public Boolean getUnderVoltageAlarm() {
        return underVoltageAlarm;
    }

    public void setUnderVoltageAlarm(Boolean underVoltageAlarm) {
        this.underVoltageAlarm = underVoltageAlarm;
    }

    public Boolean getSocLowAlarm() {
        return socLowAlarm;
    }

    public void setSocLowAlarm(Boolean socLowAlarm) {
        this.socLowAlarm = socLowAlarm;
    }

    public Boolean getSocHighAlarm() {
        return socHighAlarm;
    }

    public void setSocHighAlarm(Boolean socHighAlarm) {
        this.socHighAlarm = socHighAlarm;
    }

    public Boolean getSocBreakAlarm() {
        return socBreakAlarm;
    }

    public void setSocBreakAlarm(Boolean socBreakAlarm) {
        this.socBreakAlarm = socBreakAlarm;
    }

    public Boolean getCellOverVoltageAlarm() {
        return cellOverVoltageAlarm;
    }

    public void setCellOverVoltageAlarm(Boolean cellOverVoltageAlarm) {
        this.cellOverVoltageAlarm = cellOverVoltageAlarm;
    }

    public Boolean getCellUnderVoltageAlarm() {
        return cellUnderVoltageAlarm;
    }

    public void setCellUnderVoltageAlarm(Boolean cellUnderVoltageAlarm) {
        this.cellUnderVoltageAlarm = cellUnderVoltageAlarm;
    }

    public Boolean getBatteryMismatchAlarm() {
        return batteryMismatchAlarm;
    }

    public void setBatteryMismatchAlarm(Boolean batteryMismatchAlarm) {
        this.batteryMismatchAlarm = batteryMismatchAlarm;
    }

    public Boolean getCellConsistencyAlarm() {
        return cellConsistencyAlarm;
    }

    public void setCellConsistencyAlarm(Boolean cellConsistencyAlarm) {
        this.cellConsistencyAlarm = cellConsistencyAlarm;
    }

    public Boolean getInsulationAlarm() {
        return insulationAlarm;
    }

    public void setInsulationAlarm(Boolean insulationAlarm) {
        this.insulationAlarm = insulationAlarm;
    }

    public Boolean getDcDcTemperatureAlarm() {
        return dcDcTemperatureAlarm;
    }

    public void setDcDcTemperatureAlarm(Boolean dcDcTemperatureAlarm) {
        this.dcDcTemperatureAlarm = dcDcTemperatureAlarm;
    }

    public Boolean getBrakingSystemAlarm() {
        return brakingSystemAlarm;
    }

    public void setBrakingSystemAlarm(Boolean brakingSystemAlarm) {
        this.brakingSystemAlarm = brakingSystemAlarm;
    }

    public Boolean getDcDcStatusAlarm() {
        return dcDcStatusAlarm;
    }

    public void setDcDcStatusAlarm(Boolean dcDcStatusAlarm) {
        this.dcDcStatusAlarm = dcDcStatusAlarm;
    }

    public Boolean getDriveMotorControllerTemperatureAlarm() {
        return driveMotorControllerTemperatureAlarm;
    }

    public void setDriveMotorControllerTemperatureAlarm(Boolean driveMotorControllerTemperatureAlarm) {
        this.driveMotorControllerTemperatureAlarm = driveMotorControllerTemperatureAlarm;
    }

    public Boolean getHighVoltageInterlockStatusAlarm() {
        return highVoltageInterlockStatusAlarm;
    }

    public void setHighVoltageInterlockStatusAlarm(Boolean highVoltageInterlockStatusAlarm) {
        this.highVoltageInterlockStatusAlarm = highVoltageInterlockStatusAlarm;
    }

    public Boolean getDriveMotorTemperatureAlarm() {
        return driveMotorTemperatureAlarm;
    }

    public void setDriveMotorTemperatureAlarm(Boolean driveMotorTemperatureAlarm) {
        this.driveMotorTemperatureAlarm = driveMotorTemperatureAlarm;
    }

    public Boolean getOverChargingAlarm() {
        return overChargingAlarm;
    }

    public void setOverChargingAlarm(Boolean overChargingAlarm) {
        this.overChargingAlarm = overChargingAlarm;
    }
}
