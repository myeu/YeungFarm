import java.util.Random;

/**
 * Created by Marisa Yeung
 */
public class Plant  {
    Reservoir reservoir;
    boolean growing;
    double size;
    Random r;
    int id;

    public Plant(Reservoir reservoir, int id) {
        this.reservoir = reservoir;
        size = 1;
        r = new Random();
        this.id = id;
        growing = true;
    }

    public void nourish() {
        if (growing) {
            reservoir.useNutrients(0.1);
            reservoir.useWater(0.006 * size);
        } else {
            reservoir.useNutrients(0.05);
            reservoir.useWater(0.004 * size);
        }


        if (growing) {
            if (r.nextInt(14) == 1) {
                size += 0.5;
//            System.out.println(id + " growing " + size);
            }
        }
    }

    public double getSize() {
        return size;
    }

    public int getId() {
        return id;
    }

    public void stopGrowing() {
        growing = false;
    }
}
