package liao.thomas.javadev.basic.multithread.caseTwo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by liaodongming on 2016/12/26.
 */
public class TestMultiThread3 implements Runnable {
    private static AtomicInteger weight = new AtomicInteger(20);

    public void run() {
//        for (int i = 1; i <= 10; i++) {
//            synchronized (weight) {
//                if (weight.get() > 0) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                    }
//                    String threadName = Thread.currentThread().getName();
//                    System.out.println("thread: " + threadName + "," + i);
//
//                    if (Thread.currentThread().getName().equals("A")) {
//                        weight.getAndAdd(2);
//                    }
////                    try {
////                        Thread.sleep(100);
////                    } catch (InterruptedException e) {
////                    }
//                }
//            }
//        }

        for (int i = 1; i <= 20; i++) {
            synchronized (weight) {
                if (weight.get() > 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " " + ": " + weight.getAndDecrement() + " 张票。");
                    //					tickets--;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    System.out.println("==========现在查询还剩" + ": " + weight.get() + " 张票。");
                }
            }
        }

    }
}