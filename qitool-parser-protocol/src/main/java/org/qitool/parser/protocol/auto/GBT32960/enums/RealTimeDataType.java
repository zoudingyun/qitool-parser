package org.qitool.parser.protocol.auto.GBT32960.enums;

/**
 * 实时信息类型标志
 *
 * @author zoudingyun
 * 2024/5/14 15:51
 */
public enum RealTimeDataType {

    /**
     * 0x01
     * */
    AUTO_DATA("整车数据"),

    /**
     * 0x02
     * <br>
     * -----------------------------------------------------
     * <br>
     * 停车充电过程无需传输该数据
     * */
    DRIVE_MOTOR_DATA("驱动电机数据"),

    /**
     * 0x03
     * */
    FUEL_CELL_DATA("燃料电池数据"),

    /**
     * 0x04
     * <br>
     * -----------------------------------------------------
     * <br>
     * 停车充电过程无需传输该数据
     * */
    ENGINE_DATA("发动机数据"),

    /**
     * 0x05
     * */
    GPS_DATA("车辆位置数据"),

    /**
     * 0x06
     * */
    LIMIT_DATA("极值数据"),

    /**
     * 0x07
     * */
    ALARM_DATA("报警数据"),

    /**
     * 0x08~0x09
     * */
    TERMINAL_RESERVED_DATA("终端数据预留"),

    /**
     * 0x0A~0x2F
     * */
    EXCHANGE_CUSTOM_DATA("平台交换协议自定义数据"),

    /**
     * 0x30~0x7F
     * */
    RESERVED_DATA("预留"),

    /**
     * 0x80~0xFE
     * */
    USER_CUSTOM_DATA("用户自定义数据");

    private RealTimeDataType(String value){

    }
}
