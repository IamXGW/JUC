package com.iamxgw.join;

/**
 * @description: 不使用 join
 * @author: IamXGW
 * @create: 2023-12-23 16:43
 */
public class NoJoin {
    public static void main(String[] args) {
        Thread t3 = Thread.currentThread();
        T2 r2 = new T2();
        Thread t2 = new Thread(r2, "t2");
        T1 r1 = new T1();
        Thread t1 = new Thread(r1, "t1");

        t1.start();
        t2.start();

        System.out.println("T3 Start");
        System.out.println(t3.getName() + " done");
    }

    static class T1 implements Runnable {

        @Override
        public void run() {
            System.out.println("T1 Start");
            System.out.println(Thread.currentThread().getName() + " done");
        }
    }

    static class T2 implements Runnable {

        @Override
        public void run() {
            System.out.println("T2 Start");
            System.out.println(Thread.currentThread().getName() + " done");
        }
    }
}