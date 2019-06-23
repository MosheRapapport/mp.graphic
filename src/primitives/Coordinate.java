package primitives;

/**
 *
 * This class represents a coordinate
 *
 */

public class Coordinate {

    public static Coordinate ZERO =  new Coordinate(0.0);
    private double num;
    // It is binary, equivalent to ~1/1,000,000 in decimal (6 digits)
    private static final int ACCURACY = -20;

    /***********Getter***********/
    public double getNum() {
        return num;
    }


    /***********Constructors***********/
    public Coordinate(double x) {
        // if it too close to zero make it zero
        this.num = (getExp(x) < ACCURACY) ? 0.0 : x;
    }

    public Coordinate(Coordinate coor ) { 	//copy constructor
        this.num=coor.num;
    }

    /***********Operations***********/

    //substracts a Coordinate from this instance
    public Coordinate subtract(Coordinate other) {
        return new Coordinate(subtract(other.num));
    }

    public Coordinate scale(double num) {
        return new Coordinate (_scale(num));
    }

    //addition of 2 Coordinates
    public Coordinate add(Coordinate other) {
        return new Coordinate(add(other.num));
    }

    /************** Helpers ***************/



    // double store format (bit level): seee eeee eeee (1.)mmmm � mmmm
    // 1 bit sign, 11 bits exponent, 53 bits (52 stored) normalized mantissa
    // the number is m+2^e where 1<=m<2
    // NB: exponent is stored "normalized" (i.e. always positive by adding 1023)
    private int getExp(double num) {
        // 1. doubleToRawLongBits: "convert" the stored number to set of bits
        // 2. Shift all 52 bits to the right (removing mantissa)
        // 3. Zero the sign of number bit by mask 0x7FF
        // 4. "De-normalize" the exponent by subtracting 1023
        return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
    }

    private double subtract(double other) {
        int otherExp = other == 0.0 ? 0 : getExp(other);
        int thisExp = num == 0.0 ? 0 : getExp(num);

        // if other is too small relatively to our coordinate
        // return the original coordinate
        if (otherExp - thisExp < ACCURACY)
            return num;

        // if our coordinate is too small relatively to other
        // return negative of other coordinate
        if (thisExp - otherExp < ACCURACY)
            return -other;

        double result = num - other;
        // if the result is too small tell that it is zero
        int resultExp = getExp(result);
        return resultExp-thisExp < ACCURACY ? 0.0 : result;
    }

    private double add(double other) {
        int otherExp = getExp(num);
        int thisExp = getExp(num);

        // if other is too small relatively to our coordinate
        // return the original coordinate
        if (otherExp - thisExp < ACCURACY)
            return num;

        // if our coordinate is too small relatively to other
        // return other coordinate
        if (thisExp - otherExp < ACCURACY)
            return other;

        double result = num + other;
        // if the result is too small tell that it is zero
        int resultExp = getExp(result);
        return resultExp-thisExp < ACCURACY ? 0.0 : result;
    }

    private double _scale(double num1) {
        int deltaExp=getExp(num1-1);
        return deltaExp<ACCURACY? num: num*num1;
    }

    /***********Administration***********/

    @Override //checks if 2 instances of Coordinate are equals
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;

        Coordinate other = (Coordinate) obj;
        return subtract(other.num) == 0.0;
    }

    @Override
    public String toString() {
        return "" + num;
    }


}
