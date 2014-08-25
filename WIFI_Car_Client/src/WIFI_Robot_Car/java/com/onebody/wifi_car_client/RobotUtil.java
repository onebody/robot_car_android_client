package com.onebody.wifi_car_client;

/**
 * Created by wd7 on 14-8-25.
 */
public class RobotUtil {
    public static void process(String op) {
        String s = op.replace("\n", "");
        SocketUtil.exec(s);
//        HttpRequest.sendGet("action=" + op);
    }
}
