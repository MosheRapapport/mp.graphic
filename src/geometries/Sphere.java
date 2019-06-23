package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.*;

/**
 *
 * This class represents a Sphere extending Class RadialGeometry
 *
 * *
 */
public class Sphere extends RadialGeometry {

    private Point3D p;

    /*********** Getters ***********/

    public Point3D getP() {
        return p;
    }

    /*********** Constructors ***********/
    public Sphere(double radius, Point3D p, Color Ie, Material material) {
        super(radius, Ie, material);
        this.p = p;
    }

    // copy constructor
    public Sphere(Sphere other) {
        super(other);
        this.p = other.getP();
    }

    @Override
    /**
     * Calculate the normal to the sphere in a point
     *
     * @param point
     *            is a Point3D
     * @return the Vector normal
     */
    public Vector getNormal(Point3D point) {
        return point.subVec(p).normalize();
    }

    /**
     * Find the intersections between the Ray and the Sphere
     *
     * @param ray
     * @return a Map<Geometry,List<Point3D>> Geometry is the Key, List<Point3D> is
     *         the value
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> intersec = new HashMap<Geometry, List<Point3D>>();
        Point3D p0 = ray.getP();
        Vector v = ray.getV();
        List<Point3D> list = new ArrayList<Point3D>();
        Vector u = p.subVec(p0);
        double tm = v.dotProduct(u);
        double d = Math.sqrt(u.dotProduct(u) - tm * tm);
        if (d > radius)
            return intersec;
        double th = Math.sqrt(radius * radius - d * d);

        if (Coordinate.ZERO.equals(new Coordinate(th))) {
            if (tm >= 0)
                list.add(p0.addVec(v.scalarMult(tm)));
        } else {
            double t1 = tm - th;
            if (t1 >= 0)
                list.add(p0.addVec(v.scalarMult(t1)));
            double t2 = th + tm;
            if (t2 >= 0)
                list.add(p0.addVec(v.scalarMult(t2)));
        }
        if (!list.isEmpty())
            intersec.put(this, list);
        return intersec;
    };

}
