package bezier;

import org.junit.Test;

import static org.junit.Assert.*;

public class CurveMathTest {

    @Test
    public void getLineCoefficients() {
        // diagonal line upwards

        assertArrayEquals(
                new double[]{-1, 1, 0},
                CurveMath.getLineCoefficients(new Vector(0, 0), new Vector(1, 1)),
                0.001
        );

        // diagonal line downwards

        assertArrayEquals(
                new double[]{1, 1, 0},
                CurveMath.getLineCoefficients(new Vector(0, 0), new Vector(1, -1)),
                0.001
        );

        // vertical line

        assertArrayEquals(
                new double[]{-1, 0, 0},
                CurveMath.getLineCoefficients(new Vector(0, 0), new Vector(0, 1)),
                0.001
        );

        // horizontal line

        assertArrayEquals(
                new double[]{0, 1, 0},
                CurveMath.getLineCoefficients(new Vector(0, 0), new Vector(1, 0)),
                0.001
        );
    }

    @Test
    public void getQuadraticEquationRoots() {
        // two roots

        assertArrayEquals(
                new double[]{0, -1},
                CurveMath.getQuadraticEquationRoots(2, 2, 0),
                0.001
        );

        // single root

        assertArrayEquals(
                new double[]{0.5},
                CurveMath.getQuadraticEquationRoots(-4, 4, -1),
                0.001
        );

        // no roots

        assertArrayEquals(
                new double[]{},
                CurveMath.getQuadraticEquationRoots(2, 2, 2),
                0.001
        );
    }
}