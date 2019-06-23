package primitives;

/**
 * This class represent a color who is represented by 3 integers r for the red g
 * for the green b for the blue
 *
 */
public class Color {

    // private Color _color;
    private double r;
    private double g;
    private double b;

    /**
     * The getter checks if the integers are in the legal range who is [0-255] If
     * the integers are not in the range the getter fix it and then return the color
     *
     * @return the color
     */
    public java.awt.Color get_color() {

        r = r >= 255 ? 255 : r;
        r = r <= 0 ? 0 : r;
        g = g >= 255 ? 255 : g;
        g = g <= 0 ? 0 : g;
        b = b >= 255 ? 255 : b;
        b = b <= 0 ? 0 : b;
        return new java.awt.Color((int) r, (int) g, (int) b);
    }

    /*********** Constructors ***********/

    public Color(double _r, double _g, double _b) {
        r = _r;
        g = _g;
        b = _b;
    }

    // copy constructor
    public Color(Color c) {
        r = c.r;
        g = c.g;
        b = c.b;
    }

    /****** Administation ******/

    /**
     * The function add color one to the other to form a new color
     *
     * @param ...c
     *
     */
    public Color add(Color... c) {
        double tempr=r,tempg=g,tempb=b;
        for (Color m : c) {
            tempr += m.r;
            tempg += m.g;
            tempb += m.b;
        }
        return new Color(tempr,tempg,tempb);
    }

    /**
     * The function multiplied the Color by a double d
     *
     * @param d
     *
     */
    public Color scale(double d) {
        return new Color(r * d, g * d, b * d);

    }

    /**
     * The function reduce the intensity of the color by dividing it
     *
     * @param d
     *
     */
    public Color reduce(double d) {
        return new Color(r / d, g / d, b / d);
    }
}
