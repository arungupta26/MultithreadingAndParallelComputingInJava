package com.arun.example.basic.volatileExample;

/**
 * @author arungupta
 */
class Worker implements Runnable {


    /**
     * volatile make that variable declared at the RAM level, not at the thread cache level.
     */
    private volatile boolean isTerminated = false;

    public void run() {

        while(!isTerminated) {

            System.out.println("Hello from worker class...");

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean isTerminated) {
        this.isTerminated = isTerminated;
    }
}

public class App {

    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread t1 = new Thread(worker);
        t1.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.setTerminated(true);
        System.out.println("Finished...");
    }
}
