/**
 * Created by Marisa Yeung
 */
public class Shade {
    private boolean shading;

    public Shade() {
        shading = false;
    }

    public void cover() {
        shading = true;
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
}
