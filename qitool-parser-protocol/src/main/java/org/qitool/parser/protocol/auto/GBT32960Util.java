package org.qitool.parser.protocol.auto;

import org.qitool.parser.general.BCCUtil;
import org.qitool.parser.general.BaseDataTypeUtil;
import org.qitool.parser.protocol.auto.GBT32960.domain.AutoData;
import org.qitool.parser.protocol.auto.GBT32960.exceptions.GBT32960FormatException;

import java.util.Arrays;

/**
 * GB-T 32960.1-2016 报文工具
 *
 * @author zoudingyun
 * 2024/5/14 14:26
 */
public class GBT32960Util {

    private GBT32960Util() {}

    /**
     * 解析GB-T 32960原始报文数据
     * @param hex hex数据
     * @return 解析后的数据
     * */
    public static AutoData format(String hex) {
        return  format(BaseDataTypeUtil.hexStringToBytes(hex));
    }


    /**
     * 解析GB-T 32960原始报文数据
     * @param bytes 报文数据
     * @return 解析后的数据
     * */
    public static AutoData format(byte[] bytes) {
        // 数据校验
        checkData(bytes);

        return null;
    }

    /**
     * 初步检查数据是否符合标准
     * @param bytes 数据
     * */
    private static void checkData(byte[] bytes) {
        if (bytes == null) {
            throw new GBT32960FormatException("数据为空");
        }
        // 一个报文至少25字节
        if (bytes.length < 25) {
            throw new GBT32960FormatException(String.format("数据格式异常，至少需要25字节，当前数据长度：%s 字节",bytes.length));
        }
        // 起始符不对
        if (bytes[0] != 0x23 ||bytes[1] != 0x23) {
            byte[] head = new byte[2];
            head[0] = bytes[0];
            head[1] = bytes[1];
            throw new GBT32960FormatException(String.format("未知的起始标志符，期望的值是 '##'，当前起始字符为：'%s'",new String(head)));
        }
        // BCC校验
        byte bccByte = bytes[bytes.length - 1];
        // bcc校验的部分是头部2字节和尾部的bcc校验码以外的所有部分,截取从第三个元素开始到倒数第二个元素为止的数组
        byte[] newArray = Arrays.copyOfRange(bytes, 2, bytes.length - 1);
        byte calcBcc = BCCUtil.calculateBcc(newArray);
        if (calcBcc!=bccByte){
            throw new GBT32960FormatException(
                    String.format("BCC校验未通过，期望的值是 '0x%s'，报文校验码为：'0x%s'"
                            , BaseDataTypeUtil.bytesToHexString(calcBcc)
                            ,BaseDataTypeUtil.bytesToHexString(bccByte)
                    )
            );
        }

    }

}
