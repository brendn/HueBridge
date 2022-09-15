package dog.brendan.hue.exception;

public class LightNotFoundException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public LightNotFoundException(String s) {
        super(s);
    }

    public LightNotFoundException() {
        super("Light not found!");
    }
}
