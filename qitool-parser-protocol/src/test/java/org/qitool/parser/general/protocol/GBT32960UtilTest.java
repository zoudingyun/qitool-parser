package org.qitool.parser.general.protocol;

import org.junit.jupiter.api.Test;
import org.qitool.parser.protocol.auto.GBT32960.domain.AutoData;
import org.qitool.parser.protocol.auto.GBT32960Util;
import java.util.logging.Logger;

/**
 * GBT32960 电动汽车远程通信协议解析工具测试类
 *
 * @author zoudingyun
 * 2024/5/14 17:36
 */
public class GBT32960UtilTest {

    // 获取Logger对象，参数为当前类的名称
    private static final Logger logger = Logger.getLogger(GBT32960UtilTest.class.getName());

    @Test
    public void test() {
        AutoData autoData = GBT32960Util.format("232302fe4c5a46483235583436524430303031373101021518050e1212130101030101d4000119b818fd1d5d1a0113115c0006020101024d568737466c18fe1f3d050006744ab101d45fa0060600ffff0300ffff0430450808430702000020000000000008010119031e57018000c9b80d020cf30cf50cf20cf50cf20cf20cf20cf20cf40cf40cf20cf70cf30cf60cf40cf10cf30cf20cf40cf20cf20d000d010d030d030d020cff0d010d010d020d030d030d010d010d000d000cff0cff0cfc0d040d030d040d070d030d070d0d0d140d0f0d0d0d0d0d0b0d110d0c0d0f0d0e0d110d120d0d0d0f0d0d0d0b0d110d0e0d0b0d0e0d0f0d070d040d050d060d060d080cd90cda0cd70cd90cd80cd80cd90cd80cd80cd90cd60cd70cda0cd90cdb0cdb0cdb0cdb0cdc0cda0cdb0cda0cdc0cdc0cdb0cdb0cd90cdb0cd80cdb0cdb0cdd0cdb0cdb0cdb0cda0cdf0ce10cdf0cdd0ce00ce10cde0cde0cde0ce00ce20ce10cdf0cdf0cde0ce00ce00cdd0cf30cf50cf30cf40cf00cf20cf10cf10cf10cf30cf20cf40cf60cf60cf50cf20cf60cf30cf20cf80cf00cf40cf00cf40ce30ce30ce30ce30ce30ce20ce30ce40ce30ce30ce50ce30ce10ce00ce10ce20ce30ce40ce20ce30ce30d090d0a0d090d090d0a0d090d090d030d080d080d070d07090101004844444343434343434343434343434343434343434343434344434343434343434443434443434343444343434443434445444444444444434343434343434444444443444443434430");
        logger.info(String.format("命令单元-命令标识:%s",autoData.getCommandUnit().getCommandIdentification().value));
        if (!autoData.getCommandUnit().getCommandIdentification().value.equals("实时信息上报")){
            throw new RuntimeException("命令单元-命令标识 识别异常");
        }
        logger.info(String.format("命令单元-应答标志:%s",autoData.getCommandUnit().getResponseFlag().value));
        if (!autoData.getCommandUnit().getResponseFlag().value.equals("命令")){
            throw new RuntimeException("命令单元-应答标志 识别异常");
        }
        logger.info(String.format("VIN:%s",autoData.getVin()));
        if (!autoData.getVin().equals("LZFH25X46RD000171")){
            throw new RuntimeException("VIN 识别异常");
        }
        logger.info(String.format("加密类型:%s",autoData.getEncryptionType().value));
        if (!autoData.getEncryptionType().value.equals("未加密")){
            throw new RuntimeException("加密类型 识别异常");
        }
        logger.info(String.format("数据单元长度:%s",autoData.getDataUnitLength()));
        if (!autoData.getDataUnitLength().equals(533) ){
            throw new RuntimeException("数据单元长度 识别异常");
        }
        logger.info(String.format("数据采集时间:%s",autoData.getDataUnit().getDataTimeDesc()));
        if (!autoData.getDataUnit().getDataTimeDesc().equals("2024-05-14 18:18:19") ){
            throw new RuntimeException("数据单元长度 识别异常");
        }
        logger.info(String.format("数据采集时间:%s",autoData.getDataUnit().getDataTime()));
        if (!autoData.getDataUnit().getDataTime().toString().equals("Tue May 14 18:18:19 CST 2024") ){
            throw new RuntimeException("数据单元长度 识别异常");
        }
    }

}
