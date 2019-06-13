package unitTest;
import static org.junit.Assert.*;

import org.junit.Test;

import primitives.Coordinate;


public class CoordinateTests {

    @Test
    public void testEquals() {

        assertFalse("++Different", new Coordinate(1000).equals(new Coordinate(900)));
        assertFalse("++Close", new Coordinate(1000).equals(new Coordinate(999.999999)));
        assertTrue("++Very close", new Coordinate(1000).equals(new Coordinate(999.9999999)));
        assertFalse("++Small Close", new Coordinate(1).equals(new Coordinate(0.999999)));
        assertTrue("++Small Very close", new Coordinate(1).equals(new Coordinate(0.9999999)));
    }

    @Test
    public void testConstructor() {
        Coordinate zero = new Coordinate(0.0);
        Coordinate one = new Coordinate(1);
        Coordinate small4 = new Coordinate(0.0001);
        Coordinate small6 = new Coordinate(0.000001);
        Coordinate small7 = new Coordinate(0.0000001);
        Coordinate small8 = new Coordinate(0.00000001);
        Coordinate mone = new Coordinate(-1);
        Coordinate msmall4 = new Coordinate(-0.0001);
        Coordinate msmall6 = new Coordinate(-0.000001);
        Coordinate msmall7 = new Coordinate(-0.0000001);
        Coordinate msmall8 = new Coordinate(-0.00000001);
        assertNotEquals("One", zero, one);
        assertNotEquals("0.0001", zero, small4);
        assertNotEquals("0.000001", zero, small6);
        assertEquals("0.0000001", zero, small7);
        assertEquals("0.00000001", zero, small8);
        assertNotEquals("-One", zero, mone);
        assertNotEquals("-0.0001", zero, msmall4);
        assertNotEquals("-0.000001", zero, msmall6);
        assertEquals("-0.0000001", zero, msmall7);
        assertEquals("-0.00000001", zero, msmall8);
    }


    @Test
    public void testSubtract() {
        Coordinate veryBig = new Coordinate(100000000);
        Coordinate big = new Coordinate(100000);
        Coordinate med = new Coordinate(1);
        Coordinate small = new Coordinate(0.0001);
        Coordinate verySmall = new Coordinate(0.000000001);
        Coordinate mveryBig = new Coordinate(-100000000);
        Coordinate mbig = new Coordinate(-100000);
        Coordinate mmed = new Coordinate(-1);
        Coordinate msmall = new Coordinate(-0.0001);
        //Coordinate mverySmall = new Coordinate(-0.000000001);
        //Coordinate zero = new Coordinate(0.0);
        assertNotEquals("Subtract 1", veryBig, veryBig.subtract(big));
        assertNotEquals("Subtract 1*", mveryBig, big.subtract(veryBig));
        assertNotEquals("Subtract 1-", veryBig, veryBig.subtract(mbig));
        assertNotEquals("Subtract -1", mveryBig, mveryBig.subtract(big));
        assertNotEquals("Subtract -1-", mveryBig, mveryBig.subtract(mbig));
        assertEquals("Subtract 2", veryBig, veryBig.subtract(med));
        assertEquals("Subtract 2*", mveryBig, med.subtract(veryBig));
        assertEquals("Subtract 2-", veryBig, veryBig.subtract(mmed));
        assertEquals("Subtract -2", mveryBig, mveryBig.subtract(med));
        assertEquals("Subtract -2-", mveryBig, mveryBig.subtract(mmed));
        assertNotEquals("Subtract 3", med, med.subtract(small));
        assertNotEquals("Subtract 3-", med, med.subtract(msmall));
        assertNotEquals("Subtract -3", mmed, mmed.subtract(small));
        assertNotEquals("Subtract -3-", mmed, mmed.subtract(msmall));
        assertEquals("Subtract 4", med, med.subtract(verySmall));
    }

}
