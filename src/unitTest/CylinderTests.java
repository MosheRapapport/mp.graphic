package unitTest;

import org.junit.Test;

import geometries.Cylinder;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public class CylinderTests {

    Cylinder c1=new Cylinder(1,1,0,1,0,1,0,new Color(10,230,100),new Material(1,1,1,0,0));
    Point3D p1=new Point3D(0,1,0);

    @Test
    public void testEquals() {
        Vector normal1=c1.getNormal(p1);
        System.out.println(normal1);
    }

}
