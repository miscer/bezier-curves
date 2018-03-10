package bezier;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class BezierCurveTest {

    @Test
    public void intersectionWithVerticalLine() {
        BezierCurve curve = new BezierCurve(new Vector(-1, 0), new Vector(0, 0), new Vector(1, 0));
        Vector[] intersections = curve.intersection(new Vector(0, -1), new Vector(0, 1));

        assertEquals(1, intersections.length);
        assertEquals(0, intersections[0].getX(), 0.1);
        assertEquals(0, intersections[0].getY(), 0.1);
    }

    @Test
    public void intersectionWithHorizontalLine() {
        BezierCurve curve = new BezierCurve(new Vector(-1, 0), new Vector(0, 1), new Vector(1, 0));
        Vector[] intersections = curve.intersection(new Vector(-1, 0.2), new Vector(1, 0.2));

//        assertEquals(2, intersections.length);
//        assertEquals(0, intersections[0].getX(), 0.1);
//        assertEquals(0, intersections[0].getY(), 0.1);
    }

    @Test
    public void coefficients() {
        BezierCurve curve = new BezierCurve(new Vector(0, 0), new Vector(1, 1), new Vector(2, 0));
        Vector[] coefficients = curve.coefficients();

        for (double u = 0; u <= 1; u += 0.1) {
            Vector p1 = curve.sample(u);

            Vector a = coefficients[0].multiply(u * u);
            Vector b = coefficients[1].multiply(u);
            Vector c = coefficients[2];
            Vector p2 = a.add(b).add(c);

            assertEquals(p1.getX(), p2.getX(), 0.001);
            assertEquals(p1.getY(), p2.getY(), 0.001);
        }
    }
}