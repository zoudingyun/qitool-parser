package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 * 发动机状态
 *
 * @author zoudingyun
 * 2024/5/14 16:43
 */
public enum EngineState {

    /**
     * 0x01
     * */
    ON("启动状态"),

    /**
     * 0x02
     * */
    OFF("关闭状态"),

    /**
     * 0xFE
     * */
    EXCEPTION("异常"),

    /**
     * 0xFF
     * */
    INVALID("无效");

    EngineState(String state) {

    }
}
