package bezier;

public class Vector {
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

    public double dot(Vector other) {
        return x * other.x + y * other.y;
    }

    @Override
    public String toString() {
        return String.format("(%.3f, %.3f)", x, y);
    }
}
