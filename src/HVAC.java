/**
 * Created by marisayeung on 2/22/16.
 */
public class HVAC {

    boolean heatOn;
    boolean masterOff;

    public HVAC() {
        masterOff = true;
        heatOn = false;
    }

    public void setPower(boolean setting) {
        if (setting) {
            masterOff = false;
        } else {
            heatOn = false;
            masterOff = true;
        }
    }

    public boolean isHeatOn() {
        return heatOn;
    }

    public void turnHeatOn() {
        if (!masterOff) {
            heatOn = true;
        }
    }

    public void turnHeatOff() {
        heatOn = false;
    }
}
