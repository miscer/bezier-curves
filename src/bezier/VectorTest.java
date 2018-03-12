package bezier;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VectorTest {

    @Test
    public void sort() {
        List<Vector> points = new ArrayList<>();
        points.add(new Vector(2, 2));
        points.add(new Vector(1, 1));
        points.add(new Vector(3, 3));

        Vector.sort(points);


    }
}