/**
 * Created by Marisa Yeung
 *
 * consider using PPF instead of PAR, since PPFD is whats actually what a PAR meter measures
 *    PPFD is Photosynthetic Photon Flux Density
 */

public class LightSensor implements TimeListener{
    Sun sun;
    double sunPAR;
    double totalSun;

    Lamp lamp;
    double lampPAR;
    double totalLamp;

    Shade shade;
    double totalShade;

    // Daily light integral is the amount of PAR (radiation good for plants) per square meter, per day
    double DLI;
    double setPoint;

    public LightSensor(Sun sun, Lamp lamp, Shade shade, double setPoint) {
        this.sun = sun;
        this.lamp = lamp;
        this.shade = shade;
        this.setPoint = setPoint;
        DLI = 0.0;
        totalSun = 0.0;
        totalLamp = 0.0;
        totalShade = 0.0;
    }

    // Keep a running total of the PAR for the day
    private void samplePAR() {
        double rawSun = sun.getPAR();
        if (shade.isShading()) {
            sunPAR = rawSun * shade.filter();

            totalSun += rawSun;
            totalShade += (sun.getPAR() * (1 - shade.filter()));
        } else {
            sunPAR = sun.getPAR();
            totalSun += rawSun;
        }

        lampPAR = lamp.getPAR();
        totalLamp += lampPAR;

        DLI += (sunPAR + lampPAR);
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {
        // Reset Daily light integral for a new day
        if (hour == 23 && minute == 59) {
            System.out.printf("Day %02d  DLI: %02.2f  Sun: %.2f  Shade: %.2f  Lamp: %.2f\n", day, DLI, totalSun, totalShade, totalLamp);
            DLI = 0.0;
            totalSun = 0.0;
            totalShade = 0.0;
            totalLamp = 0.0;
            // Always turn lamps off at midnight
            lamp.turnOff();
        }

        // Always put shades away when sun is down
        if (minute == 0 && hour == Sun.SUNSET) {
            shade.unCover();
        }

        // measure the hourly PAR
        if (minute == 1) {
            samplePAR();
            checkSetPoint(hour);
        }
    }

    private void checkSetPoint(int hour) {
        // Check whether to put shades down
        if (!shade.isShading() && (Sun.SUNRISE < hour) && (hour < Sun.SUNSET)) {
            if (setPoint < DLI) {
//                System.err.println("shades shading: " + hour);
                shade.cover();
            }
        }

        // Check whether to turn on lamps
        if (!lamp.isOn() && (Sun.SUNSET < hour) && (hour <= 23)) {
            if (setPoint > DLI) {
//                System.err.println("lamp turn on: " + hour);
                lamp.turnOn();
            }
        }

        // Check whether to turn off lamps
        if (lamp.isOn() && (setPoint < DLI)) {
//            System.err.println("lamp turn off: " + hour);
            lamp.turnOff();
        }
    }
}
