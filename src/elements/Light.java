package elements;

import primitives.Color;

/**
 * This class represents a Light
 *
 * @author Baruch
 *
 */
public abstract class Light {
    protected Color _color;

    public Color getIntensity() {
        return _color;
    };

    public Light(Color c) {
        _color = c;
    }
}
