package com.example.firstdemo.test.secret;

import java.util.Map;

/**
 * Created by wwt on 2019/3/12 12:10
 * 944799421@qq.com
 */
public class HttpUtil {
    //RSA公钥
    public static final String CLIENT_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmyt+/zub50DN8dl4HzOB9tLlWnU2jorj1ybj987TSDNggGuHju0EOa4iInCk8o3YuiMQ7aq/0dPNRJAJcrnlXRciK/WbaxeK8QGVUyLu1C2P9Ylmjwj9DFAGk+9Hm+fwIZLXmQvSDvj+Ndr1DCJJCbQCmGIZp8beXVxTS4jm/YWMJdMl/cR4ynljdixg910nvADEE302dIzAOA9xYrfAS5u1vshDH0QgXjUvC0qK/mjrplRgwcxJT0+74r0XO9OAVsg42MOxAfJ74IWNwBKoeKG7nkRSK1TLzqT0cs1f0TzkaLl96gJvfk/iTHANibt3hTMYRIfQ7eksUyVmTEDYswIDAQAB";
    public static final String APP_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAyP5ZqXrs9p3rUDNewzt+vk+R1MBpcJkll97qSOebH8BdaNqBzNpxVjrkw2UUM4D3IFRLWAMmeitg83ZZhWIZOCbuFk/yRmYcKOIIlA0yreDlYtmcW7K3ASo8dYD+MHQsbGeOxu+mgvCKqpv5LIA5yCHePunasHN8hhf7nXDV8nYuOMp0P8tRJR6Sgu1RXrQrq7RCFOpXNlLkdLiV3Tly1Did1yQxVU1rA3l4WJoUSTD9bileSyL3PztbWlQ1P+L7wvG7OKgLLQPz5vL2WUmpk1DOgbYBgGrWsupgxUKGP6DSd9IGd7S48XsII2D/lRuA3RS1rwHPOXMYQ1oADGoGgQIDAQAB";//司机端测试环境
    //    public static final String APP_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAijg91MRnHPOWb8PaYsOQGjwnpHhjkxMgfKHdk6bvovuhWnlxp/rhCvW9TE5S1Nko3cwwjxs0mmLloDoCAOQZjj7gzdCU14wsOLLuDyV11blolZ/dMPqxOovL7BuT0fevhIFeRxhI4toWHzLUbdjjS1g39XBaCXYqcenapa6A6pg+hvR4Wruvz7DRTK2IH5YL6mbh1O0hBpEGgiJt6VRkCQZ19pN1RvMxIBTzvXnxZwFpNdC8ES6zgl5JyXjxHyFRdgHOS76C8OyiLHTHt7+vZELUiOWvNSvenmT5DNlajqCIZAvVJVgbKRDVu3g74CwC+agTC9OYKIxnD/9KeZLwGwIDAQAB";//司机端正式环境
    public static final String HEADER_SIGN = "sign";
    public static String getEncryptData(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        StringBuilder mapString = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            mapString.append("&")
                    .append(entry.getKey())
                    .append("=")
                    .append(entry.getValue());
        }
        return mapString.toString().replaceFirst("&", "");
    }
}
