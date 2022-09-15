package dog.brendan.hue.hue.model;

public class LightState {

    private boolean on;

    private boolean reachable;

    private int bri;

    private int hue;

    private int sat;

    public boolean isOn() {
        return on;
    }

    public void setOn(boolean on) {
        this.on = on;
    }

    public boolean isReachable() {
        return reachable;
    }

    public void setReachable(boolean reachable) {
        this.reachable = reachable;
    }

    public int getBri() {
        return bri;
    }

    public int getHue() {
        return hue;
    }

    public int getSat() {
        return sat;
    }

    @Override
    public String toString() {
        return "LightState{" +
                "on=" + on +
                ", reachable=" + reachable +
                ", bri=" + bri +
                ", hue=" + hue +
                ", sat=" + sat +
                '}';
    }
}
