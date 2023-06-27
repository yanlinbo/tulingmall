package com.ylb.base.objectoriented.inteface;

/**
 * 接口之间可以实现多继承，子接口可以获得父接口的属性；但是子接口能继承到父接口的方法吗？
 */
public class InterfaceTest {
    public static void main(String[] args) {
        System.out.println(InterfaceC.a);
        System.out.println(InterfaceC.b);
        //注意接口类方法不能被继承
        System.out.println(InterfaceA.staticTest());
    }
}

interface InterfaceA {
    //接口里定义的成员变量只能是常量
    int a = 3;
//    int d;
    //接口里定义的方法不能是private 修饰
     void testA();

     //接口里可以定义默认方法
    default void test(){
        System.out.println("默认的test方法");
    }

    //接口中还可以定义类方法
    static String staticTest(){
        return "";
    }
}

interface InterfaceB {
    int b = 5;
    void testB();

}
interface InterfaceC extends InterfaceA,InterfaceB {
    int c = 5;
    void testC();

}
