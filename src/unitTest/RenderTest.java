package unitTest;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class RenderTest {

    @Test
    public void basicRendering() {

        Scene scene = new Scene("Test");

        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1.0, 0.0, 0.0), new Vector(0.0, 0.0, -1.0),0,0));

        scene.setAmbientLight(new AmbientLight(new Color(80, 0, 0), 0.5));

        Triangle triangle = new Triangle(new Point3D(100, 0, -50), new Point3D(0, 100, -50), new Point3D(100, 100, -50),
                new Color(0, 180, 0), new Material(1, 1, 1,0,0));

        Triangle triangle2 = new Triangle(new Point3D(100, 0, -50), new Point3D(0, -100, -50),
                new Point3D(100, -100, -50), new Color(0, 0, 180), new Material(1, 1, 1,0,0));

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -50), new Point3D(0, 100, -50),
                new Point3D(-100, 100, -50), new Color(150, 150, 150), new Material(1, 1, 1,0,0));

        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -50), new Point3D(0, -100, -50),
                new Point3D(-100, -100, -50), new Color(20, 20, 20), new Material(1, 1, 1,0,0));

        scene.addGeometry(new Sphere(30, new Point3D(0.0, 0.0, -50), new Color(155, 99, 150), new Material(1, 1, 1,0,0)),
                triangle, triangle2, triangle3, triangle4);

        ImageWriter imageWriter = new ImageWriter("/src/images/Render_test1", 501, 501, 501, 501);

        Render render = new Render(imageWriter, scene);

        //render.renderImage();
        render.printGrid(50);
        render.writeToImage();

    }

}
