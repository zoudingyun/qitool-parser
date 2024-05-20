package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 * 充电状态
 *
 * @author zoudingyun
 * 2024/5/14 16:43
 */
public enum ChargingState {

    /**
     * 0x01
     * */
    STOP_CHARGING("停车充电"),

    /**
     * 0x02
     * */
    RUNNING_CHARGING("行驶充电"),

    /**
     * 0x03
     * */
    UNCHARGED("未充电"),

    /**
     * 0x04
     * */
    COMPLETED_CHARGED("充电完成"),

    /**
     * 0xFE
     * */
    EXCEPTION("异常"),

    /**
     * 0xFF
     * */
    INVALID("无效");

    private ChargingState(String state) {

    }
}
