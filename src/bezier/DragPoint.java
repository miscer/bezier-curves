package bezier;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.shape.Circle;

public class DragPoint extends Circle {
    private ObjectProperty<Vector> position = new SimpleObjectProperty<>(new Vector(0, 0));

    public DragPoint() {
        super();
        initialize();
    }

    private void initialize() {
        DoubleBinding x = Bindings.createDoubleBinding(() -> position.get().getX(), position);
        centerXProperty().bind(x);

        DoubleBinding y = Bindings.createDoubleBinding(() -> position.get().getY(), position);
        centerYProperty().bind(y);

        setOnMouseDragged(event -> {
            Vector v = new Vector(event.getX(), event.getY());
            position.set(v);
        });
    }

    public ObjectProperty<Vector> positionProperty() {
        return position;
    }
}
