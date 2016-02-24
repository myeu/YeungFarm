import java.util.ArrayList;
import java.util.List;

/**
 * Created by marisayeung on 2/22/16.
 */
public class HVAC implements TimeListener{

    List<HVACListener> subscribers;

    boolean heatOn;
    boolean masterOff;

    double HEAT = 0.5;

    public HVAC() {
        subscribers = new ArrayList<HVACListener>();
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
//            System.out.println("HVAC On");
        }
    }

    public void turnHeatOff() {
        heatOn = false;
//        System.out.println("HVAC Off");
    }

    public void addSubscriber(HVACListener listener) {
        subscribers.add(listener);
    }

    public void heat() {
        for (HVACListener listener: subscribers) {
            listener.onNewHVACTemp(HEAT);
        }
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {
        if (isHeatOn()) {
            heat();
        }
    }
}
