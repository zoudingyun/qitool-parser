package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 * 数据加密类型
 *
 * @author zoudingyun
 * 2024/5/14 15:11
 */
public enum EncryptionType {

    /**
     * 0x01
     * */
    RAW("未加密"),

    /**
     * 0x02
     * */
    RSA("rsa加密"),

    /**
     * 0x03
     * */
    AES128("aes128加密"),

    /**
     * 0xFE
     * */
    EXCEPTION("异常"),

    /**
     * 0xFF
     * */
    INVALID("无效");


    private EncryptionType(String value){

    }

}
