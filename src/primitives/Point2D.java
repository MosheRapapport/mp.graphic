package primitives;

/**
 * * @author Baruch Gehler 866256 		gehlerb@gmail.com
 * 		   Baruch Bichman 200844843		baruch913@gmail.com
 *This Class represents a point in 2D
 */
public class Point2D {
    private Coordinate x;
    private Coordinate y;

    //***********Getters***********//
    public Coordinate getX() {
        return x;
    }

    public Coordinate getY() {
        return y;
    }

    //***********Constructors***********//
    public Point2D(Coordinate x, Coordinate y) {
        this.x = new Coordinate(x.getNum());
        this.y = new Coordinate(y.getNum());
    }

    //copy constructor
    public Point2D(Point2D p) {
        this.x = new Coordinate(p.x.getNum());
        this.y = new Coordinate(p.y.getNum());
    }

    public Point2D(double x, double y){
        this.x=new Coordinate(x);
        this.y=new Coordinate(y);
    }


    /***********Administration***********/
    /**
     * checks if 2 instances of Point2D are equals
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Point2D) {
            Point2D temp =(Point2D)obj;
            if(this.x.equals(temp.getX())&&this.y.equals(temp.getY()))
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y +")";
    }





}
