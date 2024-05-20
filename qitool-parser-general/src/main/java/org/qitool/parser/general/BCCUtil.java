package org.qitool.parser.general;

/**
 * BCC校验工具类
 *
 * @author zoudingyun
 * 2024/5/14 18:09
 */
public class BCCUtil {

    private BCCUtil(){}

    /**
     * 计算数据的BCC校验码
     * @param data 需要计算校验码的数据
     * @return 校验码
     * */
    public static byte calculateBcc(byte[] data) {
        byte bcc = 0;

        for (byte b : data) {
            //逐位XOR运算
            bcc ^= b;
        }

        return bcc;
    }
}
