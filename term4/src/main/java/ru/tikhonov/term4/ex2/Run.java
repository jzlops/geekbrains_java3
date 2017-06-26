package ru.tikhonov.term4.ex2;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    private static final int THREADCOUNT = 3;

    public static void main(String[] args) {
        FileRecorder fr = new FileRecorder();
        CountDownLatch cd = new CountDownLatch(THREADCOUNT);
        ExecutorService ex = Executors.newCachedThreadPool();

        for (int i = 0; i < THREADCOUNT; i++) {
            ex.submit(() -> {
                StringBuilder buffer = new StringBuilder();
                for (int j = 0; j < 10; j++) {
                    buffer.append("Thread").append(j).append(System.getProperty("line.separator"));
                    fr.print(buffer.toString());
                    buffer.setLength(0);
                    try {
                        Thread.sleep((int) Math.random() * 20);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                cd.countDown();
            });
        }
        ex.shutdown();
        try {
            cd.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fr.close();
    }
}
