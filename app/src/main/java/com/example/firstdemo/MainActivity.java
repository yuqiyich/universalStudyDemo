package com.example.firstdemo;

import android.content.ComponentName;
<<<<<<< HEAD
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
=======
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
>>>>>>> 3d25ab1838fecdf4f5588886338bba552da364d6
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
<<<<<<< HEAD

import com.example.firstdemo.secret.RSAUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private String TAG="MainActivity";
=======
import io.reactivex.*;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
>>>>>>> 3d25ab1838fecdf4f5588886338bba552da364d6
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
<<<<<<< HEAD
        RxJavaPlugins.setErrorHandler(new RxErrorHandler());
        subcribeMessage();
=======
>>>>>>> 3d25ab1838fecdf4f5588886338bba552da364d6
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
<<<<<<< HEAD
                testGetPublicKey();
                MessageBus.getInstance().sendData("我是消息");
//                TestArrayMap.testArrayMap();
//                testRxjava2();
//                testRxBusSendEvent();
//                testRxjavaErrorHandler();

//                testZip();
                List<String> rootPackName=new ArrayList<>();
                rootPackName.add("asfddsfa");
//                RootUtils.isRooted(MainActivity.this,rootPackName);

//                Toast.makeText(MainActivity.this,""+MulitAppCheckUtil.getMulitAppContextPath(MainActivity.this)+"----->"+checkByPackageName(MainActivity.this),Toast.LENGTH_LONG).show();
            }
        });

    }


 static Disposable disposable;
    public static  void testZip(){
        System.out.println("testZip start thread : " + Thread.currentThread().getName());
       if (disposable!=null&& !disposable.isDisposed()){
           disposable.dispose();
           System.out.println("testZip  disposable has disposed");
       }
        Observable<String> source= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                System.out.println("111->ObservableOnSubscribe.subscribe thread : " + Thread.currentThread().getName());
                observableEmitter.onNext("111->observableEmitter--value:"+1);
                observableEmitter.onComplete();
            }
        });
        Observable<String> source2= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                System.out.println("222->ObservableOnSubscribe.subscribe thread  start sleep 2s: " + Thread.currentThread().getName());
                Thread.currentThread().sleep(2000);
                System.out.println("222->ObservableOnSubscribe.subscribe thread sleep 2s : " + Thread.currentThread().getName());
                observableEmitter.onNext("observableEmitter--value:"+2);

                observableEmitter.onComplete();
                System.out.println("222->ObservableOnSubscribe.subscribe  end : " );
            }
        });

                Observable.zip(source, source2, new BiFunction<String,String,String>() {
            @Override
            public String apply(String s, String s2) throws Exception {
                System.out.println("zip--->BiFunction---S:"+s+",s2:"+s2);
                return "zip--->BiFunction---S:"+s+",s2:"+s2;
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable =d;
                System.out.println("zip--->onSubscribe---d:"+d.hashCode()+"d--className:"+d.getClass().getName());
            }

            @Override
            public void onNext(String s) {
                System.out.println("zip--->onNext---s:"+s);
=======
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
>>>>>>> 3d25ab1838fecdf4f5588886338bba552da364d6
            }

            @Override
            public void onError(Throwable e) {
<<<<<<< HEAD
                System.out.println("zip--->onError---s:"+e.getMessage());
=======

>>>>>>> 3d25ab1838fecdf4f5588886338bba552da364d6
            }

            @Override
            public void onComplete() {
<<<<<<< HEAD
                System.out.println("zip--->onComplete---s:");
            }
        });
        if (disposable!=null&& !disposable.isDisposed()){
            disposable.dispose();
            System.out.println("testZip end disposable has disposed");
        }
    }

=======

            }
        });
        Toast.makeText(getApplicationContext(),"绑定了服务", Toast.LENGTH_SHORT).show();
    }

    private void testRxBusSendEvent() {
        RxBus.getInstance().post(new RxEventData());
    }


