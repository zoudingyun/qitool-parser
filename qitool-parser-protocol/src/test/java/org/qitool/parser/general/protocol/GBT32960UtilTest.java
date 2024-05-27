package org.qitool.parser.general.protocol;

import org.junit.jupiter.api.Test;
import org.qitool.parser.protocol.auto.GBT32960.domain.AutoData;
import org.qitool.parser.protocol.auto.GBT32960Util;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String hex = "232302fe4c5a46483235583436524430303031373101021518050e1212130101030101d4000119b818fd1d5d1a0113115c0006020101024d568737466c18fe1f3d050006744ab101d45fa0060600ffff0300ffff0430450808430702000020000000000008010119031e57018000c9b80d020cf30cf50cf20cf50cf20cf20cf20cf20cf40cf40cf20cf70cf30cf60cf40cf10cf30cf20cf40cf20cf20d000d010d030d030d020cff0d010d010d020d030d030d010d010d000d000cff0cff0cfc0d040d030d040d070d030d070d0d0d140d0f0d0d0d0d0d0b0d110d0c0d0f0d0e0d110d120d0d0d0f0d0d0d0b0d110d0e0d0b0d0e0d0f0d070d040d050d060d060d080cd90cda0cd70cd90cd80cd80cd90cd80cd80cd90cd60cd70cda0cd90cdb0cdb0cdb0cdb0cdc0cda0cdb0cda0cdc0cdc0cdb0cdb0cd90cdb0cd80cdb0cdb0cdd0cdb0cdb0cdb0cda0cdf0ce10cdf0cdd0ce00ce10cde0cde0cde0ce00ce20ce10cdf0cdf0cde0ce00ce00cdd0cf30cf50cf30cf40cf00cf20cf10cf10cf10cf30cf20cf40cf60cf60cf50cf20cf60cf30cf20cf80cf00cf40cf00cf40ce30ce30ce30ce30ce30ce20ce30ce40ce30ce30ce50ce30ce10ce00ce10ce20ce30ce40ce20ce30ce30d090d0a0d090d090d0a0d090d090d030d080d080d070d07090101004844444343434343434343434343434343434343434343434344434343434343434443434443434343444343434443434445444444444444434343434343434444444443444443434430";
        GBT32960Util.checkData(hex);
        Long start = System.currentTimeMillis();
        AutoData autoData = GBT32960Util.format(hex);
        System.out.printf("解析时间：%sms%n", (System.currentTimeMillis() - start));
        logger.info(String.format("命令单元-命令标识:%s",autoData.getCommandUnit().getCommandIdentification().value));
        assertEquals(autoData.getCommandUnit().getCommandIdentification().value,"实时信息上报");
        logger.info(String.format("命令单元-应答标志:%s",autoData.getCommandUnit().getResponseFlag().value));
        assertEquals(autoData.getCommandUnit().getResponseFlag().value,"命令");
        logger.info(String.format("VIN:%s",autoData.getVin()));
        assertEquals(autoData.getVin(),"LZFH25X46RD000171");
        logger.info(String.format("加密类型:%s",autoData.getEncryptionType().value));
        assertEquals(autoData.getEncryptionType().value,"未加密");
        logger.info(String.format("数据单元长度:%s",autoData.getDataUnitLength()));
        assertEquals(autoData.getDataUnitLength(),533);
        logger.info(String.format("数据采集时间:%s",autoData.getDataUnit().getRealTimeData().getDataTimeDesc()));
        assertEquals(autoData.getDataUnit().getRealTimeData().getDataTimeDesc(),"2024-05-14 18:18:19");
        logger.info(String.format("数据采集时间:%s",autoData.getDataUnit().getRealTimeData().getDataTime()));
        assertEquals(autoData.getDataUnit().getRealTimeData().getDataTime().toString(),"Tue May 14 18:18:19 CST 2024");

    }


    @Test
    public void test2() {
        String hex = "232303fe4c5a46483235583434524430303031373001023518051b0a2d0d010103010159000179f8178140e53801230c946400020101015c5456a59bae1790415f05000674b4a601d484720607080c79071c0c3f01014a08094807020000200000000000080101178140e501800001c80c5a0c540c510c4d0c580c4c0c580c4a0c510c5a0c4a0c550c4a0c570c480c580c550c530c530c490c530c570c4b0c540c580c4e0c4a0c4a0c520c510c4b0c520c560c510c4e0c570c520c4f0c510c520c490c510c520c580c520c4f0c4f0c530c5d0c550c500c4c0c570c490c570c490c4e0c540c4a0c540c470c530c470c570c530c500c500c4a0c520c550c490c540c570c4f0c470c480c480c510c4e0c500c500c500c520c530c4f0c4d0c520c520c4c0c480c4e0c510c530c510c520c520c5d0c540c510c490c570c470c580c580c520c540c480c570c4a0c580c4a0c5a0c560c4f0c540c490c520c560c4d0c550c570c500c4f0c4b0c500c500c4e0c510c550c4f0c4f0c560c500c4d0c530c510c4a0c4b0c4e0c570c530c520c530c510c5d0c550c520c470c550c490c540c540c4d0c540c450c540c480c530c5d0c560c560c4f0c530c530c510c560c550c500c570c4e0c500c4a0c500c500c4b0c500c540c4f0c4f0c580c4d0c4c0c4f0c500c470c4f0c4a0c540c520c4e0c4f0c4e0c550c4f0c4a0c430c510c420c530c460901010048494949494949494949484948484949494949494949494949494848484949484949494849484949494949494949494949494949494949494949484948484949494949494949494949ea";
        GBT32960Util.checkData(hex);
        Long start = System.currentTimeMillis();
        AutoData autoData = GBT32960Util.format(hex);
        System.out.printf("解析时间：%sms%n", (System.currentTimeMillis() - start));

    }

}
