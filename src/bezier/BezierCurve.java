package bezier;

import java.util.ArrayList;
import java.util.List;

public class BezierCurve {
    private Vector p1;
    private Vector p2;
    private Vector p3;

    public BezierCurve(Vector p1, Vector p2, Vector p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Vector sample(double u) {
        // (1-u)^2 * p1 + 2u * (1-u) * p2 + u^2 * p3;

        Vector a = p1.multiply(Math.pow(1-u, 2));
        Vector b = p2.multiply(2 * u * (1 - u));
        Vector c = p3.multiply(Math.pow(u, 2));

        return a.add(b).add(c);
    }

    public Vector tangent(double u) {
        // (2u - 2) * p1 + (2 - 4u) * p2 + 2u * p3

        Vector a = p1.multiply(2 * u - 2);
        Vector b = p2.multiply(2 - 4 * u);
        Vector c = p3.multiply(2 * u);

        return a.add(b).add(c);
    }

    public Vector normal(double u) {
        Vector t = tangent(u);
        return new Vector(-t.getY(), t.getX());
    }

    public Vector[] coefficients() {
        return new Vector[]{
                p1.add(p2.multiply(-2)).add(p3),
                p1.multiply(-2).add(p2.multiply(2)),
                p1
        };
    }

    public Vector[] intersection(Vector l1, Vector l2) {
        double[] line = CurveMath.getLineCoefficients(l1, l2);
        Vector[] curve = coefficients();

        double[] roots = CurveMath.getQuadraticEquationRoots(
                line[0] * curve[0].getX() + line[1] * curve[0].getY(),
                line[0] * curve[1].getX() + line[1] * curve[1].getY(),
                line[0] * curve[2].getX() + line[1] * curve[2].getY() + line[2]
        );

        List<Vector> intersections = new ArrayList<>();

        for (int i = 0; i < roots.length; i++) {
            double root = roots[i];

            if (root <= 1 && root >= 0) {
                intersections.add(sample(root));
            }
        }

        return intersections.toArray(new Vector[]{});
    }
}
