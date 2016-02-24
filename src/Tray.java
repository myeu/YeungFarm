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

    double nutrientSolutionRequirement;
    boolean needsNutrientRelease;

    public Tray(int newId) {
        id = newId;
        nutrientSolutionRequirement = 100;
        reservoir = new Reservoir(6.3, nutrientSolutionRequirement);
        plants = new ArrayList<Plant>();
        createPlants(15);
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {

//        20 days from start
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

//        four times a day
        if ((hour % 6) == 0 && minute == 2) {
            // Check water levels
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
                if (!needsWater) {
                    System.out.println("water is good");
                }
            }

            if (reservoir.getNutrientConcentration() < (nutrientSolutionRequirement - Reservoir.NUTRIENT_STEP)) {
//                System.out.println("Adding to Nutrient soln: " + reservoir.getNutrientConcentration());
                reservoir.addNutrients();
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
