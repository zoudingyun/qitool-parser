package org.qitool.parser.protocol.auto.GBT32960.exceptions;

/**
 * 未知的命令标识
 *
 * @author zoudingyun
 * 2024/5/15 9:49
 */
public class UnknowCommandIdentification extends GBT32960FormatException{

    // 传递异常值
    public UnknowCommandIdentification(String hex) {
        super(String.format("未知的命令标识:0x%s",hex));
    }

}
