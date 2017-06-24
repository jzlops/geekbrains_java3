package ru.tikhonov.term4.ex3;

/**
 * Edit by Tikhonov Sergey
 */
class MFP {
    private volatile int printCount = 0;
    private volatile int scanCount = 0;
    private final Object scanMon = new Object();
    private final Object printMon = new Object();

    void print() {
        synchronized (this.printMon) {
            this.printCount++;
            System.out.printf("%s %d %n", "Printed document count", this.printCount);
        }
    }

    void scan() {
        synchronized (this.scanMon){
        this.scanCount++;
        System.out.printf("%s %d %n", "Scanning document count", this.scanCount);
    }}
}
