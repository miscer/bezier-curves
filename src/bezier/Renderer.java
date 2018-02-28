package bezier;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {
    public static void renderState(GraphicsContext context, State state) {
        renderCurve(context, state);
        renderLight(context, state);
    }

    public static void renderCurve(GraphicsContext context, State state) {
        int samples = state.samplesProperty().get();

        for (int i = 0; i <= samples; i++) {
            double u = (double) i / samples;
            renderPoint(context, state, u);
        }
    }

    public static void renderPoint(GraphicsContext context, State state, double u) {
        BezierCurve curve = state.curveProperty().get();
        Vector light = state.lightProperty().get();

        Vector sample = curve.sample(u);
        Vector normal = curve.normal(u).unit();
        Vector ray = sample.subtract(light).unit();

        double brightness = Math.abs(ray.dot(normal));
        context.setFill(Color.gray(brightness));

        context.fillOval(sample.getX(), sample.getY(), 5, 5);
    }

    public static void renderLight(GraphicsContext context, State state) {
        Vector light = state.lightProperty().get();

        context.setFill(Color.YELLOW);
        context.fillOval(light.getX(), light.getY(), 10, 10);
    }
}
