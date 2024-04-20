package org.qitool.parser.gis.domain;

import org.qitool.parser.gis.exceptions.LongitudeLatitudeFormatException;

import java.math.BigDecimal;

/**
 * 经纬度坐标
 * @author zdy
 * 2024年4月20日
 * */
public class LatLongCoordinate {

    /**
     * 纬度
     * */
    private BigDecimal latitude;

    /**
     * 经度
     * */
    private BigDecimal longitude;



    public LatLongCoordinate(){}

    /**
     * 构造方法
     * @param longitude 初始化经度
     * @param latitude 初始化纬度
     * */
    public LatLongCoordinate(BigDecimal longitude,BigDecimal latitude){
        setLatitude(latitude);
        setLongitude(longitude);
    }

    /**
     * 构造方法
     * @param longitude 初始化经度
     * @param latitude 初始化纬度
     * */
    public LatLongCoordinate(String longitude,String latitude){
        setLatitude(latitude);
        setLongitude(longitude);
    }

    /**
     * 构造方法
     * @param longitude 初始化经度
     * @param latitude 初始化纬度
     * */
    public LatLongCoordinate(Double longitude,Double latitude){
        setLatitude(latitude);
        setLongitude(longitude);
    }


    /**
     * 获取纬度
     * @return 精确的维度
     * */
    public BigDecimal getLatitude() {
        return latitude;
    }

    /**
     * 获取纬度字符串
     * @return 转为字符串类型的维度
     * */
    public String getLatitudeString() {
        try {
            return latitude.toString();
        }catch (NullPointerException nullPointerException){
            throw new LongitudeLatitudeFormatException();
        }
    }

    /**
     * 获取纬度的double类型值
     * @return 转为double类型的维度
     * */
    public Double getLatitudeDouble() {
        try {
            return latitude.doubleValue();
        }catch (NullPointerException nullPointerException){
            throw new LongitudeLatitudeFormatException();
        }
    }

    /**
     * 纬度赋值
     * @param latitude 精确的维度
     * */
    public void setLatitude(BigDecimal latitude) {
        if (latitude == null){
            throw new LongitudeLatitudeFormatException();
        }else {
            this.latitude = latitude;
        }
    }

    /**
     * 使用字符串给纬度赋值
     * @param latitudeStr 维度的字符串
     * */
    public void setLatitude(String latitudeStr) {
        try {
            this.latitude = new BigDecimal(latitudeStr);
        }catch (NullPointerException nullPointerException){
            throw new LongitudeLatitudeFormatException();
        }catch (Exception ex){
            throw new LongitudeLatitudeFormatException(latitudeStr, ex.getMessage());
        }
    }


    /**
     * 使用double类型给纬度赋值
     * @param latitudeDouble 维度的double值
     * */
    public void setLatitude(Double latitudeDouble) {
        try {
            this.latitude = new BigDecimal(latitudeDouble.toString());
        }catch (NullPointerException nullPointerException){
            throw new LongitudeLatitudeFormatException();
        }
    }


    /**
     * 获取经度
     * @return 精确的维度
     * */
    public BigDecimal getLongitude() {
        return longitude;
    }


    /**
     * 获取经度字符串
     * @return 经度字符串
     * */
    public String getLongitudeString() {
        try {
            return longitude.toString();
        }catch (NullPointerException nullPointerException){
            throw new LongitudeLatitudeFormatException();
        }

    }


    /**
     * 获取经度double类型值
     * @return 经度double类型值
     * */
    public Double getLongitudeDouble() {
        try {
            return longitude.doubleValue();
        }catch (NullPointerException nullPointerException){
            throw new LongitudeLatitudeFormatException();
        }
    }


    /**
     * 经度赋值
     * @param longitude 精确的经度
     * */
    public void setLongitude(BigDecimal longitude) {
        if (longitude == null){
            throw new LongitudeLatitudeFormatException();
        }else {
            this.longitude = longitude;
        }
    }

    /**
     * 使用字符串给经度赋值
     * @param longitudeStr 经度字符串
     * */
    public void setLongitude(String longitudeStr) {
        try {
            this.longitude = new BigDecimal(longitudeStr);
        }catch (NullPointerException nullPointerException){
            throw new LongitudeLatitudeFormatException();
        }catch (Exception ex){
            throw new LongitudeLatitudeFormatException(longitudeStr, ex.getMessage());
        }
    }

    /**
     * 使用double类型给经度赋值
     * @param longitudeStr double类型经度
     * */
    public void setLongitude(Double longitudeStr) {
        try {
            this.longitude = new BigDecimal(longitudeStr.toString());
        }catch (NullPointerException nullPointerException){
            throw new LongitudeLatitudeFormatException();
        }
    }
}
