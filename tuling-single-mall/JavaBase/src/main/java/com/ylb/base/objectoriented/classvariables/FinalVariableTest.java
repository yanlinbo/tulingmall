package com.ylb.base.objectoriented.classvariables;

/**
 * 需求：final表示修饰的类、方法、变量 不可变
 * 1，定义成员变量时是否必须指定默认值？
 */
public class FinalVariableTest {

    //定义成员变量时是否必须指定默认值？
    //    final int a;
    final int b = 6;

    //如果没有赋初始值，可以通过构造方法来赋值
    final String str;
    final int a;
    public FinalVariableTest(String str, int a) {
        this.str = str;
        this.a = a;
        //已经对finanl修饰的变量赋过初始值，那么在构造器中就不能再次赋值
//        this.b = 7;
    }

    //如果没有赋初始值，可以通过构造方法来赋值
    final int c;
     {
        c = 2;
    }

    //普通方法不能为final修饰的成员变量赋值
    final static int d;
//    public int setd(){
//        d = 12;
//    }
    static {
        d = 12;
    }
}
