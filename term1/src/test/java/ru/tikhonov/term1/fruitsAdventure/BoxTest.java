package ru.tikhonov.term1.fruitsAdventure;

import org.junit.Assert;
import org.junit.Test;
import ru.tikhonov.term1.fruitsAdventure.fruits.Apple;
import ru.tikhonov.term1.fruitsAdventure.fruits.Orange;

import java.util.Collections;

import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;

/**
 * Edit by Tikhonov Sergey
 * Тесты для работы основных методов
 */

public class BoxTest {
    /**
     * Тестируем корректность заполнения коробок фруктами
     */
    @Test
    public void addFruitsToBoxAndGetFruitsListFromBox() throws Exception {
        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();
        Box<Apple> box3 = new Box<>();

        for (int i = 1; i < 4; i++) {
            box1.add(new Apple());
            box2.add(new Orange());
            box3.add(new Apple());
        }

        Assert.assertThat(box1.getFruits().isEmpty(), is(false));
        Assert.assertThat(box1.getFruits().isEmpty(), is(false));
        Assert.assertThat(box1.getFruits().isEmpty(), is(false));

        Assert.assertThat(box1.getFruits().size(), is(3));
        Assert.assertThat(box2.getFruits().size(), is(3));
        Assert.assertThat(box3.getFruits().size(), is(3));

        Assert.assertThat(box1.getFruits(), hasItems(new Apple(), new Apple(), new Apple()));
        Assert.assertThat(box2.getFruits(), hasItems(new Orange(), new Orange(), new Orange()));
        Assert.assertThat(box3.getFruits(), hasItems(new Apple(), new Apple(), new Apple()));
    }

    /**
     * Тестируем корректность расчета веса коробок
     */
    @Test
    public void getWeightAndCompareBoxesByWeight() throws Exception {
        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();
        Box<Apple> box3 = new Box<>();

        for (int i = 1; i < 4; i++) {
            box1.add(new Apple());
            box2.add(new Orange());
            box3.add(new Apple());
        }
        Assert.assertEquals(3f, box1.getWeight(), 0.001f);
        Assert.assertEquals(4.5f, box2.getWeight(), 0.001f);
        Assert.assertEquals(3f, box3.getWeight(), 0.001f);

        Assert.assertThat(box1.compare(box3), is(true));
        Assert.assertThat(box3.compare(box1), is(true));
        Assert.assertThat(box1.compare(box2), is(false));
        Assert.assertThat(box2.compare(box1), is(false));
    }

    /**
     * Тестируем метод по перекладке яблок из коробки в коробку
     */
    @Test
    public void lard() throws Exception {
        Box<Apple> box1 = new Box<>();
        Box<Apple> box3 = new Box<>();

        for (int i = 1; i < 4; i++) {
            box1.add(new Apple());
            box3.add(new Apple());
        }
        Assert.assertThat(box1.getFruits().size(), is(3));
        Assert.assertThat(box3.getFruits().size(), is(3));

        Assert.assertThat(box1.getFruits(), hasItems(new Apple(), new Apple(), new Apple()));
        Assert.assertThat(box3.getFruits(), hasItems(new Apple(), new Apple(), new Apple()));

        box1.lard(box3);

        Assert.assertThat(box1.getFruits().size(), is(6));
        Assert.assertThat(box3.getFruits().size(), is(0));

        Assert.assertThat(box1.getFruits(), hasItems(new Apple(), new Apple(), new Apple(), new Apple(), new Apple(), new Apple()));
        Assert.assertThat(box3.getFruits(), is(Collections.emptyList()));

    }

    /**
     * Тестируем метод по поустошению коробки с аблоками
     */
    @Test
    public void waste() throws Exception {
        Box<Apple> box1 = new Box<>();
        Box<Orange> box2 = new Box<>();
        Box<Apple> box3 = new Box<>();

        for (int i = 1; i < 4; i++) {
            box1.add(new Apple());
            box2.add(new Orange());
            box3.add(new Apple());
        }
        Assert.assertThat(box1.getFruits().isEmpty(), is(false));
        Assert.assertThat(box1.getFruits().isEmpty(), is(false));
        Assert.assertThat(box1.getFruits().isEmpty(), is(false));

        box1.waste();
        box2.waste();
        box3.waste();

        Assert.assertThat(box1.getFruits().isEmpty(), is(true));
        Assert.assertThat(box1.getFruits().isEmpty(), is(true));
        Assert.assertThat(box1.getFruits().isEmpty(), is(true));
    }

}