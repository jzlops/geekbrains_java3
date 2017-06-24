package ru.tikhonov.term4.ex3;

/**
 * Edit by Tikhonov Sergey
 */
public class PrintThread extends Thread {
    private MFP mfp;

    PrintThread(MFP mfp) {
        this.mfp = mfp;
    }

    @Override
    public void run() {
        this.mfp.print();
    }
}
