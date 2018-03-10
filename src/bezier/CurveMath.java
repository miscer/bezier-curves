package bezier;

public class CurveMath {
    public static double[] getLineCoefficients(Vector p1, Vector p2) {
        double a = p1.getY() - p2.getY();
        double b = p2.getX() - p1.getX();
        double c = p1.getX() * p2.getY() - p2.getX() * p1.getY();

        return new double[]{a, b, c};
    }

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
}
