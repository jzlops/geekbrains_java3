package ru.tikhonov.term4.ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    public static void main(String[] args) {
        Printer printer = new Printer();
        ExecutorService ex = Executors.newCachedThreadPool();
        ex.submit(new ThreadA(printer));
        ex.submit(new ThreadB(printer));
        ex.submit(new ThreadC(printer));
        ex.shutdown();
    }
}
