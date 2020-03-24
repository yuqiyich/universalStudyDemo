package com.example.firstdemo.secret;


/**
 * Created by wwt on 2019/3/12 10:57
 * 944799421@qq.com
 */
public class Base64 {

    public static String encode(byte[] data) {
        return android.util.Base64.encodeToString(data, android.util.Base64.DEFAULT);
    }


    public static byte[] encode(String data) {
        return android.util.Base64.encode(data.getBytes(), android.util.Base64.DEFAULT);
    }

    public static byte[] decode(String str) {
        return android.util.Base64.decode(str, android.util.Base64.DEFAULT);
    }
}
