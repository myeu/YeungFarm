
import java.util.Random;

/**
 * Created by marisayeung on 2/23/16.
 */
public class Reservoir {

    static double NUTRIENT_STEP = 5.0;

    double WATER_MAX = 400;

    double pH;
    double nutrientConcentration;
    double waterVolume;
    Random s = new Random();

    /*

        Nutrient

     */

    public Reservoir(double pH, double nutrientConcentration) {
        this.pH = pH;
        this.nutrientConcentration = nutrientConcentration;
        waterVolume = WATER_MAX;
    }

    public double getNutrientConcentration() {
        return nutrientConcentration;
    }

    public void useNutrients(double nutrients) {
        changeNutrients(false, nutrients);
    }

    public void addNutrients() {
        changeNutrients(true, NUTRIENT_STEP);
    }

    synchronized public void changeNutrients(boolean up, double nutrients) {
        if (up) {
            nutrientConcentration += nutrients;
        } else {
            nutrientConcentration -= nutrients;
        }
    }

    /*

        Acidity

     */

    public double getpH() {
        return pH;
    }

    public void changePH() {
        // Randomly choose which direction to adjust the pH
        if (s.nextBoolean()) {
            pH -= 0.2;
        } else {
            pH += 0.2;
        }
    }

//    Release solution to raise up the pH
    public void pHUp() {
        pH += .2;
    }

//    Release solution to lower the pH
    public void pHDown() {
        pH -= .2;
    }

    /*

        Water levels

     */

    // TODO: check if there is 0 water?
    public void useWater(double waterUsed) {
        waterVolume -= waterUsed;
//        System.out.println("water levels: " + waterVolume);
    }

    public boolean isWaterLow() {
        if (waterVolume < 250) {
            System.out.println("water is low");
            return true;
        } else {
            return false;
        }
    }

    public boolean addWater() {
        double addAmount = 20 + waterVolume;
        if (addAmount < WATER_MAX) {
            waterVolume = addAmount;
            System.out.println("water added. Water: " + waterVolume);
            return true;
        } else {
            return false;
        }
    }
}
