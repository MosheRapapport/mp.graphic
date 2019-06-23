package elements;

import primitives.Color;

/**
 * This Class represents the ambient light of the scene The values are the
 * colors of the light and the factor of the intensity
 *
 *
 */
public class AmbientLight extends Light {

    /*********** Constructors ***********/
    /**
     * The constructor receive the color and the factor, the intensity of the color
     * is changed in the constructor
     *
     * @param _c
     * @param _Ka
     */
    public AmbientLight(Color _c, double _Ka) {
        super(_c);
        _color = _color.scale(_Ka);
    }

}
