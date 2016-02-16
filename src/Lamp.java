/**
 * Created by marisayeung on 2/15/16.
 */
public class Lamp {
    private boolean on;

    public Lamp() {
        on = false;
    }

    public double getPAR() {
        if (on) {
            return 1.0;
        }
        else {
            return 0.0;
        }
    }

    public void turnOn() {
        on = true;
    }

    public void turnOff() {
        on = false;
    }
}
