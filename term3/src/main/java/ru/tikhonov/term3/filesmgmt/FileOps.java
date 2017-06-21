package ru.tikhonov.term3.filesmgmt;


import java.io.*;
import java.util.*;

/**
 * Edit by Tikhonov Sergey
 * Класс для работы с файлами
 */
public class FileOps {
    public static void readAndPrintFile() {
        FileInputStream fis = null;
        File file = new File(FilesGenerator.PREFIX + "file1.txt");
        try {
            fis = new FileInputStream(file);
            int fileSize = (int) file.length();
            byte[] fileAsArray = new byte[fileSize];
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> prepareBookPages() {
        int pageSize = 1817;
        File bigFile = new File(FilesGenerator.PREFIX + "big.txt");
        ArrayList<String> book = new ArrayList<>(1000);
        try (BufferedReader br = new BufferedReader(new FileReader(bigFile))) {
            char[] page = new char[pageSize];
            while (br.read(page) != -1) {
                book.add(new String(page));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book;
    }
}
