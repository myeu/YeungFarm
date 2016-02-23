/**
 * Created by Marisa Yeung
 */
public class Main {
    public static void main(String[] args) {
        int DAYS = 30;
        int MINUTES = (DAYS * 60 * 24);

        Timer timer = new Timer();

        Sun sun = new Sun();
        timer.addTimeSubscriber(sun);

        Greenhouse g1 = new Greenhouse(timer, sun, 1, 18);
        timer.addTimeSubscriber(g1);

        int releaseInterval = 20;
        Tray t1 = new Tray(1, releaseInterval);
        timer.addTimeSubscriber(t1);

        for (int i = 0; i < MINUTES; i++) {
            timer.incrementTime();
        }
    }
}
