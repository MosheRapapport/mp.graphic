package unitTest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import geometries.*;
import primitives.*;

public class PlaneTests {

    Plane p1=new Plane(new Point3D(0,0,0),new Vector(0,1,0),new Color(200,10,100),new Material(1,1,1,0,0));
    Plane p2=new Plane(new Point3D(0,0,0),new Vector(0,0,1),new Color(200,80,100),new Material(1,1,1,0,0));
    Ray r1=new Ray(new Point3D(0,-1,0),new Vector(0,1,0));
    Ray r2=new Ray(new Point3D(0,1,0),new Vector(0,1,0));
    Ray r3=new Ray(new Point3D(0,0,0),new Vector(1,0,0));

    @Test
    public void findIntersectionsTest() {
        assertEquals("",p1.findIntersections(r1).size(),1);
        //assertEquals("",p1.findIntersections(r1).get(0),new Point3D(0,0,0));
        assertEquals("",p1.findIntersections(r2).size(),0);
        // checks if the vector is contained in the plane there is no intersections
        assertEquals("",p2.findIntersections(r3).size(),0);
    }
}
