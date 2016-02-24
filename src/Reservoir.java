import org.omg.CORBA.MARSHAL;

import java.util.Random;

/**
 * Created by marisayeung on 2/23/16.
 */
public class Reservoir {

    double WATER_MAX = 100;

    double pH;
    double nutrientConcentration;
    double waterVolume;
    Random s = new Random();

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

    public void addNutrients(double nutrients) {
        changeNutrients(true, nutrients);
    }

    synchronized public void changeNutrients(boolean up, double nutrients) {
        if (up) {
            nutrientConcentration += nutrients;
        } else {
            nutrientConcentration -= nutrients;
        }
    }

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

    // TODO: check if there is 0 water?
    public void useWater(double waterUsed) {
        waterVolume -= waterUsed;
//        System.out.println("water levels: " + waterVolume);
    }

    public boolean isWaterLow() {
        if (waterVolume < 40) {
            System.out.println("water is low");
            return true;
        } else {
            return false;
        }
    }

    public boolean addWater() {
        double addAmount = 10 + waterVolume;
        if (addAmount < WATER_MAX) {
            waterVolume = addAmount;
            System.out.println("water added. Water: " + waterVolume);
            return true;
        } else {
            return false;
        }
    }
}
