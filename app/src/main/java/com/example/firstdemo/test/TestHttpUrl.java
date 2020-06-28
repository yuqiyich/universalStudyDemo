package com.example.firstdemo.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.HttpUrl;


public class TestHttpUrl{
    //  static   CompositeDisposable comDisposable = new CompositeDisposable();
    public static void main(String[] args) throws Exception {


        HttpUrl url = HttpUrl.parse("https://www.baidu.com/a/b/a/b/" );
        System.out.println("old url:"+url.toString());
        HttpUrl newUrl= url.resolve("/c/d/e");
        System.out.println("前面带斜杠/c/d/e的newUrl:"+newUrl.toString());

        HttpUrl newUrl2= url.resolve("c/d/e");
        System.out.println("前面不带斜杠c/d/e的newUrl2:"+newUrl2.toString());
    }

    public static void testRxjava2() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                System.out.println("ObservableOnSubscribe.subscribe thread : " + Thread.currentThread().getName());
                observableEmitter.onNext("1");
                observableEmitter.onComplete();
            }
        })//顶层Observable
                .observeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())//第一次subscribeOn
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {
                        System.out.println("onSubscribe  thread : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println("onNext  thread : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        System.out.println("onError  thread : " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("onComplete  thread : " + Thread.currentThread().getName());
                    }
                });
    }

    public static void testRxjava() {
        Observable<String> observable = Observable
                .create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<String> emitter) throws
                            Exception {
                        System.out.println("subscribe:");
                        emitter.onNext("hello");
                    }
                })
                /*.map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        System.out.println("apply:"+s);
                        return s+"map";
                    }
                })*/
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread());
        System.out.println("订阅");
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                System.out.println("onSubscribe：" + d.toString());
            }

            @Override
            public void onNext(String s) {
                System.out.println("onNext:" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

}
