package org.qitool.parser.gis.domain;

import org.junit.jupiter.api.Test;
import org.qitool.parser.gis.exceptions.LongitudeLatitudeFormatException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 经纬度成员方法测试类
 * @author zdy
 * 2024年4月20日
 * */
public class LatLongCoordinateTest {

    /**
     * 初始化经纬度测试
     * */
    @Test
    void newLatLongCoordinateTest(){

        LatLongCoordinate latLongCoordinate;


        latLongCoordinate = new LatLongCoordinate(new BigDecimal("1.0"), new BigDecimal("2.0"));
        assertEquals("1.0",latLongCoordinate.getLongitudeString());
        assertEquals("2.0",latLongCoordinate.getLatitudeString());
        assertEquals(1.0,latLongCoordinate.getLongitudeDouble());
        assertEquals(2.0,latLongCoordinate.getLatitudeDouble());
        assertEquals(new BigDecimal("1.0"),latLongCoordinate.getLongitude());
        assertEquals( new BigDecimal("2.0"),latLongCoordinate.getLatitude());

        latLongCoordinate = new LatLongCoordinate("1.1", "2.1");
        assertEquals("1.1",latLongCoordinate.getLongitudeString());
        assertEquals("2.1",latLongCoordinate.getLatitudeString());
        assertEquals(1.1,latLongCoordinate.getLongitudeDouble());
        assertEquals(2.1,latLongCoordinate.getLatitudeDouble());
        assertEquals(new BigDecimal("1.1"),latLongCoordinate.getLongitude());
        assertEquals( new BigDecimal("2.1"),latLongCoordinate.getLatitude());


        latLongCoordinate = new LatLongCoordinate(1.2, 2.2);
        assertEquals(((Double)1.2).toString(),latLongCoordinate.getLongitudeString());
        assertEquals(((Double)2.2).toString(),latLongCoordinate.getLatitudeString());
        assertEquals(1.2,latLongCoordinate.getLongitudeDouble());
        assertEquals(2.2,latLongCoordinate.getLatitudeDouble());
        assertEquals(new BigDecimal("1.2"),latLongCoordinate.getLongitude());
        assertEquals( new BigDecimal("2.2"),latLongCoordinate.getLatitude());

    }

    /**
     * 异常测试
     * */
    @Test
    void exceptionTest(){

        // 测试空类型转换
        try {
            new LatLongCoordinate().getLatitudeString();
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }

        try {
            new LatLongCoordinate().getLongitudeString();
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }

        try {
            new LatLongCoordinate().getLatitudeDouble();
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }

        try {
            new LatLongCoordinate().getLongitudeDouble();
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }

        // 空字符串赋值测试
        try {
            new LatLongCoordinate("","1.1");
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("An error occurred while converting latitude and longitude data formats. Please check the data format.The problematic value is '', its type is 'class java.lang.String', and the error message is 'null'."
                    ,longitudeLatitudeFormatException.getMessage());
        }
        try {
            new LatLongCoordinate("1.1","");
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("An error occurred while converting latitude and longitude data formats. Please check the data format.The problematic value is '', its type is 'class java.lang.String', and the error message is 'null'."
                    ,longitudeLatitudeFormatException.getMessage());
        }

        // 空数据赋值测试
        try {
            BigDecimal a = null;
            new LatLongCoordinate(a,new BigDecimal(0));
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }
        try {
            BigDecimal a = null;
            new LatLongCoordinate(new BigDecimal(0),a);
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }

        // 空double数据赋值测试
        try {
            Double a = null;
            new LatLongCoordinate((double)0,a);
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }
        try {
            Double a = null;
            new LatLongCoordinate(a,(double)0);
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }

        // null字符串赋值测试
        try {
            String a = null;
            new LatLongCoordinate("0",a);
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }
        try {
            String a = null;
            new LatLongCoordinate(a,"0");
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("The data is empty and cannot be converted."
                    ,longitudeLatitudeFormatException.getMessage());
        }

        // 非数值字符串赋值测试
        try {
            String a = "哈哈哈";
            new LatLongCoordinate(a,a);
        }catch (LongitudeLatitudeFormatException longitudeLatitudeFormatException){
            assertEquals("An error occurred while converting latitude and longitude data formats. Please check the data format.The problematic value is '哈哈哈', its type is 'class java.lang.String', and the error message is 'null'."
                    ,longitudeLatitudeFormatException.getMessage());
        }


    }


}
