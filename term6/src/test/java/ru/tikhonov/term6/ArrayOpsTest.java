package ru.tikhonov.term6;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * Edit by Tikhonov Sergey
 */
public class ArrayOpsTest {
    private static ArrayOps arrayOps;

    @BeforeClass
    public static void startTest() {
        arrayOps = new ArrayOps();
    }

    @Test(expected = RuntimeException.class)
    public void haulRuntimeException() throws Exception {
        arrayOps.haul(new int[]{1, 2, 3, 5});
    }

    @Test
    public void haulWithCompleteArrayEnded4() throws Exception {
        Assert.assertNull(arrayOps.haul(new int[]{1, 2, 3, 4}));
    }

    @Test
    public void haulWithCompleteArrayEndedNot4() throws Exception {
        Assert.assertArrayEquals(new int[]{1, 3, 5}, arrayOps.haul(new int[]{1, 2, 4, 1, 3, 5}));
        Assert.assertArrayEquals(new int[]{1, 3, 5}, arrayOps.haul(new int[]{4, 2, 4, 1, 3, 5}));
    }

    @Test
    public void haulWithNullArray() throws Exception {
        Assert.assertNull(arrayOps.haul(null));
    }


    @Test
    public void checkWith1And4Only() throws Exception {
        Assert.assertTrue(arrayOps.check(new int[]{4, 4, 4, 1, 4, 1}));
    }

    @Test
    public void checkWith1Only() throws Exception {
        Assert.assertFalse(arrayOps.check(new int[]{1, 1, 1, 1, 1, 1}));
    }

    @Test
    public void checkWith4Only() throws Exception {
        Assert.assertFalse(arrayOps.check(new int[]{4, 4, 4, 4, 4, 4}));
    }

    @Test
    public void checkWithout1and4() throws Exception {
        Assert.assertFalse(arrayOps.check(new int[]{2, 3, 7, 5}));
    }


}