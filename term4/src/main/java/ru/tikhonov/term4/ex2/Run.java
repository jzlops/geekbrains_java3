package ru.tikhonov.term4.ex2;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    public static void main(String[] args) {
        FileRecorder fileRecorder = new FileRecorder();
        Thread t1 = new Thread(new Thread1(fileRecorder));
        Thread t2 = new Thread(new Thread2(fileRecorder));
        Thread t3 = new Thread(new Thread3(fileRecorder));
            t1.start();
            t2.start();
            t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        fileRecorder.close();
    }
}
