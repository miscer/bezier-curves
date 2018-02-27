package bezier;

public class State {
    private BezierCurve curve;

    public State(BezierCurve curve) {
        this.curve = curve;
    }

    public BezierCurve getCurve() {
        return curve;
    }
}
