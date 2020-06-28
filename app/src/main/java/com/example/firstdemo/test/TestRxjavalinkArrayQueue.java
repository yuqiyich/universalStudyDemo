package com.example.firstdemo.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.schedulers.Schedulers;


public class TestRxjavalinkArrayQueue {
//  static   CompositeDisposable comDisposable = new CompositeDisposable();
    public static void main(String[] args) throws Exception {

        testRxjava2SpscArrayQueue();
    }
public static void testRxjava2SpscArrayQueue(){
    SpscLinkedArrayQueue<String> sq=new SpscLinkedArrayQueue<>(128);
    for (int i = 0; i < 500; i++) {
        System.out.println("i:"+i+"offer value:"+sq.offer(i+""));
    }
    System.out.println("size:"+sq.size());
    for (int i = 0; i < 19; i++) {
        System.out.println("i:"+i+"value:"+sq.poll());
    }

    }

}
