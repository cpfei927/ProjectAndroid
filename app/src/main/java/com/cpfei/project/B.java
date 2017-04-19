package com.cpfei.project;

/**
 * Created by cpfei on 2017/4/14.
 */

public class B extends A {

    public void do1(){
        super.do1();
    }

    @Override
    public void do2() {
        System.out.println("B");
    }


}
