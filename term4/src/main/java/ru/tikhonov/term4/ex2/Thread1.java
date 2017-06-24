package ru.tikhonov.term4.ex2;

/**
 * Edit by Tikhonov Sergey
 */
public class Thread1 implements Runnable {
    private FileRecorder fileRecorder;

    Thread1(FileRecorder fileRecorder) {
        this.fileRecorder = fileRecorder;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            this.fileRecorder.print("Thread 1" + System.getProperty("line.separator"));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
