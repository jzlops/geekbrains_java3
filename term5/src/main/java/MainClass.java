import java.util.ArrayList;
import java.util.concurrent.*;

/**
 * Edit by Tikhonov Sergey
 */
public class MainClass {

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(RaceEnvironment.CARS_COUNT);
        ArrayList<Callable<Car>> carsList = new ArrayList<>();
        Car[] cars = new Car[RaceEnvironment.CARS_COUNT];
        Race race = new Race(new Road(60), new Tunnel(40, (int) RaceEnvironment.CARS_COUNT / 2), new Road(40));

        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Подготовка!!!");
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (Car car : cars) {
            carsList.add(car);
        }
        try {
            ex.invokeAll(carsList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка закончилась!!!");
        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Победитель: " + Car.getWinner() + " !!!");
        ex.shutdown();
    }
}
