package org.qitool.parser.general;

import java.math.BigInteger;

import static org.qitool.parser.common.CommonVariable.*;

/**
 * 基础数据类型调整工具
 *
 * @author zoudingyun
 * 2024/4/19 10:47
 */
public class BaseDataTypeUtil {

    private BaseDataTypeUtil() {}


    /**
     * byte数据转Hex字符串
     * @param byteData byte数据
     * @return 转换后的hex字符串
     * */
    public static String bytesToHexString(byte byteData) {
        byte[] bytes = new byte[1];
        bytes[0] = byteData;
        return bytesToHexString(bytes);
    }


    /**
     * byte数据组转Hex字符串
     * @param bytes byte数组
     * @return 转换后的hex字符串
     * */
    public static String bytesToHexString(byte[] bytes) {
        if (bytes == null || bytes.length == 0){
            return NULL_STRING;
        }else {
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                // 使用 String.format() 方法将字节转换为十六进制字符串，并在高位补零
                sb.append(String.format("%02X", b & 0xFF));
            }
            return sb.toString();
        }
    }


    /**
     * Hex字符串转byte数据组
     * @param hex hex字符串
     * @return 转换后的byte数组
     * */
    public static byte[] hexStringToBytes(String hex) {
        if (hex == null || hex.isEmpty()){
            return (new byte[0]);
        }else {
            return new BigInteger(hex, 16).toByteArray();
        }
    }

}
