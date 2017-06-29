/**
 * Edit by Tikhonov Sergey
 */
public class Road extends Stage {
    Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + this.description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + this.description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
