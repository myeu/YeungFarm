import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marisa Yeung
 */
public class Greenhouse implements TimeListener {
    double setPointDLI;
    int numTrays;
    List<Tray> trays;

    @Override
    public void onNewTime(int day, int hour, int minute) {
        //System.out.println("<Time> " + day + " " + hour + ":" + minute);

    }

    public Greenhouse(Timer timer, Sun sun, int numTrays, int DLI) {
        this.numTrays = numTrays;
        setPointDLI = (double) DLI;
        trays = new ArrayList<Tray>();

        Lamp lamp = new Lamp();
        Shade shade = new Shade();
        LightSensor lightSensor = new LightSensor(sun, lamp, shade, setPointDLI);
        timer.addTimeSubscriber(lightSensor);

    }

    public void addTray(Tray tray) {
        trays.add(tray);
    }

    public double getSetPointDLI() {
        return setPointDLI;
    }

    public void addThermostat(Thermostat thermostat) {

    }

}
