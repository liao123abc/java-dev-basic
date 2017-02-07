package liao.thomas.javadev.basic.override;


import liao.thomas.javadev.basic.accessing.Foo;

/**
 * Created by ThomasLiao on 2016/12/24.
 * Foo中有一个default方法test1(), 但是因为是default，所以是无法访问这个方法的
 * Foo中有一个protected方法test2(), 我可以通过新建一个类集成Foo，然后做事情哦
 */

public class Foo2 extends Foo {

    /**
     * 嘿嘿，我可以从父类集成它的protected方法
     */
    @Override
    protected void test2() {
        //do something
        super.test2();
        //do someting
    }
}
