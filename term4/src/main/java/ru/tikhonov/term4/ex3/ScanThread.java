package ru.tikhonov.term4.ex3;

/**
 * Edit by Tikhonov Sergey
 */
public class ScanThread extends Thread {
   private MFP mfp;

    ScanThread(MFP mfp) {
        this.mfp = mfp;
    }

    @Override
    public void run() {
        this.mfp.scan();
    }
}
