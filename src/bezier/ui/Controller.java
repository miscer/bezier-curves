package bezier.ui;

import bezier.curves.BezierCurve;
import bezier.curves.CubicBezierCurve;
import bezier.curves.QuadraticBezierCurve;
import bezier.math.Vector;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    public Slider samples;
    public ToggleGroup type;
    public RadioButton quadratic;
    public RadioButton cubic;
    public Pane container;

    private DragPoint light;
    private DragPoint[] points;
    private ObjectBinding<BezierCurve> curve;
    private Scene scene = new Scene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        createPoints();

        type.selectedToggleProperty().addListener(observable -> {
            cleanUpPoints();
            createPoints();
        });

        scene.samplesProperty().bind(samples.valueProperty());

        Renderer.render(context, scene);

        scene.addListener((observable) -> {
            Renderer.clear(context);
            Renderer.render(context, scene);
        });
    }

    private void createPoints() {
        if (quadratic.isSelected()) {
            DragPoint p1 = DragPoint.createControlPoint(new Vector(255, 135));
            DragPoint p2 = DragPoint.createControlPoint(new Vector(165, 295));
            DragPoint p3 = DragPoint.createControlPoint(new Vector(400, 70));
            points = new DragPoint[]{p1, p2, p3};

            light = DragPoint.createLightPoint(new Vector(170, 110));

            container.getChildren().addAll(p1, p2, p3, light);

            curve = Bindings.createObjectBinding(
                    () -> new QuadraticBezierCurve(p1.getPosition(), p2.getPosition(), p3.getPosition()),
                    p1.positionProperty(), p2.positionProperty(), p3.positionProperty()
            );
        } else if (cubic.isSelected()) {
            DragPoint p1 = DragPoint.createControlPoint(new Vector(195, 140));
            DragPoint p2 = DragPoint.createControlPoint(new Vector(125, 280));
            DragPoint p3 = DragPoint.createControlPoint(new Vector(385, 20));
            DragPoint p4 = DragPoint.createControlPoint(new Vector(290, 250));
            points = new DragPoint[]{p1, p2, p3, p4};

            light = DragPoint.createLightPoint(new Vector(150, 100));

            container.getChildren().addAll(p1, p2, p3, p4, light);

            curve = Bindings.createObjectBinding(
                    () -> new CubicBezierCurve(p1.getPosition(), p2.getPosition(), p3.getPosition(), p4.getPosition()),
                    p1.positionProperty(), p2.positionProperty(), p3.positionProperty(), p4.positionProperty()
            );
        }

        scene.curveProperty().bind(curve);
        scene.lightProperty().bind(light.positionProperty());
    }

    private void cleanUpPoints() {
        scene.lightProperty().unbind();
        scene.curveProperty().unbind();

        container.getChildren().remove(light);

        for (DragPoint point : points) {
            container.getChildren().remove(point);
        }
    }
}
