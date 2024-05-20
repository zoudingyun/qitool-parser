package org.qitool.parser.protocol.auto.GBT32960.domain.dto;

/**
 * 已检查过的数据
 *
 * @author zoudingyun
 * 2024/5/16 17:48
 */
public class CheckedValue {

    /**
     * 数据是否有效
     * */
    private boolean effective;

    /**
     * 转化后的数据
     * */
    private Object value;

    /**
     * 执行完本次分析后剩余的数据数组
     * */
    private byte[] dataBytes;


    public boolean getEffective() {
        return effective;
    }

    public void setEffective(boolean effective) {
        this.effective = effective;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public byte[] getDataBytes() {
        return dataBytes;
    }

    public void setDataBytes(byte[] dataBytes) {
        this.dataBytes = dataBytes;
    }
}
