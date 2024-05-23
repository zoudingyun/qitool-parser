package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import java.math.BigInteger;
import java.util.List;

/**
 * 告警数据
 *
 * @author zoudingyun
 * 2024/5/23 14:26
 */
public class AlarmData {

    /**
     * 告警等级
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 当前发生的故障中的最高等级值，有效值范围：0～3
     * <br>
     * “0”表示无故障；<br>
     * “1”表示1级故障，指代不影响车辆正常行驶的  故障；<br>
     * “2”表示2级故障，指代影响车辆性能，需驾驶员限制行 驶的故障；<br>
     * “3”表示3级故障，为最高级别故障，指代驾驶员应 立即停车处理或请求救援的故障；<br>
     * 具体等级对应的故障内容,由厂商自行定义；<br>
     * “0xFE”表示异常，“0xFF”表示无效
     * */
    private Object alarmLevel;


    /**
     * 通用报警标志位
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true:    有报警
     * false：   报警停止
     * */
    private AlarmCommonFlag alarmCommonFlag;



    /**
     * 可充电储能装置故障总数 N₁
     * <br>
     * --------------------------------------------------------------
     * <br>
     * N₁个可充电储能装置故障，有效值范围：0～252,“0xFE”表 示异常，“0xFF”表示无效
     * <br>
     * “0xFE”表示异常，“0xFF”表示无效
     * */
    private Object energyStorageAlarmCount;


    /**
     * 可充电储能装置故障代码列表
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 扩展性数据，由厂商自行定义，故障代码个数等于可充电储能装置故障总数N₁
     * <br>
     * 工具已将报警代码解析为无符号整数
     * */
    private List<BigInteger> energyStorageAlarmCodes;


    /**
     * 驱动电机故障总数N₂
     * <br>
     * --------------------------------------------------------------
     * <br>
     * N₂个驱动电机故障，有效值范围：0～252,“0xFE”表 示异常，“0xFF”表示无效
     * <br>
     * “0xFE”表示异常，“0xFF”表示无效
     * */
    private Object driveMotorAlarmCount;


    /**
     * 驱动电机故障代码列表
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 扩展性数据，由厂商自行定义，故障代码个数等于驱动电机故障总数N₁
     * <br>
     * 工具已将报警代码解析为无符号整数
     * */
    private List<BigInteger> driveMotorAlarmCodes;


    /**
     * 发动机故障总数N₃
     * <br>
     * --------------------------------------------------------------
     * <br>
     * N₃个发动机故障，有效值范围：0～252,“0xFE”表 示异常，“0xFF”表示无效
     * <br>
     * “0xFE”表示异常，“0xFF”表示无效
     * */
    private Object engineAlarmCount;


    /**
     * 发动机故障代码列表
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 扩展性数据，由厂商自行定义，故障代码个数等于发动机故障总数N₃
     * <br>
     * 工具已将报警代码解析为无符号整数
     * */
    private List<BigInteger> engineAlarmCodes;


    /**
     * 其他故障总数N₄
     * <br>
     * --------------------------------------------------------------
     * <br>
     * N₄个其他故障，有效值范围：0～252,“0xFE”表 示异常，“0xFF”表示无效
     * <br>
     * “0xFE”表示异常，“0xFF”表示无效
     * */
    private Object otherAlarmCount;


    /**
     * 其他故障代码列表
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 扩展性数据，由厂商自行定义，故障代码个数等于其他故障总数N₄
     * <br>
     * 工具已将报警代码解析为无符号整数
     * */
    private List<BigInteger> otherAlarmCodes;


    public Object getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(Object alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public AlarmCommonFlag getAlarmCommonFlag() {
        return alarmCommonFlag;
    }

    public void setAlarmCommonFlag(AlarmCommonFlag alarmCommonFlag) {
        this.alarmCommonFlag = alarmCommonFlag;
    }

    public Object getEnergyStorageAlarmCount() {
        return energyStorageAlarmCount;
    }

    public void setEnergyStorageAlarmCount(Object energyStorageAlarmCount) {
        this.energyStorageAlarmCount = energyStorageAlarmCount;
    }

    public List<BigInteger> getEnergyStorageAlarmCodes() {
        return energyStorageAlarmCodes;
    }

    public void setEnergyStorageAlarmCodes(List<BigInteger> energyStorageAlarmCodes) {
        this.energyStorageAlarmCodes = energyStorageAlarmCodes;
    }

    public Object getDriveMotorAlarmCount() {
        return driveMotorAlarmCount;
    }

    public void setDriveMotorAlarmCount(Object driveMotorAlarmCount) {
        this.driveMotorAlarmCount = driveMotorAlarmCount;
    }

    public List<BigInteger> getDriveMotorAlarmCodes() {
        return driveMotorAlarmCodes;
    }

    public void setDriveMotorAlarmCodes(List<BigInteger> driveMotorAlarmCodes) {
        this.driveMotorAlarmCodes = driveMotorAlarmCodes;
    }

    public Object getEngineAlarmCount() {
        return engineAlarmCount;
    }

    public void setEngineAlarmCount(Object engineAlarmCount) {
        this.engineAlarmCount = engineAlarmCount;
    }

    public List<BigInteger> getEngineAlarmCodes() {
        return engineAlarmCodes;
    }

    public void setEngineAlarmCodes(List<BigInteger> engineAlarmCodes) {
        this.engineAlarmCodes = engineAlarmCodes;
    }

    public Object getOtherAlarmCount() {
        return otherAlarmCount;
    }

    public void setOtherAlarmCount(Object otherAlarmCount) {
        this.otherAlarmCount = otherAlarmCount;
    }

    public List<BigInteger> getOtherAlarmCodes() {
        return otherAlarmCodes;
    }

    public void setOtherAlarmCodes(List<BigInteger> otherAlarmCodes) {
        this.otherAlarmCodes = otherAlarmCodes;
    }

}
