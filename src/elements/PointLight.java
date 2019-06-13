package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * This class represents a point light
 *
 * @author Baruch
 *
 */
public class PointLight extends Light implements LightSource {

    protected Point3D _position;
    protected double _Kc, _Kl, _Kq;

    public Point3D getPosition() {
        return _position;
    }
    public double get_Kc() {
        return _Kc;
    }

    public double get_Kl() {
        return _Kl;
    }

    public double get_Kq() {
        return _Kq;
    }

    /**
     * The constructor receives the color and the position of the light and three
     * doubles to calculate a specific factor for the intensity of the light
     *
     * @param c
     * @param position
     * @param Kc
     * @param Kl
     * @param Kq
     */
    public PointLight(Color c, Point3D position, double Kc, double Kl, double Kq) {
        super(c);
        _position = position;
        _Kc = Kc;
        _Kl = Kl;
        _Kq = Kq;
    }

    /**
     * The function calculates the intensity of the light
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d = p.distance(_position);
        double factor = _Kc + _Kl * d + _Kq * d * d;
        return _color.reduce(factor);
    }

    /**
     * The function return the vector direction
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subVec(_position).normalize();
    }

    /**
     * The function return the vector direction
     */
    @Override
    public Vector getD(Point3D p) {
        return p.subVec(_position).normalize();
    }

}
