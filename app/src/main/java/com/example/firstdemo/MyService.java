package com.example.firstdemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {

   public List<Book> mBookList=new ArrayList();
    private IBookManager.Stub mbinder = new IBookManager.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {
            //什么也不做
        }



        @Override
        public void addBook(Book book) throws RemoteException {
            //添加书本
            if(!mBookList.contains(book)){
                mBookList.add(book);
                Log.d("yich","服务端的线程：processs："+android.os.Process.myPid( )+"Threadname："+Thread.currentThread().getName()+";Thread.currentThread():"+Thread.currentThread());
            }

            Toast.makeText(getApplicationContext(),"Threadname："+Thread.currentThread().getName()+";Thread.currentThread():"+Thread.currentThread(),Toast.LENGTH_SHORT).show();

        }

        @Override
        public List<Book> getBookList() throws RemoteException {
            return mBookList;
        }
    };

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mbinder;
    }


}
