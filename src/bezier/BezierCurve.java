package bezier;

public class BezierCurve {
    private Vector p1;
    private Vector p2;
    private Vector p3;

    public BezierCurve(Vector p1, Vector p2, Vector p3) {
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    public Vector[] interpolate(int samples) {
        if (samples <= 0) {
            throw new IllegalArgumentException("Number of samples has to be greater than zero");
        }

        Vector[] results = new Vector[samples];

        for (int i = 0; i < samples; i++) {
            double u = (double) i / samples;
            results[i] = sample(u);
        }

        return results;
    }

    public Vector sample(double u) {
        // (1-u)^2 * p1 + 2u * (1-u) * p2 + u^2 * p3;

        Vector a = p1.multiply(Math.pow(1-u, 2));
        Vector b = p2.multiply(2 * u * (1 - u));
        Vector c = p3.multiply(Math.pow(u, 2));

        return a.add(b).add(c);
    }
}
