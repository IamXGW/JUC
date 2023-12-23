package com.iamxgw.join;

/**
 * @description: 执行顺序：T1 -> T2 -> T3
 * @author: IamXGW
 * @create: 2023-12-23 16:43
 */
public class UseJoin {
    public static void main(String[] args) {
        T1 r1 = new T1();
        Thread t1 = new Thread(r1, "t1");
        Thread t3 = Thread.currentThread();
        T2 r2 = new T2(t1);
        Thread t2 = new Thread(r2, "t2");

        t1.start();
        t2.start();

        System.out.println("T3 Start");
        if (t2 != null) {
            try {
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
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
        Thread t1;

        public T2(Thread t1) {
            this.t1 = t1;
        }
        @Override
        public void run() {
            System.out.println("T2 Start");
            if (t1 != null) {
                try {
                    t1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(Thread.currentThread().getName() + " done");
        }
    }
}