package ru.tikhonov.term4.ex3;

/**
 * Edit by Tikhonov Sergey
 */
class MFP {
    private volatile int printCount = 0;
    private volatile int scanCount = 0;

    synchronized void print() {
        this.printCount++;
        System.out.printf("%s %d %n", "Printing document", this.printCount);
    }

    synchronized void scan() {
        this.scanCount++;
        System.out.printf("%s %d %n", "Scanning document", this.scanCount);
    }
}
