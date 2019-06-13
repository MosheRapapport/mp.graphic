package geometries;

import java.util.List;
import java.util.Map;

import primitives.*;

/**
 *
 * @author Baruch Gehler 866256 gehlerb@gmail.com Baruch Bichman 200844843
 *         baruch913@gmail.com This abstract class represents geometrics groups
 *
 */
public abstract class Geometry {

    private Color _Ie;
    private Material _material;

    public Color get_Ie() {
        return _Ie;
    }

    public Material get_material() {
        return _material;
    }

    public Geometry(Color Ie, Material material) {
        _Ie = Ie;
        _material = material;
    }

    public Geometry(Geometry g) {
    }

    public Geometry() {
    }

    // abstract function, returns a normal Vector
    /**
     * abstract function Calculate the normal to the Geometry in a point
     *
     * @param p
     * @return the normal
     */
    public abstract Vector getNormal(Point3D p);

    /**
     * abstract function Find the intersections between the Ray and the Geometry
     *
     * @param ray
     * @return a Map<Geometry,List<Point3D>> Geometry is the Key, List<Point3D> is
     *         the value
     */
    public abstract Map<Geometry, List<Point3D>> findIntersections(Ray ray);

}
