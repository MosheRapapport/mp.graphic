package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents a Spot light extending a Point light
 *
 * @author Baruch
 *
 */
public class SpotLight extends PointLight {

    private Vector _direction;

    /**
     * The constructor receives the color, the position and the direction of the
     * light and three doubles to calculate a specific factor for the intensity of
     * the light
     *
     * @param c
     * @param position
     * @param Kc
     * @param Kl
     * @param Kq
     * @param direction
     */
    public SpotLight(Color c, Point3D position, double Kc, double Kl, double Kq, Vector direction) {
        super(c, position, Kc, Kl, Kq);
        _direction = direction.normalize();
    }

    /**
     * The function calculates the intensity of the light in a given point
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d = _position.distance(p);
        double factor = _direction.dotProduct(getL(p));
        factor = factor > 0 ? factor /  (_Kc + _Kl * d + _Kq * d * d) : 0;
        return _color.scale(factor);
    }

    /**
     * The function returns a vector between the source of the light and a given
     * point p
     */
    @Override
    public Vector getL(Point3D p) {
        return (p.subVec(_position)).normalize();
    }

    /**
     * The function return the vector direction of the light
     */
    @Override
    public Vector getD(Point3D p) {
        return _direction;
    }

}
