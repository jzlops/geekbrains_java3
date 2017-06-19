package ru.tikhonov.term1.fruitsAdventure.fruits;

import java.util.Objects;

/**
 * Абстрактный класс фрукт
 */
public abstract class Fruit {
    /**
     * Веезвращает вес фрукта
     *
     * @return вес фрукта
     */
    abstract public float getWeight();

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }

        if ((getClass() == o.getClass()) && (this.toString().equals(o.toString()))) {
            return true;
        }
        return false;
    }

}
