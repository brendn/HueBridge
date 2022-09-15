package dog.brendan.hue.api.model;

public class Light {

    private String id;

    private String type;

    private String name;

    private LightState state;

    public Light(String id, String type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public LightState getState() {
        return state;
    }

    public void setState(LightState state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Light{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
