import java.util.concurrent.Callable;
import java.util.concurrent.CyclicBarrier;

/**
 * Edit by Tikhonov Sergey
 */
public class Car implements Callable {
    private volatile static int CAR_NUM;
    private static CyclicBarrier BARRIER;
    private volatile static String WINNER;
    private volatile static boolean START;

    static String getWinner() {
        return Car.WINNER;
    }

    static {
        Car.CAR_NUM = 0;
        Car.WINNER = null;
        Car.BARRIER = new CyclicBarrier(Race.CARS_COUNT);
        Car.START = false;
    }

    private Race race;
    private int speed;
    private String name;

    String getName() {
        return this.name;
    }

    int getSpeed() {
        return this.speed;
    }

    Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        Car.CAR_NUM++;
        this.name = "Участник #" + Car.CAR_NUM;
    }

    @Override
    public Object call() throws Exception {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            Car.BARRIER.await();
            if (!Car.START) {
                Car.START = true;
                System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка началась!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            this.race.getStages().get(i).go(this);
        }
        if (Car.WINNER == null) {
            Car.WINNER = this.name;
        }
        return null;
    }
}