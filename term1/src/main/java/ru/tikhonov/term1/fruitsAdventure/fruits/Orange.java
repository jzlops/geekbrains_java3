package ru.tikhonov.term1.fruitsAdventure.fruits;

/**
 * Класс апельсин
 */
public class Orange extends Fruit {
    private static final float WEIGHT = 1.5f;

    /**
     * Возвращает вес апельсина
     *
     * @return вес апельсина
     */
    @Override
    public float getWeight() {
        return Orange.WEIGHT;
    }
}
