package com.onebody.wifi_car_client.webiopi_util;

import com.trouch.webiopi.client.PiClient;
import com.trouch.webiopi.client.PiHttpClient;
import com.trouch.webiopi.client.devices.digital.NativeGPIO;

/**
 * 描述：
 *
 * @author: fengcj
 * @Date: 14-8-4 上午12:24
 */

public class WebIOPI_Util {
    public static String webiopi_server = "192.168.1.102";
    public static String webiopi_username = "webiopi";
    public static String webiopi_password = "webiopi";
    private static NativeGPIO gpio = null;

    public static NativeGPIO getGPIO(String host, String userName, String password) {
        PiClient client = new PiHttpClient(host, PiHttpClient.DEFAULT_PORT);
        client.setCredentials(userName, password);
        if (gpio == null) {
            gpio = new NativeGPIO(client);
        }
        webiopi_server = host;
        webiopi_username = userName;
        webiopi_password = password;
        return gpio;
    }

    public static NativeGPIO getDefaultGPIO() {
        return getGPIO(webiopi_server, webiopi_username, webiopi_password);
    }
}
