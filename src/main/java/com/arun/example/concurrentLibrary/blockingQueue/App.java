package com.arun.example.concurrentLibrary.blockingQueue;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * BlockingQueue -> an interface that represents a queue that is thread safe
 * Put items or take items from it ...
 * <p>
 * For example: one thread putting items into the queue and another thread taking items from it
 * at the same time !!!
 * We can do it with producer-consumer pattern !!!
 * <p>
 * put() putting items to the queue
 * take() taking items from the queue
 */

class FirstWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public FirstWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            int counter = 1;
            while (true) {
                blockingQueue.put(counter);
                System.out.println("Putting in queue " + counter);
                counter++;
                Thread.sleep(10);
                if (counter>100)
                    break;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class SecondWorker implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    public SecondWorker(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("taking from queue "+blockingQueue.take());
                Thread.sleep(1000);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {

    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

        FirstWorker firstWorker = new FirstWorker(queue);
        SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();

    }
}
