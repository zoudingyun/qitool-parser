package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 * 运行模式
 * （1 byte）
 * @author zoudingyun
 * 2024/5/14 16:48
 */
public enum WorkMode {

    /**
     * 0x01
     * */
    ELECTRICITY("纯电"),

    /**
     * 0x02
     * */
    OIL_ELECTRICITY("混动"),

    /**
     * 0x03
     * */
    OIL("燃油"),

    /**
     * 0xFE
     * */
    EXCEPTION("异常"),

    /**
     * 0xFF
     * */
    INVALID("无效");

    private WorkMode(String mode){

    }

}
