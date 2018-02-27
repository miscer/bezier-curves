package bezier;

public class State {
    private BezierCurve curve;
    private Vector light;

    public State(BezierCurve curve, Vector light) {
        this.curve = curve;
        this.light = light;
    }

    public BezierCurve getCurve() {
        return curve;
    }

    public Vector getLight() {
        return light;
    }
}
