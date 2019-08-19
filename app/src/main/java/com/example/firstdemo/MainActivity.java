package com.example.firstdemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    IBookManager mIBookManager;
    private ServiceConnection mServiceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
             mIBookManager= IBookManager.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
    Handler h=new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv=findViewById(R.id.tv);
        Intent intentService = new Intent();
        intentService.setAction("com.example.firstdemo.MyService");
        intentService.setPackage(getPackageName());
        intentService.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.bindService(intentService, mServiceConnection, BIND_AUTO_CREATE);
        getBaseContext();
        getApplicationContext();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inMutable=false;
        options.inSampleSize=2;
        options.inJustDecodeBounds=true;
//        startActivity(null);
//        SystemManager.getService("name");
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mIBookManager!=null){
                    try {
                        mIBookManager.addBook(new Book(19,"aaaaa"));
                        Toast.makeText(getApplicationContext(),mIBookManager.getBookList().size()+""+Thread.currentThread().getName(),Toast.LENGTH_SHORT).show();
                        Log.d("yich","客户端的线程：processs："+android.os.Process.myPid( )+"Threadname："+Thread.currentThread().getName()+";Thread.currentThread():"+Thread.currentThread());
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
//                TestArrayMap.testArrayMap();
//                testRxjava2();
//                testRxBusSendEvent();
            }
        });
        Handler had=new Handler(){};
        RxBus.getInstance().toObservable(RxEventData.class).subscribe(new Observer<RxEventData>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(RxEventData rxEventData) {
                System.out.println("RxEventData data send"+ Thread.currentThread().getName());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        Toast.makeText(getApplicationContext(),"绑定了服务", Toast.LENGTH_SHORT).show();
    }

    private void testRxBusSendEvent() {
        RxBus.getInstance().post(new RxEventData());
    }


    /**
     * rxjava 的 Observable.create
     */
    public static void testRxjava2(){
        System.out.println("testRxjava2 --->start");
        Observable source= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                System.out.println("ObservableOnSubscribe.subscribe thread : " + Thread.currentThread().getName());
                for (int i = 0; i <2 ; i++) {
                    observableEmitter.onNext("observableEmitter--value:"+i);
                }

                observableEmitter.onComplete();
                observableEmitter.onNext("observableEmitter--value:end");
            }
        })//顶层Observable
                .subscribeOn(Schedulers.io())//第一次subscribeOn
                .observeOn(Schedulers.newThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        System.out.println("map---->  thread : " + Thread.currentThread().getName()+s);
                        return s+"w";
                    }
                })
                .flatMap(new Function<String, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(String s) throws Exception {
                        return Observable.just("s:next");
                    }
                })
                .observeOn(Schedulers.io());
        for (int i = 0; i <2 ; i++) {
            source.subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable disposable) {
                    System.out.println("onSubscribe  thread : " + Thread.currentThread().getName());
                }

                @Override
                public void onNext(String s) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("onNext  thread : string_result:"+s + Thread.currentThread().getName());
                }

                @Override
                public void onError(Throwable throwable) {
                    System.out.println("onError  thread : " + Thread.currentThread().getName());
                }

                @Override
                public void onComplete() {
                    System.out.println("onComplete  thread : " +                                Thread.currentThread().getName());
                }
            });
        }

    }
}
