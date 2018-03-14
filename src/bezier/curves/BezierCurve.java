package bezier.curves;

import bezier.math.Vector;

import java.util.List;

public abstract class BezierCurve {
    /**
     * Samples the point on the curve at u
     * @param u Value from 0 to 1
     * @return Point on the curve
     */
    public abstract Vector sample(double u);

    /**
     * Calculates the curve tangent vector at u
     * @param u Value from 0 to 1
     * @return Tangent vector
     */
    public abstract Vector tangent(double u);

    /**
     * Calculates the curve normal vector at u
     * @param u Value from 0 to 1
     * @return Normal vector
     */
    public Vector normal(double u) {
        Vector t = tangent(u);
        return new Vector(-t.getY(), t.getX());
    }

    /**
     * Calculates the coefficients of a polynomial corresponding to the curve
     * @return Array of coefficients, e.g. [a, b, c] from ax^2 + bx + c
     */
    public abstract Vector[] coefficients();

    /**
     * Finds the points of intersection between the curve and the line
     * @param l1 A point on the line
     * @param l2 Another point on the line
     * @return All points of intersection
     */
    public abstract List<Vector> intersection(Vector l1, Vector l2);
}
