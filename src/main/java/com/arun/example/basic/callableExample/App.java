package com.arun.example.basic.callableExample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

class Processor implements Callable<String> {
    private int id;

    public Processor(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {

        Thread.sleep(2000);
        return "Id :" + id;
    }
}


public class App {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Future<String> future = executor.submit(new Processor(i + 1));
            futures.add(future);
        }

        for (Future f : futures) {
            System.out.println(f.get()
            );
        }
        System.out.println("main thread completed");
    }

}
