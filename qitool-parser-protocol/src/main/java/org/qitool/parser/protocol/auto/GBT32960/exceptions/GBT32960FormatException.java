package org.qitool.parser.protocol.auto.GBT32960.exceptions;

/**
 *  GBT32960数据格式化出错
 *
 * @author zoudingyun
 * 2024/5/14 17:45
 */
public class GBT32960FormatException extends RuntimeException{

    // 默认构造函数
    public GBT32960FormatException() {
        super();
    }

    // 带有详细信息的构造函数
    public GBT32960FormatException(String message) {
        super(message);
    }

    // 带有原因的构造函数
    public GBT32960FormatException(Throwable cause) {
        super(cause);
    }

    // 既带有详细信息又带有原因的构造函数
    public GBT32960FormatException(String message, Throwable cause) {
        super(message, cause);
    }

}
