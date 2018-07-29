package com.arun.example.basic.waitAndNotify;

/**
 * @author arungupta
 *
 * Please reffer output for better understanding
 */
class Processor {

    public void produce() throws InterruptedException {


        System.out.println("Producer called");
        synchronized (this) {
            System.out.println("Entered the synchronized blocked");
            System.out.println("We are in the producer method");
            System.out.println("We are in the producer method, waiting...");
            wait();
            System.out.println("Again producer method...");
            System.out.println("Left the synchronized blocked");
        }
    }

    public void consume() throws InterruptedException {


        System.out.println("\tConsumer called :: starting ");
        System.out.println("\tConsumer called :: Initial sleeping, Before synchronied ");
        Thread.sleep(10000);
        System.out.println("\tConsumer called :: woke up , Before synchronied  ");

        synchronized (this) {
            System.out.println("\tConsumer called :: notifying , But still holds the lock at class level and inside synchronied  ");

            notify();
            //notifyAll();

            System.out.println("\tConsumer called :: notified , But still holds the lock at class level and inside synchronied and sleeping for 3 sec ");

            Thread.sleep(3000);
        }
    }
}

public class App {

    public static void main(String[] args) {

        Processor processor = new Processor();

        Thread t1 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
