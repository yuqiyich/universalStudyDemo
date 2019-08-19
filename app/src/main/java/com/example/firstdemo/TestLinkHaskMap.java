package com.example.firstdemo;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import java.util.*;


public class TestLinkHaskMap {
//  static   CompositeDisposable comDisposable = new CompositeDisposable();
    public static void main(String[] args) throws Exception {

        test();
    }
public static void test(){
    // 第三个参数用于指定accessOrder值
    LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(16, 0.75f, true);
    linkedHashMap.put("name1", "josan1");
    linkedHashMap.put("name2", "josan2");
    linkedHashMap.put("name3", "josan3");
    System.out.println("开始时顺序：");
    Set<Map.Entry<String, String>> set = linkedHashMap.entrySet();
    Iterator<Map.Entry<String, String>> iterator = set.iterator();
    while(iterator.hasNext()) {
        Map.Entry entry = iterator.next();
        String key = (String) entry.getKey();
        String value = (String) entry.getValue();
        System.out.println("key:" + key + ",value:" + value);
    }
    System.out.println("通过get方法，导致key为name1对应的Entry到表尾");
    linkedHashMap.get("name1");
    Set<Map.Entry<String, String>> set2 = linkedHashMap.entrySet();
    Iterator<Map.Entry<String, String>> iterator2 = set2.iterator();
    while(iterator2.hasNext()) {
        Map.Entry entry = iterator2.next();
        String key = (String) entry.getKey();
        String value = (String) entry.getValue();
        System.out.println("key:" + key + ",value:" + value);
    }
}


}
