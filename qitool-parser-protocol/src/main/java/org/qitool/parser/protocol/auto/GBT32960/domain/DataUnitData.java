package org.qitool.parser.protocol.auto.GBT32960.domain;

import java.util.Date;

/**
 * 数据单元数据
 *
 * @author zoudingyun
 * 2024/5/14 15:18
 */
public class DataUnitData {

    /**
     * 数据采集时间
     * */
    private Date dataTime;

    /**
     * 数据采集时间字符串
     * */
    private String dataTimeDesc;

    public Date getDataTime() {
        return dataTime;
    }

    public void setDataTime(Date dataTime) {
        this.dataTime = dataTime;
    }

    public String getDataTimeDesc() {
        return dataTimeDesc;
    }

    public void setDataTimeDesc(String dataTimeDesc) {
        this.dataTimeDesc = dataTimeDesc;
    }
}
