package unitTest;

import java.awt.Desktop;
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

public class ProjectTest {


    @Test
    public void ProjectTest1() throws IOException, InterruptedException {
        Scene scene = new Scene("ProjectTest01");
        scene.setCamera(new Camera(new Point3D(400, 0, 50), new Vector(0, 0, 1), new Vector(-1, 0, 0), 15, 6000));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setDistance(400);

        Plane plane1 = new Plane(new Point3D(0, 0, -500), new Vector(0, 0, 1), new Color(0, 0, 0),
                new Material(1, 1, 20, 0.8, 0));

        scene.addGeometry(plane1);
        for (int i = 0; i < 12; i++) {
            scene.addGeometry(new Sphere(400, new Point3D(-i * 1000, -2000 + i * 1000, -1100), new Color(20, 40, 0),
                    new Material(1, 1, 20, 0.2, 0)));
            scene.addGeometry(new Sphere(300, new Point3D(-i * 1000, -2000 + i * 1000, -400), new Color(40, 20, 0),
                    new Material(1, 1, 20, 0, 0.2)));
            scene.addGeometry(new Sphere(200, new Point3D(-i * 1000, -2000 + i * 1000, 100), new Color(20, 0, 40),
                    new Material(1, 1, 20, 0.2, 0)));
            scene.addGeometry(new Sphere(100, new Point3D(-i * 1000, -2000 + i * 1000, 400), new Color(0, 20, 40),
                    new Material(1, 1, 20, 0, 0.2)));
            scene.getLights()
                    .add(new SpotLight(new Color(30, 30, 30), new Point3D(500 - i * 1000, -2500 + i * 1000, 2400), 1, 0,
                            0.000001, new Vector(new Point3D(-i * 1000, -2000 + i * 1000, 1600)
                            .subVec(new Point3D(500 - i * 1000, -2500 + i * 1000, 2400)))));
        }
        scene.getLights()
                .add(new PointLight(new Color(170, 170, 170), new Point3D(-1000 , -1500 , 2400), 1, 0,
                        0.000001));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");

        Desktop d = Desktop.getDesktop();
        d.open(f);

    }






