package com.ylb.advance.concurrence;

/**
 * 自增自减 有内置锁（synchronized）的情况
 */
public class SyncDemo01 {
//    private static volatile int counter = 0;
    private static int counter = 0;
    private static String lock = "";

    public static void increment() {

        synchronized(lock){
            counter++;
        }
    }

    public static void decrement() {
        synchronized(lock){
            counter--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                increment();
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 50000; i++) {
                decrement();
            }
        }, "t2");

        t1.start();
        t2.start();
        t1.join();
        t1.join();
        //思考： counter=？
        System.out.println(counter);

    }
}
