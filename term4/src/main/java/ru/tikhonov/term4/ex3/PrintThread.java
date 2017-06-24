package ru.tikhonov.term4.ex3;

/**
 * Edit by Tikhonov Sergey
 */
class PrintThread implements Runnable {
    private MFP mfp;

    PrintThread(MFP mfp) {
        this.mfp = mfp;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(40);
            this.mfp.print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
