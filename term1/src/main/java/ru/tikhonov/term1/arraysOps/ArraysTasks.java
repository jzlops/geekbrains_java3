package ru.tikhonov.term1.arraysOps;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Edit by Tikhonov Sergey
 * Класс совершает простые операции над массивом (не делается проверка на Exceptions)
 * Сделаны junit тесты
 */
class ArraysTasks<T> {
    /**
     * Метод меняет 2 элемента массива местами
     *
     * @param array  исходный массив
     * @param index1 индекс 1-го элемента
     * @param index2 индекс 2-го элемента
     */
    void swap(T[] array, int index1, int index2) {
        T element;
        element = array[index1];
        array[index1] = array[index2];
        array[index2] = element;
    }

    /**
     * Метод создает коллекцию ArrayList из переданного массива
     * (можно было сделать цикл и пробежаться по массиву и т.д. и т.п. но так оно короче выходит)
     *
     * @param array - исходный массив
     * @return - возвращаемая коллекция
     */
    ArrayList<T> convertToList(T[] array) {
        return new ArrayList<>(Arrays.asList(array));
    }


}
