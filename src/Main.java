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

        int numberOfTrays = 1;
        int dailyLightInterval = 18;
        Greenhouse g1 = new Greenhouse(timer, sun, numberOfTrays, dailyLightInterval);
        timer.addTimeSubscriber(g1);
        System.out.println(g1.getSetPointDLI() + "");

        int releaseInterval = 20;
        int id = 1;
        Tray t1 = new Tray(id, releaseInterval);
        System.out.println(t1.getId() + "");
        g1.addTray(t1);
        timer.addTimeSubscriber(t1);

        for (int i = 0; i < MINUTES; i++) {
            timer.incrementTime();
        }
    }
}
