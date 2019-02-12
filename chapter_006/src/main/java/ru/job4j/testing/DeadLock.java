package ru.job4j.testing;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLock {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            service.submit(new Runner(i, countDownLatch));
        }

        service.shutdown();
        for (int i = 0; i < 2; i++) {
            countDownLatch.countDown();
        }
    }
}

class Runner implements Runnable {
    private int id;
    private CountDownLatch countDownLatch;

    public Runner(int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread id " + id + " running");
    }
}

