package ru.tikhonov.term4.ex1;

/**
 * Edit by Tikhonov Sergey
 */
public class ThreadA implements Runnable {
    private Printer printer;

    ThreadA(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            this.printer.printA();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
