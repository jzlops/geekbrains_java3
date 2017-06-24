package ru.tikhonov.term4.ex3;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MFP mfp = new MFP();
        for (int i = 0; i < 10; i++) {
            (new Thread(new PrintThread(mfp))).start();
            (new Thread(new ScanThread(mfp))).start();
        }
    }
}
