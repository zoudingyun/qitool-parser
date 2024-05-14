package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 * 车辆状态
 *
 * @author zoudingyun
 * 2024/5/14 16:38
 */
public enum AutoState {

    /**
     * 0x01
     * */
    ON("车辆已启动"),

    /**
     * 0x02
     * */
    OFF("车辆已熄火"),

    /**
     * 0x03
     * */
    OTHER("其它状态"),

    /**
     * 0xFE
     * */
    EXCEPTION("异常"),

    /**
     * 0xFF
     * */
    INVALID("无效");

    private AutoState(String value){

    }
}
