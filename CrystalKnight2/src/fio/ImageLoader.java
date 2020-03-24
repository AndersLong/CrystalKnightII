package fio;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage sheet,
								playerDown1,
								playerRight1,playerRight2,playerRight3,playerRight4,playerRight5,playerRight6,
								playerRightAttack1,playerRightAttack2,playerRightAttack3,playerRightAttack4,
								playerRightAttack21,playerRightAttack22,playerRightAttack23,playerRightAttack24,
								playerLeft1,playerLeft2,playerLeft3,playerLeft4,playerLeft5,playerLeft6,
								playerLeftAttack1,playerLeftAttack2,playerLeftAttack3,playerLeftAttack4,
								playerLeftAttack21,playerLeftAttack22,playerLeftAttack23,playerLeftAttack24,
								gob1right1,gob1right2,gob1left1,gob1left2,
								startButton,helpButton,quitButton;
	int imgD = 32;
	

	public void init() {
		sheet = loadImage("/spritesheet.png");
		playerDown1 = getSubImage(sheet,imgD*0,imgD*0,imgD,imgD);
		playerRight1 = getSubImage(sheet,imgD*0,imgD*1,imgD,imgD);
		playerRight2 = getSubImage(sheet,imgD*1,imgD*1,imgD,imgD);
		playerRight3 = getSubImage(sheet,imgD*2,imgD*1,imgD,imgD);
		playerRight4 = getSubImage(sheet,imgD*3,imgD*1,imgD,imgD);
		playerRight5 = getSubImage(sheet,imgD*4,imgD*1,imgD,imgD);
		playerRight6 = getSubImage(sheet,imgD*5,imgD*1,imgD,imgD);
		playerLeft1 = getSubImage(sheet,imgD*0,imgD*2,imgD,imgD);
		playerLeft2 = getSubImage(sheet,imgD*1,imgD*2,imgD,imgD);
		playerLeft3 = getSubImage(sheet,imgD*2,imgD*2,imgD,imgD);
		playerLeft4 = getSubImage(sheet,imgD*3,imgD*2,imgD,imgD);
		playerLeft5 = getSubImage(sheet,imgD*4,imgD*2,imgD,imgD);
		playerLeft6 = getSubImage(sheet,imgD*5,imgD*2,imgD,imgD);
		playerRightAttack1 = getSubImage(sheet,imgD*0,imgD*3,imgD,imgD);
		playerRightAttack2 = getSubImage(sheet,imgD*1,imgD*3,imgD*2,imgD);
		playerRightAttack3 = getSubImage(sheet,imgD*3,imgD*3,imgD*2,imgD);
		playerRightAttack4 = getSubImage(sheet,imgD*5,imgD*3,imgD*2,imgD);
		playerLeftAttack1 = getSubImage(sheet,imgD*0,imgD*4,imgD,imgD);
		playerLeftAttack2 = getSubImage(sheet,imgD*1,imgD*4,imgD*2,imgD);
		playerLeftAttack3 = getSubImage(sheet,imgD*3,imgD*4,imgD*2,imgD);
		playerLeftAttack4 = getSubImage(sheet,imgD*5,imgD*4,imgD*2,imgD);
		playerRightAttack21 = getSubImage(sheet,imgD*0,imgD*5,imgD,imgD);
		playerRightAttack22 = getSubImage(sheet,imgD*1,imgD*5,imgD*2,imgD);
		playerRightAttack23 = getSubImage(sheet,imgD*3,imgD*5,imgD*2,imgD);
		playerRightAttack24 = getSubImage(sheet,imgD*5,imgD*5,imgD*2,imgD);
		playerLeftAttack21 = getSubImage(sheet,imgD*0,imgD*6,imgD,imgD);
		playerLeftAttack22 = getSubImage(sheet,imgD*1,imgD*6,imgD*2,imgD);
		playerLeftAttack23 = getSubImage(sheet,imgD*3,imgD*6,imgD*2,imgD);
		playerLeftAttack24 = getSubImage(sheet,imgD*5,imgD*6,imgD*2,imgD);
		gob1left1 = getSubImage(sheet,imgD*0,imgD*11,imgD,imgD);
		gob1left2 = getSubImage(sheet,imgD*1,imgD*11,imgD,imgD);
		gob1right1 = getSubImage(sheet,imgD*2,imgD*11,imgD,imgD);
		gob1right2 = getSubImage(sheet,imgD*3,imgD*11,imgD,imgD);
		startButton = getSubImage(sheet,0,imgD*17,imgD*2,imgD);
		helpButton = getSubImage(sheet,imgD*2,imgD*17,imgD*2,imgD);
		quitButton = getSubImage(sheet,imgD*4,imgD*17,imgD*2,imgD);

		
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
