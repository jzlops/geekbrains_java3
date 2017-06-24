package ru.tikhonov.term4.ex1;

/**
 * Edit by Tikhonov Sergey
 */
public class ThreadB implements Runnable {
    private Printer printer;

    ThreadB(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            this.printer.printB();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
