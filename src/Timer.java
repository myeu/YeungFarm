import java.util.ArrayList;

/**
 * Created by marisayeung on 2/15/16.
 */
public class Timer {
    int MINUTES_IN_HOUR = 59;
    int HOURS_IN_DAY = 23;

    ArrayList<TimeListener> subscribers;

    int day;
    int hour;
    int minute;

    public Timer() {
        subscribers = new ArrayList<TimeListener>();
        hour = 0;
        minute = 0;
        day = 0;
    }

    public void addTimeSubscriber(TimeListener listener) {
    }

    public void incrementTime() {
        addMinute();
        for (TimeListener listener : subscribers) {
            listener.onNewTime(day, hour, minute);
        }
    }

    private void addMinute() {
        if (minute == MINUTES_IN_HOUR) {
            minute = 0;
            addHour();
        }
    }

    private void addHour() {
        if (hour == HOURS_IN_DAY) {
            hour = 0;
            day += 1;
        }
    }
}
