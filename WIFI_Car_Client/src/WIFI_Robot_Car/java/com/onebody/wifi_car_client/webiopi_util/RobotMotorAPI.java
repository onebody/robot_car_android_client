package com.onebody.wifi_car_client.webiopi_util;

import com.trouch.webiopi.client.devices.digital.NativeGPIO;

/**
 * 描述：前后直流电机控制
 * TODO 后面加入 电机PWM调速控制
 *
 * @author: fengcj
 * @Date: 14-8-2 上午12:12
 */

public class RobotMotorAPI {
    private int IN1 = 22; //GPIO 3  IN1
    private int IN2 = 27; //GPIO 2  IN2
    private int ENA = 7;// H-Bridge 1,2EN

    private int IN3 = 17; //GPIO 0  IN3
    private int IN4 = 18; //GPIO 1  IN4
    private int ENB = 9;// H-Bridge 3,4EN

    private boolean init_flag = false;

    private NativeGPIO gpio = WebIOPI_Util.getDefaultGPIO();

    private void init() {
        if (init_flag == false) {
            String params = IN1 + "," + IN2 + "," + ENA + "," + IN3 + "," + IN4 + "," + ENB;
            gpio.callMacro("robotMotor_setup", params);
            init_flag = true;
        }
    }

    public void turn_left_forward() {
        init();
        gpio.callMacro("robotMotor_control", "turn_left_forward");
    }

    public void turn_right_forward() {
        init();
        gpio.callMacro("robotMotor_control", "turn_right_forward");
    }

    public void forward() {
        init();
        gpio.callMacro("robotMotor_control", "forward");
    }

    public void backward() {
        init();
        gpio.callMacro("robotMotor_control", "backward");
    }

    public void stop() {
        gpio.callMacro("robotMotor_control", "stop");
    }

    public void auto() {
        gpio.callMacro("robotMotor_control", "auto");
    }

    public static void main(String args[]) {
        try {
            long delay = 500;

            RobotMotorAPI motor = new RobotMotorAPI();
//            System.out.print("forward >>>>\n");
//            motor.forward();
//            Thread.sleep(delay);

            testAll(delay, motor);

            System.out.print("stop >>>>\n");
            motor.stop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testAll(long delay, RobotMotorAPI motor) throws InterruptedException {
        System.out.print("forward >>>>\n");
        motor.forward();
        Thread.sleep(delay);

        System.out.print("forward left >>>>\n");
        motor.turn_left_forward();

        Thread.sleep(delay);

        System.out.print("forward right >>>>\n");
        motor.turn_right_forward();
        Thread.sleep(delay);

        System.out.print("backward >>>>\n");
        motor.backward();
    }
}
