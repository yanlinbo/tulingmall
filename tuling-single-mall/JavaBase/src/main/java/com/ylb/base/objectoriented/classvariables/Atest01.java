package com.ylb.base.objectoriented.classvariables;

public class Atest01 {
    public static void main(String[] args)
    {
        // 创建A类的实例
        A a = new A();
        // 让a实例的类变量a的值自加
        a.a ++;
        System.out.println(a.a);

//=========================================

        // 创建A类的实例
        A b = new A();
        // 输出b实例的类变量a的值
        System.out.println(b.a);
    }


}
