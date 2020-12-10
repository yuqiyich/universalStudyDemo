package com.example.firstdemo.test;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.operators.observable.ObservableCreate;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;

/**
 * @Description:[一句话说明类的作用]
 * @Author: [yuqi]
 * @CreateDate: [2020/7/3 11:21 AM]
 * @email: [yichitgo@gmail.com]
 */
public class RxTest {
    // 用于线程直接切换
    private ObservableTransformer mObservableTransformer;
    private BehaviorSubject<Boolean> mDisposeSubject;
    public Disposable mDisposeTest;

    private ObservableSource<Boolean> getDisposeSubject() {
        if (mDisposeSubject == null) {
            mDisposeSubject = BehaviorSubject.create();
        }
        return mDisposeSubject;
    }

    /**
     * 用于直接设置线程切换ObservableTransformer
     *
     * @param <T>
     * @return
     */
    public final <T> ObservableTransformer<T, T> applySchedulers() {
        if (mObservableTransformer == null) {
            mObservableTransformer = (ObservableTransformer<T, T>) upstream -> upstream.takeUntil(getDisposeSubject())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
        return (ObservableTransformer<T, T>) mObservableTransformer;
    }

    public  void testRxjavaTakeUtil() {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            System.out.println("######emitter  thread : string_result:" + Thread.currentThread().getName());
            emitter.onNext(12312);
            emitter.onComplete();
        }).compose(applySchedulers())
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        System.out.println("######onSubscribe  thread-------:" + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(Integer integer) {
                        System.out.println("######onNext  thread : string_result:" +integer+ Thread.currentThread().getName());

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("######onError  thread : string_result:" + Thread.currentThread().getName());

                    }

                    @Override
                    public void onComplete() {
                        System.out.println("######onComplete  thread : string_result:" + Thread.currentThread().getName());

                    }
                });
    }

    /**
     * dispose 方法使用的线程如果是发射源的工作线程，则调用线程池的shotdown(不立马杀死所有的发射源的task，等待task完成之后关闭线程池)
     *        如果调用dispose的是在其他线程则调用线程池的shotdownNow（立马杀死当前的工作的task，并且关闭线程池）
     *
     */
    public  void testRxDispose(){
       Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                for (int i = 0; i < 10; i++) {
                    System.out.println("emitter i:"+i);
//                    Thread.currentThread().sleep(100);
                    emitter.onNext(String.valueOf(i));
                    if (i==2){
                        if (mDisposeTest!=null&&!mDisposeTest.isDisposed()){
                            System.out.println("mDisposeTest dispose :"+i);
                            mDisposeTest.dispose();
                        }
                    }
                }
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                if (mDisposeTest!=null&&!mDisposeTest.isDisposed()){
                    mDisposeTest.dispose();
                }
                mDisposeTest= d;
                System.out.println("Rxtest-----onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("----onNext i:"+s);
            }

            @Override
            public void onError(Throwable e) {
                System.out.println("-----onError"+e.getMessage());

            }

            @Override
            public void onComplete() {
                System.out.println("-----onComplete");
            }
        });
    }
}
