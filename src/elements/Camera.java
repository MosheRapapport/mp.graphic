package elements;

import primitives.*;

/**
 *
 * This class represents a camera
 *
 * @author Baruch Gehler 866256 gehlerb@gmail.com Baruch Bichman 200844843
 *         baruch913@gmail.com
 **/

public class Camera {

    private Point3D p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;

    /*********** Getters ***********/
    public Point3D getP0() {
        return p0;
    }

    public Vector getVUp() {
        return vUp;
    }

    public Vector getVTo() {
        return vTo;
    }

    public Vector getVRight() {
        return vRight;
    }

    /*********** Constructors ***********/

    /**
     * The constructor calculates the third vector for the camera The contructor
     * checks if the vectors are orthogonals
     *
     * @param p
     * @param vUp
     * @param vTo
     */
    public Camera(Point3D p, Vector vUp, Vector vTo) {
        this.p0 = new Point3D(p);
        this.vUp = new Vector(vUp).normalize();
        this.vTo = new Vector(vTo).normalize();
        if (vUp.dotProduct(vTo) != 0)
            throw new ArithmeticException("The vectors must be orthogonals");
        this.vRight = new Vector(vUp.crossProduct(vTo)).normalize();

    }

    /*********** Administration ***********/
    /**
     *
     * The function calculate the ray given the parameters of the screen
     *
     * @param Nx
     *            number of pixels in the width
     * @param Ny
     *            number of pixels in the height
     * @param i,j
     *            index of the viewPlane where the ray
     * @param screenDistance
     *            distance from the camera to the viewplane
     * @param width
     *            width of the viewplane
     * @param height
     *            height of the viewplane
     * @return the ray from the camera to the viewplane
     */
    public Ray constructRayThroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double width,
                                        double height) {
        Point3D Pc = p0.addVec(vTo.scalarMult(screenDistance));
        double Ry = height / Ny;
        double Rx = width / Nx;
        Point3D Pij = Pc.addVec(new Vector(vRight.scalarMult((i - (Nx + 1) / 2) * Rx))
                .subtract(vUp.scalarMult((j - (Ny + 1) / 2) * Ry)));
        Vector Vij = (Pij.subVec(p0)).normalize();
        return new Ray(p0, Vij);
    }

}
