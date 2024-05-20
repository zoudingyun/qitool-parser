package org.qitool.parser.general;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 基础数据类型转换工具测试类
 *
 * @author zoudingyun
 * 2024年4月20日01点49分
 * */
public class BaseDataTypeUtilTest {

    /**
     * 测试byte[] 是否能正常转为Hex字符串
     * */
    @Test
    void bytesToHexStringTest() {
        byte[] byteArray1 = {10, -20, 30, -40, 50};
        assertEquals("0AEC1ED832", BaseDataTypeUtil.bytesToHexString(byteArray1));
        byte[] byteArray2 = {};
        assertEquals("", BaseDataTypeUtil.bytesToHexString(byteArray2));
        byte[] byteArray3 = new byte[0];
        assertEquals("", BaseDataTypeUtil.bytesToHexString(byteArray3));
        assertEquals("", BaseDataTypeUtil.bytesToHexString(null));
    }


    /**
     * 测试Hex字符串是否能正常转为byte[]
     * */
    @Test
    void hexStringToBytesTest() {
        assertEquals( "0AEC1ED832", BaseDataTypeUtil.bytesToHexString(BaseDataTypeUtil.hexStringToBytes("0AEC1ED832")));
        assertEquals(0,BaseDataTypeUtil.hexStringToBytes("").length );
        assertEquals(0,BaseDataTypeUtil.hexStringToBytes(null).length );
    }

    /**
     * 测试字节数组转小数
     * */
    @Test
    void stringToBytesTest() {
        assertEquals(BaseDataTypeUtil.bytesToBigDecimal(BaseDataTypeUtil.hexStringToBytes("01"),new BigDecimal("0.1")),new BigDecimal("0.1"));
    }

}
