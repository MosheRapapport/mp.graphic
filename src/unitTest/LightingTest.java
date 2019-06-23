package unitTest;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import elements.PointLight;
import elements.SpotLight;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Color;
import primitives.Material;
import primitives.Point3D;
import primitives.Vector;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

public class LightingTest {


    @Test
    public void pointLightTestTSphere() throws IOException, InterruptedException {

        Scene scene = new Scene("pointLightTestTSphere");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1.0, 0.0, 0.0), new Vector(0.0, 0.0, -1.0),0,-1));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setDistance(200);

        Sphere sphere = new Sphere(600, new Point3D(0, 0, -1000), new Color(0, 0, 100), new Material(1, 0.7, 80,0,0));
        scene.addGeometry(sphere);

        scene.getLights()
                .add(new PointLight(new Color(150, 250, 150), new Point3D(-200, -200, -100), 0, 0, 0.000008));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();


        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }

    @Test
    public void spotLightTestSphere() throws IOException, InterruptedException {

        Scene scene = new Scene("spotLightTestSphere");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1.0, 0.0, 0.0), new Vector(0.0, 0.0, -1.0),0,-1));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setDistance(200);
        Sphere sphere = new Sphere(600, new Point3D(0, 0, -1000), new Color(0, 0, 100), new Material(1, 0.7, 80,0,0));
        scene.addGeometry(sphere);

        scene.getLights().add(
                new SpotLight(new Color(250, 150, 150), new Point3D(-200, -200, -100), 0, 0.0018, 0,
                        new Vector(0,3,-2)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);

        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();

        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }

    @Test
    public void pointLightTestTriangle() throws IOException, InterruptedException {
        Scene scene = new Scene("pointLightTestTriangle");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1),0,-1));

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.getLights()
                .add(new PointLight(new Color(255, 100, 100), new Point3D(200, 200, -1500), 1, 0, 0.000001));

        Triangle triangle1 = new Triangle(new Point3D(3000, 3000, -2000), new Point3D(-3000, -3000, -1000),
                new Point3D(3000, -3000, -2000), new Color(0, 0, 0), new Material(1, 1, 80,0,0));

        Triangle triangle2 = new Triangle(new Point3D(3000, 3000, -2000)
                , new Point3D(-3000, 3000, -1000),
                new Point3D(-3000, -3000, -1000), new Color(0, 0, 0), new Material(1, 1, 80,0,0));

        scene.addGeometry(triangle1, triangle2);

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }

    @Test
    public void spotLightTestTriangle() throws IOException, InterruptedException {
        Scene scene = new Scene("spotLightTestTriangle");
        scene.setCamera(new Camera(new Point3D(0, 0, 10), new Vector(1, 0, 0), new Vector(0, 0, -1),0,-1));

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.getLights().add(new SpotLight(new Color(255, 100, 100), new Point3D(200, 200, -100), 1, 0.00001, 0,
                new Vector(-10, -10, -3)));

        Triangle triangle1 = new Triangle(new Point3D(3000, 3000, -2000),
                new Point3D(-3000, -3000, -1000),
                new Point3D(3000, -3000, -2000),
                new Color(0, 0, 0),
                new Material(1, 1, 50,0,0));

        Triangle triangle2 = new Triangle(new Point3D(3000, 3000, -2000),
                new Point3D(-3000, 3000, -1000),
                new Point3D(-3000, -3000, -1000),
                new Color(0, 0, 0),
                new Material(1, 1, 50,0,0));

        scene.addGeometry(triangle1, triangle2);

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);

        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }

    @Test
    public void pointLightTestTriangle2() throws IOException, InterruptedException {
        Scene scene = new Scene("pointLightTestTriangle2");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0,-1, 0), new Vector(0, 0, -1),0,-1));

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        scene.getLights()
                .add(new PointLight(new Color(255, 150, 150), new Point3D(100, 100, -150), 1, 0, 0.00001));

        scene.getLights()
                .add(new SpotLight(new Color(150, 255, 150), new Point3D(-100, -100, -150), 1, 0.001, 0, new Vector (-1,-1,-1)));

        Triangle triangle1 = new Triangle(new Point3D(200, 200, -200), new Point3D(-200, -200, -200),
                new Point3D(200, -200, -100), new Color(0, 0, 0), new Material(1, 1, 80,0,0));

        Triangle triangle2 = new Triangle(new Point3D(200, 200, -200)
                , new Point3D(-200, 200, -100),
                new Point3D(-200, -200, -200), new Color(0, 0, 0), new Material(1, 1, 80,0,0));

        scene.addGeometry(triangle1, triangle2);

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }

    @Test
    public void shaddowTest() throws IOException, InterruptedException {
        Scene scene=new Scene("Shaddow Test");
        scene.setCamera(new Camera(new Point3D(0,0,0),new Vector(1,0, 0), new Vector(0, 0, -1),0,-1));
        scene.setAmbientLight(new AmbientLight(new Color(255,255,255),0.1));
        scene.getLights().add(new PointLight(new Color(255, 150, 150), new Point3D(150, -300, 0), 1, 0, 0.00001));
        scene.getLights().add(new SpotLight(new Color(150, 255, 150), new Point3D(300, 75, 150), 1, 0.01, 0, new Vector(-1,-1,-1)));
        scene.setDistance(300);

        Triangle t1=new Triangle(new Point3D(100,50,-250),new Point3D(20,-150,-200),new Point3D(20,-50,-200), new Color(0,0,100),new Material(1,1,80,0,0.1));
        Sphere s1 =new Sphere(200, new Point3D(0, 0, -500), new Color(200, 0, 0), new Material(1, 0.7, 80,0.5,0));
        scene.addGeometry(t1,s1);

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);

    }

    @Test
    public void DirectionalLightTest() throws IOException, InterruptedException {
        Scene scene = new Scene("DirectionalLightTest");
        scene.setCamera(new Camera(new Point3D(0, 0, 0), new Vector(0,0, 1), new Vector(0, 1, 0),0,-1));
        scene.setDistance(100);

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

        Plane plane1 =new Plane(new Point3D(0,500,0),new Vector(0, 1, 0),new Color(200, 20, 20), new Material(1, 1, 800,0.2,0.2));
        Plane plane2 =new Plane(new Point3D(0,0,-150),new Vector(0, 0, 1),new Color(20, 200, 20), new Material(1, 1, 80,0.2,0));
        Plane plane3 =new Plane(new Point3D(-200,0,0),new Vector(-1, 0, 0),new Color(20, 20, 200), new Material(1, 1, 80,0,0.2));

        Sphere sphere = new Sphere(100, new Point3D(0, 250,0), new Color(0, 5, 100), new Material(1, 1, 80,0.2,0));
        scene.addGeometry(sphere,plane1,plane2,plane3);

        scene.getLights().add(new DirectionalLight(new Color(150, 150, 150), new Vector(-1, 1,-1)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);

    }

}
