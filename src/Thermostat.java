import java.util.*;

/**
 * Created by marisayeung on 2/22/16.
 */
public class Thermostat implements TimeListener, InternalTempListener {
    Hashtable<Integer, Double> schedule;
    HVAC hvac;
    double currentSetPoint;
    int minute;

    public Thermostat(HVAC hvac) {
        this.hvac = hvac;
        schedule = new Hashtable<Integer, Double>();
        currentSetPoint = 70.0;
        minute = 0;
    }

    @Override
    public void onNewInternalTemp(double tempInF) {
        if (hvac.isHeatOn() && (tempInF >= (currentSetPoint + 0.9))) {
            hvac.turnHeatOff();
            System.out.printf("HVAC Off  min: %02d  inside: %.2f\n", minute, tempInF);
        } else if (!hvac.isHeatOn() && (tempInF < currentSetPoint)) {
            hvac.turnHeatOn();
            System.out.printf("HVAC On   min: %02d  inside: %.2f\n", minute, tempInF);

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
        this.minute = minute;
        if (minute == 0) {
            if (schedule.containsKey(hour)) {
                currentSetPoint = schedule.get(hour);
            }
        }
    }
}
