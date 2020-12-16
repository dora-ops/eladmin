package tomcat.yzl;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Test8 {

    /**
     * 连接linux系统
     * @param args
     */
    public static void main(String[] args) {
        ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        executorService.prestartAllCoreThreads();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                while (true){
                    lock.lock();
                    if (a.isEmpty()){
                        try {
                            empty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    a.remove(0);
                    full.signalAll();
                    lock.unlock();
                }
            }
        });

       executorService.execute( new Runnable() {
           @Override
           public void run() {
               while (true){
                   lock.lock();
                   if (a.size()==5){
                       try {
                           full.await();
                       } catch (InterruptedException e) {
                           e.printStackTrace();
                       }
                   }
                   a.add("1");
                   empty.signalAll();
                   lock.unlock();
               }
           }
       });
    }

   static ReentrantLock lock=new ReentrantLock();
    static Condition full=lock.newCondition();
    static Condition empty=lock.newCondition();
    static List<String> a=new ArrayList<>();





}
