package ru.tikhonov.term3.filesmgmt;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Edit by Tikhonov Sergey
 */
public class FilesGenerator {
    public static final String PREFIX = System.getProperty("user.dir") + "/Junk/";

    public static void fileGen() {
        File dir = new File(PREFIX);
        if (!dir.exists()) {
            dir.mkdir();
        }
        try (FileOutputStream fout1 = new FileOutputStream(PREFIX + "file1.txt");
             FileOutputStream fout2 = new FileOutputStream(PREFIX + "file2.txt");
             FileOutputStream fout3 = new FileOutputStream(PREFIX + "file3.txt");
             FileOutputStream fout4 = new FileOutputStream(PREFIX + "file4.txt");
             FileOutputStream fout5 = new FileOutputStream(PREFIX + "file5.txt")) {
            for (int i = 0; i < 50; i++) {
                fout1.write(i);
                fout2.write(i);
                fout3.write(i);
                fout4.write(i);
                fout5.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
