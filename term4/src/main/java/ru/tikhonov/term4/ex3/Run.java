package ru.tikhonov.term4.ex3;

import java.util.ArrayList;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    public static void main(String[] args) throws InterruptedException {
        MFP mfp = new MFP();
        ArrayList<Thread> printList = new ArrayList<>();
        ArrayList<Thread> scanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            printList.add(new PrintThread(mfp));
            scanList.add(new ScanThread(mfp));
            printList.get(i).run();
            scanList.get(i).run();
            printList.get(i).join();
            scanList.get(i).join();
        }

    }
}
