package bezier;

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

    private State state = new State();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        BezierCurve curve = new BezierCurve(new Vector(20, 300), new Vector(300, -300), new Vector(580, 300));
        Vector light = new Vector(550, 150);

        state.curveProperty().set(curve);
        state.lightProperty().set(light);
        state.samplesProperty().bind(samples.valueProperty());

        Renderer.render(context, state);

        state.samplesProperty().addListener((observable, oldValue, newValue) -> {
            Renderer.clear(context);
            Renderer.render(context, state);
        });
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        System.out.printf("Mouse clicked %f %f\n", mouseEvent.getX(), mouseEvent.getY());
    }


}