    @Test
    public void RoomTest2() throws IOException, InterruptedException{
        Scene scene= new Scene("RooomTest");
        scene.setCamera(new Camera(new Point3D(400, 0, 150), new Vector(-0.5,0,1), new Vector(-1, 0, -0.5),12,-50));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setDistance(400);

        Plane plane1 =new Plane(new Point3D(0,-200,0),new Vector(0, 1, 0),new Color(100, 100, 100), new Material(1, 1, 800,0.5,0));
        Plane plane2 =new Plane(new Point3D(0,0,-200),new Vector(0, 0, 1),new Color(100, 100, 100), new Material(1, 1, 800,0.5,0));
        Plane plane3 =new Plane(new Point3D(0,0,200),new Vector(0, 0, 1),new Color(100, 100, 100), new Material(1, 1, 800,0.5,0));
        Plane plane4 =new Plane(new Point3D(-200,0,0),new Vector(1, 0, 0),new Color(100, 100, 100), new Material(1, 1, 800,0.5,0));
        Plane plane5 =new Plane(new Point3D(0,200,0),new Vector(0, 1, 0),new Color(100, 100,100), new Material(1, 1, 800,0.5,0));

        Triangle t1=new Triangle(new Point3D(-100,-200,-200),new Point3D(-100,-200,100),new Point3D(-100,200,-200), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t2=new Triangle(new Point3D(-100,200,100),new Point3D(-100,-200,100),new Point3D(-100,200,-200), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t3=new Triangle(new Point3D(-100,200,100),new Point3D(-100,-200,100),new Point3D(-200,-200,100), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t4=new Triangle(new Point3D(-200,200,100),new Point3D(-100,200,100),new Point3D(-200,-200,100), new Color(100,100,100),new Material(1,1,80,0,0));

        Triangle t5=new Triangle(new Point3D(-195,0,0),new Point3D(-195,-100,100),new Point3D(-195,100,100), new Color(200,200,200),new Material(1,1,80,0,0));
        Triangle t6=new Triangle(new Point3D(-195,0,150),new Point3D(-195,100,50),new Point3D(-195,-100,50), new Color(200,200,200),new Material(1,1,80,0,0));

        Triangle t7=new Triangle(new Point3D(0,-200,-200),new Point3D(0,-200,0),new Point3D(0,200,-200), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t8=new Triangle(new Point3D(0,200,0),new Point3D(0,-200,0),new Point3D(0,200,-200), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t9=new Triangle(new Point3D(0,200,0),new Point3D(0,-200,0),new Point3D(-100,-200,0), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t10=new Triangle(new Point3D(-100,200,0),new Point3D(0,200,0),new Point3D(-100,-200,0), new Color(100,100,100),new Material(1,1,80,0,0));

        Triangle t11=new Triangle(new Point3D(1,0,-175),new Point3D(1,-100,-75),new Point3D(1,100,-75), new Color(0,0,0),new Material(1,1,80,0,0));
        Triangle t12=new Triangle(new Point3D(1,0,-25),new Point3D(1,100,-125),new Point3D(1,-100,-125), new Color(0,0,0),new Material(1,1,80,0,0));

        Triangle t13=new Triangle(new Point3D(100,-200,-200),new Point3D(100,-200,-100),new Point3D(100,200,-200), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t14=new Triangle(new Point3D(100,200,-100),new Point3D(100,-200,-100),new Point3D(100,200,-200), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t15=new Triangle(new Point3D(100,200,-100),new Point3D(100,-200,-100),new Point3D(0,-200,-100), new Color(100,100,100),new Material(1,1,80,0,0));
        Triangle t16=new Triangle(new Point3D(0,200,-100),new Point3D(100,200,-100),new Point3D(0,-200,-100), new Color(100,100,100),new Material(1,1,80,0,0));

        Triangle t17=new Triangle(new Point3D(0,-2,200),new Point3D(0,2,200),new Point3D(0,2,170), new Color(0,0,0),new Material(1,1,80,0,0));
        Triangle t18=new Triangle(new Point3D(0,-2,200),new Point3D(0,2,170),new Point3D(0,-2,170), new Color(0,0,0),new Material(1,1,80,0,0));

        Triangle t19=new Triangle(new Point3D(100,-100,-200),new Point3D(200,-100,-200),new Point3D(150,0,-100), new Color(0,100,100),new Material(1,1,80,0,0.6));
        Triangle t20=new Triangle(new Point3D(200,100,-200),new Point3D(200,-100,-200),new Point3D(150,0,-100), new Color(0,100,100),new Material(1,1,80,0,0.6));
        Triangle t21=new Triangle(new Point3D(200,100,-200),new Point3D(100,100,-200),new Point3D(150,0,-100), new Color(0,100,100),new Material(1,1,80,0,0.6));
        Triangle t22=new Triangle(new Point3D(100,-100,-200),new Point3D(100,100,-200),new Point3D(150,0,-100), new Color(0,100,100),new Material(1,1,80,0,0.6));


        Sphere s1 = new Sphere(40, new Point3D(-150,-150,150), new Color(0, 20, 200), new Material(1, 1, 40,1,0));  //blue sphere
        Sphere s2 = new Sphere(40, new Point3D(-50, 150,50), new Color(0, 150, 0), new Material(1, 1, 40,0.5,0));    //green sphere
        Sphere s3 = new Sphere(40, new Point3D(50, 0,-50), new Color(100, 30, 20), new Material(1, 1, 40,0.5,0));    //red sphere
        Sphere s4 = new Sphere(20, new Point3D(0, 0,150), new Color(255, 30, 20), new Material(1, 1, 40,0,0.2));  // light sphere


        scene.addGeometry(plane1,plane2,plane3,plane4,plane5,t1,t2,t3,t4,t7,t8,t9,t10,t13,t14,t15,t16,t17,t18,t19,t20,t21,t22,s1,s2,s3,s4);
        // scene.getLights().add(new SpotLight(new Color(255, 255, 255), new Point3D(200, 200, 200), 1, 0, 0.000001,new Vector(-1,-1,-1)));
        scene.getLights().add(new PointLight(new Color(155, 155, 155), new Point3D(0, 0, 150), 1, 0, 0.000001));

        ImageWriter imageWriter = new ImageWriter(scene.getName(), 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");
        Desktop d = Desktop.getDesktop();
        d.open(f);
    }


    @Test
    public void ProjectTest3() throws IOException, InterruptedException {
        Scene scene = new Scene("ProjectTest3");
        scene.setCamera(new Camera(new Point3D(400, 0, 50), new Vector(0, 0, 1), new Vector(-1, 0, 0), 100, 3000));
        scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0));
        scene.setDistance(400);

        Plane plane1 = new Plane(new Point3D(0, 0, -500), new Vector(0, 0, 1), new Color(100,100, 100),
                new Material(1, 1, 20, 0.8, 0));

        scene.addGeometry(plane1);
        for (int i = 1; i < 4; i++) {
            scene.addGeometry(new Sphere(400, new Point3D(-i * 1000, -2000 + i * 1200, -100), new Color(20, 40, 0),
                    new Material(1, 1, 20, 0, 0.5)));
            scene.addGeometry(new Sphere(200, new Point3D(-i * 1000, -2000 + i * 1200, -100), new Color(40, 0, 90),
                    new Material(1, 1, 20, 0, 0)));
            scene.addGeometry(new Sphere(300, new Point3D(-i * 1000, -2000 + i * 1200, 600), new Color(40, 20, 0),
                    new Material(1, 1, 20, 0, 0.2)));
            scene.addGeometry(new Sphere(200, new Point3D(-i * 1000, -2000 + i * 1200, 1100), new Color(20, 0, 40),
                    new Material(1, 1, 20, 0.2, 0)));
            scene.addGeometry(new Sphere(100, new Point3D(-i * 1000, -2000 + i * 1200, 1400), new Color(0, 20, 40),
                    new Material(1, 1, 20, 0, 0.2)));
            scene.getLights()
                    .add(new SpotLight(new Color(255, 255, 255), new Point3D(-500-i * 1000, -2500 + i * 1200, 1700), 1, 0,
                            0.000001,
                            new Vector(new Point3D(-i * 1000, -2000 + i * 1200, -100).subVec(new Point3D(-500-i * 1000, -2500 + i * 1200, 1700)))));
        }


        //5th
        scene.addGeometry(new Sphere(400, new Point3D(-8 * 1000, -2000 + 8 * 1200, -100), new Color(20, 40, 0),
                new Material(1, 1, 20, 0, 0.5)));
        scene.addGeometry(new Sphere(200, new Point3D(-8 * 1000, -2000 + 8 * 1200, -100), new Color(40, 0, 90),
                new Material(1, 1, 20, 0, 0)));
        scene.addGeometry(new Sphere(300, new Point3D(-8 * 1000, -2000 + 8 * 1200, 600), new Color(40, 20, 0),
                new Material(1, 1, 20, 0, 0.2)));
        scene.addGeometry(new Sphere(200, new Point3D(-8 * 1000, -2000 + 8 * 1200, 1100), new Color(20, 0, 40),
                new Material(1, 1, 20, 0.2, 0)));
        scene.addGeometry(new Sphere(100, new Point3D(-8 * 1000, -2000 + 8 * 1200, 1400), new Color(0, 20, 40),
                new Material(1, 1, 20, 0, 0.2)));
        scene.getLights()
                .add(new SpotLight(new Color(255, 255, 255), new Point3D(-500 -8 * 1000, -2500 + 8 * 1200, 1700), 1, 0,
                        0.000001,
                        new Vector(new Point3D(-8 * 1000, -2000 + 8 * 1200, -100).subVec(new Point3D(-500-8 * 1000, -2500 + 8 * 1200, 1700)))));



        //4th
        scene.addGeometry(new Sphere(400, new Point3D(-5 * 1000, -2000 + 5 * 1200, -100), new Color(20, 40, 0),
                new Material(1, 1, 20, 0, 0.5)));
        scene.addGeometry(new Sphere(400, new Point3D(-5 * 1000, -2000 + 5 * 1200, -100), new Color(40, 0, 90),
                new Material(1, 1, 20, 0, 0.5)));
        scene.addGeometry(new Sphere(200, new Point3D(-5 * 1000, -2000 + 5 * 1200, -100), new Color(20, 40, 0),
                new Material(1, 1, 20, 0.2, 0)));
        scene.addGeometry(new Sphere(300, new Point3D(-5 * 1000, -2000 + 5 * 1200, 600), new Color(40, 20, 0),
                new Material(1, 1, 20, 0, 0.2)));
        scene.addGeometry(new Sphere(200, new Point3D(-5 * 1000, -2000 + 5 * 1200, 1100), new Color(20, 0, 40),
                new Material(1, 1, 20, 0.2, 0)));
        scene.addGeometry(new Sphere(100, new Point3D(-5 * 1000, -2000 + 5 * 1200, 1400), new Color(0, 20, 40),
                new Material(1, 1, 20, 0, 0.2)));
        scene.getLights()
                .add(new SpotLight(new Color(255, 255, 255), new Point3D(-500-5 * 1000, -2500 + 5 * 1200, 1700), 1, 0,
                        0.000001,
                        new Vector(new Point3D(-5 * 1000, -2000 + 5 * 1200, -100).subVec(new Point3D(-500-5 * 1000, -2500 + 5 * 1200, 1700)))));





        ImageWriter imageWriter = new ImageWriter(scene.getName(), 1000, 1000, 1000, 1000);
        Render render = new Render(imageWriter, scene);
        render.renderImage();
        render.writeToImage();
        File f = new File(scene.getName() + ".jpg");

        Desktop d = Desktop.getDesktop();
        d.open(f);

    }

}
