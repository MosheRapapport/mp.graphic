package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents a directional light
 *
 *
 *
 */

public class DirectionalLight extends Light implements LightSource {

    private Vector _direction;

    /******************** Constructor ********************/

    public DirectionalLight(Color c, Vector direction) {
        super(c);
        _direction = direction.normalize();
    }

    /******************** Administration ********************/

    @Override
    public Color getIntensity(Point3D p) {
        return new Color(_color);
    }

    /**
     * The function return the vector direction because the directonal light doesn't
     * change
     */
    @Override
    public Vector getL(Point3D p) {
        return _direction;
    }

    @Override
    public Vector getD(Point3D p) {
        return _direction;
    }

}
