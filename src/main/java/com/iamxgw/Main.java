package com.iamxgw;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        int i = 0;

        new Thread() {
            public void run() {
                System.out.println();
            }
        }.start();
    }
}