package bezier;

import java.util.List;

public abstract class BezierCurve {
    public abstract Vector sample(double u);

    public abstract Vector tangent(double u);

    public Vector normal(double u) {
        Vector t = tangent(u);
        return new Vector(-t.getY(), t.getX());
    }

    public abstract Vector[] coefficients();

    public abstract List<Vector> intersection(Vector l1, Vector l2);
}
