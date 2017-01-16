package liao.thomas.javadev.basic.override;

import liao.thomas.androidsnacks.javabasic.accessing.Foo;

/**
 * Created by Administrator on 2016/12/24.
 */

public class MyCalculator extends BaseCalculator{

    public MyCalculator() {
        super();
        super.doSomething();//调用父类BaseCalculator的
        doSomething();//调用MyCalculator的，如果MyCalculator没有这个方法就调用父类的
        this.doSomething();//同上
    }

    public void doSomething() {

    }

    /**
     *重写父类（基类）divide方法
     * @param a
     * @param b
     * @return
     */
    @Override
    public float divide(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        } else {
            return super.divide(a, b);//调用父类BaseCalculator的divide方法
        }
    }

    public void test() {
        Foo foo = new Foo();
        //foo.test1(); //会报错，因为test1是default方法，无法在别的包访问

        Foo2 foo2 = new Foo2();
        foo2.test2();//嘿嘿，调用继承后的test2方法,如果Foo2没有override test2方法，这里会报错，你可以试一下注释掉Foo2的test2方法
    }
}
