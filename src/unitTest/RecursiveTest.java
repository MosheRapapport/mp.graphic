package unitTest;

import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import org.junit.Test;

import elements.AmbientLight;
import elements.Camera;
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

public class RecursiveTest {

    @Test
    public void ReflectedLightTest() throws IOException, InterruptedException {
        Scene scene = new Scene("ReflectedTest");
        scene.setCamera(new Camera(new Point3D(0, 50, 0), new Vector(0, 0, 1), new Vector(0, 1, 0),0,-1));
        scene.setDistance(300);

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        Plane plane1 = new Plane(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Color(100, 0, 0),
                new Material(1, 1, 800, 0, 0));
        Plane plane2 = new Plane(new Point3D(0, 100, 0), new Vector(0, 1, 0), new Color(0, 50, 0),
                new Material(1, 1, 800, 0.5, 0.5));
        scene.addGeometry(plane1, plane2);
        scene.getLights().add(new SpotLight(new Color(255, 255, 255), new Point3D(0, 50, 20), 1, 0, 0.000001,
                new Vector(0, -1, -0.5)));
        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }

    @Test
    public void CrazyTest() throws IOException, InterruptedException {
        Scene scene = new Scene("CrazyTest");
        scene.setCamera(new Camera(new Point3D(400, 0, 80), new Vector(0, 0, 1), new Vector(-1, 0, 0), 0, 10));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        scene.setDistance(300);

        Plane plane1 = new Plane(new Point3D(0, -200, 0), new Vector(0, 1, 0), new Color(100, 0, 100),
                new Material(1, 1, 800, 0, 0));
        Plane plane2 = new Plane(new Point3D(0, 0, -200), new Vector(0, 0, 1), new Color(100, 0, 100),
                new Material(1, 1, 800, 0, 0));
        Plane plane3 = new Plane(new Point3D(0, 0, 200), new Vector(0, 0, 1), new Color(100, 0, 100),
                new Material(1, 1, 800, 0, 0));
        Plane plane4 = new Plane(new Point3D(-200, 0, 0), new Vector(1, 0, 0), new Color(100, 0, 100),
                new Material(1, 1, 800, 0, 0));
        Plane plane5 = new Plane(new Point3D(0, 200, 0), new Vector(0, 1, 0), new Color(100, 0, 100),
                new Material(1, 1, 800, 0, 0));
        Triangle t1 = new Triangle(new Point3D(-100, -200, -200), new Point3D(-100, -200, -100),
                new Point3D(-100, 200, -200), new Color(0, 50, 100), new Material(1, 1, 80, 0, 0.3));
        Triangle t2 = new Triangle(new Point3D(-100, 200, -100), new Point3D(-100, -200, -100),
                new Point3D(-100, 200, -200), new Color(0, 50, 100), new Material(1, 1, 80, 0, 0.3));
        Triangle t3 = new Triangle(new Point3D(-100, 200, -100), new Point3D(-100, -200, -100),
                new Point3D(-200, -200, -100), new Color(0, 50, 100), new Material(1, 1, 80, 0, 0.3));
        Triangle t4 = new Triangle(new Point3D(-200, 200, -100), new Point3D(-100, 200, -100),
                new Point3D(-200, -200, -100), new Color(0, 50, 100), new Material(1, 1, 80, 0, 0.3));
        Triangle t5 = new Triangle(new Point3D(-195, 0, 0), new Point3D(-195, -100, 100), new Point3D(-195, 100, 100),
                new Color(50, 0, 150), new Material(1, 1, 80, 0, 0.3));
        Triangle t6 = new Triangle(new Point3D(-195, 0, 150), new Point3D(-195, 100, 50), new Point3D(-195, -100, 50),
                new Color(50, 0, 150), new Material(1, 1, 80, 0, 0.3));
        Sphere s1 = new Sphere(40, new Point3D(-150, -150, -50), new Color(0, 0, 200), new Material(1, 1, 40, 0, 0.3));
        Sphere s2 = new Sphere(40, new Point3D(-50, 150, -150), new Color(0, 150, 0), new Material(1, 1, 40, 0, 0.3));
        scene.addGeometry(plane1, plane2, plane3, plane4, plane5, t1, t2, t3, t4, t5, t6, s1, s2);
        // scene.getLights().add(new SpotLight(new Color(100, 100, 100), new Point3D(0,
        // 50, 20), 1, 0.0002, 0.000001,new Vector(0,-1,-0.5)));
        scene.getLights().add(new PointLight(new Color(155, 155, 155), new Point3D(0, 0, 180), 1, 0, 0.000001));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 500, 500, 500, 500);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
        Toolkit.getDefaultToolkit().beep();
    }

    @Test
    public void focusTest2() throws IOException, InterruptedException {
        Scene scene = new Scene("focusTest2");
        scene.setCamera(
                new Camera(new Point3D(30, -100, 130), new Vector(0, 0, 1), new Vector(0, 1, 0).normalize(), 10, 100));
        scene.setDistance(250);
        //scene.setBackground(new Color(200,200,0));

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        Plane p1 = new Plane(new Point3D(0, 0, -500), new Vector(0, 0, 1), new Color(100, 100, 100),
                new Material(1, 1, 800, 0.3, 0));
        Sphere s1 = new Sphere(40, new Point3D(0, 50, 40), new Color(100, 150, 100), new Material(1, 1, 80, 0.2, 0));
        Sphere s2 = new Sphere(40, new Point3D(100, 150, 40), new Color(150, 100, 100), new Material(1, 1, 80, 0, 0));
        Sphere s3 = new Sphere(40, new Point3D(-100, 250, 40), new Color(100, 100, 150),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s4 = new Sphere(40, new Point3D(0, 350, 40), new Color(100, 100, 100), new Material(1, 1, 80, 0, 0));
        scene.addGeometry(p1, s1, s2, s3, s4);
        scene.getLights().add(new PointLight(new Color(155, 155, 155), new Point3D(-180, 0, 180), 1, 0, 0.000001));
        ImageWriter imageWriter = new ImageWriter(scene.getName(), 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
        Toolkit.getDefaultToolkit().beep();

    }



    @Test
    public void focusTest() throws IOException, InterruptedException {
        Scene scene = new Scene("focusTest");
        scene.setCamera(
                new Camera(new Point3D(30, -100, 130), new Vector(0, 0, 1), new Vector(0, 1, 0).normalize(), 15, 10));
        scene.setDistance(400);

        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
        Plane p1 = new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Color(100, 100, 100),
                new Material(1, 1, 800, 0.3, 0));

        Sphere s1 = new Sphere(40, new Point3D(0, 50, 40), new Color(100, 150, 100), new Material(1, 1, 80, 0.2, 0));
        Sphere s2 = new Sphere(40, new Point3D(100, 150, 40), new Color(150, 100, 100), new Material(1, 1, 80, 0, 0));
        Sphere s3 = new Sphere(40, new Point3D(-100, 250, 40), new Color(100, 100, 150),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s4 = new Sphere(40, new Point3D(0, 350, 40), new Color(100, 100, 100), new Material(1, 1, 80, 0, 0));

        Sphere s1_1 = new Sphere(40, new Point3D(-450, 450, 440), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s1_2 = new Sphere(40, new Point3D(-350, 450, 440), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s1_3 = new Sphere(40, new Point3D(-250, 450, 440), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s1_4 = new Sphere(40, new Point3D(-150, 450, 440), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s1_5 = new Sphere(40, new Point3D(-50, 450, 440), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s1_6 = new Sphere(40, new Point3D(100, 450, 440), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s1_7 = new Sphere(40, new Point3D(150, 450, 440), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s1_8 = new Sphere(40, new Point3D(250, 450, 440), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s1_9 = new Sphere(40, new Point3D(350, 450, 440), new Color(120, 100, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s1_10 = new Sphere(40, new Point3D(450, 450, 440), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.2, 0));

        Sphere s2_1 = new Sphere(40, new Point3D(-450, 150, 140), new Color(100, 100, 120),
                new Material(1, 1, 80, 0, 0));
        Sphere s2_2 = new Sphere(40, new Point3D(-350, 150, 140), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s2_3 = new Sphere(40, new Point3D(-250, 150, 140), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s2_4 = new Sphere(40, new Point3D(-150, 150, 140), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s2_5 = new Sphere(40, new Point3D(-50, 150, 140), new Color(100, 100, 120),
                new Material(1, 1, 80, 0, 0));
        Sphere s2_6 = new Sphere(40, new Point3D(50, 150, 140), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s2_7 = new Sphere(40, new Point3D(150, 150, 140), new Color(120, 100, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s2_8 = new Sphere(40, new Point3D(250, 150, 140), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s2_9 = new Sphere(40, new Point3D(350, 150, 140), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s2_10 = new Sphere(40, new Point3D(450, 150, 140), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));

        Sphere s3_1 = new Sphere(40, new Point3D(-450, 250, 240), new Color(120, 100, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s3_2 = new Sphere(40, new Point3D(-350, 250, 240), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s3_3 = new Sphere(40, new Point3D(-250, 250, 240), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s3_4 = new Sphere(40, new Point3D(-150, 250, 240), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s3_5 = new Sphere(40, new Point3D(-50, 250, 240), new Color(120, 100, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s3_6 = new Sphere(40, new Point3D(50, 250, 240), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s3_7 = new Sphere(40, new Point3D(150, 250, 240), new Color(100, 100, 120),
                new Material(1, 1, 80, 0, 0));
        Sphere s3_8 = new Sphere(40, new Point3D(250, 250, 240), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));
        Sphere s3_9 = new Sphere(40, new Point3D(350, 250, 240), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.2, 0));
        Sphere s3_10 = new Sphere(40, new Point3D(450, 250, 240), new Color(100, 120, 100),
                new Material(1, 1, 80, 0, 0));

        Sphere s4_1 = new Sphere(40, new Point3D(-450, 350, 340), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s4_2 = new Sphere(40, new Point3D(-350, 350, 340), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s4_3 = new Sphere(40, new Point3D(-250, 350, 340), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s4_4 = new Sphere(40, new Point3D(-150, 350, 340), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s4_5 = new Sphere(40, new Point3D(-50, 350, 340), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s4_6 = new Sphere(40, new Point3D(50, 350, 340), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s4_7 = new Sphere(40, new Point3D(150, 350, 340), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s4_8 = new Sphere(40, new Point3D(250, 350, 340), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s4_9 = new Sphere(40, new Point3D(350, 350, 340), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s4_10 = new Sphere(40, new Point3D(450, 350, 340), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.5, 0));

        Sphere s5_1 = new Sphere(40, new Point3D(-450, 550, 540), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s5_2 = new Sphere(40, new Point3D(-350, 550, 540), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s5_3 = new Sphere(40, new Point3D(-250, 550, 540), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s5_4 = new Sphere(40, new Point3D(-150, 550, 540), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s5_5 = new Sphere(40, new Point3D(-50, 550, 540), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s5_6 = new Sphere(40, new Point3D(100, 550, 540), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s5_7 = new Sphere(40, new Point3D(150, 550, 540), new Color(100, 100, 120),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s5_8 = new Sphere(40, new Point3D(250, 550, 540), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.0, 0));
        Sphere s5_9 = new Sphere(40, new Point3D(350, 550, 540), new Color(120, 100, 100),
                new Material(1, 1, 80, 0.5, 0));
        Sphere s5_10 = new Sphere(40, new Point3D(450, 550, 540), new Color(100, 120, 100),
                new Material(1, 1, 80, 0.0, 0));

        scene.addGeometry(p1, s1_1, s1_2, s1_3, s1_4, s1_5, s1_6, s1_7, s1_8, s1_9, s1_10, s2_1, s2_2, s2_3, s2_4, s2_5,
                s2_6, s2_7, s2_8, s2_9, s2_10, s3_1, s3_2, s3_3, s3_4, s3_5, s3_6, s3_7, s3_8, s3_9, s3_10, s4_1, s4_2,
                s4_3, s4_4, s4_5, s4_6, s4_7, s4_8, s4_9, s4_10, s5_1, s5_2, s5_3, s5_4, s5_5, s5_6, s5_7, s5_8, s5_9,
                s5_10);

        scene.getLights().add(new PointLight(new Color(155, 155, 155), new Point3D(-180, 0, 180), 1, 0, 0.000001));

        Triangle t1 = new Triangle(new Point3D(-500, 600, 0), new Point3D(500, 600, 0), new Point3D(500, 600, 600),
                new Color(100, 100, 100), new Material(1, 1, 80, 0.8, 0));

        Triangle t2 = new Triangle(new Point3D(-500, 600, 0), new Point3D(-500, 600, 600), new Point3D(500, 600, 600),
                new Color(100, 100, 100), new Material(1, 1, 80, 0.8, 0));

        Triangle t3 = new Triangle(new Point3D(-500, 800, 600), new Point3D(-500, 600, 600), new Point3D(500, 600, 600),
                new Color(100, 100, 100), new Material(1, 1, 80, 0, 0));

        Triangle t4 = new Triangle(new Point3D(-500, 800, 600), new Point3D(500, 800, 600), new Point3D(500, 600, 600),
                new Color(100, 100, 100), new Material(1, 1, 80, 0, 0));

        // scene.addGeometry(t1, t2, t3, t4);

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
        Toolkit.getDefaultToolkit().beep();

    }



    @Test
    public void focusTest3() throws IOException, InterruptedException {
        Scene scene = new Scene("focusTest3");
        scene.setCamera(new Camera(new Point3D(400, 0, 70), new Vector(0, 0, 1), new Vector(-1, 0, 0), 20, 10));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setDistance(800);

        Plane plane1 = new Plane(new Point3D(0, 0, -250), new Vector(0, 0, 1), new Color(0, 100, 100),
                new Material(1, 1, 20, 1, 0.2));
        Plane plane2 = new Plane(new Point3D(-800, 0, 0), new Vector(-1, 0, 0), new Color(40, 40, 40),
                new Material(1, 1, 20, 0, 0));

        scene.addGeometry(plane1);
        for (int i = 0; i < 6; i++) {
            scene.addGeometry(new Sphere(40, new Point3D(-i * 100, -200 + i * 100, 40), new Color(20, 20, 20),
                    new Material(1, 1, 20, 0.5, 0.5)));
            scene.getLights().add(
                    new PointLight(new Color(30, 30, 30), new Point3D(-i * 100, -20 + i * 100, 120), 1, 0, 0.000001));
        }
        ImageWriter imageWriter = new ImageWriter(scene.getName(), 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);

    }



    @Test
    public void focusTest4() throws IOException, InterruptedException {
        Scene scene = new Scene("focusTest5");
        scene.setCamera(new Camera(new Point3D(400, 0, 50), new Vector(0, 0, 1), new Vector(-1, 0, 0), 30, 50));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setDistance(800);

        Plane plane1 = new Plane(new Point3D(0, 0, -50), new Vector(0, 0, 1), new Color(0, 0, 0),
                new Material(1, 1, 20, 0.8, 0));

        scene.addGeometry(plane1);
        for (int i = 0; i < 12; i++) {
            scene.addGeometry(new Sphere(40, new Point3D(-i * 100, -200 + i * 100, 40), new Color(20, 40, 0),
                    new Material(1, 1, 20, 0, 0)));
            scene.addGeometry(new Sphere(30, new Point3D(-i * 100, -200 + i * 100, 110), new Color(40, 20, 0),
                    new Material(1, 1, 20, 0, 0)));
            scene.addGeometry(new Sphere(20, new Point3D(-i * 100, -200 + i * 100, 160), new Color(20, 0, 40),
                    new Material(1, 1, 20, 0, 0)));
            scene.addGeometry(new Sphere(10, new Point3D(-i * 100, -200 + i * 100, 190), new Color(0, 20, 40),
                    new Material(1, 1, 20, 0, 0)));
            scene.getLights()
                    .add(new SpotLight(new Color(80, 80, 80), new Point3D(50 - i * 100, -250 + i * 100, 240), 1, 0,
                            0.000001, new Vector(new Point3D(-i * 100, -200 + i * 100, 160)
                            .subVec(new Point3D(50 - i * 100, -250 + i * 100, 240)))));
        }

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");

        Desktop d = Desktop.getDesktop();
        d.open(f);

    }

    @Test
    public void focusTest6() throws IOException, InterruptedException {
        Scene scene = new Scene("focusTest4");
        scene.setCamera(new Camera(new Point3D(400, 0, 100), new Vector(0,0, 1), new Vector(-1, 0, 0), 15, 50));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setDistance(400);

        Plane plane1 = new Plane(new Point3D(0, 0, 0), new Vector(0, 0, 1), new Color(500, 50, 50),
                new Material(1, 1, 20,0, 0));
        //scene.addGeometry(plane1);
        scene.addGeometry(new Sphere(40, new Point3D(-50, -100, 80),
                new Color(255,0,0 ), new Material(1, 1, 20, 0, 0)));
        scene.addGeometry(new Sphere(60, new Point3D(-50, -100, 80),
                new Color(0,0,250 ), new Material(1, 1, 20, 0, 0)));

        scene.addGeometry(new Sphere(60, new Point3D(-100, 100, 80),
                new Color(0,0,250 ), new Material(1, 1, 20, 0, 0)));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }

}
