package liao.thomas.javadev.basic.multithread.caseTwo;

/**
 * Created by liaodongming on 2016/12/26.
 */
public class Test3 {
    public static void test() {
        TestMultiThread3 demo = new TestMultiThread3();
        new Thread(demo, "A").start();
        new Thread(demo, "B").start();
    }
}
