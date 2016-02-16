/**
 * Created by marisayeung on 2/15/16.
 */
public class Tray implements TimeListener{
    int id;
    int releaseCount;
    int releaseCounter;

    public Tray(int newId, int newReleaseCount) {
        id = newId;
        releaseCount = newReleaseCount;
        releaseCounter = releaseCount;
    }

    @Override
    public void onNewTime(int day, int hour, int minute) {
        if (releaseCounter == 1) {
            releaseSolution(day, hour, minute);
            releaseCounter = releaseCount;
        } else {
            releaseCounter--;
        }

    }

    public void releaseSolution(int day, int hour, int minute) {
        //System.out.println("<Time> " + day + " " + hour + ":" + minute + " <Tray " + id + "> Solution released");
    }
}
