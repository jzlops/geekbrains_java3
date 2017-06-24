package ru.tikhonov.term4.ex3;

/**
 * Edit by Tikhonov Sergey
 */
public class ScanThread implements Runnable {
   private MFP mfp;

    ScanThread(MFP mfp) {
        this.mfp = mfp;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(50);
            this.mfp.scan();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
