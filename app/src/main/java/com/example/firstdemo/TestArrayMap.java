package com.example.firstdemo;

import android.util.ArrayMap;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.firstdemo.TestLinkHaskMap.test;


public class TestArrayMap {
//  static   CompositeDisposable comDisposable = new CompositeDisposable();
    public static void main(String[] args) throws Exception {

//        testArrayMap();
        test1();
    }

    private static void test1() {
        int i=0;
        int[] a=new int[0];
        int[] b=new int[0];
        System.out.println(a==b);
    }

    public static void testArrayMap() {
        ArrayMap<String,String> arrayMap=new ArrayMap<>();

    arrayMap.put("1","vaule:1");
    arrayMap.put("2","vaule:2");
    arrayMap.put("3","vaule:3");

    arrayMap.get("1");

}
}
