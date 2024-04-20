package org.qitool.parser.gis;

import org.junit.jupiter.api.Test;
import org.qitool.parser.gis.domain.LatLongCoordinate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 坐标工具测试类
 * @author zdy
 * 2024年4月21日
 * */
public class CoordinateUtilTest {

    @Test
    void testGcj02(){

        LatLongCoordinate latLongCoordinate = CoordinateUtil.wgs84ToGcj02(new BigDecimal("106.755684"),new BigDecimal("29.662899"));
        assertEquals("106.759690740586638567",latLongCoordinate.getLongitudeString());
        assertEquals("29.6602192867631649235",latLongCoordinate.getLatitudeString());

        latLongCoordinate = CoordinateUtil.gcj02ToWgs84(new BigDecimal("106.759690740586638567"),new BigDecimal("29.6602192867631649235"));
        assertEquals("106.755682695313280317",latLongCoordinate.getLongitudeString());
        assertEquals("29.6629007372371081551",latLongCoordinate.getLatitudeString());
    }

}
