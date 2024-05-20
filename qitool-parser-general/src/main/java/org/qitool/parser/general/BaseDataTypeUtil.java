package org.qitool.parser.general;

import java.math.BigDecimal;
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


    /**
     * byte数据组转整数
     * @param bytes byte数组
     * @return 转换后的hex字符串
     * */
    public static BigInteger bytesToBigInteger(byte[] bytes) {
        return new BigInteger(bytes);
    }

    /**
     * 通过byte数据生成无符号整数
     * @param byteData byte数据
     * @return 正整数
     * */
    public static BigInteger byteToUnsignedBigInteger(byte byteData){
        byte[] bytes = new byte[1];
        bytes[0] = byteData;
        return bytesToBigInteger(bytes);
    }


    /**
     * 通过byte数组生成无符号整数
     * @param bytes byte数组
     * @return 正整数
     * */
    public static BigInteger bytesToUnsignedBigInteger(byte[] bytes) {
        byte[] unsignedBytes = new byte[bytes.length];
        // 将每个字节视为无符号数值
        for (int i = 0; i < bytes.length; i++) {
            unsignedBytes[i] = (byte) (bytes[i] & 0xFF);
        }
        // 使用无符号的byte数组创建BigInteger 参数1表示正数
        return new BigInteger(1, unsignedBytes);
    }


    /**
     * 通过bytes数据生成有符号精确数字
     * @param bytes bytes数据
     * @param multiplier 小数倍率,结果需要×倍率
     * @return 数据
     * */
    public static BigDecimal bytesToBigDecimal(byte[] bytes, BigDecimal multiplier){
        return new BigDecimal(bytesToBigInteger(bytes)).multiply(multiplier);
    }

    /**
     * 通过bytes数据生成无符号精确数字
     * @param bytes bytes数据
     * @param multiplier 小数倍率,结果需要×倍率
     * @return 数据
     * */
    public static BigDecimal bytesToUnsignedBigDecimal(byte[] bytes, BigDecimal multiplier){
        return new BigDecimal(bytesToUnsignedBigInteger(bytes)).multiply(multiplier);
    }


    /**
     * 检查一个字节的某一位值是否为1
     * @param b 需要检查的字节
     * @param position 要检查的位的位置（二进制数从左往右数，1开始数）
     * @return 是否为1
     * */
    public static boolean checkBitValue(byte b, int position) {
        // 将要检查的位移动到最右边
        int shifted = b >>> (8-position);

        // 使用按位与操作符获取最右边的位
        int bitValue = shifted & 0x01;

        // 判断最右边的位是1还是0
        return (bitValue == 1);
    }

}
