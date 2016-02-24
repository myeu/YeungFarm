import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Marisa Yeung
 */
public class Tray implements TimeListener{
    int id;
    Reservoir reservoir;
    Random r = new Random();
    List<Plant> plants;
    boolean needsWater;

    public Tray(int newId) {
        id = newId;
        reservoir = new Reservoir(6.3, 5.0);
        plants = new ArrayList<Plant>();
        createPlants(2);
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {

        if (day == 20 && hour == 12 && minute == 0) {
            for (Plant plant : plants) {
                plant.stopGrowing();
                System.out.println("Plant " + plant.getId() + " size: " + plant.getSize());
            }
        }

        /*if ((day % 5) == 0) {
            for (Plant plant : plants) {
                System.out.println("Plant " + plant.getId() + " size: " + plant.getSize());
            }
        }*/

        if ((hour % 12) == 0 && minute == 2) {
            // Check water levels at start of each day
            if (reservoir.isWaterLow()) {
                needsWater = true;
            }
        }

        // Every hour
        if (minute == 30) {
            // Simulate pH changes
            if (r.nextInt(8) == 1) {
                reservoir.changePH();
            }

            // Add water if its still not at MAX
            if (needsWater) {
                needsWater = reservoir.addWater();
            }
        }

        // Every 30 minutes
        if ((minute % 30) == 0 ) {
            // Grow plants
            for (Plant plant : plants) {
                plant.nourish();
            }
        }

    }

    public int getId() {
        return id;
    }

    private void createPlants(int num) {
        for (int i = 0; i < num; i++) {
            int newID = (id * 10) + i;
            Plant p = new Plant(reservoir, newID);
            plants.add(p);
        }
    }

}
