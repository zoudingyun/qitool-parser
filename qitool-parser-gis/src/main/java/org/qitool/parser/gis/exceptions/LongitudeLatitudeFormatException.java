package org.qitool.parser.gis.exceptions;

/**
 * 经纬度转换格式异常
 * @author zdy
 * 2024年4月20日
 * */
public class LongitudeLatitudeFormatException extends RuntimeException{

    private static final String msg = "An error occurred while converting latitude and longitude data formats. Please check the data format.";
    private static final String prompt = "The problematic value is '%s', its type is '%s', and the error message is '%s'.";

    private static final String nullPrompt = "The data is empty and cannot be converted.";

    /**
    * 空数据
    * */
    public LongitudeLatitudeFormatException() {
        super(nullPrompt);
    }

    /**
     * 数据转换报错
     * @param value 错误值
     * @param errorMsg 错误信息
     * */
    public LongitudeLatitudeFormatException(Object value,String errorMsg) {
        super(msg+String.format(prompt,value,value.getClass(),errorMsg));
    }

}
