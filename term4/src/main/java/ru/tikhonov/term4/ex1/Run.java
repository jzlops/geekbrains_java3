package ru.tikhonov.term4.ex1;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    public static void main(String[] args) {
        Printer printer = new Printer();
        Thread ta = new Thread(new ThreadA(printer));
        Thread tb = new Thread(new ThreadB(printer));
        Thread tc = new Thread(new ThreadC(printer));
        ta.start();
        tb.start();
        tc.start();

    }
}
