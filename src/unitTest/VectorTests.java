package unitTest;
import primitives.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class VectorTests {

    Vector v1=new Vector(1,1,1);
    Vector v2=new Vector(2,2,2);
    Vector v3=new Vector(3,3,3);
    Vector v4=new Vector(8,8,8);
    Vector v5=new Vector(1,1,2);
    Vector v6=new Vector(-1,1,0);
    Vector v7=new Vector(1,0,0);
    Vector v8=new Vector(2,-1,-1);
    Vector v9=new Vector(-1,-1,-1);

    @Test
    public void testEquals()	{
        assertNotEquals("",v1,v2);
        assertEquals("",v1, new Vector(1,1,1));
    }

    @Test
    public void testAdd() {
        assertEquals("",v3,v2.add(v1));
        assertNotEquals("",v1,v3.add(v2));
    }

    @Test
    public void subTest() {
        assertEquals("",v3.subtract(v2),v1);
        assertNotEquals("",v2.subtract(v3),v1);
    }

    @Test
    public void scalarMultTest(){
        assertEquals("",v2.scalarMult(4),v4);
        assertNotEquals("",v2.scalarMult(2),v4);
    }


    @Test
    public void dotProductTest() {
        assertTrue("",v1.dotProduct(v2)==6);
        assertFalse("",v1.dotProduct(v2)==5);
        assertTrue("acute angle",v5.dotProduct(v1)==4);
        assertTrue("large angle",v6.dotProduct(v7)==-1);
        assertTrue("orthogonal",v1.dotProduct(v8)==0);
        assertTrue("opposite direction",v1.dotProduct(v9)==-3);
    }

    @Test
    public void crossProductTest() {
        assertEquals("",v1.crossProduct(v2),new Vector(0,0,0));
        assertEquals("acute angle",v5.crossProduct(v1),new Vector(-1,1,0));
        assertEquals("large angle",v6.crossProduct(v7),new Vector(0,0,-1));
        assertEquals("orthogonal",v1.crossProduct(v8),new Vector(0,3,-3));
        assertEquals("opposite direction",v1.crossProduct(v9),new Vector(0,0,0));
    }

    @Test
    public void sizeTest() {
        assertTrue("",v3.size()==3*Math.sqrt(3));
        assertFalse("",v3.size()==3*Math.sqrt(2));
    }

    @Test
    public void normalizeTest() {
        assertEquals("",new Vector(9,0,0).normalize(),new Vector(1,0,0));
        try {
            new Vector(0,0,0).normalize();
            fail("didn't throw divide by zero exception");
        }catch(ArithmeticException e) {
            assertTrue(true);
        }
    }
}
