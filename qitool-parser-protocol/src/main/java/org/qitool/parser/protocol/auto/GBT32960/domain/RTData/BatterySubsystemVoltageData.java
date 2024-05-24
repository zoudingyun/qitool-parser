package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import java.util.List;

/**
 * 可充电储能装置子系统电压数据
 *
 * @author zoudingyun
 * 2024/5/23 17:18
 */
public class BatterySubsystemVoltageData {

    /**
     * 可充电储能子系统号
     * <br>
     * --------------------------------------------------------
     * <br>
     * */
    private int subsystemId;


    /**
     * 可充电储能装置电压
     * <br>
     * --------------------------------------------------------
     * <br>
     * 有效值范围：0～10000(表示0V～1000 V),最小计量单元 0.1V
     * “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效
     * */
    private Object voltage;


    /**
     * 可充电储能装置电流
     * <br>
     * --------------------------------------------------------
     * <br>
     * 有效值范围：0～20000(数值偏移量1000 A,表示 -1000 A～+1000 A),最小计量单元：0.1 A
     * “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效
     * */
    private Object current;


    /**
     * 单体电池总数
     * <br>
     * --------------------------------------------------------
     * <br>
     * N个电池单体，有效值范围：1～65531
     * “OxFF,0xFE”表示异常，“0xFF,0xFF”表示无效
     * */
    private Object cellCount;


    /**
     * 本帧起始电池序号
     * <br>
     * --------------------------------------------------------
     * <br>
     * 当本帧单体个数超过200时，应拆分成多帧数据进行传输
     * 有效值范围：1～65531
     * */
    private int datsStartCellId;


    /**
     * 本帧单体电池总数
     * <br>
     * --------------------------------------------------------
     * <br>
     * 本帧单体总数m;
     * 有效值范围：1～200
     * */
    private int dataCellCount;


    /**
     * 单体电池电压
     * */
    private List<BatterySubsystemCellVoltageData> cellVoltageData;


    public int getSubsystemId() {
        return subsystemId;
    }

    public void setSubsystemId(int subsystemId) {
        this.subsystemId = subsystemId;
    }

    public Object getVoltage() {
        return voltage;
    }

    public void setVoltage(Object voltage) {
        this.voltage = voltage;
    }

    public Object getCurrent() {
        return current;
    }

    public void setCurrent(Object current) {
        this.current = current;
    }

    public Object getCellCount() {
        return cellCount;
    }

    public void setCellCount(Object cellCount) {
        this.cellCount = cellCount;
    }

    public int getDatsStartCellId() {
        return datsStartCellId;
    }

    public void setDatsStartCellId(int datsStartCellId) {
        this.datsStartCellId = datsStartCellId;
    }

    public int getDataCellCount() {
        return dataCellCount;
    }

    public void setDataCellCount(int dataCellCount) {
        this.dataCellCount = dataCellCount;
    }

    public List<BatterySubsystemCellVoltageData> getCellVoltageData() {
        return cellVoltageData;
    }

    public void setCellVoltageData(List<BatterySubsystemCellVoltageData> cellVoltageData) {
        this.cellVoltageData = cellVoltageData;
    }
}
