package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import org.qitool.parser.gis.domain.LatLongCoordinate;

/**
 * 车辆位置数据
 *
 * @author zoudingyun
 * 2024/5/16 9:38
 */
public class PositionData {

    /**
     * 定位状态
     * <br>
     * --------------------------------------------------------------
     * <br>
     * true: 有效定位；
     * <br>
     * false：无效定位，当数据通信正常，而不能获取定位信息时，发送最后一次有效定位信息，并将定 位状态置为无效
     * */
    private boolean enabled;

    /**
     * 地球坐标系
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 格式：东经+北纬 <br>
     * */
    private LatLongCoordinate wgs84;

    /**
     * 火星坐标系
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 格式：东经+北纬 <br>
     * */
    private LatLongCoordinate gcj02;


    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public LatLongCoordinate getWgs84() {
        return wgs84;
    }

    public void setWgs84(LatLongCoordinate wgs84) {
        this.wgs84 = wgs84;
    }

    public LatLongCoordinate getGcj02() {
        return gcj02;
    }

    public void setGcj02(LatLongCoordinate gcj02) {
        this.gcj02 = gcj02;
    }
}
