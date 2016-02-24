import java.util.ArrayList;

/**
 * Created by Marisa Yeung
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
        subscribers.add(listener);
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
        } else {
            minute++;
        }
    }

    private void addHour() {
        if (hour == HOURS_IN_DAY) {
            hour = 0;
            day++;
        } else {
            hour++;
        }
    }
}
