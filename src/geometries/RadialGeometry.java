package geometries;

import java.util.List;
import java.util.Map;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 * This class represents geometrics groups who has a radius extending Geometry
 * Class
 *
 *  */
public abstract class RadialGeometry extends Geometry {

    protected double radius;

    /*********** Getters ***********/
    public double getRadius() {
        return radius;
    }

    /*********** Constructors ***********/
    public RadialGeometry(double r, Color Ie, Material material) {
        super(Ie, material);
        if (r <= 0)
            throw new IllegalArgumentException("radius must be positive");
        this.radius = r;
    }

    // copy constructor
    public RadialGeometry(RadialGeometry other) {
        super(other.get_Ie(), other.get_material());
        this.radius = other.getRadius();
    }

    /**
     * abstract function calculate the vector normal to the body/form in a point
     *
     * @param p
     *            is a Point3D
     * @return a Vector normal to the RadialGeometry
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * abstract function Find the intersections between the Ray and the
     * RadialGeometry
     *
     * @param ray
     * @return a Map<Geometry,List<Point3D>> Geometry is the Key, List<Point3D> is
     *         the value
     */
    public abstract Map<Geometry, List<Point3D>> findIntersections(Ray ray);

}
