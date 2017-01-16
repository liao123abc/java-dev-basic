package liao.thomas.javadev.basic.override;

/**
 * Created by ThomasLiao on 2016/12/24.
 * This is a sample introducing override.
 */
public class BaseCalculator {

    public BaseCalculator() {
    }

    protected void doSomething() {

    }

    /**
     * 除法
     * @param a
     * @param b
     */
    public float divide(int a, int b) {
        float result = a / b;
        return result;
    }
}
