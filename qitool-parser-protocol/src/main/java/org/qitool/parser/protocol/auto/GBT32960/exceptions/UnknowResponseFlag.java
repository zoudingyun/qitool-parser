package org.qitool.parser.protocol.auto.GBT32960.exceptions;

/**
 * 未知的应答标志
 *
 * @author zoudingyun
 * 2024/5/15 9:49
 */
public class UnknowResponseFlag extends GBT32960FormatException{

    // 传递异常值
    public UnknowResponseFlag(String hex) {
        super(String.format("未知的应答标志:0x%s",hex));
    }

}
