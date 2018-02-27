package bezier;

import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public Canvas canvas;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GraphicsContext context = canvas.getGraphicsContext2D();

        BezierCurve curve = new BezierCurve(new Vector(10, 10), new Vector(200, 100), new Vector(250, 300));
        State state = new State(curve);

        Renderer.renderState(context, state);
    }

    public void onMouseClicked(MouseEvent mouseEvent) {
        System.out.printf("Mouse clicked %f %f\n", mouseEvent.getX(), mouseEvent.getY());
    }


}
