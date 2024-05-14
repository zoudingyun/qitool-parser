package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 *  命令标识
 *  命令标识应是发起方的唯一标识
 * @author zoudingyun
 * 2024/5/14 14:37
 */
public enum CommandIdentification {

    /**
     * 0x01
     * */
    UP_AUTO_LOGIN("车辆登入"),

    /**
     * 0x02
     * */
    UP_REAL_TIME_DATA_REPORT("实时信息上报"),

    /**
     * 0x03
     * */
    UP_REAL_TIME_DATA_COMPLETE("补发信息上报"),

    /**
     * 0x04
     * */
    UP_AUTO_LOGOUT("车辆登出"),

    /**
     * 0x05~0x06
     * */
    CUSTOM_DATA_OCCUPY("平台传输数据占用"),

    /**
     * 0x07
     * */
    UP_HEARTBEAT("心跳"),

    /**
     * 0x08
     * */
    UP_TIMING("终端校时"),

    /**
     * 0x09~0x7F
     * */
    UP_DATA_SYSTEM_RESERVATION("上行数据系统预留"),

    /**
     * 0x80
     * */
    DOWN_COMMAND_QUERY("查询命令"),

    /**
     * 0x81
     * */
    DOWN_COMMAND_SETUP("设置命令"),

    /**
     * 0x82
     * */
    DOWN_COMMAND_CONTROL("车载终端控制命令"),

    /**
     * 0x83~0xBF
     * */
    DOWN_DATA_SYSTEM_RESERVATION("下行数据系统预留"),

    /**
     * 0xC0~0xFE
     * */
    CUSTOM_DATA_EXCHANGE("平台交换自定义数据");


    private CommandIdentification(String value) {
    }

}
