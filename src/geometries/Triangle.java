package geometries;

import java.util.List;
import java.util.Map;
import primitives.*;

/**
 * This Class represents a Triangle
 *
 * @author Baruch Gehler 866256 gehlerb@gmail.com Baruch Bichman 200844843
 *         baruch913@gmail.com This class represents a triangle
 *
 */
public class Triangle extends Plane {
    private Point3D p1;
    private Point3D p2;
    private Point3D p3;

    /*********** Getters ***********/
    public Point3D getP1() {
        return p1;
    }

    public Point3D getP2() {
        return p2;
    }

    public Point3D getP3() {
        return p3;
    }

    /*********** Constructors ***********/

    public Triangle(Point3D p1, Point3D p2, Point3D p3, Color Ie,Material material) {
        super(p1, p2, p3, Ie,material);
        this.p1 = p1;
        this.p2 = p2;
        this.p3 = p3;
    }

    // copy constructor
    public Triangle(Triangle other) {
        super(other.getP1(), other.getP2(), other.getP3(), other.get_Ie(), other.get_material());
        this.p1 = other.getP1();
        this.p2 = other.getP2();
        this.p3 = other.getP3();
    }

    /*********** Administration ***********/

    /**
     * Find the intersections between the Ray and the Triangle, The function checks
     * if the points who are the intersections between the ray and the plane are in
     * the triangle
     *
     * @param ray
     * @return a Map<Geometry,List<Point3D>> Geometry is the Key, List<Point3D> is
     *         the value
     */
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> intersec = super.findIntersections(ray);
        if (intersec.size() == 0)
            return intersec;
        Point3D p = intersec.get(this).get(0);
        Vector v1 = p1.subVec(ray.getP());
        Vector v2 = p2.subVec(ray.getP());
        Vector v3 = p3.subVec(ray.getP());
        Vector N1 = v1.crossProduct(v2);
        Vector N2 = v2.crossProduct(v3);
        Vector N3 = v3.crossProduct(v1);
        double arr[] = new double[] { N1.dotProduct((p.subVec(ray.getP()))), N2.dotProduct((p.subVec(ray.getP()))),
                N3.dotProduct((p.subVec(ray.getP()))) };
        int positive = 3, negative = 3;
        for (int i = 0; i < 3; i++) {
            if (Coordinate.ZERO.equals(new Coordinate(arr[i]))) {
                intersec.clear();
                return intersec;
            }
            if (arr[i] > 0)
                --positive;
            if (arr[i] < 0)
                --negative;
        }

        if (positive != 0 && negative != 0)
            intersec.clear();
        return intersec;
    }
}
