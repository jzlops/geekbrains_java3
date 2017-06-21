package ru.tikhonov.term3;

import ru.tikhonov.term3.filesmgmt.FilesGenerator;
import ru.tikhonov.term3.filesmgmt.FileOps;


/**
 * Edit by Tikhonov Sergey
 */
public class Main {
    public static void main(String[] args) {
        //Генерируем фалы для задания (попка Junk в корне проекта)
        FilesGenerator.fileGen();

        //Читаем файл в байтовый массив и печатаем это дело в консоль
        FileOps.readAndPrintFile();

        //Читаем 5 файлов в 1 файл и попутно выводим лог работы в консоль
        FileOps.readManyFilesToOne();

    }
}
