package bezier;

import javafx.scene.canvas.GraphicsContext;

public class Renderer {
    public static void renderState(GraphicsContext context, State state) {
        BezierCurve curve = state.getCurve();

        if (curve != null) {
            renderCurve(context, curve);
        }
    }

    public static void renderCurve(GraphicsContext context, BezierCurve curve) {
        Vector[] samples = curve.interpolate(30);

        Vector first = samples[0];
        context.moveTo(first.getX(), first.getY());

        for (int i = 1; i < samples.length; i++) {
            Vector sample = samples[i];
            context.lineTo(sample.getX(), sample.getY());
        }

        context.stroke();
    }
}
