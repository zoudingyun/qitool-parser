package org.qitool.parser.protocol.auto.GBT32960.domain.RTData;

import org.qitool.parser.protocol.auto.GBT32960.enums.DriveMotorState;

/**
 * 驱动电机数据
 *
 * @author zoudingyun
 * 2024/5/16 9:38
 */
public class DriveMotorData {

    /**
     * 驱动电机序号
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 驱动电机顺序号，有效值范围1～253
     * */
    private int orderNumber;

    /**
     * 驱动电机状态
     * */
    private DriveMotorState motorState;


    /**
     * 驱动电机控制器温度
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～250(数值偏移量40 ℃,表示-40 ℃ ~ +210 ℃)<br>
     * 最小计量单元：1 ℃<br>
     * “0xFE”(254)表示异常，“OxFF”(255)表示无效
     * */
    private int controllerTemperature;

    /**
     * 驱动电机转速
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0 ～65531(数值偏移量20000表示 -20000 r/min～45531 r/min)<br>
     * 最小计量单元：1 r/min<br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private int speed;

    /**
     * 驱动电机转矩
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～65531 (数值偏移量20000表示 -2000 N·m～4553.1 N·m)<br>
     * 最小计量单元：0.1 N·m<br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float torque;

    /**
     * 驱动电机温度
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～250(数值偏移量40 ℃,表示-40 ℃ ~ +210 ℃)<br>
     * 最小计量单元：1 ℃<br>
     * “0xFE”(254)表示异常，“OxFF”(255)表示无效
     * */
    private int temperature;


    /**
     * 电机控制器输入电压
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～60000(表示0V～6000 V)<br>
     * 最小计量单元：0.1V<br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float controllerInputVoltage;


    /**
     * 电机控制器直流母线电流
     * <br>
     * --------------------------------------------------------------
     * <br>
     * 有效值范围：0～20000(数值偏移量1000A,表示 -1000 A ~ +1000 A)<br>
     * 最小计量单元：0.1A <br>
     * “0xFF,0xFE”(65534)表示异常，“0xFF,0xFF”(65535)表示无效
     * */
    private float controllerBusCurrentDc;


    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public DriveMotorState getMotorState() {
        return motorState;
    }

    public void setMotorState(DriveMotorState motorState) {
        this.motorState = motorState;
    }

    public int getControllerTemperature() {
        return controllerTemperature;
    }

    public void setControllerTemperature(int controllerTemperature) {
        this.controllerTemperature = controllerTemperature;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public float getTorque() {
        return torque;
    }

    public void setTorque(float torque) {
        this.torque = torque;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public float getControllerInputVoltage() {
        return controllerInputVoltage;
    }

    public void setControllerInputVoltage(float controllerInputVoltage) {
        this.controllerInputVoltage = controllerInputVoltage;
    }

    public float getControllerBusCurrentDc() {
        return controllerBusCurrentDc;
    }

    public void setControllerBusCurrentDc(float controllerBusCurrentDc) {
        this.controllerBusCurrentDc = controllerBusCurrentDc;
    }
}
