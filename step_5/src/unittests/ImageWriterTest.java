package unittests;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import renderer.ImageWriter;

public class ImageWriterTest {

	/**
	 * This test create image without renderer.
	 * The image is one color with grid in another color.
	 */
	@Test
	public void test() {
		
		int w=1600,h=1000,nX=800,nY=500;
		
		ImageWriter img = new ImageWriter("ImageTest", w, h, nX, nY);
		
		for(int j = 0; j < nY; j++) {
			for(int i = 0; i < nX; i++) {
				if (j % 50 == 0 || i % 50 == 0) {
                    img.writePixel(i, j, Color.WHITE);
                }
				else
					img.writePixel(i, j, Color.PINK);
			}
				
		}
		img.writeToImage();
	}

}
