import java.util.ArrayList;
import java.util.List;

/**
 * Created by marisayeung on 2/23/16.
 */
public class InternalTempSimulator implements ExternalTempListener, HVACListener, TimeListener {

    double currentTempInF;
    double currentExternalTemp;

    List<InternalTempListener> subscribers;

    public InternalTempSimulator(double initialTemp) {
        currentTempInF = initialTemp;
        subscribers = new ArrayList<InternalTempListener>();
    }

    @Override
    public void onNewExternalTemp(double tempInF) {
        boolean mod = true;
        changeTemp(tempInF, mod);
        currentExternalTemp = tempInF;
    }

    @Override
    public void onNewHVACTemp(double tempInF) {
        boolean mod = false;
        changeTemp(currentTempInF += tempInF, mod);
    }

    synchronized private void changeTemp(double tempInF, boolean modulate) {
        if (modulate) {
            double modulation = (tempInF - currentTempInF) / 20;
            currentTempInF += modulation;
        } else {
            currentTempInF = tempInF;
        }
        for (InternalTempListener listener: subscribers) {
            listener.onNewInternalTemp(currentTempInF);
        }
//        System.out.printf("Inside: %.2f F  mod: %.2f\n", currentTempInF, modulation);
//        System.out.printf("Inside: %.2f F\n", currentTempInF);
    }

    public void addSubscriber(InternalTempListener listener) {
        subscribers.add(listener);
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {
        if ((minute % 10) == 0) {
//            System.out.printf("Day %02d  Hour %02d  Outside: %.2f F  Inside: %.2f\n", day, hour, currentExternalTemp, currentTempInF);
        }
    }
}
