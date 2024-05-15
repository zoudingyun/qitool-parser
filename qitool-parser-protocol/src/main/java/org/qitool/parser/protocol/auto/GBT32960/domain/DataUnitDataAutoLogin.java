package org.qitool.parser.protocol.auto.GBT32960.domain;

/**
 * 数据单元-车辆登入
 * <br>
 * ==============================================
 * <br>
 * 注：可充电储能子系统指当车辆存在多套可充电储能系统混合使用时,
 * 每套可充电储能系统为一个可充电储能子系统。
 * <br>
 * ==============================================
 *
 * @author zoudingyun
 * 2024/5/14 15:18
 */
public class DataUnitDataAutoLogin extends DataUnitData {

    /**
     * 登入流水号
     * */
    private Integer loginNumber;

    /**
     * ICCID
     * <br>
     * -----------------------------------------------------
     * <br>
     * SIM卡ICCID号(ICCID应为终端从SIM卡获取的值,不应人为填写或修改)
     * */
    private String iccId;


    /**
     * 可充电储能子系统数
     * <br>
     * -----------------------------------------------------
     * <br>
     * 有效值范围:0～250
     * */
    private Integer energyStorageSystemCount ;


    /**
     * 可充电储能系统编码长度
     * 有效范围:0～50,“0”表示不上传该编码
     * */
    private Integer energyStorageSystemCodeLength ;

    /**
     * 可充电储能系统编码
     * <br>
     * -----------------------------------------------------
     * <br>
     * 可充电储能系统编码 = 可充电储能子系统数 × 可充电储能系统编码长度
     * */
    private Integer energyStorageSystemCode ;


    public Integer getLoginNumber() {
        return loginNumber;
    }

    public void setLoginNumber(Integer loginNumber) {
        this.loginNumber = loginNumber;
    }

    public String getIccId() {
        return iccId;
    }

    public void setIccId(String iccId) {
        this.iccId = iccId;
    }

    public Integer getEnergyStorageSystemCount() {
        return energyStorageSystemCount;
    }

    public void setEnergyStorageSystemCount(Integer energyStorageSystemCount) {
        this.energyStorageSystemCount = energyStorageSystemCount;
    }

    public Integer getEnergyStorageSystemCodeLength() {
        return energyStorageSystemCodeLength;
    }

    public void setEnergyStorageSystemCodeLength(Integer energyStorageSystemCodeLength) {
        this.energyStorageSystemCodeLength = energyStorageSystemCodeLength;
    }

    public Integer getEnergyStorageSystemCode() {
        return energyStorageSystemCode;
    }

    public void setEnergyStorageSystemCode(Integer energyStorageSystemCode) {
        this.energyStorageSystemCode = energyStorageSystemCode;
    }
}
