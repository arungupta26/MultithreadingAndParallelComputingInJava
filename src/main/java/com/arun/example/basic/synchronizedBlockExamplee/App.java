package com.arun.example.basic.synchronizedBlockExamplee;


public class App {

    private static Integer count1 = 0;
    private static Integer count2 = 0;

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();


    public static void add() {
        synchronized (lock1) {
            count1++;
        }
    }

    public static void addAgain() {
        synchronized (lock2) {
            count2++;
        }

    }

    public static void compute() {
        for (int i = 0; i < 100; ++i) {
            add();
            addAgain();
        }
    }

    public static void main(String[] args) {

        Thread t1 = new Thread(() -> {
            compute();
        });

        Thread t2 = new Thread(() -> {
            compute();

        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Count1=" + count1 + " ::: Count2=" + count2);
    }
}
