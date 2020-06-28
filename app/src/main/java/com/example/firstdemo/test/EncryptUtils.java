package com.example.firstdemo.test;

import android.text.TextUtils;


import com.example.firstdemo.test.secret.AESUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;

/**
 * utils about encrypt.
 *
 * @author mikegao
 * @date 2019/4/18
 */
public class EncryptUtils {

    /**
     * 生成md5摘要字符串
     *
     * @param string 原始字符串
     * @return String
     */
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        } else {
            MessageDigest md5;

            try {
                md5 = MessageDigest.getInstance("MD5");
                byte[] bytes = md5.digest(string.getBytes());
                String result = "";
                byte[] var4 = bytes;
                int var5 = bytes.length;

                for (int var6 = 0; var6 < var5; ++var6) {
                    byte b = var4[var6];
                    String temp = Integer.toHexString(b & 255);
                    if (temp.length() == 1) {
                        temp = "0" + temp;
                    }

                    result = result + temp;
                }

                return result;
            } catch (NoSuchAlgorithmException var9) {
                var9.printStackTrace();
                return "";
            }
        }
    }

    /**
     * AES对称加密
     *
     * @param data 原始数据
     */
    public static String encrypt(String data) {
        if (!TextUtils.isEmpty(data)) {
            return AESUtils.des(data, getFixedAesKey(), Cipher.ENCRYPT_MODE);
        }
        return data;
    }

    /**
     * AES对称解密
     *
     * @param data 原始数据
     */
    public static String decrypt(String data) {
        if (!TextUtils.isEmpty(data)) {
            return AESUtils.des(data, getFixedAesKey(), Cipher.DECRYPT_MODE);
        }
        return data;
    }

    private static String getFixedAesKey() {
        return EncryptUtils.md5("357594090368649").substring(8, 24);
    }
}
