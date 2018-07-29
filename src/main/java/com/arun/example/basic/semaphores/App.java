package com.arun.example.basic.semaphores;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * - semaphore maintains a set of permits
 * - acquire() -> if a permit is available then takes it
 * - release() -> adds a permit
 * <p>
 * Semaphore just keeps a count of the number available
 * new Semaphore(int permits, boolean fair) !!!
 */

enum Downloader {

    INSTANCE;

    private Semaphore semaphore = new Semaphore(5, true);

    public void downloadData() {

        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }

    }

    private void download() {
        try {
            Thread.sleep(10 * LocalDateTime.now().getSecond());
            System.out.println("Downloading data from the web...");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 120; i++) {
            executorService.execute(() -> Downloader.INSTANCE.downloadData());
        }

    }
}