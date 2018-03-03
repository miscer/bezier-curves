package bezier;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Point2D;
import javafx.scene.shape.Circle;

public class DragPoint extends Circle {
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
}
