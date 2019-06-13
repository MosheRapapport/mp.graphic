package primitives;
/**
 *
 * @author Baruch Gehler 866256 		gehlerb@gmail.com
 * 		   Baruch Bichman 200844843		baruch913@gmail.com
 *This class represents a Ray
 *
 */

public class Ray {
    private Point3D p;
    private Vector v;

    /***********Getters***********/
    public Point3D getP(){
        return p;
    }

    public Vector getV() {
        return v;
    }

    /***********Constructors***********/
    /**
     * Constructor receive a point and a vector
     * @param p
     * @param v
     */
    public Ray(Point3D p,Vector v )
    {
        this.p=p;
        this.v=v.normalize();
    }

    //copy constructor
    public Ray(Ray other){
        this.p=other.getP();
        this.v=other.getV();
    }

    /***********Administration***********/

    /**
     * checks if 2 instances of Ray are equals
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Ray) {
            Ray temp = (Ray) obj;
            if (this.p.equals(temp.getP())&&this.v.equals(temp.getV()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ray: point " + p.toString()+ ", " +v.toString();
    }

}
