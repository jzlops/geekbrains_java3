import java.util.ArrayList;
import java.util.Arrays;

/**
 * Edit by Tikhonov Sergey
 */
class Race {
    private ArrayList<Stage> stages;
    static final int CARS_COUNT = 5;
    static final int CARS_TUNNEL_LIMIT = 2;
    ArrayList<Stage> getStages() {
        return this.stages;
    }

    Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}