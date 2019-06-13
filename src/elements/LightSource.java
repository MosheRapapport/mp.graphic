package elements;

import primitives.*;

/**
 * This class is an interface for the different sources of the lights
 *
 * @author Baruch
 *
 */
public interface LightSource {
    /**
     * The function return the intensity of the light in a point p
     *
     * @param p
     * @return
     */
    public abstract Color getIntensity(Point3D p);

    /**
     * The function calculates the vector from the source of the light to a point p
     *
     * @param p
     * @return
     */
    public abstract Vector getL(Point3D p);

    /**
     * The function calculates the vector direction of the light
     *
     * @param p
     * @return
     */
    public abstract Vector getD(Point3D p);

}
