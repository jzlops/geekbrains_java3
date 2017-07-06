package ru.tikhonov.term7.test.testkeepers;

import ru.tikhonov.term7.test.annotations.AfterSuite;
import ru.tikhonov.term7.test.annotations.BeforeSuite;
import ru.tikhonov.term7.test.annotations.Test;

/**
 * Edit by Tikhonov Sergey
 */
public class TestKeeper3 {
    @Test(priority = -3)
    public void first() {
        System.out.println("First test in: " + this.getClass().getCanonicalName());
    }

    @Test
    public void second() {
        System.out.println("Second test in: " + this.getClass().getCanonicalName());
    }

    @Test(priority = 1)
    public void third() {
        System.out.println("Third test in: " + this.getClass().getCanonicalName());
    }

    @BeforeSuite
    public void before() {
        System.out.println("Before test in: " + this.getClass().getCanonicalName());
    }

    @AfterSuite
    public void after() {
        System.out.println("After test in: " + this.getClass().getCanonicalName());
    }

}
