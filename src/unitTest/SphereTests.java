package unitTest;

import primitives.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import geometries.*;

public class SphereTests {

    Sphere s1 = new Sphere(2, new Point3D(0, 0, -2), new Color(200, 10, 100), new Material(1, 1, 1,0,0));
    Ray r1 = new Ray(new Point3D(0, 0, 2), new Vector(0, 0, 1));
    Ray r2 = new Ray(new Point3D(0, 0, 2), new Vector(0, 0, -1));
    Ray r3 = new Ray(new Point3D(0, -2, 0), new Vector(0, 1, 0));
    Ray r4 = new Ray(new Point3D(0, -2, 0), new Vector(0, 0, -1));

    Sphere s2 = new Sphere(180, new Point3D(200, 50, 0), new Color(200, 10, 100), new Material(1, 1, 1,0,0));
    Ray rTest = new Ray(new Point3D(0, 0, 0), new Vector(100, 80, 150));

    @Test
    public void findIntersectionsTests() {

        assertEquals("", s1.findIntersections(r1).get(s1).size(), 0);
        assertEquals("", s1.findIntersections(r2).get(s1).size(), 2);
        assertEquals("", s1.findIntersections(r3).get(s1).size(), 1);
        assertEquals("", s1.findIntersections(r4).get(s1).size(), 1);

    }
}
