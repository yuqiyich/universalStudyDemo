package com.example.firstdemo;

import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机号验证工具
 */
public class PhoneValidator {
    private static final String MOBILE_REG = "^((1[0-9]))\\d{9}$";
    //	private static final String MOBILE_REG = "^1(3[0-9]|4[57]|5[0-35-9]|7[0-9]|8[0-9]|70)\\d{8}$";
    // private static final String PHONE_REGION_REG =
    // "^(0[1-9][0-9]{1,2})([0-9]{7,8})$";
    private static final String PHONE_400 = "^400([0-9]{7})$";
    private static final String ORDER_NUMBER = "C{1}[J,N]{1}[0-9]{12}";
    /**
     * 正则表达式：验证邮箱
     */
    public static final String REGEX_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
//	public static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$\n";


    public static boolean validateMobile(String phone) {
        Pattern pattern = Pattern.compile(MOBILE_REG);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    public static boolean validateEmail(String mail) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    public static boolean validPhone400(String phone) {
        Pattern pattern = Pattern.compile(PHONE_400);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }


    public static boolean ordernumber(String orderNumber) {
        Pattern pattern = Pattern.compile(ORDER_NUMBER);
        Matcher matcher = pattern.matcher(orderNumber);
        return matcher.matches();
    }

    public static boolean validatePhoneOrMobile(String phone) {
        return validateMobile(phone);
    }

    public static void main(String[] args) {
        System.out.println("15919603033 is:"+validateMobile("15919603033"));
        System.out.println("159 1960 3033 is:"+validateMobile("159 1960 3033"));

       String str="asdfasdf-159300*!";
        System.out.println(str);
        //str = str.replaceAll(" ", "");//去除空格
        Pattern p = Pattern.compile("[`\\-~☆★!@#$%^&*()+=|{}':;,\\[\\]》·.<>/?~！@#￥%……（）——+|{}【】‘；：”“’。，、？]");//去除特殊字符
        Matcher m = p.matcher(str);
        str = m.replaceAll("").trim().replace(" ", "").replace("\\", "");//将匹配的特殊字符转变为空
        System.out.println(str);

    }
}
