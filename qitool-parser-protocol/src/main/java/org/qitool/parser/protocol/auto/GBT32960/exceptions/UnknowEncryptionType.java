package org.qitool.parser.protocol.auto.GBT32960.exceptions;

import org.qitool.parser.general.BaseDataTypeUtil;

/**
 * 未知的数据加密方式
 *
 * @author zoudingyun
 * 2024/5/15 9:49
 */
public class UnknowEncryptionType extends GBT32960FormatException{

    // 传递异常值
    public UnknowEncryptionType(byte value) {
        super(String.format("未知的数据加密方式:0x%s", BaseDataTypeUtil.bytesToHexString(value)));
    }

}
