package bezier;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer {
    public static void clear(GraphicsContext context) {
        Canvas canvas = context.getCanvas();
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public static void render(GraphicsContext context, Scene scene) {
        renderCurve(context, scene);
    }

    private static void renderCurve(GraphicsContext context, Scene scene) {
        int samples = scene.samplesProperty().get();

        for (int i = 0; i <= samples; i++) {
            double u = (double) i / samples;
            renderPoint(context, scene, u);
        }
    }

    private static void renderPoint(GraphicsContext context, Scene scene, double u) {
        BezierCurve curve = scene.curveProperty().get();
        Vector light = scene.lightProperty().get();

        Vector sample = curve.sample(u);
        Vector normal = curve.normal(u).unit();
        Vector ray = sample.subtract(light).unit();

        double brightness = Math.abs(ray.dot(normal));
        context.setFill(Color.gray(brightness));

        context.fillOval(sample.getX(), sample.getY(), 5, 5);
    }
}
