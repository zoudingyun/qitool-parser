package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import org.qitool.parser.protocol.auto.GBT32960.enums.AutoState;
import org.qitool.parser.protocol.auto.GBT32960.enums.ChargingState;
import org.qitool.parser.protocol.auto.GBT32960.enums.DcDcState;
import org.qitool.parser.protocol.auto.GBT32960.enums.WorkMode;

/**
 * 整车数据
 *
 * @author zoudingyun
 * 2024/5/14 16:37
 */
public class AutoStatisticsData {

    /**
     * 车辆状态
     * */
    private AutoState autoState;

    /**
     * 充电状态
     * */
    private ChargingState chargingState;

    /**
     * 运行模式
     * */
    private WorkMode workMode;

    /**
     * 车速
     * <br>
     * --------------------------------------------------------<br>
     * 有效值范围:0～2200(表示0 km/h~220 km/h),最小计量单元:0.1 km/h
     * 报文“0xFF,0xFE”表示异常,“OxFF,OxFF”表示无效<br>
     * --------------------------------------------------------<br>
     * */
    private Object speed;

    /**
     * 累计里程
     * <br>
     * --------------------------------------------------------<br>
     * 有效值范围:0～9 999 999(表示0 km~999 999.9 km),最小计量单元:0.1 km。
     * 报文“0xFF，0xFF，0xFF,0xFE”表示异常，“oxFF,0xFF,OxFF,0xFF”表示无效<br>
     * --------------------------------------------------------<br>
     * */
    private Object mileage;

    /**
     * 总电压
     * <br>
     * --------------------------------------------------------<br>
     * 有效值范围:0～10 000(表示0 V~1 000 V),最小计量单元0.1 V
     * “OxFF,OxFE”表示异常,“OxFF,0xFF”表示无效<br>
     * --------------------------------------------------------<br>
     * */
    private Object totalVoltage;

    /**
     * 总电流
     * <br>
     * --------------------------------------------------------<br>
     * 有效值范围:0～20 000(偏移量1 000 A,表示一1 000 A~～+1 000 A)
     * 最小计量单元:0.1 A, “0xFF,0xFE”表示异常，“OxFF,0xFF”表示无效<br>
     * --------------------------------------------------------<br>
     * */
    private Object totalCurrent;


    /**
     * soc
     * <br>
     * --------------------------------------------------------<br>
     * 有效值范围:0～100(表示0%~100%),最小计量单元:1%<br>
     * --------------------------------------------------------<br>
     * */
    private Object soc;

    /**
     * DC/DC状态
     * <br>
     * --------------------------------------------------------<br>
     * Ox01:工作;0x02:断开<br>
     * --------------------------------------------------------<br>
     * */
    private DcDcState dcDcState ;

    /**
     * 有驱动力
     * <br>
     * --------------------------------------------------------<br>
     * true：有 false：没有<br>
     * --------------------------------------------------------<br>
     * */
    private Boolean driving;

    /**
     * 有制动力
     * <br>
     * --------------------------------------------------------<br>
     * true：有 false：没有<br>
     * --------------------------------------------------------<br>
     * */
    private Boolean braking;

    /**
     * 档位
     * */
    private String gear;

    /**
     * 绝缘电阻
     * <br>
     * --------------------------------------------------------<br>
     * 有效范围0～60 000(表示0 kΩ～60 000 kΩ),最小计量单元: 1 kΩ<br>
     * --------------------------------------------------------<br>
     * */
    private Integer resistance;

    /**
     * 加速踏板行程
     * <br>
     * --------------------------------------------------------<br>
     * 有效值范围:0～100(表示0%～100%),最小计量单元:1%<br>
     * -1：异常<br>
     * -2：数据无效<br>
     * --------------------------------------------------------<br>
     * */
    private Object acceleratorPedalState;


    /**
     * 制动踏板状态
     * <br>
     * --------------------------------------------------------<br>
     * 有效值范围:0～100(表示0%～100%),最小计量单元:1%<br>
     * 101: 有效（无具体值）<br>
     * -1：异常<br>
     * -2：数据无效<br>
     * --------------------------------------------------------<br>
     * */
    private Object brakePedalState;

    /**
     * 末尾两字节预留数据位未处理的数据
     * */
    private byte[] reservedRawData;


    public AutoState getAutoState() {
        return autoState;
    }

    public void setAutoState(AutoState autoState) {
        this.autoState = autoState;
    }

    public ChargingState getChargingState() {
        return chargingState;
    }

    public void setChargingState(ChargingState chargingState) {
        this.chargingState = chargingState;
    }

    public WorkMode getWorkMode() {
        return workMode;
    }

    public void setWorkMode(WorkMode workMode) {
        this.workMode = workMode;
    }

    public Object getSpeed() {
        return speed;
    }

    public void setSpeed(Object speed) {
        this.speed = speed;
    }

    public Object getMileage() {
        return mileage;
    }

    public void setMileage(Object mileage) {
        this.mileage = mileage;
    }

    public Object getTotalVoltage() {
        return totalVoltage;
    }

    public void setTotalVoltage(Object totalVoltage) {
        this.totalVoltage = totalVoltage;
    }

    public Object getTotalCurrent() {
        return totalCurrent;
    }

    public void setTotalCurrent(Object totalCurrent) {
        this.totalCurrent = totalCurrent;
    }

    public Object getSoc() {
        return soc;
    }

    public void setSoc(Object soc) {
        this.soc = soc;
    }

    public DcDcState getDcDcState() {
        return dcDcState;
    }

    public void setDcDcState(DcDcState dcDcState) {
        this.dcDcState = dcDcState;
    }

    public Boolean getDriving() {
        return driving;
    }

    public void setDriving(Boolean driving) {
        this.driving = driving;
    }

    public Boolean getBraking() {
        return braking;
    }

    public void setBraking(Boolean braking) {
        this.braking = braking;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public Integer getResistance() {
        return resistance;
    }

    public void setResistance(Integer resistance) {
        this.resistance = resistance;
    }

    public Object getAcceleratorPedalState() {
        return acceleratorPedalState;
    }

    public void setAcceleratorPedalState(Object acceleratorPedalState) {
        this.acceleratorPedalState = acceleratorPedalState;
    }

    public Object getBrakePedalState() {
        return brakePedalState;
    }

    public void setBrakePedalState(Object brakePedalState) {
        this.brakePedalState = brakePedalState;
    }

    public byte[] getReservedRawData() {
        return reservedRawData;
    }

    public void setReservedRawData(byte[] reservedRawData) {
        this.reservedRawData = reservedRawData;
    }
}
