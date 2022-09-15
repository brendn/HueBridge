package dog.brendan.hue.api.model;

public class LightState {

    private boolean on;

    private boolean reachable;

    private int bri;

    private int hue;

    private int sat;

    public LightState(boolean on, boolean reachable, int bri, int hue, int sat) {
        this.on = on;
        this.reachable = reachable;
        this.bri = bri;
        this.hue = hue;
        this.sat = sat;
    }

    public boolean isOn() {
        return on;
    }

    public boolean isReachable() {
        return reachable;
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
