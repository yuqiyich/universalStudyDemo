package com.example.firstdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestLock {
    // 普通的原子类，存在ABA问题
    AtomicInteger a1 = new AtomicInteger(10);
    // 带有时间戳的原子类，不存在ABA问题，第二个参数就是默认时间戳，这里指定为0
    AtomicStampedReference<Integer> a2 = new AtomicStampedReference<Integer>(10, 0);

    public static void main(String[] args) throws Exception {
        TestLock a = new TestLock();
        a.test();
    }

    public void test() {
//        new Thread1().start();
//        new Thread2().start();
//        new Thread3().start();
//        new Thread4().start();

        final Lock lock = new ReentrantLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread A is waiting to get lock");
                synchronized (lock) {
                    try {
                        System.out.println("thread A get lock");
                        TimeUnit.SECONDS.sleep(3);
                        System.out.println("thread A do wait method");
                        lock.wait();
                        System.out.println("wait end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread B is waiting to get lock");
                synchronized (lock) {
                    System.out.println("thread B get lock");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("thread B do notify method");
                    lock.notify();
                    System.out.println("thread A will be alive in five seconds");
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                try {
                    System.out.println("thread B aready notify thread A");
                    TimeUnit.SECONDS.sleep(5);
                    System.out.println("特么我睡醒了");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    class Thread1 extends Thread {
        @Override
        public void run() {
            a1.compareAndSet(10, 11);
            a1.compareAndSet(11, 10);
        }
    }
    class Thread2 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(200);  // 睡0.2秒，给线程1时间做ABA操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AtomicInteger原子操作：" + a1.compareAndSet(10, 11));
        }
    }
    class Thread3 extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(500);  // 睡0.5秒，保证线程4先执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int stamp = a2.getStamp();
            a2.compareAndSet(10, 11, stamp, stamp + 1);
            stamp = a2.getStamp();
            a2.compareAndSet(11, 10, stamp, stamp + 1);
        }
    }
    class Thread4 extends Thread {
        @Override
        public void run() {
            int stamp = a2.getStamp();
            try {
                Thread.sleep(1000);  // 睡一秒，给线程3时间做ABA操作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AtomicStampedReference原子操作:" + a2.compareAndSet(10, 11, stamp, stamp + 1));
        }
    }
}
