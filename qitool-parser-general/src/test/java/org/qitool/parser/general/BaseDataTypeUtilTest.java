package org.qitool.parser.general;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 基础数据类型转换工具测试类
 *
 * @author zoudingyun
 * 2024年4月20日01点49分
 * */
public class BaseDataTypeUtilTest {

    @Test
    void testAddition() {
        byte[] byteArray1 = {10, -20, 30, -40, 50};
        assertEquals("0AEC1ED832", BaseDataTypeUtil.bytesToHexString(byteArray1));
        byte[] byteArray2 = {};
        assertEquals("", BaseDataTypeUtil.bytesToHexString(byteArray2));
        byte[] byteArray3 = new byte[0];
        assertEquals("", BaseDataTypeUtil.bytesToHexString(byteArray3));
        assertEquals("", BaseDataTypeUtil.bytesToHexString(null));
    }

}
