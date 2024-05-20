package org.qitool.parser.protocol.auto.GBT32960.exceptions;

/**
 * 数据长度不匹配
 *
 * @author zoudingyun
 * 2024/5/15 9:49
 */
public class DataUnitLengthMismatch extends GBT32960FormatException{

    // 传递异常值
    public DataUnitLengthMismatch(int bytesLength,int decodeLength) {
        super(String.format("数据长度不匹配，报文中数据长度为:%s，报文信息中汇报的长度为:%s",bytesLength,decodeLength));
    }

}
