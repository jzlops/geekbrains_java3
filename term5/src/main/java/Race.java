import java.util.ArrayList;
import java.util.Arrays;

/**
 * Edit by Tikhonov Sergey
 */
class Race {
    private ArrayList<Stage> stages;

    ArrayList<Stage> getStages() {
        return this.stages;
    }

    Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
}