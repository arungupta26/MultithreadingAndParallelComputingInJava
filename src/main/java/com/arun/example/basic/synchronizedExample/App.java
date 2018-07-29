package com.arun.example.basic.synchronizedExample;


public class App {

    private static int counter = 0;


    /**
     * if we use below code it will less likly or never give o/p as 2000, because of unsynchronised behaviour
     */
//
//    public static  void increment() {
//        ++counter;
//    }

    public static synchronized void increment() {
        ++counter;
    }

    public static void process() throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 1000; ++i)
                    increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {

            public void run() {
                for (int i = 0; i < 1000; ++i)
                    increment();
            }
        });

        t1.start();
        t2.start();


        //this to wait till all computation occur. else main thread will print some intermittent value
        t1.join();
        t2.join();
    }

    public static void main(String[] args) throws InterruptedException {

        process();
        System.out.println(counter);

    }
}
