package bezier.math;

import java.util.List;

public class Vector {
    /**
     * Calculates the dot product of two vectors
     * @param a First vector
     * @param b Second vector
     * @return Dot product
     */
    public static double dot(Vector a, Vector b) {
        return a.x * b.x + a.y * b.y;
    }

    /**
     * Sorts vectors that are on one line
     * @param list List of vectors to be sorted
     */
    public static void sort(List<Vector> list) {
        list.sort((u, v) -> {
            if (u.x != v.x) {
                return v.x > u.x ? 1 : -1;
            } else if (u.y != v.y) {
                return v.y > u.y ? 1 : -1;
            } else {
                return 0;
            }
        });
    }

    private final double EQUAL_DISTANCE = 0.001;

    private double x;
    private double y;

    public Vector(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector setX(double x) {
        return new Vector(x, y);
    }

    public Vector setY(double y) {
        return new Vector(x, y);
    }

    public double magnitude() {
        return Math.sqrt(x * x + y * y);
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector subtract(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector multiply(double n) {
        return new Vector(x * n, y * n);
    }

    public Vector divide(double n) {
        return new Vector(x / n, y / n);
    }

    public Vector unit() {
        double m = magnitude();

        if (m > 0) {
            return divide(magnitude());
        } else {
            return this;
        }
    }

    @Override
    public String toString() {
        return String.format("(%.3f, %.3f)", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector vector = (Vector)obj;
            return Math.abs(vector.x - x) < EQUAL_DISTANCE && Math.abs(vector.y - y) < EQUAL_DISTANCE;
        } else {
            return super.equals(obj);
        }
    }
}
