package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 * 驱动电机状态
 *
 * @author zoudingyun
 * 2024/5/14 16:38
 */
public enum DriveMotorState {

    /**
     * 0x01
     * */
    USING_ELECTRICITY("耗电"),

    /**
     * 0x02
     * */
    GENERATING_ELECTRICITY("发电"),

    /**
     * 0x03
     * */
    OFF("关闭状态"),

    /**
     * 0x04
     * */
    PREPARE("准备状态"),

    /**
     * 0xFE
     * */
    EXCEPTION("异常"),

    /**
     * 0xFF
     * */
    INVALID("无效");


    public final String value;

    DriveMotorState(String value){
        this.value = value;
    }
}
