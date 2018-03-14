package bezier.math;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VectorTest {

    @Test
    public void sort() {
        Vector a = new Vector(2, 2);
        Vector b = new Vector(1, 1);
        Vector c = new Vector(3, 3);

        List<Vector> points = new ArrayList<>();
        points.add(a);
        points.add(b);
        points.add(c);

        Vector.sort(points);

        assertEquals(c, points.get(0));
        assertEquals(a, points.get(1));
        assertEquals(b, points.get(2));
    }
}