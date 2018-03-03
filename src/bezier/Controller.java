package bezier;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;
    public Slider samples;
    public DragPoint light;
    public DragPoint p1;
    public DragPoint p2;
    public DragPoint p3;

    private State state = new State();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        ObjectBinding<BezierCurve> curve = Bindings.createObjectBinding(
                () -> new BezierCurve(p1.getPosition(), p2.getPosition(), p3.getPosition()),
                p1.positionProperty(), p2.positionProperty(), p3.positionProperty()
        );

        state.curveProperty().bind(curve);
        state.lightProperty().bind(light.positionProperty());
        state.samplesProperty().bind(samples.valueProperty());

        Renderer.render(context, state);

        state.addListener((observable) -> {
            Renderer.clear(context);
            Renderer.render(context, state);
        });
    }

}
