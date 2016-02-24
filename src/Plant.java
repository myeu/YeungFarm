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
        reservoir.useNutrients(0.01 * size);
        reservoir.useWater(0.006 * size);

        if (growing) {
            if (r.nextInt(14) == 1) {
                size += 1;
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
