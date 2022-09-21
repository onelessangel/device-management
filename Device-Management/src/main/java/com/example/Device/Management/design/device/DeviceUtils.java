package com.example.Device.Management.design.device;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeviceUtils {
    private static final String REGEX = "^([0-9A-Fa-f]{2}[:]){5}([0-9A-Fa-f]{2})$";

    public static boolean isMacAddressValid(String macAddr) {
        Pattern pattern = Pattern.compile(REGEX);

        if (macAddr == null) {
            return false;
        }

        Matcher matcher = pattern.matcher(macAddr);
        return matcher.matches();
    }

    public static boolean isSerialValid(String serial) {
        return serial.length() < 150;
    }
}
