package com.example.firstdemo.test;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * IM消息通知给各个订阅者
 *
 * @author lukeyan
 * @date 2019/4/18
 */
public class MessageBus {

    private static final String TAG = "MessageBus";
    private final Subject<Object> mSubject;

    private MessageBus() {
        mSubject = PublishSubject.create().toSerialized();
    }

    static class SingleInstance {
        static final MessageBus INSTANCE = new MessageBus();
    }


    public static MessageBus getInstance() {
        return SingleInstance.INSTANCE;
    }

    public void sendData(Object data) {
        Log.i(TAG, "发送消息sendData" + data.toString());
        mSubject.onNext(data);
    }


    public <T> Observable<T> toObservable(Class<T> dataClass) {
        return mSubject.ofType(dataClass).map(new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                Log.i(TAG, "发送消息toObservable" + t.getClass().getSimpleName());
                return t;
            }
        });
    }


}
