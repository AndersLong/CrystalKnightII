package fio;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public void init() {
		
	}
	
	
	public BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResourceAsStream(path));
		} catch (IOException e) {
			System.exit(1);
			return null;
		}
	}
	
	public BufferedImage getSubImage(BufferedImage img, int x, int y, int w, int h) {
		return img.getSubimage(x, y, w, h);
	}
}
