package ru.tikhonov.term4.ex2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Edit by Tikhonov Sergey
 */
class FileRecorder {
    private static final String FILENAME = System.getProperty("user.dir") + "/Junk/threadsFile.txt";

    static {
        File file = new File(FILENAME);
        try {
            file.delete();
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private volatile FileWriter fw;

    FileRecorder() {
        try {
            this.fw = new FileWriter(FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    synchronized void print(String s) {
        try {
            this.fw.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void close() {
        try {
            this.fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
