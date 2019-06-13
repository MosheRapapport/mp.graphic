package primitives;

/**
 *
 * @author Baruch Gehler 866256 gehlerb@gmail.com Baruch Bichman 200844843
 *         baruch913@gmail.com This class represents a vector
 *
 */
public class Vector {

    private Point3D head;

    /***********Getter***********/
    public Point3D getHead() {
        return head;
    }

    /***********Constructors***********/
    public Vector(Point3D point) {
        this.head = new Point3D(point);
    }

    public Vector(double x, double y,double z) {
        this.head= new Point3D(x,y,z);
    }

    //copy constructor
    public Vector(Vector other) {
        this.head = other.getHead();
    }

    /***********Administration***********/

    @Override // checks if 2 instances of Vector are equals
    public boolean equals(Object obj) {
        if (obj instanceof Vector) {
            Vector temp = (Vector) obj;
            if (this.head.equals(temp.getHead()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "vector " + head.toString();
    }

    /***********Operations***********/

    /**
     *addition of 2 vectors, return a new vector
     */
    public Vector add(Vector v) {
        return new Vector(new Point3D(this.head.getX().add(v.head.getX()), this.head.getY().add(v.head.getY()),
                this.head.getZ().add(v.head.getZ())));
    }

    /**
     *@param v is a Vector
     *@return return a new vector by substracting the parameter from "this" vector
     */
    public Vector subtract(Vector v) {
        return new Vector(new Point3D(this.head.getX().subtract(v.head.getX()),
                this.head.getY().subtract(v.head.getY()), this.head.getZ().subtract(v.head.getZ())));
    }


    /**
     *@param scalar is a double
     *@return a new Vector by multiplying "this" vector by scalar
     */
    public Vector scalarMult(double scalar) {
        return new Vector(new Point3D(new Coordinate(scalar * this.head.getX().getNum()),
                new Coordinate(scalar * this.head.getY().getNum()),
                new Coordinate(scalar * this.head.getZ().getNum())));
    }


    /**
     * @param v is a Vector
     * @return a double who is the dot product between "this" vector and the parameter
     */
    public double dotProduct(Vector v) {
        return (this.head.getX().getNum() * v.getHead().getX().getNum()
                + this.head.getY().getNum() * v.getHead().getY().getNum()
                + this.head.getZ().getNum() * v.getHead().getZ().getNum());
    }

    /**
     *@param v is a Vector
     *@return a new vector who is the result of the cross product between "this" vector and the parameter
     */
    public Vector crossProduct(Vector v) {
        return new Vector(new Point3D(
                new Coordinate(this.head.getY().getNum() * v.getHead().getZ().getNum()
                        - this.head.getZ().getNum() * v.getHead().getY().getNum()),
                new Coordinate(this.head.getZ().getNum() * v.getHead().getX().getNum()
                        - this.head.getX().getNum() * v.getHead().getZ().getNum()),
                new Coordinate(this.head.getX().getNum() * v.getHead().getY().getNum()
                        - this.head.getY().getNum() * v.getHead().getX().getNum())));
    }

    /**
     *
     * @return the size of the Vector
     */
    public double size() {
        return head.distance(new Point3D(new Coordinate(0), new Coordinate(0), new Coordinate(0)));
    }

    /**
     *
     * @return the normal of this vector
     */
    public Vector normalize() {
        if(this.size()==0)
            throw new ArithmeticException("can't divide by zero"+this.toString());
        return this.scalarMult(1 / this.size());
    }

}