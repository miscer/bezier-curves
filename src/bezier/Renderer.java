package bezier;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Arrays;

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
        BezierCurve curve = scene.getCurve();
        Vector light = scene.getLight();

        Vector sample = curve.sample(u);
        Vector normal = curve.normal(u).unit();
        Vector ray = sample.subtract(light).unit();

        double brightness = isIlluminated(scene, sample) ? Math.abs(ray.dot(normal)) : 0;
        context.setFill(Color.gray(brightness));

        context.fillOval(sample.getX(), sample.getY(), 5, 5);
    }

    private static boolean isIlluminated(Scene scene, Vector point) {
        Vector light = scene.getLight();
        Vector[] intersections = scene.getCurve().intersection(light, point);

        if (intersections.length == 2) {
            Vector v1 = light.subtract(intersections[0]);
            Vector v2 = light.subtract(intersections[1]);

            double d0 = light.subtract(point).magnitude();
            double d1 = v1.magnitude();
            double d2 = v2.magnitude();

            return (Math.abs(d0 - d1) < 1 && d1 < d2) || (Math.abs(d0 - d2) < 1 && d2 < d1);
        } else if (intersections.length == 1) {
            return true;
        } else {
            return false;
        }
    }
}
