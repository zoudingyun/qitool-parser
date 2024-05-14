package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 * DC/DC状态
 *
 * @author zoudingyun
 * 2024/5/14 16:43
 */
public enum DcDcState {

    /**
     * 0x01
     * */
    ON("工作"),

    /**
     * 0x02
     * */
    OFF("断开"),

    /**
     * 0xFE
     * */
    EXCEPTION("异常"),

    /**
     * 0xFF
     * */
    INVALID("无效");

    private DcDcState(String state) {

    }
}
