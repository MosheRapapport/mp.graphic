package primitives;

/**
 *
 * @author Baruch Gehler 866256 		gehlerb@gmail.com
 * 		   Baruch Bichman 200844843		baruch913@gmail.com
 *This class represents a point in 3D extending Class Point2D
 *
 */

public class Point3D extends Point2D {

    private Coordinate z;

    /***********Getter***********/
    public Coordinate getZ() {
        return z;
    }


    /***********Constructors***********/
    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        this.z=new Coordinate(z.getNum());
    }

    public Point3D(Point3D p) {		//copy constructor using super copy constructor
        super(p);
        this.z=new Coordinate(p.getZ().getNum());
    }

    public Point3D(double x, double y, double z) {
        super(x,y);
        this.z=new Coordinate(z);
    }

    /***********Administration***********/

    @Override	//checks if 2 instances of Point3D are equals
    public boolean equals(Object obj) {
        if(obj instanceof Point3D) {
            Point3D temp =(Point3D)obj;
            if(super.equals(temp)&&this.z.equals(temp.getZ()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "("+this.getX()+","+this.getY()+","+z+")";
    }

    /***********Operations***********/

    /**
     *
     * @param p is a Point3D
     * @return the result of the substraction between 2 points who is a Vector
     */
    public Vector subVec(Point3D p) {
        return (new Vector(this).subtract(new Vector(p)));

    }

    /***
     *
     * @param v is a Vector
     * @return a Vector who is the result of the addition of this Point3D and a Vector
     */
    public Point3D addVec(Vector v) {
        return new Point3D(this.getX().add(v.getHead().getX()),
                this.getY().add(v.getHead().getY()),
                z.add(v.getHead().getZ()));
    }


    /**
     *
     * @param p is a Point3D
     * @return the distance between 2 points
     */
    public double distance(Point3D p) {
        if(this.equals(p))
            return 0;
        double d= Math.sqrt(Math.pow((this.getX().subtract(p.getX())).getNum(), 2)+
                Math.pow((this.getY().subtract(p.getY())).getNum(), 2)+
                Math.pow((this.getZ().subtract(p.getZ())).getNum(), 2));
        return d;
    }


}
