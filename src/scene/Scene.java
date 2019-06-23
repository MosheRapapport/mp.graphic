package scene;


import elements.AmbientLight;
import elements.Camera;
import geometries.Geometries;
import geometries.Geometry;
import primitives.Color;
import elements.*;

import java.util.ArrayList;
import java.util.List;
/**
 * The Class represents a scene
 *
 *
 */
public class Scene {

    private String name;


    private Color background;
    private Geometries geometries;
    private Camera camera;
    private double distance;
    private AmbientLight ambientLight;
    private List<LightSource> lights;
    public String getName() {
        return name;
    }

    public List<LightSource> getLights(){
        return lights;
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public AmbientLight getAmbientLight() {
        return ambientLight;
    }

    public void setAmbientLight(AmbientLight a) {
        ambientLight = a;
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    /*********** Constructors ***********/
    public Scene(String _name) {
        name = _name;
        background = new Color(0, 0, 0);
        camera = null;
        geometries = new Geometries();
        distance = 100;
        ambientLight = null;
        lights=new ArrayList<LightSource>();
    }

    public Scene(Scene scene) {
        name = scene.name;
        background = scene.background;
        camera = scene.camera;
        geometries = scene.geometries;
        distance = scene.distance;
        ambientLight = scene.ambientLight;
        lights=scene.lights;
    }

    /**
     * The function add Geometries to a list of Geometry
     *
     * @param ...g
     */
    public void addGeometry(Geometry... g) {
        for (Geometry geo : g)
            geometries.add(geo);
    }
}
