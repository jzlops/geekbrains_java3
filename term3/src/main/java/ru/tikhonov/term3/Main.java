package ru.tikhonov.term3;

import ru.tikhonov.term3.filesmgmt.FilesGenerator;
import ru.tikhonov.term3.filesmgmt.FileOps;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Edit by Tikhonov Sergey
 * 3-е задание сделал возможно не по феншую. Решил загрузить целиком книгу в память, и разбить по страницам.
 * Каждая страница - элемент ArrayList. В теории (первоночальный вариант) можно было при каждом вводе страницы, пробегаться по файлу либо RandomAccessFile либо,
 * исхитрься и обычным FileReader пользоваться, но учитываю что книга небольшая - закинул ее в ArrayList всю.
 */
public class Main {
    public static void main(String[] args) {
        //Генерируем фалы для задания (попка Junk в корне проекта)
        FilesGenerator.fileGen();

        //Читаем файл в байтовый массив и печатаем это дело в консоль
        FileOps.readAndPrintFile();

        //Читаем 5 файлов в 1 файл
        FileOps.readManyFilesToOne();

        //Загружаем книгу в ArrayList, каждый элемент которого содержит страницу String по 1817 символа (можно и 1800 но не красиво получается)
        ArrayList<String> book = FileOps.prepareBookPages();

        //Далее работаем уже с ArrayList, в котором листаем наши страницы, и Scanner
        Scanner scanner = new Scanner(System.in);
        String command;
        while (scanner.hasNext()) {
            command = scanner.next();
            if ("/exit".equals(command)) {
                break;
            } else {
                try {
                    int pageNum = Integer.valueOf(command);
                    System.out.printf("%s%n", book.get(pageNum - 1));
                } catch (RuntimeException e) {
                    System.out.printf("Unrecognised argument %n exit %s%n", command);
                    scanner.nextLine();
                }
            }
        }
    }
}
