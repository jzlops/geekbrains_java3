import java.util.concurrent.Semaphore;

/**
 * Edit by Tikhonov Sergey
 */
public class Tunnel extends Stage {
    private Semaphore semaphore;

    Tunnel(int length, int carLimit) {
        this.length = length;
        this.description = "Тоннель " + length + " метров";
        this.semaphore = new Semaphore(Race.CARS_TUNNEL_LIMIT);
    }

    @Override
    public void go(Car c) {
        try {
            this.semaphore.acquire();
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " +
                        this.description);
                System.out.println(c.getName() + " начал этап: " + this.description);
                Thread.sleep(this.length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " +
                        this.description);
            }
            this.semaphore.release();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}