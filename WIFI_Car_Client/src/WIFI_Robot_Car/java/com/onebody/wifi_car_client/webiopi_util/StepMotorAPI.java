package com.onebody.wifi_car_client.webiopi_util;

import com.trouch.webiopi.client.devices.digital.NativeGPIO;

import java.util.Scanner;

/**
 * 描述：步进电机控制，控制摄像头云台转动
 *
 * @author: fengcj
 * @Date: 14-8-3 下午11:51
 */
public class StepMotorAPI {
    private int coil_A_pin = 8;
    private int coil_B_pin = 11;
    private int coil_C_pin = 25;
    private int coil_D_pin = 7;
    private double delay = 0.01;
    private int steps = 45;


    private NativeGPIO gpio = WebIOPI_Util.getDefaultGPIO();

    public void left() {
        String params = steps + ",0," + delay;
        gpio.callMacro("webcamStepMotor_turnWebcam", params);
    }

    public void right() {
        String params = steps + ",1," + delay;
        gpio.callMacro("webcamStepMotor_turnWebcam", params);
    }

    public void setup(int w1, int w2, int w3, int w4, double delay, int steps) {
        coil_A_pin = w1;
        coil_B_pin = w2;
        coil_C_pin = w3;
        coil_D_pin = w4;
        delay = delay;
        steps = steps;

        String params = coil_A_pin + "," + coil_B_pin + "," + coil_C_pin + "," + coil_D_pin;

        gpio.callMacro("webcamStepMotor_setup", params);
    }

    public static void main(String args[]) {
        StepMotorAPI stepMotorAPI = new StepMotorAPI();
        System.out.print("请输入::::\n");
        Scanner sc = new Scanner(System.in);

        stepMotorAPI.setup(8, 11, 25, 7, 0.01, 20);

        while (true) {
            String inStr = sc.nextLine();
            try {
                if ("r".equalsIgnoreCase(inStr)) {
                    System.out.print("向右转 >>\n");
                    stepMotorAPI.right();
                } else if ("l".equalsIgnoreCase(inStr)) {
                    System.out.print("向左转 \n");
                    stepMotorAPI.left();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
