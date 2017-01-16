package liao.thomas.javadev.basic.overload;

/**
 * Created by ThomasLiao on 2016/12/24.
 * This is a sample introducing
 */

public class Calculator {

    /**
     *
     * @param a
     * @param b
     * @return
     */
    public int sum(int a, int b) {
        int sum = 0;
        sum += a;
        sum += b;
        return sum;
    }

    /**
     * overloading sum
     * 重载上面的sum方法，三个参数，这样我们三个参数的时候就可以调用这个方法了
     * @param a
     * @param b
     * @return
     */
    public int sum(int a, int b, int c) {
        int sum = 0;
        sum += a;
        sum += b;
        sum += c;
        return sum;
    }

}
