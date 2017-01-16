package liao.thomas.javadev.basic.multithread.caseOne;

/**
 * Created by liaodongming on 2016/12/26.
 */
public class Count {
    int times = 0;
    int countA = 0;

    public Count(int times) {
        this.times = times;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public int getCountA() {
        return countA;
    }

    public void setCountA(int countA) {
        this.countA = countA;
    }
}