package ru.tikhonov.term4.ex1;

/**
 * Edit by Tikhonov Sergey
 */
class Printer {
    private volatile String letter = "A";
    private final Object mon = new Object();

    void printA() throws InterruptedException {
        synchronized (this.mon) {
            for (int i = 0; i < 3; i++) {
                while (!"A".equals(this.letter)) {
                    mon.wait();
                }
                System.out.printf("%s", this.letter);
                this.letter = "B";
                this.mon.notifyAll();
            }
        }
    }

    void printB() throws InterruptedException {
        synchronized (this.mon) {
            for (int i = 0; i < 3; i++) {
                while (!"B".equals(this.letter)) {
                    mon.wait();
                }
                System.out.printf("%s", this.letter);
                this.letter = "C";
                this.mon.notifyAll();
            }
        }
    }

    void printC() throws InterruptedException {
        synchronized (this.mon) {
            for (int i = 0; i < 3; i++) {
                while (!"C".equals(this.letter)) {
                    mon.wait();
                }
                System.out.printf("%s", this.letter);
                this.letter = "A";
                this.mon.notifyAll();
            }
        }
    }

}
