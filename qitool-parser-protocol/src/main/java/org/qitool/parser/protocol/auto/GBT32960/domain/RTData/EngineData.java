package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import org.qitool.parser.protocol.auto.GBT32960.enums.EngineState;

/**
 * 发动机数据
 *
 * @author zoudingyun
 * 2024/5/16 9:38
 */
public class EngineData {

    /**
     * 发动机状态
     * */
    private EngineState engineState;

    /**
     * 曲轴转速
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效范围：0～60000(表示0r/min～60000 r/min) <br>
     * 最小计量单元：1r/min <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private int speed;

    /**
     * 燃料消耗率
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～60000(表示0 L/100 km ~ 600 L/100 km)<br>
     * 最小计量单元： 0.01 L/100 km <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float fuelConsumptionRate;

    public EngineState getEngineState() {
        return engineState;
    }

    public void setEngineState(EngineState engineState) {
        this.engineState = engineState;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getFuelConsumptionRate() {
        return fuelConsumptionRate;
    }

    public void setFuelConsumptionRate(float fuelConsumptionRate) {
        this.fuelConsumptionRate = fuelConsumptionRate;
    }
}
