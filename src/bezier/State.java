package bezier;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class State {
    private ObjectProperty<BezierCurve> curve = new SimpleObjectProperty<>();
    private ObjectProperty<Vector> light = new SimpleObjectProperty<>();
    private IntegerProperty samples = new SimpleIntegerProperty(30);

    public ObjectProperty<BezierCurve> curveProperty() {
        return curve;
    }

    public ObjectProperty<Vector> lightProperty() {
        return light;
    }

    public IntegerProperty samplesProperty() {
        return samples;
    }
}
