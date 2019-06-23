package geometries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 *
 */
public class Geometries extends Geometry {

    List<Geometry> geometriesList;

    public Geometries() {
        super(null, null);
        geometriesList = new ArrayList<Geometry>();
    }

    /**
     * The function add a Geometry to the list
     *
     * @param g
     *
     */
    public void add(Geometry g) {
        geometriesList.add(g);
    }

    @Override
    public Vector getNormal(Point3D p) {
        return null;
    }

    /**
     * This function find the intersections between the list of Geometry and a Ray
     *
     * @param ray
     * @return the list of Point3D who are the intersections
     */
    @Override
    public Map<Geometry, List<Point3D>> findIntersections(Ray ray) {
        Map<Geometry, List<Point3D>> intersections = new HashMap<Geometry, List<Point3D>>();
        for (Geometry g : geometriesList)
            intersections.putAll(g.findIntersections(ray));
        return intersections;
    }

}
