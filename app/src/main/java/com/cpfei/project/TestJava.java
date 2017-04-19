package com.cpfei.project;

/**
 * Created by cpfei on 2017/4/14.
 */

public class TestJava {


    public static void main(String... args) {

        String str = "I Love";

        str.replace("I", "U");

        System.out.println(str);



//        B b = new B();
//        b.do1();


        int a = 10;
        int b = 20;

        method(a, b);
        System.out.println("a == " + a);
        System.out.println("b == " + b);



    }


    public static void method(int a, int b){
        a = 100;
        b = 200;
        System.out.println("a == " + a);
        System.out.println("b == " + b);
        System.exit(0);
    }

}
