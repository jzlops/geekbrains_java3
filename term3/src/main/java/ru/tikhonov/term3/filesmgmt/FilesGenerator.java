package ru.tikhonov.term3.filesmgmt;

import java.io.*;

/**
 * Edit by Tikhonov Sergey
 * Класс генерирует файлы для ДЗ (кладет их в корень проекта в папку Junk)
 */
public class FilesGenerator {
    static final String PREFIX = System.getProperty("user.dir") + "/Junk/";

    public static void fileGen() {
        File dir = new File(PREFIX);
        if (!dir.exists()) {
            dir.mkdir();
        }
        //создаем 5 маленьких одинаковых файла
        try (FileOutputStream fout1 = new FileOutputStream(PREFIX + "file1.txt");
             FileOutputStream fout2 = new FileOutputStream(PREFIX + "file2.txt");
             FileOutputStream fout3 = new FileOutputStream(PREFIX + "file3.txt");
             FileOutputStream fout4 = new FileOutputStream(PREFIX + "file4.txt");
             FileOutputStream fout5 = new FileOutputStream(PREFIX + "file5.txt")
        ) {
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
        //создаем 1 большой файл ~ 70 mb
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(PREFIX + "big.txt"))) {
            String s = new String(("Это какой то текс, это какой то текст, это какой то текст, это какой то текст" + System.getProperty("line.separator")).getBytes(), "UTF-8");
            for (int i = 0; i < 500000; i++) {
                bf.write(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
