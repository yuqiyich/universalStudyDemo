//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.example.firstdemo.test;

public class Base64 {
    public Base64() {
    }

    public static String encode(byte[] data) {
        return android.util.Base64.encodeToString(data, 0);
    }

    public static byte[] encode(String data) {
        return android.util.Base64.encode(data.getBytes(), 0);
    }

    public static byte[] decode(String str) {
        return android.util.Base64.decode(str, 0);
    }
}
