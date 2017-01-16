package liao.thomas.javadev.basic.multithread.caseOne;

/**
 * Created by liaodongming on 2016/12/26.
 */
public class MyThread2 extends Thread {

    private Count count;

    private Person person;

    public MyThread2(Count count, String name, Person person) {
        this.count = count;
        this.setName(name);
        this.person = person;
    }

    @Override
    public void run() {

        while (count.getTimes() <= 9) {
            //上锁， 只需要给这部分会修改count的代码加锁就可以了，不要放在外面，否则会影响线程调度
            synchronized(this) {
                //输出调用总调用次数
                System.out.println("thread: " + this.getName() + "," + count.getTimes());
                count.setTimes(count.getTimes() + 1);
                if (this.getName().equals("A")) {
                    person.setWeight(person.getWeight() + 2);
                    count.setCountA(count.getCountA() + 1);//记录下来A线程调用次数
                }
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //当最后一次的时候，输出A线程的总调用次数，还有person的weight
                if (count.getTimes() == 10 && this.getName().equals("A")) {
                    System.out.println("thread: " + this.getName() + " count: " +
                            count.getCountA() + " weight: " + person.getWeight());

                }
            }
        }
    }
}