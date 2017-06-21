package ru.tikhonov.term3.filesmgmt;

import java.io.*;
import java.util.*;

/**
 * Edit by Tikhonov Sergey
 */
public class FileOps {
    public static void readAndPrintFile() {
        FileInputStream fis = null;
        try {
            File file = new File(FilesGenerator.PREFIX + "file1.txt");
            fis = new FileInputStream(file);
            long fileSize = file.length();
            byte[] fileAsArray = new byte[(int) fileSize];
            fis.read(fileAsArray);
            System.out.printf("%s%n", Arrays.toString(fileAsArray));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void readManyFilesToOne() {
        File file1 = new File(FilesGenerator.PREFIX + "file1.txt");
        File file2 = new File(FilesGenerator.PREFIX + "file2.txt");
        File file3 = new File(FilesGenerator.PREFIX + "file3.txt");
        File file4 = new File(FilesGenerator.PREFIX + "file4.txt");
        File file5 = new File(FilesGenerator.PREFIX + "file5.txt");
        File finalFile = new File(FilesGenerator.PREFIX + "finalFile.txt");
        List<InputStream> list = new LinkedList<>();
        try {
            list.add(new FileInputStream(file1));
            list.add(new FileInputStream(file2));
            list.add(new FileInputStream(file3));
            list.add(new FileInputStream(file4));
            list.add(new FileInputStream(file5));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Enumeration<InputStream> enumList = Collections.enumeration(list);
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(finalFile));
             BufferedInputStream bis = new BufferedInputStream(new SequenceInputStream(enumList))) {
            byte[] buffer = new byte[10];
            while (bis.read(buffer) != -1) {
                bos.write(buffer);
                System.out.printf("%s%n", Arrays.toString(buffer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
