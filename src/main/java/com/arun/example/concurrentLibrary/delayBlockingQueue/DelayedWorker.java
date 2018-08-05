package com.arun.example.concurrentLibrary.delayBlockingQueue;


import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayedWorker implements Delayed {

    private long duration;
    private String message;

    public DelayedWorker(long duration, String message) {
        this.duration = System.currentTimeMillis() + duration;
        this.message = message;
    }

    @Override
    public int compareTo(Delayed otherDelayed) {
/*
if (this.duration < ((DelayedWorker) otherDelayed).getDuration()) {
//            return -1;
//        }
//
//        if (this.duration > ((DelayedWorker) otherDelayed).getDuration()) {
//            return 1;
//        }
//
//        return 0;
*/

//this sorting decide the queue ordering

        return (int) (this.duration - ((DelayedWorker) otherDelayed).getDuration());

    }

    @Override
    public long getDelay(TimeUnit timeUnit) {
        return timeUnit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    public long getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
