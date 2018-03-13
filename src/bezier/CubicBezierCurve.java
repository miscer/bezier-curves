package bezier;

import java.util.ArrayList;
import java.util.List;

public class CubicBezierCurve extends BezierCurve {
    private Vector p1;
    private Vector p2;
    private Vector p3;
    private Vector p4;

    public CubicBezierCurve(Vector p1, Vector p2, Vector p3, Vector p4) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
        this.p4 = p4;
    }

    @Override
    public Vector sample(double u) {
        // (1-u)^3 * p1 + 3u * (1-u)^2 * p2 + 3u^2 * (1-u) * p3 + u^3 * p4;

        Vector a = p1.multiply(Math.pow(1 - u, 3));
        Vector b = p2.multiply(3 * u * Math.pow(1 - u, 2));
        Vector c = p3.multiply(3 * Math.pow(u, 2) * (1 - u));
        Vector d = p4.multiply(Math.pow(u, 3));

        return a.add(b).add(c).add(d);
    }

    @Override
    public Vector tangent(double u) {
        Vector a = p1.multiply(-3 * Math.pow(1 - u, 2));
        Vector b = p2.multiply(3 * (3 * Math.pow(u, 2) - 4 * u + 1));
        Vector c = p3.multiply(3 * (-3 * Math.pow(u, 2) + 2 * u));
        Vector d = p4.multiply(3 * Math.pow(u, 2));

        return a.add(b).add(c).add(d);
    }

    @Override
    public Vector[] coefficients() {
        return new Vector[]{
                p1.multiply(-1).add(p2.multiply(3)).add(p3.multiply(-3)).add(p4),
                p1.multiply(3).add(p2.multiply(-6)).add(p3.multiply(3)),
                p1.multiply(-3).add(p2.multiply(3)),
                p1
        };
    }

    @Override
    public List<Vector> intersection(Vector l1, Vector l2) {
        double[] line = CurveMath.getLineCoefficients(l1, l2);
        Vector[] curve = coefficients();

        double[] roots = CurveMath.getCubicEquationRoots(
                line[0] * curve[0].getX() + line[1] * curve[0].getY(),
                line[0] * curve[1].getX() + line[1] * curve[1].getY(),
                line[0] * curve[2].getX() + line[1] * curve[2].getY(),
                line[0] * curve[3].getX() + line[1] * curve[3].getY() + line[2]
        );

        List<Vector> intersections = new ArrayList<>();

        for (double root : roots) {
            if (root <= 1 && root >= 0) {
                intersections.add(sample(root));
            }
        }

        return intersections;
    }
}
