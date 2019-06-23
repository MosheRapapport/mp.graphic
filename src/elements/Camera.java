package elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import primitives.*;

/**
 *
 * This class represents a camera
 *
 * **/

public class Camera {

    private Point3D p0;
    private Vector vUp;
    private Vector vTo;
    private Vector vRight;
    private double aperture;
    private double focalLength;
    private static int NUM_RAYS_FOCUS = 15;
    private static Random r = new Random();


    /*********** Getters ***********/
    /**
     * The distance between the view plane and the focal plane
     *
     * @return double
     */
    public double getFocalLength() {
        return focalLength;
    }

    /**
     * The size of the aperture
     *
     * @return double
     */
    public double getAperture() {
        return aperture;
    }

    /**
     * The coordinates of the camera
     *
     * @return Point3D
     */
    public Point3D getP0() {
        return p0;
    }

    /**
     * The vector "up" of the camera
     *
     * @return vector
     */
    public Vector getVUp() {
        return vUp;
    }

    /**
     * The vector "To" of the camera
     *
     * @return vector
     */
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
     *
     * @param p
     * @param vUp
     * @param vTo
     * @param _aperture size of the aperture on the viewplane
     * @param _focalLength distance to the focal plane from the view plane
     */
    public Camera(Point3D p, Vector vUp, Vector vTo, double _aperture, double _focalLength) {
        this.aperture = _aperture;
        this.focalLength = _focalLength;
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
     * The function calculate one ray for ,but if there is focus calculates a list of ray given the parameters of the screen
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
     * @return list of ray from the the viewplane to focus point if there is focus else from the camera to the viewplane
     */
    public List<Ray> constructRaysThroughPixel(int Nx, int Ny, int i, int j, double screenDistance, double width,
                                               double height) {

        List<Ray> rayList = new ArrayList<Ray>();
        Point3D Pij = p0.addVec(vTo.scalarMult(screenDistance));
        double Ry = height / Ny;
        double Rx = width / Nx;
        double dx = (i - (Nx + 1) / 2) * Rx;
        if (!Coordinate.ZERO.equals(dx))
            Pij = Pij.addVec(vRight.scalarMult(dx));
        double dy = (j - (Ny + 1) / 2) * Ry;
        if (!Coordinate.ZERO.equals(dx))
            Pij = Pij.addVec(vUp.scalarMult(-dy));
        Vector Vij = (Pij.subVec(p0)).normalize();
        Ray cameraRay = new Ray(p0, Vij);
        if (focalLength <= 0)
            rayList.add(cameraRay);
        else {
            double distanceToPij = Pij.distance(p0);
            Point3D fp = intersectionFocalPlane(cameraRay.getV(), screenDistance, distanceToPij);// point on the focus plane
            double halfAperture = aperture / 2;
            for (int k = 0; k < NUM_RAYS_FOCUS; k++) {
                double randValue1 = -halfAperture + (2 * halfAperture) * r.nextDouble();
                double randValue2 = -halfAperture + (2 * halfAperture) * r.nextDouble();
                Vector v1Right = getVRight().scalarMult(randValue1);
                Vector v2Up = getVUp().scalarMult(randValue2);
                Point3D headRay = Pij.addVec(v1Right).addVec(v2Up);
                rayList.add(new Ray(headRay, fp.subVec(headRay)));
            }
        }
        return rayList;
    }


    /**
     * Calculates the intersection with the focal plane
     * This is the focus point
     *
     * @param direction from the camera to the plane
     * @param screenDistance distance to the view plane
     * @param distancePixel distance to the pixel
     * @return the point of the intersection
     */
    public Point3D intersectionFocalPlane(Vector direction, double screenDistance, double distancePixel) {
        Vector v = direction.normalize();
        double scalar = distancePixel * (screenDistance + focalLength) / screenDistance;
        v = v.scalarMult(scalar);
        return p0.addVec(v);
    }

}
