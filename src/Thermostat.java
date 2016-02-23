import java.util.*;

/**
 * Created by marisayeung on 2/22/16.
 */
public class Thermostat implements TimeListener, InternalTempListener {
    Hashtable<Integer, Double> schedule;
    HVAC hvac;
    double currentSetPoint;

    public Thermostat(HVAC hvac) {
        this.hvac = hvac;
        schedule = new Hashtable<Integer, Double>();
        currentSetPoint = 70.0;
    }

    @Override
    public void onNewInternalTemp(double tempInF) {
        if (hvac.isHeatOn() && (tempInF >= currentSetPoint)) {
            hvac.turnHeatOff();
        } else if (!hvac.isHeatOn() && (tempInF < currentSetPoint)) {
            hvac.turnHeatOn();
        }
    }

    public void setCurrentSetPoint(double tempInF) {
        currentSetPoint = tempInF;
    }

    public void addScheduleTemp(int hour, double tempInF) {
        schedule.put(hour, tempInF);
    }

    public boolean removeScheduleTemp(int hour) {
        if (schedule.containsKey(hour)) {
            schedule.remove(hour);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {
        if (minute == 0) {
            if (schedule.containsKey(hour)) {
                currentSetPoint = schedule.get(hour);
            }
        }
    }
}
