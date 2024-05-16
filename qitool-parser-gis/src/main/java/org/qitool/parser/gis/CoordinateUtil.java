package org.qitool.parser.gis;

import org.qitool.parser.gis.domain.LatLongCoordinate;

import java.math.BigDecimal;

import static java.lang.Math.PI;

/**
 * 地图坐标工具类
 * @author zdy
 * 2024年4月20日
 * */
public class CoordinateUtil {

    private CoordinateUtil(){}

    /**
     * 地球半径(m)
     * 克拉索夫斯基椭球参数长半轴
     */
    private static final BigDecimal RADIUS = new BigDecimal("6378245.0");

    /**
     * 克拉索夫斯基椭球参数第一偏心率平方(偏心率ee)
     */
    private static final BigDecimal E_E = new BigDecimal("0.00669342162296594323");


    /**
     * WGS84（地球坐标系） 转 GCJ-02（火星坐标系）
     * @param longitude 经度
     * @param latitude  纬度
     * @return 转换后的经纬度坐标
     * */
    public static LatLongCoordinate wgs84ToGcj02(BigDecimal longitude,BigDecimal latitude){
        LatLongCoordinate offsetCoordinate = coordinateOffset(longitude.doubleValue(),latitude.doubleValue(), true);
        return new LatLongCoordinate(longitude.add(offsetCoordinate.getLongitude()), latitude.add(offsetCoordinate.getLatitude()));
    }

    /**
     * WGS84（地球坐标系） 转 GCJ-02（火星坐标系）
     * @param wgs84Coordinate 地球坐标数据
     * @return 转换后的经纬度坐标
     * */
    public static LatLongCoordinate wgs84ToGcj02(LatLongCoordinate wgs84Coordinate){
        return wgs84ToGcj02(wgs84Coordinate.getLongitude(),wgs84Coordinate.getLatitude());
    }

    /**
     * GCJ-02（火星坐标系） 转 WGS84（地球坐标系）
     * @param longitude 经度
     * @param latitude  纬度
     * @return 转换后的经纬度坐标
     * */
    public static LatLongCoordinate gcj02ToWgs84(BigDecimal longitude,BigDecimal latitude){
        LatLongCoordinate offsetCoordinate = coordinateOffset(longitude.doubleValue(),latitude.doubleValue(), false);
        return new LatLongCoordinate(longitude.add(offsetCoordinate.getLongitude()), latitude.add(offsetCoordinate.getLatitude()));
    }

    /**
     * GCJ-02（火星坐标系） 转 WGS84（地球坐标系）
     * @param gcjCoordinate 火星坐标
     * @return 转换后的经纬度坐标
     * */
    public static LatLongCoordinate gcj02ToWgs84(LatLongCoordinate gcjCoordinate){
        return gcj02ToWgs84(gcjCoordinate.getLongitude(),gcjCoordinate.getLatitude());
    }


    /**
     * WGS84（地球坐标系） 与 GCJ-02 (火星坐标系) 的偏移计算
     * <a href="https://download.csdn.net/download/weixin_38702726/13133428">参考代码</a>
     * @param lng 经度
     * @param lat 纬度
     * @param needOffset 是否需要偏移 true： WGS84->GCJ-02  |  false： GCJ-02->WGS84
     * @return 偏移后坐标
     */
    private static LatLongCoordinate coordinateOffset(double lng, double lat, boolean needOffset) {
        double transformedLng = transformLng(lng - 105.0, lat - 35.0);
        double transformedLat = transformLat(lng - 105.0, lat - 35.0);

        double magic = Math.sin(lat / 180.0 * PI);
        magic = 1 - E_E.doubleValue() * magic * magic;
        final double sqrtMagic = Math.sqrt(magic);

        transformedLng = (transformedLng * 180.0) / (RADIUS.doubleValue() / sqrtMagic * Math.cos(lat / 180.0 * PI) * PI);
        transformedLat = (transformedLat * 180.0) / ((RADIUS.doubleValue() * (1 - E_E.doubleValue())) / (magic * sqrtMagic) * PI);

        if(!needOffset){
            transformedLng = - transformedLng;
            transformedLat = - transformedLat;
        }

        return new LatLongCoordinate(transformedLng, transformedLat);
    }


    /**
     * 计算经度坐标
     * 参考代码：<a href="https://blog.csdn.net/qq_22266389/article/details/132543618">参考代码</a>
     * @param lng 经度坐标
     * @param lat 纬度坐标
     * @return ret 计算完成后的经度
     */
    private static double transformLng(double lng, double lat) {
        double ret = 300.0 + lng + 2.0 * lat + 0.1 * lng * lng + 0.1 * lng * lat + 0.1 * Math.sqrt(Math.abs(lng));
        ret = getRet(lng, lng, ret);
        ret += ((150.0 * Math.sin((lng / 12.0) * PI) + 300.0 * Math.sin((lng / 30.0) * PI)) * 2.0) / 3.0;
        return ret;
    }


    /**
     * 计算纬度坐标
     * 参考代码：<a href="https://blog.csdn.net/qq_22266389/article/details/132543618">参考代码</a>
     * @param lng 经度坐标
     * @param lat 纬度坐标
     * @return ret 计算完成后的纬度
     */
    private static double transformLat(double lng, double lat) {
        double ret = -100.0 + 2.0 * lng + 3.0 * lat + 0.2 * lat * lat + 0.1 * lng * lat
                + 0.2 * Math.sqrt(Math.abs(lng));
        ret = getRet(lng, lat, ret);
        ret += ((160.0 * Math.sin((lat / 12.0) * PI) + 320 * Math.sin((lat * PI) / 30.0)) * 2.0) / 3.0;
        return ret;
    }

    /**
     * 通用混淆计算
     * @param lng 经度坐标
     * @param lat 纬度坐标
     * @param ret 初始化后待处理的经、纬度
     * @return 混淆后的结果数据
     * */
    private static double getRet(double lng, double lat, double ret) {
        ret += ((20.0 * Math.sin(6.0 * lng * PI) + 20.0 * Math.sin(2.0 * lng * PI)) * 2.0) / 3.0;
        ret += ((20.0 * Math.sin(lat * PI) + 40.0 * Math.sin((lat / 3.0) * PI)) * 2.0) / 3.0;
        return ret;
    }


}
