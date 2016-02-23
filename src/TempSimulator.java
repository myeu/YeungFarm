import java.util.ArrayList;
import java.util.List;

/**
 * Created by marisayeung on 2/22/16.
 */
public class TempSimulator implements TimeListener{

    List<ExternalTempListener> listeners;


    public TempSimulator() {
        listeners = new ArrayList<ExternalTempListener>();
    }

    public void addTempSubscriber(ExternalTempListener listener) {
        listeners.add(listener);
    }


    @Override
    public void onNewTime(int day, int hour, int minute) {

    }
}
