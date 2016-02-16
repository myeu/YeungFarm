import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.Random;

/*
    simplifying seasons: assume 360 days a year
 */

/**
 * Created by marisayeung on 2/15/16.
 */

public class Sun implements TimeListener {
    static int SUNRISE = 7;
    static int SUNSET = 19;
    static String WINTER = "winter";
    static String SUMMER = "summer";

    private boolean up;
    private String season;

    private double PAR;

    private Random summer;
    private NormalDistribution winter;

    public Sun() {
        //summer = new NormalDistribution(1.58, 1.6);
        //winter = new NormalDistribution(0.6, .6);
        summer = new Random();
        season = WINTER;
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {
        // save whether sun is risen at the farm
        if (!up && hour == SUNRISE) {
            up = true;
        } else if(up && hour == SUNSET) {
            up = false;
        }

        // simulate a new PAR amount every hour
        if (minute == 0) {
            generatePAR();
        }


        // after about half a year, switch season
        if (season == WINTER && (day % 180) == 0) {
            season = SUMMER;
        }
    }

    private void generatePAR() {
        // radiate onto farm only during the day
        if (up) {
            PAR = (summer.nextDouble() * 3);
        } else {
            // night
            PAR = 0.0;
        }
    }

    /*
        Return the current photosynthetically active radiation (PAR)
        Called by sensors in the greenhouses
     */
    public double getPAR() {
        return PAR;
    }

}
