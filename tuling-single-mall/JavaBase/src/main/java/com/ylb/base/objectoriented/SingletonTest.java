package com.ylb.base.objectoriented;

/**
 * 需求：实现一个单例类
 */
public class SingletonTest {
    public static void main(String[] args) {
        //创建两个对象，看看这两个对象是否为同一个对象
        Singleton singleton01 = Singleton.getInstance();
        Singleton singleton02 = Singleton.getInstance();
        System.out.println(singleton01 == singleton02);
    }

}

class Singleton{
    //使用一个类变量来实现曾经创建的实例
    private static Singleton instance;
    //对构造器进行访问权限私有化设置
    private Singleton(){}
    //提供一个类方法，用来返回Singleton实例
    public static Singleton getInstance(){
        //如果instance为null，说明instance还没有创建，那么就进行创建
        if(instance == null){
            instance = new Singleton();
        }
        //如果instance不为null，说明instance已经创建，那么就直接返回
        return instance;
    }
    //
}
