package bezier.ui;

import bezier.curves.BezierCurve;
import bezier.math.Vector;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

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

        double brightness = isIlluminated(scene, sample) ? Math.abs(Vector.dot(ray, normal)) : 0;
        context.setFill(Color.gray(brightness));

        context.fillOval(sample.getX() - 2, sample.getY() - 2, 4, 4);
    }

    private static boolean isIlluminated(Scene scene, Vector point) {
        Vector light = scene.getLight();

        // find all intersection points and add the light point
        List<Vector> points = scene.getCurve().intersection(light, point);
        points.add(light);

        // sort the points on the line from the light to the point
        Vector.sort(points);

        // check if the light and the point are next to each other and there is no other point between them
        int lightIndex = points.indexOf(light);
        int pointIndex = points.indexOf(point);

        return Math.abs(lightIndex - pointIndex) == 1;
    }
}
