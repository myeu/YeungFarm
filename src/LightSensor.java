/**
 * Created by marisayeung on 2/15/16.
 *
 * consider using PPF instead of PAR, since PPFD is whats actually what a PAR meter measures
 *    PPFD is Photosynthetic Photon Flux Density
 */

public class LightSensor implements TimeListener{
    Sun sun;
    double sunPAR;

    Lamp lamp;
    double lampPAR;

    Shade shade;

    // Daily light integral is the amount of PAR (radiation good for plants) per square meter, per day
    double DLI;
    double totalShade;

    public LightSensor(Sun sun, Lamp lamp, Shade shade) {
        this.sun = sun;
        this.lamp = lamp;
        this.shade = shade;
        DLI = 0.0;
    }

    // Keep a running total of the PAR for the day
    private void samplePAR() {
        if (shade.isShading()) {
            sunPAR = sun.getPAR() * shade.filter();
            totalShade += (sun.getPAR() * (1 - shade.filter()));
        } else {
            sunPAR = sun.getPAR();
        }

        lampPAR = lamp.getPAR();

        DLI += (sunPAR + lampPAR);
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {
        // Reset Daily light integral for a new day
        if (hour == 23 && minute == 59) {
            System.out.printf("Day %02d  DLI: %02.2f Shade: %.2f\n", day, DLI, totalShade);
            DLI = 0.0;
            totalShade = 0.0;
        }

        if (minute == 0 && hour == (Sun.SUNRISE + 7)) {
            shade.cover();
        }

        if (minute == 0 && hour == Sun.SUNSET) {
            shade.unCover();
        }

        // measure the hourly PAR
        if (minute == 1) {
            samplePAR();
        }
    }
}
