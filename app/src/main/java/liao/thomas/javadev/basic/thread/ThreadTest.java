package liao.thomas.javadev.basic.thread;

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
}
