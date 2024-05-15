package org.qitool.parser.protocol.auto.GBT32960.exceptions;

import org.qitool.parser.protocol.auto.GBT32960.enums.EncryptionType;

/**
 * 不支持的加密方式
 *
 * @author zoudingyun
 * 2024/5/15 9:49
 */
public class UnsupportedEncryptionMethod extends GBT32960FormatException{

    // 传递异常值
    public UnsupportedEncryptionMethod(EncryptionType encryptionType) {
        super(String.format("不支持的加密方式:0x%s",encryptionType.value));
    }

}
