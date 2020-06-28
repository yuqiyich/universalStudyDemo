package com.example.firstdemo.test;

import com.example.firstdemo.A;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/**
 * @Description:[一句话说明类的作用]
 * @Author: [yuqi]
 * @CreateDate: [2020/4/7 3:31 PM]
 * @email: [yichitgo@gmail.com]
 */
public class TestGson {

    public static void main(String[] args) {
//        print();
        System.out.println(test(1,3));
        System.out.println(test(1,1));
        System.out.println(test(2,2));
        System.out.println(test(1,2));

    }

    private static boolean test(int a, int b) {
        if (a==1^b==1){
            return true;
        }else {
            return false;
        }
    }

    public static void print() {

        LatLng latLng= new LatLng(9.99,2.9900);
        A a =new A();
        a.a=latLng;
        System.out.println("data:"+new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return f.getName().contains("sClassJsonMap")
                        || f.getName().contains("mJsonFields")
                        || f.getName().contains("mFieldDeserializer");
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        }).create().toJson(a));
    }
}
