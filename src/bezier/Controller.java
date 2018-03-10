package bezier;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    public Slider samples;
    public DragPoint light;
    public DragPoint p1;
    public DragPoint p2;
    public DragPoint p3;

    private Scene scene = new Scene();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        ObjectBinding<BezierCurve> curve = Bindings.createObjectBinding(
                () -> new BezierCurve(p1.getPosition(), p2.getPosition(), p3.getPosition()),
                p1.positionProperty(), p2.positionProperty(), p3.positionProperty()
        );

        scene.curveProperty().bind(curve);
        scene.lightProperty().bind(light.positionProperty());
        scene.samplesProperty().bind(samples.valueProperty());

        Renderer.render(context, scene);

        scene.addListener((observable) -> {
            Renderer.clear(context);
            Renderer.render(context, scene);
        });
    }

}
