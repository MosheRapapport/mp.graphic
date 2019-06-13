package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * This class represents a Plane
 *
 * @author Baruch Gehler 866256 gehlerb@gmail.com Baruch Bichman 200844843
 *         baruch913@gmail.com
 *
 */
public class Plane extends Geometry {

    private Point3D p;
    private Vector normal;

    /*********** Getters ***********/
    public Point3D getP() {
        return p;
    }

    /*********** Constructors ***********/

    public Plane(Point3D p, Vector normal, Color Ie, Material material) {
        super(Ie, material);
        this.p = new Point3D(p);
        this.normal = new Vector(normal);
    }

    /**
     * The constructor receives 3 points and check if they are not on the same line
     * and build a plane
     *
     * @param p1
     * @param p2
     * @param p3
     * @param Ie
     * @param material
     */
    public Plane(Point3D p1, Point3D p2, Point3D p3, Color Ie, Material material) {
        super(Ie, material);
        this.p = new Point3D(p1);
        Vector v = new Vector(p1.subVec(p2)).crossProduct(p1.subVec(p3));
        if (v.size() == 0)
            throw new IllegalArgumentException("The points are on the same line");
        this.normal = v;

    }

    // copy constructor
    public Plane(Plane other) {
        super(other.get_Ie(), other.get_material());
        this.p = other.p;
        this.normal = other.normal;
    }

    /**
     * Calculate the normal to a plane in a point
     *
     * @param p
     * @return the normal to the plane
     */
    public Vector getNormal(Point3D p) {
        return normal.normalize();
    }

    /*********** Administration ***********/
    /**
     * Find the intersections between the Ray and the plane
     *
     * @param ray
     * @return a Map<Geometry,List<Point3D>> Geometry is the Key, List<Point3D> is
     *         the value
     */
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> intersec = new HashMap<Geometry, List<Point3D>>();
        List<Point3D> list = new ArrayList<Point3D>();
        if (ray.getV().dotProduct(normal) == 0)
            return intersec;
        double Nv = normal.dotProduct(ray.getV());
        double Nqp = normal.dotProduct(p.subVec(ray.getP()));
        double t = Nqp / Nv;
        if (t > 0) {
            list.add(ray.getP().addVec(ray.getV().scalarMult(t)));
            intersec.put(this, list);
        }
        return intersec;
    }

}
