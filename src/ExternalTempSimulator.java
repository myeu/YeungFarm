import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Random;

/**
 * Created by marisayeung on 2/22/16.
 */
public class ExternalTempSimulator implements TimeListener{

    double[] MIN;
    double[] MAX;
    int DAY = 0;
    int NIGHT = 1;

    double DAY_MIN = 60;
    double DAY_MAX = 80;
    double NIGHT_MIN = 40;
    double NIGHT_MAX = 70;

    List<ExternalTempListener> subscribers;

    double dayTempInF;
    double nightTempInF;

    double currentTempInF;

    Random tempSimulator = new Random();
    Random signSimulator = new Random();

    public ExternalTempSimulator() {
        subscribers = new ArrayList<ExternalTempListener>();

        MIN = new double[2];
        MAX = new double[2];

        MIN[DAY] = DAY_MIN;
        MIN[NIGHT] = NIGHT_MIN;
        MAX[DAY] = DAY_MAX;
        MAX[NIGHT] = NIGHT_MAX;

        Random r = new Random();
        currentTempInF = MIN[NIGHT] + (MAX[NIGHT] - MIN[NIGHT]) * r.nextDouble();
    }

    public void addTempSubscriber(ExternalTempListener listener) {
        subscribers.add(listener);
    }

    private void changeTemp(double tempInF) {
        currentTempInF = tempInF;
        for (ExternalTempListener listener: subscribers) {
            listener.onNewExternalTemp(currentTempInF);
        }
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {
        if (hour == 0 && minute == 0) {
            Random r = new Random();
            dayTempInF = MIN[DAY] + (MAX[DAY] - MIN[DAY]) * r.nextDouble();
            nightTempInF = MIN[NIGHT] + (MAX[NIGHT] - MIN[NIGHT]) * r.nextDouble();
        }

        double temp;
        if (hour == Sun.SUNRISE && minute == 0) {
            temp = currentTempInF + 5;
            if (temp < MAX[DAY]) {
                changeTemp(temp);
            }
        }
        if (hour == Sun.SUNSET  && minute == 0) {
            temp = currentTempInF - 5;
            if (temp > MIN[NIGHT]) {
                changeTemp(temp);
            }
        }

//        simulateTemp(hour);
        if ((minute % 10) == 0) {
            simulateTemp(hour);
            //changeTemp(currentTempInF);
            //System.out.printf("Day %02d  Hour %02d  Outside: %.2f F ", day, hour, currentTempInF);
        }
    }

    private void simulateTemp(int hour) {
        int dayState;
        if (hour > Sun.SUNRISE && hour < Sun.SUNSET) {
            dayState = DAY;
        } else {
            dayState = NIGHT;
        }

        double temp;
        if (signSimulator.nextBoolean()) {
            temp = currentTempInF + tempSimulator.nextDouble();
            if (temp <= MAX[dayState]) {
                changeTemp(temp);
                //System.out.println("change +");
            }
        } else {
            temp = currentTempInF - tempSimulator.nextDouble();
            if (temp >= MIN[dayState]) {
                changeTemp(temp);
                //System.out.println("change -");
            }
        }

    }
}
