package me.zhengjie;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportTest {

    static volatile int a=0;

    static AtomicInteger b=new AtomicInteger(0);

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor= (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        threadPoolExecutor.prestartCoreThread();
        ReentrantLock lock=new ReentrantLock();
        for (int i = 0; i <3 ; i++) {
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    lock.lock();
                    a=2;
                    lock.unlock();
                }
            });
        }

//        threadPoolExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                b.compareAndSet(0,1);
//            }
//        });



    }
}
