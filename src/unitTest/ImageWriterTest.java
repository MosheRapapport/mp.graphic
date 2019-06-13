package unitTest;

import org.junit.Test;

import renderer.ImageWriter;

public class ImageWriterTest {

    //String imageName, int width, int height, int Nx, int Ny

    ImageWriter _image=new ImageWriter("Test",500,500,500,500);

    @Test
    public void writeImageTest() {

        for(int i=0;i<500;i++)
            for(int j=50;j<500;j+=50)
                _image.writePixel(j, i,java.awt.Color.WHITE);


        for(int i=0;i<500;i++)
            for(int j=50;j<500;j+=50)
                _image.writePixel(i, j,java.awt.Color.WHITE);

        _image.writeToimage();


    }
}
