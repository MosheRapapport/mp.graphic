package unitTest;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import primitives.Point3D;

public class Point3DTests {

    Point3D p0=new Point3D(0,0,0);
    Point3D p1=new Point3D(5,5,5);
    Point3D p2=new Point3D(-5,-5,-5);


    @Test
    public void TestDistance() {
        assertTrue("",p0.distance(p1)==(Math.sqrt(75)));
        assertTrue("",p0.distance(p2)==(Math.sqrt(75)));

    }

}