>>>>>>> 3d25ab1838fecdf4f5588886338bba552da364d6
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
<<<<<<< HEAD

    /**
     * rxjava 的 Observable.create
     */
    public static void testRxjavaErrorHandler(){
        System.out.println("testRxjava2 --->start");
        Observable source= Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                System.out.println("ObservableOnSubscribe.subscribe thread : " + Thread.currentThread().getName());
                for (int i = 0; i <1; i++) {
                    observableEmitter.onNext("observableEmitter--value:"+i);
                }

                observableEmitter.onComplete();
                observableEmitter.onNext("observableEmitter--value:end");
            }
        })//顶层Observable
                .observeOn(Schedulers.io());
            source.subscribe(new Observer<String>() {
                @Override
                public void onSubscribe(Disposable disposable) {
                    System.out.println("onSubscribe  thread : " + Thread.currentThread().getName());
                }

                @Override
                public void onNext(String s) {
                    try {
                        returnString();
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

    private static String  returnString() {
        String a=null;
        a.equals("aaa");
        return "bbb" ;
    }

    public final class RxErrorHandler implements Consumer<Throwable> {
        private final String TAG = "RxErrorHandler";

        @Override
        public void accept(Throwable e) throws Exception {
            e.printStackTrace();
            System.out.println("error"+e.getMessage());
            if (e instanceof UndeliverableException) {
                e = e.getCause();
            }
            if ((e instanceof IOException) || (e instanceof SocketException)) {
                // fine, irrelevant network problem or API that throws on cancellation
                return;
            }
            if (e instanceof InterruptedException) {
                // fine, some blocking code was interrupted by a dispose call
                return;
            }
            if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                // that's likely a bug in the application
                return;
            }
            if (e instanceof IllegalStateException) {
                // that's a bug in RxJava or in a custom operator
                return;
            }
        }

        private String getStackTrace(Throwable ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            try {
                ex.printStackTrace(pw);
                return sw.toString();
            } finally {
                pw.close();
            }

        }
    }

    /**
     * 分身APP的中会有获取App列表，会有两个自己
     * @param context
     * @return
     */
    private boolean checkByPackageName(Context context) {
        try {
            if (context == null) {
                return false;
            }
            int count = 0;
            String packageName = context.getPackageName();
            PackageManager pm = context.getPackageManager();
            List<PackageInfo> pkgs = pm.getInstalledPackages(0);
            for (PackageInfo info : pkgs) {
                if (packageName.equals(info.packageName)) {
                    count++;
                }
            }
            return count > 1;
        } catch (Exception ignore) {}
        return false;
    }

    private   Observer newOrderOb;
    public  void subcribeMessage(){
         newOrderOb=new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG,"subcribeMessage-->onSubscribe");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG,"subcribeMessage-->onNext"+s);
                returnString();


            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG,"subcribeMessage-->onError");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"subcribeMessage-->onComplete");

            }
        };
         MessageBus.getInstance().toObservable(String.class).observeOn(Schedulers.io()).subscribe(newOrderOb);
    }

    public void testDisgest(){

//        EncryptUtils.decrypt("加密的字符传", defValue));

    }

    public void testGetPublicKey(){
        try {
           String a= RSAUtil.encryptByPublicKey("b0afed702f8227f5".getBytes(), Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmyt+/zub50DN8dl4HzOB9tLlWnU2jorj1ybj987TSDNggGuHju0EOa4iInCk8o3YuiMQ7aq/0dPNRJAJcrnlXRciK/WbaxeK8QGVUyLu1C2P9Ylmjwj9DFAGk+9Hm+fwIZLXmQvSDvj+Ndr1DCJJCbQCmGIZp8beXVxTS4jm/YWMJdMl/cR4ynljdixg910nvADEE302dIzAOA9xYrfAS5u1vshDH0QgXjUvC0qK/mjrplRgwcxJT0+74r0XO9OAVsg42MOxAfJ74IWNwBKoeKG7nkRSK1TLzqT0cs1f0TzkaLl96gJvfk/iTHANibt3hTMYRIfQ7eksUyVmTEDYswIDAQAB"));
            Log.d("Yich","ase encry result:"+a);
           Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

=======
>>>>>>> 3d25ab1838fecdf4f5588886338bba552da364d6
}
