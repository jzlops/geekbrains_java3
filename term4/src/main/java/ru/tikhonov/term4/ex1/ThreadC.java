package ru.tikhonov.term4.ex1;

/**
 * Edit by Tikhonov Sergey
 */
public class ThreadC implements Runnable {
    private Printer printer;

    ThreadC(Printer printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        try {
            this.printer.printC();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
