package com.example.snmpManager.util;

import java.util.regex.Pattern;

public class AddressValidation {
    public static Boolean isValidIpv4(String address) {
        // Regex expression for validating IPv4
        String regex = "(([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])\\.){3}([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])";
        Pattern p = Pattern.compile(regex);

        if (p.matcher(address).matches())
            return true;
        return false;
    }
}