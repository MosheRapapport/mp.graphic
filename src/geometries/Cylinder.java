package geometries;

import java.util.List;
import java.util.Map;

import primitives.*;

/**
 *
 * @author Baruch Gehler 866256 gehlerb@gmail.com Baruch Bichman 200844843
 *         baruch913@gmail.com This class represents a cylinder extending Class
 *         RadialGeometry
 *
 */
public class Cylinder extends RadialGeometry {

    private Ray ray;

    /*********** Getters ***********/
    public Ray getRay() {
        return ray;
    }

    /*********** Constructors ***********/
    public Cylinder(double radius, Ray ray, Color Ie, Material material) {
        super(radius, Ie, material);
        this.ray = ray;
    }

    public Cylinder(double radius, double centerX, double centerY, double centerZ, double x, double y, double z,
                    Color Ie, Material material) {
        super(radius, Ie, material);
        this.ray = new Ray(new Point3D(centerX, centerY, centerZ), new Vector(x, y, z));
    }

    // copy constructor
    public Cylinder(Cylinder other) {
        super(other);
        this.ray = other.ray;
    }

    /************ Operations ************/
    /**
     * Calculate the normal of the cylinder in a specific point
     *
     * @param p
     *            is the point
     * @return the normal
     */
    @Override
    public Vector getNormal(Point3D p) {
        Vector u = p.subVec(ray.getP());
        Point3D proj = ray.getV().scalarMult(u.dotProduct(ray.getV()) / (ray.getV().dotProduct(ray.getV()))).getHead();
        return p.subVec(proj).normalize();
    }

    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        // TODO Auto-generated method stub
        return null;
    }

}
