package bezier;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {
    public static void renderState(GraphicsContext context, State state) {
        renderCurve(context, state.getLight(), state.getCurve());
        renderLight(context, state.getLight());
    }

    public static void renderCurve(GraphicsContext context, Vector light, BezierCurve curve) {
        for (int i = 0; i <= 30; i++) {
            double u = (double) i / 30;
            renderPoint(context, light, curve, u);
        }
    }

    public static void renderPoint(GraphicsContext context, Vector light, BezierCurve curve, double u) {
        Vector sample = curve.sample(u);
        Vector normal = curve.normal(u).unit();
        Vector ray = sample.subtract(light).unit();

        double brightness = Math.abs(ray.dot(normal));
        context.setFill(Color.gray(brightness));

        context.fillOval(sample.getX(), sample.getY(), 5, 5);
    }

    public static void renderLight(GraphicsContext context, Vector light) {
        context.setFill(Color.YELLOW);
        context.fillOval(light.getX(), light.getY(), 10, 10);
    }
}
