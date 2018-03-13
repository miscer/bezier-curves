package bezier;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class DragPoint extends Circle {
    public static DragPoint createLightPoint(Vector position) {
        return createPoint(position, Color.YELLOW);
    }

    public static DragPoint createControlPoint(Vector position) {
        return createPoint(position, Color.RED);
    }

    private static DragPoint createPoint(Vector position, Color color) {
        DragPoint light = new DragPoint();
        light.setRadius(5);
        light.setFill(color);
        light.setPosition(position);
        return light;
    }

    private ObjectProperty<Vector> position = new SimpleObjectProperty<>(new Vector(0, 0));

    public DragPoint() {
        super();
        initialize();
    }

    private void initialize() {
        DoubleBinding x = Bindings.createDoubleBinding(() -> position.get().getX(), position);
        layoutXProperty().bind(x);

        DoubleBinding y = Bindings.createDoubleBinding(() -> position.get().getY(), position);
        layoutYProperty().bind(y);

        setOnMouseDragged(event -> {
            Point2D mousePosition = getParent().screenToLocal(event.getScreenX(), event.getScreenY());
            Vector mouseVector = new Vector(mousePosition.getX(), mousePosition.getY());
            position.set(mouseVector);
        });
    }

    public ObjectProperty<Vector> positionProperty() {
        return position;
    }

    public Vector getPosition() {
        return position.get();
    }

    public void setPosition(Vector position) {
        this.position.set(position);
    }
}
