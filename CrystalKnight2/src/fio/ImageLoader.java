package fio;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class ImageLoader {
	
	public static BufferedImage sheet,
								startScrn,
								helpScrn,
								playerDown1,
								playerRight1,playerRight2,playerRight3,playerRight4,playerRight5,playerRight6,
								playerRightAttack1,playerRightAttack2,playerRightAttack3,playerRightAttack4,
								playerRightAttack21,playerRightAttack22,playerRightAttack23,playerRightAttack24,
								playerLeft1,playerLeft2,playerLeft3,playerLeft4,playerLeft5,playerLeft6,
								playerLeftAttack1,playerLeftAttack2,playerLeftAttack3,playerLeftAttack4,
								playerLeftAttack21,playerLeftAttack22,playerLeftAttack23,playerLeftAttack24,
								gob1right1,gob1right2,gob1left1,gob1left2,
								banditLeft1,banditLeft2,banditRight1,banditRight2,
								banditAttackLeft1,banditAttackLeft2,banditAttackLeft3,
								banditAttackRight1,banditAttackRight2,banditAttackRight3,
								startButton,helpButton,quitButton,
								plainsTile1,forestTile1,
								waterTile1,waterTile2,
								mountainTile1,
								hRoad,vRoad,urRoad,ulRoad,brRoad,blRoad,
								dessert1,dessert2,
								owPlayerRight1, owPlayerRight2,
								owPlayerLeft1,owPlayerLeft2,
								owGob1Left1,owGob1Left2,
								owGob1Right1,owGob1Right2,
								owBanditLeft1,owBanditLeft2,
								town1,
								owBanditRight1,owBanditRight2;
	int imgD = 32;
	int tileD = 16;
	

	public void init() {
		sheet = loadImage("/spritesheet.png");
		startScrn = loadImage("/titleScreen.png");
		helpScrn = loadImage("/helpMenu.jpg");
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
		
		banditLeft1 = getSubImage(sheet,imgD*0,imgD*12,imgD,imgD);
		banditLeft2 = getSubImage(sheet,imgD*1,imgD*12,imgD,imgD);
		banditRight1 = getSubImage(sheet,imgD*2,imgD*12,imgD,imgD);
		banditRight2 = getSubImage(sheet,imgD*3,imgD*12,imgD,imgD);		
		banditAttackLeft1 = getSubImage(sheet,imgD*0,imgD*13,imgD,imgD);
		banditAttackLeft2 = getSubImage(sheet,imgD*1,imgD*13,imgD*2,imgD);
		banditAttackLeft3 = getSubImage(sheet,imgD*3,imgD*13,imgD*2,imgD);
		banditAttackRight1 = getSubImage(sheet,imgD*0,imgD*14,imgD,imgD);
		banditAttackRight2 = getSubImage(sheet,imgD*1,imgD*14,imgD*2,imgD);
		banditAttackRight3 = getSubImage(sheet,imgD*3,imgD*14,imgD*2,imgD);
				
		startButton = getSubImage(sheet,0,imgD*17,imgD*2,imgD);
		helpButton = getSubImage(sheet,imgD*2,imgD*17,imgD*2,imgD);
		quitButton = getSubImage(sheet,imgD*4,imgD*17,imgD*2,imgD);
		
		owPlayerRight1 = getSubImage(sheet,0,imgD*16+tileD*0,tileD,tileD);
		owPlayerRight2 = getSubImage(sheet,tileD*1,imgD*16+tileD*0,tileD,tileD);
		owPlayerLeft1 = getSubImage(sheet,tileD*2,imgD*16+tileD*0,tileD,tileD);
		owPlayerLeft2 = getSubImage(sheet,tileD*3,imgD*16+tileD*0,tileD,tileD);
		
		owGob1Left1 = getSubImage(sheet,tileD*4,imgD*16+tileD*0,tileD,tileD);
		owGob1Left2 = getSubImage(sheet,tileD*5,imgD*16+tileD*0,tileD,tileD);
		owGob1Right1 = getSubImage(sheet,tileD*6,imgD*16+tileD*0,tileD,tileD);
		owGob1Right2 = getSubImage(sheet,tileD*7,imgD*16+tileD*0,tileD,tileD);
		
		owBanditLeft1 = getSubImage(sheet,tileD*8,imgD*16+tileD*0,tileD,tileD);
		owBanditLeft2 = getSubImage(sheet,tileD*9,imgD*16+tileD*0,tileD,tileD);
		owBanditRight1 = getSubImage(sheet,tileD*10,imgD*16+tileD*0,tileD,tileD);
		owBanditRight2 = getSubImage(sheet,tileD*11,imgD*16+tileD*0,tileD,tileD);
		
		plainsTile1 = getSubImage(sheet,0,imgD*16+tileD*1,tileD,tileD);
		forestTile1 = getSubImage(sheet,tileD*1,imgD*16+tileD*1,tileD,tileD);
		waterTile1 = getSubImage(sheet,tileD*2,imgD*16+tileD*1,tileD,tileD);
		waterTile2 = getSubImage(sheet,tileD*3,imgD*16+tileD*1,tileD,tileD);
		mountainTile1 = getSubImage(sheet,tileD*4,imgD*16+tileD*1,tileD,tileD);
		vRoad = getSubImage(sheet,tileD*5,imgD*16+tileD*1,tileD,tileD);
		hRoad = getSubImage(sheet,tileD*6,imgD*16+tileD*1,tileD,tileD);
		urRoad = getSubImage(sheet,tileD*7,imgD*16+tileD*1,tileD,tileD);
		ulRoad = getSubImage(sheet,tileD*8,imgD*16+tileD*1,tileD,tileD);
		brRoad = getSubImage(sheet,tileD*9,imgD*16+tileD*1,tileD,tileD);
		blRoad = getSubImage(sheet,tileD*10,imgD*16+tileD*1,tileD,tileD);
		dessert1 = getSubImage(sheet,tileD*11,imgD*16+tileD*1,tileD,tileD);
		dessert2 = getSubImage(sheet,tileD*12,imgD*16+tileD*1,tileD,tileD);

		town1 = getSubImage(sheet,tileD*13,imgD*16+tileD*1,tileD,tileD);

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
