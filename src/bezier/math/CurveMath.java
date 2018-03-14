package bezier.math;

public class CurveMath {
    /**
     * Calculates the coefficients of a general form line equation (ax + by + c = 0)
     * @param p1 A point on the line
     * @param p2 Another point on the line
     * @return Array containing the values of a, b and c
     */
    public static double[] getLineCoefficients(Vector p1, Vector p2) {
        double a = p1.getY() - p2.getY();
        double b = p2.getX() - p1.getX();
        double c = p1.getX() * p2.getY() - p2.getX() * p1.getY();

        return new double[]{a, b, c};
    }

    /**
     * Calculates the real roots of a quadratic equation
     * @param a First coefficient
     * @param b Second coefficient
     * @param c Third coefficient
     * @return Array of all roots
     */
    public static double[] getQuadraticEquationRoots(double a, double b, double c) {
        double discriminant = Math.pow(b, 2) - 4 * a * c;

        if (discriminant > 0) {
            return new double[]{
                    (-b + Math.sqrt(discriminant)) / (2 * a),
                    (-b - Math.sqrt(discriminant)) / (2 * a)
            };
        } else if (discriminant == 0) {
            return new double[]{
                    -b / (2 * a)
            };
        } else {
            return new double[]{};
        }
    }

    /**
     * Calculates the real roots of a cubic equation
     *
     * Code taken from Parallel Java Library by Alan Kaminsky
     * https://www.cs.rit.edu/~ark/pj.shtml
     * @param a First coefficient
     * @param b Second coefficient
     * @param c Third coefficient
     * @param d Fourth coefficient
     * @return Array of all roots
     */
    public static double[] getCubicEquationRoots(double a, double b, double c, double d) {
        // Verify preconditions.
        if (a == 0.0) {
            return getQuadraticEquationRoots(b, c, d);
        }

        // Normalize coefficients.
        double denom = a;
        a = b / denom;
        b = c / denom;
        c = d / denom;

        // Commence solution.
        double a_over_3 = a / 3.0;
        double Q = (3 * b - a * a) / 9.0;
        double Q_CUBE = Q * Q * Q;
        double R = (9 * a * b - 27 * c - 2 * a * a * a) / 54.0;
        double R_SQR = R * R;
        double D = Q_CUBE + R_SQR;

        if (D < 0.0) {
            // Three unequal real roots.
            double theta = Math.acos(R / Math.sqrt(-Q_CUBE));
            double SQRT_Q = Math.sqrt(-Q);
            double x1 = 2.0 * SQRT_Q * Math.cos(theta / 3.0) - a_over_3;
            double x2 = 2.0 * SQRT_Q * Math.cos((theta + 2.0 * Math.PI) / 3.0) - a_over_3;
            double x3 = 2.0 * SQRT_Q * Math.cos((theta + 4.0 * Math.PI) / 3.0) - a_over_3;

            return new double[]{x1, x2, x3};
        } else if (D > 0.0) {
            // One real root.
            double SQRT_D = Math.sqrt(D);
            double S = Math.cbrt(R + SQRT_D);
            double T = Math.cbrt(R - SQRT_D);
            double x1 = (S + T) - a_over_3;

            return new double[]{x1};
        } else {
            // Three real roots, at least two equal.
            double CBRT_R = Math.cbrt(R);
            double x1 = 2 * CBRT_R - a_over_3;
            double x2 = CBRT_R - a_over_3;

            if (Double.compare(x1, x2) == 0) {
                return new double[]{x1};
            } else {
                return new double[]{x1, x2};
            }
        }
    }


}
