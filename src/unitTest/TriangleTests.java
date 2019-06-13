package unitTest;

import primitives.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import geometries.*;

public class TriangleTests {
    Triangle t1=new Triangle(new Point3D(0,0,4),new Point3D(0,0,0),new Point3D(4,0,0),new Color(200,10,100),new Material(1,1,1,0,0));
    Ray r1=new Ray(new Point3D(0,-2,0),new Vector(1,2,1));
    Ray r2=new Ray(new Point3D(0,-2,0),new Vector(-1,1,1));
    Ray r3=new Ray(new Point3D(0,-2,0),new Vector(-1,-1,1));
    Ray r4=new Ray(new Point3D(0,-2,0),new Vector(2,1,1));

    Triangle t2=new Triangle(new Point3D(4,-4,4),new Point3D(-3,3,5),new Point3D(5,2,5),new Color(200,10,100),new Material(1,1,1,0,0));


    @Test
    public void findIntersectionsTests() {
        assertEquals("",t1.findIntersections(r1).size(),1);
        assertEquals("",t1.findIntersections(r2).size(),0);
        assertEquals("",t1.findIntersections(r3).size(),0);
        assertEquals("",t1.findIntersections(r4).size(),0);
    }


}
