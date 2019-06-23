package unitTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;
import elements.Camera;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

public class CameraTests {

    Point3D p1=new Point3D(0,0,0);
    Camera c1=new Camera(p1,new Vector(0,-1,0),new Vector(0,0,-1),0,0);
    Vector v1=new Vector(1/Math.sqrt(6),1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3));
    Ray r11=new Ray(p1,v1);
    Ray r12=new Ray(p1,new Vector(-1/Math.sqrt(6),-1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r13=new Ray(p1,new Vector(-1/Math.sqrt(6),1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r14=new Ray(p1,new Vector(1/Math.sqrt(6),-1/Math.sqrt(6),-Math.sqrt(2)/Math.sqrt(3)));
    Ray r15=new Ray(p1,new Vector(0,0,-1));

    Point3D p2=new Point3D(-3,3,-3);
    Camera c2=new Camera(p2,new Vector(1,1,1),new Vector(-1,2,-1),0,0);
    Ray r21=new Ray(p2,new Vector(140,260,-223).normalize());



    @Test
    public void constructorCamera() {
        assertEquals("",c1.getVRight(),new Vector(1,0,0));
        try {
            new Camera(p1,v1,new Vector(1,9,8),0,0);
            fail("didn't throw The vectors must be orthogonals");
        }catch(ArithmeticException e) {
            assertTrue(true);
        }
    }


    @Test
    public void buidRayTest() {
        assertEquals("",c1.constructRaysThroughPixel(3, 3, 3, 3, 100, 150, 150),r11);
        assertEquals("",c1.constructRaysThroughPixel(3, 3, 1, 1, 100, 150, 150),r12);
        assertEquals("",c1.constructRaysThroughPixel(3, 3, 1, 3, 100, 150, 150),r13);
        assertEquals("",c1.constructRaysThroughPixel(3, 3, 3, 1, 100, 150, 150),r14);
        assertEquals("",c1.constructRaysThroughPixel(3, 3, 2, 2, 100, 150, 150),r15);
    }

    Ray r31=new Ray(p1,v1);
    Ray r32=new Ray(p1,new Vector(-75,-75,-100).normalize());
    Ray r33=new Ray(p1,new Vector(0,0,0));
    Ray r34=new Ray(p1,new Vector(0,0,0));
    Ray r35=new Ray(p1,new Vector(0,0,-1));

    Ray r36=new Ray(p2,new Vector(140,260,-223).normalize());

    //@Test
    //public void buidRayTestEven() {
    //assertEquals("",c1.constructRayThroughPixel(4, 4, 4, 4, 100, 200, 200),r11);
    //assertEquals("",c1.constructRayThroughPixel(4, 4, 1, 1, 100, 200, 200),r32);
    //assertEquals("",c1.constructRayThroughPixel(4, 4, 4, 1, 100, 200, 200),r13);
    //assertEquals("",c1.constructRayThroughPixel(4, 4, 1, 4, 100, 200, 200),r14);


    //}
}
