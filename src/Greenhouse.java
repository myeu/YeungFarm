/**
 * Created by marisayeung on 2/15/16.
 */
public class Greenhouse implements TimeListener {
    int DLI_goal;
    int numTrays;

    @Override
    public void onNewTime(int day, int hour, int minute) {
        //System.out.println("<Time> " + day + " " + hour + ":" + minute);

    }

    public Greenhouse(Timer timer, Sun sun, int numTrays, int DLI) {
        Lamp lamp = new Lamp();
        Shade shade = new Shade();
        LightSensor lightSensor = new LightSensor(sun, lamp, shade);

        timer.addTimeSubscriber(lightSensor);

        this.numTrays = numTrays;
        this.DLI_goal = DLI;

    }

}
