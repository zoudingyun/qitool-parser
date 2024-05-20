package org.qitool.parser.protocol.auto.GBT32960.domain;

import org.qitool.parser.protocol.auto.GBT32960.enums.EncryptionType;

/**
 * GBT32960 汽车数据
 *
 * @author zoudingyun
 * 2024/5/14 14:30
 */
public class AutoData {

    /**
     * 起始符
     * <br>
     * 国标要求使用 “##”（0x23 0x23）表示
     * */
    private String flag;

    /**
     * 命令单元
     * */
    private CommandUnit commandUnit;

    /**
     * vin码
     * */
    private String vin;

    /**
     * 数据加密方式
     * */
    private EncryptionType encryptionType;

    /**
     * 数据单元长度
     * */
    private Integer dataUnitLength;

    /**
     * 数据单元
     * */
    private DataUnit dataUnit;

    /**
     * 校验码
     * */
    private Integer bcc;


    public Integer getBcc() {
        return bcc;
    }

    public void setBcc(Integer bcc) {
        this.bcc = bcc;
    }

    public DataUnit getDataUnit() {
        return dataUnit;
    }

    public void setDataUnit(DataUnit dataUnit) {
        this.dataUnit = dataUnit;
    }

    public Integer getDataUnitLength() {
        return dataUnitLength;
    }

    public void setDataUnitLength(Integer dataUnitLength) {
        this.dataUnitLength = dataUnitLength;
    }

    public EncryptionType getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(EncryptionType encryptionType) {
        this.encryptionType = encryptionType;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public CommandUnit getCommandUnit() {
        return commandUnit;
    }

    public void setCommandUnit(CommandUnit commandUnit) {
        this.commandUnit = commandUnit;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
