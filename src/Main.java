/**
 * Created by marisayeung on 2/15/16.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");

        Timer timer = new Timer();

        Greenhouse g1 = new Greenhouse();
        timer.addTimeSubscriber(g1);

        int releaseInterval = 20;
        Tray t1 = new Tray(1, releaseInterval);
        timer.addTimeSubscriber(t1);

        for (int i = 0; i < 1950; i++) {
            timer.incrementTime();
        }
    }
}
