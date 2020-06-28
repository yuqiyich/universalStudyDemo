package com.example.firstdemo.test;

import com.alibaba.fastjson.JSON;
import com.example.firstdemo.A;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;

/**
 * @Description:[一句话说明类的作用]
 * @Author: [yuqi]
 * @CreateDate: [2020/6/26 1:14 PM]
 * @email: [yichitgo@gmail.com]
 */
public class TestFastjson
{
    public static void main(String[] args) {
        LatLng latLng= new LatLng(9.99,2.9900);
//        A a =new A();
//        a.a=latLng;
        System.out.println("fast json 序列化:"+JSON.toJSONString(latLng));
       String json="{\"altitude\":0,\"latitude\":9.99,\"longitude\":2.99}";
        System.out.println("fast json 反序列化:"+JSON.parseObject(json, LatLng.class));
    }
}
