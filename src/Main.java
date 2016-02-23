/**
 * Created by Marisa Yeung
 */
public class Main {
    public static void main(String[] args) {
        int DAYS = 2;
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

        ExternalTempSimulator externalTempSimulator = new ExternalTempSimulator();
        timer.addTimeSubscriber(externalTempSimulator);

        double initTemp = 70;
        InternalTempSimulator internalTempSimulator = new InternalTempSimulator(initTemp);
        externalTempSimulator.addTempSubscriber(internalTempSimulator);
        timer.addTimeSubscriber(internalTempSimulator);

        HVAC hvac = new HVAC();
        hvac.setPower(true);
        hvac.addSubscriber(internalTempSimulator);
        timer.addTimeSubscriber(hvac);

        Thermostat thermostat = new Thermostat(hvac);
        g1.addThermostat(thermostat);
        internalTempSimulator.addSubscriber(thermostat);

        for (int i = 0; i < MINUTES; i++) {
            timer.incrementTime();
        }
    }
}
