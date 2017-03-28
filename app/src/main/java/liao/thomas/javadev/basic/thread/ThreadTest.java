package liao.thomas.javadev.basic.thread;

import java.util.Vector;
import java.util.concurrent.Callable;

/**
 * Created by liaodongming on 2017/1/16.
 */
public class ThreadTest {

    public void test() {
        // case 1
        HelloThread thread = new HelloThread();
        thread.start();

        //case 2
        Thread thread1 = new Thread(new HelloRunnable());
        thread1.start();
    }

    public static class HelloThread extends Thread {
        public void run() {
            //do something
        }
    }

    public static class HelloRunnable implements  Runnable {
        @Override
        public void run() {
            //do something
        }
    }

    public static void differOfCallable() {

//        Vector

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

            }
        };

        /**
         * Callable的 call() 方法可以返回值和抛出异常，而Runnable的run()方法没有这些功能。
         * Callable可以返回装载有计算结果的Future对象
         */
        Callable callable = new Callable() {
            @Override
            public Object call() throws Exception {
                return null;
            }
        };
    }
}
