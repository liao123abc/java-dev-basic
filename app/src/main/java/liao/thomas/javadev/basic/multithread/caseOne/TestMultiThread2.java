package liao.thomas.javadev.basic.multithread.caseOne;

/**
 * Created by liaodongming on 2016/12/26.
 */

public class TestMultiThread2 {

    public void test() {
        Count count = new Count(0);
        Person person = new Person(50);
        MyThread2 A = new MyThread2(count, "A", person);
        MyThread2 B = new MyThread2(count, "B", person);
        A.start();
        B.start();
    }
}
