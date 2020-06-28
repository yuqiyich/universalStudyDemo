package com.example.firstdemo.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class TestDowhile {
//  static   CompositeDisposable comDisposable = new CompositeDisposable();
    public static void main(String[] args) throws Exception {
        int a=0,c=0;
        do{
          --c;
          a=a-1;
        }while(a>0);
        System.out.println(c);
    }


}
