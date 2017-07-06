package main;

import ru.tikhonov.term7.test.testkeepers.TestKeeper1;
import ru.tikhonov.term7.test.testkeepers.TestKeeper2;
import ru.tikhonov.term7.test.testkeepers.TestKeeper3;

/**
 * Edit by Tikhonov Sergey
 */
public class Run {
    private static Class[] classes = new Class[]{TestKeeper1.class, TestKeeper2.class, TestKeeper3.class};

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            Class c = classes[i];
            new Thread(() -> Tester.start(c)).start();
        }
    }
}
