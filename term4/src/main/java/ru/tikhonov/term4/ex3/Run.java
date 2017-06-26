package ru.tikhonov.term4.ex3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService ex = Executors.newCachedThreadPool();
        MFP mfp = new MFP();
        for (int i = 0; i < 10; i++) {
            ex.submit(new PrintThread(mfp));
            ex.submit(new ScanThread(mfp));
        }
        ex.shutdown();
    }
}
