package com.example.firstdemo.test;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description:[日历]
 * @Author: [yuqi]
 * @CreateDate: [2020/4/21 10:12 AM]
 * @email: [yichitgo@gmail.com]
 */
public class TestCalc {
    public static void main(String[] args) {
        System.out.println("星期2："+getWeek(1587441600000l));
        System.out.println("星期3："+getWeek(1587528000000l));
        System.out.println("星期4："+getWeek(1587614400000L));
        System.out.println("星期5："+getWeek(1587700800000L));
        System.out.println("星期6："+getWeek(1587787200000L));
        System.out.println("星期7："+getWeek(1587873600000L));
        System.out.println("星期1："+getWeek(1587960000000L));
        System.out.println("星期2："+getWeek(1588046400000L));
    }


    public static int getWeek(long millis){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(millis));
      return   calendar.get(Calendar.DAY_OF_WEEK);
    }
}
