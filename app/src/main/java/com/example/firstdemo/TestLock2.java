package com.example.firstdemo;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * mothed的synchronized的锁住的这个类对象,必须要有对象
 *
 */
public class TestLock2 {
  static   DateFormat bf = new SimpleDateFormat("yyyy-MM-dd E a HH:mm:ss");

    public static void main(String[] args) throws Exception {
        TestLock2 a = new TestLock2();
        a.test();
    }

    public void test() {
//        new Thread1().start();
//        new Thread2().start();
//        new Thread3().start();
//        new Thread4().start();

        final SynObj obj = new SynObj();
        final SynObj obj2 = new SynObj();


// mothed的synchronized的锁住的这个类对象
       new Thread(new Runnable() {
            @Override
            public void run() {
                obj.methodA("obj");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                obj2.methodA("obj2");
            }
        }).start();




    }
    static class SynObj {

    public  synchronized static void methodd(String s) {
        System.out.println("synchronized static methodd.....sleep 5：obj:" + s + ";time:" +bf.format(new Date()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public synchronized void methodA(String  s) {
        System.out.println("synchronized  method.....sleep 5：obj:" + s + ";time:" +bf.format(new Date()));
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public  void methodB() {
        synchronized(this) {
            System.out.println("methodB.....");
        }
    }

    public void methodC() {
        String str = "sss";
        synchronized (str) {
            System.out.println("methodC.....");
        }
    }
}

}
