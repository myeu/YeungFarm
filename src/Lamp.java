/**
 * Created by Marisa Yeung
 */
public class Lamp {
    private boolean on;
    private boolean masterOff;

    public Lamp() {
        on = false;
        masterOff = true;
    }

    public double getPAR() {
        if (on) {
            return 1.5;
        }
        else {
            return 0.0;
        }
    }

    public void turnOn() {
        if (!masterOff) {
            on = true;
        }
    }

    public void turnOff() {
        on = false;
    }

    public boolean isOn() {
        return on;
    }

    public void setPower(boolean setting) {
        if (setting) {
            masterOff = false;
        } else {
            on = false;
            masterOff = true;
        }
    }
}
