package bezier.ui;

import bezier.curves.BezierCurve;
import bezier.math.Vector;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.List;

public class Scene implements Observable, InvalidationListener {
    private List<InvalidationListener> listeners = new ArrayList<>();

    private ObjectProperty<BezierCurve> curve = new SimpleObjectProperty<>();
    private ObjectProperty<Vector> light = new SimpleObjectProperty<>();
    private IntegerProperty samples = new SimpleIntegerProperty();

    public Scene() {
        curve.addListener(this);
        light.addListener(this);
        samples.addListener(this);
    }

    public ObjectProperty<BezierCurve> curveProperty() {
        return curve;
    }

    public ObjectProperty<Vector> lightProperty() {
        return light;
    }

    public IntegerProperty samplesProperty() {
        return samples;
    }

    public BezierCurve getCurve() {
        return curve.get();
    }

    public Vector getLight() {
        return light.get();
    }

    public int getSamples() {
        return samples.get();
    }

    @Override
    public void addListener(InvalidationListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(InvalidationListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void invalidated(Observable observable) {
        for (InvalidationListener listener : listeners) {
            listener.invalidated(observable);
        }
    }
}
