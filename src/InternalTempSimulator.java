import java.util.ArrayList;
import java.util.List;

/**
 * Created by marisayeung on 2/23/16.
 */
public class InternalTempSimulator implements ExternalTempListener, HVACListener{

    double currentTemp;

    List<InternalTempListener> subscribers;

    public InternalTempSimulator(double initialTemp) {
        currentTemp = initialTemp;
        subscribers = new ArrayList<InternalTempListener>();
    }

    @Override
    public void onNewExternalTemp(double tempInF) {
        changeTemp(tempInF);
    }

    @Override
    public void onNewHVACTemp(double tempInF) {
        changeTemp(tempInF);
    }

    synchronized private void changeTemp(double tempInF) {
        double modulation = (tempInF - currentTemp) / 4;
        currentTemp += modulation;
        for (InternalTempListener listener: subscribers) {
            listener.onNewInternalTemp(currentTemp);
        }
    }
}
