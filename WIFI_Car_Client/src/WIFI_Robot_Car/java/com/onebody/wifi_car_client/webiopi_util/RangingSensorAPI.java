package com.onebody.wifi_car_client.webiopi_util;

import com.trouch.webiopi.client.devices.digital.NativeGPIO;

import java.math.BigDecimal;

/**
 * 描述：利用 HC_SR04超声波感应器 测距
 *
 * @author: fengcj
 * @Date: 14-8-3 下午11:51
 */

public class RangingSensorAPI {
    private static int beforeTriger = 23;
    private static int beforeEcho = 24;

    private static int afterTriger = 3;
    private static int afterEcho = 4;

    public static double beforeDistance() {
        NativeGPIO gpio = WebIOPI_Util.getDefaultGPIO();
        String args = RangingSensorAPI.beforeTriger + "," + RangingSensorAPI.beforeEcho;
        String res = gpio.callMacro("rangingSensor_Distance", args);
        BigDecimal bg = new BigDecimal(Double.parseDouble(res));
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static double afterDistance() {
        NativeGPIO gpio = WebIOPI_Util.getDefaultGPIO();
        String args = RangingSensorAPI.afterTriger + "," + RangingSensorAPI.afterEcho;
        String res = gpio.callMacro("rangingSensor_Distance", args);
        BigDecimal bg = new BigDecimal(Double.parseDouble(res));
        double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return f1;
    }

    public static void main(String args[]) {
        double afterDistance = RangingSensorAPI.afterDistance();
        System.out.print("后方距离 :=" + afterDistance + "\n");

        double beforeDistance = RangingSensorAPI.beforeDistance();
        System.out.print("前方距离 :=" + beforeDistance);
    }

}
