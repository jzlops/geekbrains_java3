package ru.tikhonov.term1.fruitsAdventure;

import ru.tikhonov.term1.fruitsAdventure.fruits.Fruit;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

/**
 * Класс коробка
 */
class Box<T extends Fruit> {
    private ArrayList<T> fruits = new ArrayList<>();

    /**
     * Метод возаращает указатель на лист фруктов в коробке
     *
     * @return лист фруктов в коробке
     */
    ArrayList<T> getFruits() {
        return this.fruits;
    }

    /**
     * Метод выстыитвает вес коробки с фруктами
     *
     * @return вес всех фркутов в коробке
     */
    float getWeight() {
        float w = 0;
        for (T fruit : this.fruits) {
            w = w + fruit.getWeight();
        }
        return w;
    }

    /**
     * Метод для сравнения вса текущей коробки и переданой в метод
     *
     * @param box срасниваемая коробка
     * @return true если вес совпадает до 2 знака после запятой
     */
    boolean compare(Box<? extends Fruit> box) {
        return abs(this.getWeight() - box.getWeight()) <= 0.01f;
    }

    /**
     * Метод пересыпает фрукты из переданной коробки в исходную
     *
     * @param box коробка откуда мы пересыпаем фркуты
     */
    void lard(Box<T> box) {
        this.fruits.addAll(box.getFruits());
        box.waste();
    }

    /**
     * Метод добавляет фрукт в коробку
     *
     * @param fruit добавляемый фрукт
     */
    void add(T fruit) {
        this.fruits.add(fruit);
    }

    /**
     * Метод опустошает коробку
     */
    void waste() {
        this.fruits.clear();
    }

    /**
     * Метод "печатает" фрукты из коробок в консоль
     *
     * @param list исходный лист
     */
    static void print(List<? extends Fruit> list) {
        System.out.println();
        System.out.println(list);
        System.out.println();
    }
}
