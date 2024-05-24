package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

/**
 * 可充电储能装置子系统单体电池电压数据
 *
 * @author zoudingyun
 * 2024/5/23 17:18
 */
public class BatterySubsystemCellVoltageData {

    /**
     * 可充电储能子系统号
     * */
    private int cellId;


    /**
     * 单体电池电压
     * <br>
     * --------------------------------------------------------
     * <br>
     * 有效值范围：0～60000(表示0V～60.000 V)
     * 最小计量单元：0.001 V,单体电池电压个数等于本帧单体电池总数m
     * “0xFF,0xFE”表示异常，“0xFF,0xFF”表示无效
     * */
    private Object voltage;


    public int getCellId() {
        return cellId;
    }

    public void setCellId(int cellId) {
        this.cellId = cellId;
    }

    public Object getVoltage() {
        return voltage;
    }

    public void setVoltage(Object voltage) {
        this.voltage = voltage;
    }
}
