package ru.tikhonov.term1.arraysOps;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.is;


/**
 * Edit by Tikhonov Sergey
 * Тесты для работы основных методов
 */
public class ArraysTasksTest {

    /**
     * Тестируем перестановку элементов в массиве Number
     */
    @Test
    public void swapArraysNumbersElements() throws Exception {
        Integer[] arrayI = new Integer[]{1, 2, 3, 4, 5, 6};
        ArraysTasks<Number> arraysTasksN = new ArraysTasks<>();
        arraysTasksN.swap(arrayI, 0, 4);

        Assert.assertThat(arrayI[0], is(5));
        Assert.assertThat(arrayI[4], is(1));
        Assert.assertThat(arrayI[5], is(6));
        Assert.assertThat(arrayI[1], is(2));
        Assert.assertThat(arrayI.length, is(6));
    }
    /**
     * Тестируем перестановку элементов в массиве String
     */
    @Test
    public void swapArraysStringElements() throws Exception {
        String[] arrayS = new String[]{"1", "12", "John", "Да здравствует великий Мао"};
        ArraysTasks<String> arraysTasksS = new ArraysTasks<>();
        arraysTasksS.swap(arrayS, 0, 2);

        Assert.assertThat(arrayS[0], is("John"));
        Assert.assertThat(arrayS[2], is("1"));
        Assert.assertThat(arrayS[1], is("12"));
        Assert.assertThat(arrayS[3], is("Да здравствует великий Мао"));
        Assert.assertThat(arrayS[1], is("12"));
        Assert.assertThat(arrayS.length, is(4));


    }
    /**
     * Тестируем перестановку конвертацию массива String в лист элементов String
     */
    @Test
    public void convertArrayToListString() throws Exception {
        String[] arrayS = new String[]{"1", "12", "John", "Да здравствует великий Мао"};
        ArraysTasks<String> arraysTasksS = new ArraysTasks<>();
        ArrayList<String> arrayListS = arraysTasksS.convertToList(arrayS);

        Assert.assertThat(arrayListS.size(), is(4));
        Assert.assertThat(arrayListS.get(0), is("1"));
        Assert.assertThat(arrayListS.get(3), is("Да здравствует великий Мао"));
    }

    /**
     * Тестируем перестановку конвертацию массива Numbers в лист элементов Numbers
     */
    @Test
    public void convertArrayToListNumbers() throws Exception {
        Integer[] arrayI = new Integer[]{1, 2, 3, 4, 5, 6};
        Float[] arrayF = new Float[]{15f, 2.333f, 3f, 4f, 5.001f};
        ArraysTasks<Number> arraysTasksN = new ArraysTasks<>();

        ArrayList<Number> arrayListI = arraysTasksN.convertToList(arrayI);
        ArrayList<Number> arrayListF = arraysTasksN.convertToList(arrayF);

        Assert.assertThat(arrayListI.size(), is(6));
        Assert.assertThat(arrayListF.size(), is(5));

        Assert.assertThat(arrayListI.get(0), is(1));
        Assert.assertThat(arrayListI.get(5), is(6));

        Assert.assertEquals(15f, arrayListF.get(0).floatValue(), 0.001f);
        Assert.assertEquals(5.001f, arrayListF.get(4).floatValue(), 0.001f);

    }

}