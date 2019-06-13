package renderer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import elements.LightSource;
import elements.PointLight;
import geometries.*;
import primitives.Color;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

/**
 * This function is rendering the scene
 *
 * @author Baruch
 *
 */

public class Render {

    private ImageWriter imageWriter;
    private Scene scene;

    /***************** Constructors **********************/

    public Render(ImageWriter _imageWriter, Scene _scene) {
        imageWriter = new ImageWriter(_imageWriter);
        scene = new Scene(_scene);
    }

    /********* Administration *********/

    /**
     * This function draw a grid given an int who is the interval between row and
     * columns
     *
     * @param interval
     *
     */
    public void printGrid(int interval) {
        int height = imageWriter.getHeight();
        int width = imageWriter.getWidth();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (i % interval == 0 || j % interval == 0)
                    imageWriter.writePixel(j, i, 255, 255, 255);
            }
        }
    }

    /**
     * This function represents a scene in a picture Check each pixel in the scene
     * and draw it in function of the intersections and the geometries who are in
     * the picture
     */
    public void renderImage() {
        for (int i = 0; i < imageWriter.getHeight(); i++) {
            for (int j = 0; j < imageWriter.getWidth(); j++) {
                Ray ray = scene.getCamera().constructRayThroughPixel(imageWriter.getNx(), imageWriter.getNy(), j, i,
                        scene.getDistance(), imageWriter.getWidth(), imageWriter.getHeight());
                Map<Geometry, Point3D> closestPoint = getClosestPoint(scene.getGeometries().findIntersections(ray));

                if (closestPoint == null) {
                    imageWriter.writePixel(j, i, scene.getBackground());
                } else {
                    Entry<Geometry, Point3D> gEntry = closestPoint.entrySet().iterator().next();
                    Color temp = calcColor(gEntry.getKey(), gEntry.getValue(),ray);
                    imageWriter.writePixel(j, i, temp.get_color());
                }
            }
        }

    }

    /**
     * This function draw the image
     */
    public void writeToImage() {
        imageWriter.writeToimage();
    }

    /**
     * This function checks the closest point to a given Geometry
     *
     * @param map
     *
     * @return the closest intersection
     */
    private Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> map) {

        if (map == null || map.size() == 0)
            return null;

        final Map<Geometry, Point3D> result = new HashMap<Geometry, Point3D>();
        Point3D p0 = scene.getCamera().getP0();
        // _maxD = Double.MAX_VALUE;
        double _maxD = Double.MAX_VALUE;

        for (Entry<Geometry, List<Point3D>> g : map.entrySet()) {
            for (Point3D p : g.getValue()) {
                double dist = p0.distance(p);
                if (dist < _maxD) {
                    _maxD = dist;
                    result.clear();
                    result.put(g.getKey(), p);
                }
            }
        }

        return result;
    }

    /**
     * The function receive a Ray and find the closest intersection to the geometries
     * @param ray
     * @return the closest intersection
     */
    private Entry<Geometry, Point3D> findClosestIntersection(Ray ray) {

        Map<Geometry, List<Point3D>> intersectionPoints = scene.getGeometries().findIntersections(ray);

        if (intersectionPoints==null ||intersectionPoints.size() == 0)
            return null;

        Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
        Entry<Geometry, Point3D> entry = closestPoint.entrySet().iterator().next();
        return entry;

    }

    /**
     * The function calculates the diffusion of the light who is reflected
     *
     * @param Kd
     * @param l vector from the light to the geometry
     * @param n vector normal from a specific point of the geometry
     * @param lightIntensity
     * @return a color
     */
    private Color calcDiffuse(double Kd, Vector l, Vector n, Color lightIntensity) {
        double k = Kd * Math.abs(n.dotProduct(l));
        return lightIntensity.scale(k);
    }

    /**
     * The function calculates the specular of the lights who is reflected
     *
     * @param Ks
     * @param l vector from the light to the geometry
     * @param n vector normal from a specific point of the geometry
     * @param v vector from the camera to the geometry
     * @param shininess
     * @param lightIntensity
     * @return a color
     */
    private Color calcSpecular(double Ks, Vector l, Vector n, Vector v, int shininess, Color lightIntensity) {
        double ln = l.dotProduct(n) * 2;
        Vector lnn = n.scalarMult(ln);
        Vector r = l.subtract(lnn).normalize();
        double vr = v.dotProduct(r);
        if (vr >= 0)
            return new Color(0, 0, 0);
        double k = Ks * Math.pow(-vr, shininess);
        return lightIntensity.scale(k);
    }

    /**
     * The function cheks if the geometry is occluded by another geometry / helps to draw the shaddow
     *
     * @param l vector from the light to the geometry
     * @param g the geometry to check
     * @param p the point on the geometry
     * @return
     */
    private double occluded(Vector l, Geometry g, Point3D p,double distance) {
        Vector lightDirection = l.scalarMult(-1);
        Vector normal = g.getNormal(p);
        Vector epsVector = normal.scalarMult((normal.dotProduct(lightDirection) > 0 ? 2 : -2));
        Point3D geoPoint = p.addVec(epsVector);
        Ray fromObjToLight = new Ray(geoPoint, lightDirection);
        Map<Geometry, List<Point3D>> intersections = scene.getGeometries().findIntersections(fromObjToLight);
        double shadowK=1;
        for(Map.Entry<Geometry, List<Point3D>> entry:intersections.entrySet()) {
            HashMap<Geometry, List<Point3D>> hash=new HashMap<Geometry, List<Point3D>>();
            hash.put(entry.getKey(),entry.getValue());
            Point3D closest=getClosestPoint(hash).entrySet().iterator().next().getValue();
            if(closest.distance(geoPoint)<distance)
                shadowK *= entry.getKey().get_material().get_Kt();
        }
        return shadowK;
    }


    /**
     * The function build the ray of the reflection to a geometry
     *
     * @param n normal
     * @param p point in the geometry
     * @param inRay ray from the light to the point
     * @return the ray of the reflection
     */
    private Ray constructReflectedRay(Vector n, Point3D p, Ray inRay) {
        Vector epsVector = n.scalarMult((n.dotProduct(inRay.getV()) > 0 ? 2 : -2));
        double ln = inRay.getV().dotProduct(n) * 2;
        Vector lnn = n.scalarMult(ln);
        Vector r = inRay.getV().subtract(lnn).normalize(); //CHECK//
        return new Ray(p.addVec(epsVector),r);
    }


    /**
     *The function build the ray of the refraction to a geometry
     *
     * @param n normal
     * @param p poimt in the geometry
     * @param inRay ray from the light to the point
     * @return the ray of the reflection
     */
    private Ray constructRefractedRay(Vector n,Point3D p, Ray inRay) {
        Vector epsVector=n.scalarMult((n.dotProduct(inRay.getV()) > 0 ? 2 : -2));
        return new Ray(p.addVec(epsVector),inRay.getV());
    }

    private Color calcColor(Geometry g, Point3D p,Ray inRay) {

        return calcColor(g,p,inRay,3,1);

    }

    /**
     * This function calculate the color in a specific point of the scene
     *
     * @param p
     *
     * @return the color in the point
     */
    private Color calcColor(Geometry g, Point3D p,Ray inRay, int level, double k) {

        if(level==0||Coordinate.ZERO.equals(new Coordinate(k)))
            return new Color(0,0,0);

        Color ip = scene.getAmbientLight().getIntensity();
        ip = ip.add(g.get_Ie());
        Vector n = g.getNormal(p);
        Vector v = inRay.getV();
        int nShininess = g.get_material().get_nShininess();
        double Kd = g.get_material().get_Kd();
        double Ks = g.get_material().get_Ks();
        double o=1;
        for (LightSource lsource : scene.getLights()) {
            Vector l = lsource.getL(p);
            if (l.dotProduct(n) * v.dotProduct(n) >= 0) {
                Double distance=Double.MAX_VALUE;
                if(lsource instanceof PointLight) {
                    PointLight temp=(PointLight)lsource;
                    distance=p.distance(temp.getPosition());
                }
                o=occluded(l,g,p,distance);
            }
            if (!(Coordinate.ZERO.equals(new Coordinate(o*k)))) {
                Color lightIntensity=lsource.getIntensity(p).scale(o);
                ip = ip.add(calcDiffuse(Kd, l, n, lightIntensity),
                        calcSpecular(Ks, l, n, v, nShininess, lightIntensity));
            }
        }

        Ray reflectedRay = constructReflectedRay(g.getNormal(p), p, inRay);
        Entry<Geometry, Point3D> reflectedEntry = findClosestIntersection(reflectedRay);
        double kr=g.get_material().get_Kr();
        Color reflected=new Color(0,0,0);
        if (reflectedEntry != null){
            reflected = calcColor(reflectedEntry.getKey(),
                    reflectedEntry.getValue(), reflectedRay, level - 1,k*kr).scale(kr);
        }

        // Recursive call for a refracted ray
        Ray refractedRay = constructRefractedRay(n, p, inRay);
        Entry<Geometry, Point3D> refractedEntry = findClosestIntersection(refractedRay);
        double kt=g.get_material().get_Kt();
        Color refracted=new Color(0,0,0);
        if (refractedEntry != null){
            refracted = calcColor(refractedEntry.getKey(),
                    refractedEntry.getValue(), refractedRay, level - 1,k*kt).scale(kt);
        }


        return (ip.add(reflected).add(refracted));
    }

}
