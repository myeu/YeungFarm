/**
 * Created by Marisa Yeung
 */
public class Shade {
    private boolean shading;
    private boolean masterOff;

    public Shade() {
        shading = false;
        masterOff = true;
    }

    public void cover() {
        if (!masterOff) {
            shading = true;
        }
    }

    public void unCover() {
        shading = false;
    }

    public boolean isShading() {
        return shading;
    }

    public double filter() {
        if (shading) {
            return 0.35;
        } else {
            return 0.0;
        }
    }

    public void setPower(boolean setting) {
        if (setting) {
            masterOff = false;
        } else {
            shading = false;
            masterOff = true;
        }
    }
}
