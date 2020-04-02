package gpy;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import fio.ImageLoader;

public class Tile extends GameObject{

	public Tile(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 32;
		this.h = 32;
	}
	
	public Tile(int x, int y, Cycler cycler, OBJ_ID id) {
		this(x, y, 0, 0, cycler, id);
	}

	@Override
	public void update() {}

	public void draw(Graphics g) {
		switch(id) {
		case PLAINS:
			this.drawImg(g, ImageLoader.plainsTile1);
			break;
		case FOREST:
			this.drawImg(g, ImageLoader.forestTile1);
			break;
		case WATER:
			this.timerDraw(g, ImageLoader.waterTile1, ImageLoader.waterTile2);
			break;
		case MOUNTAIN:
			this.drawImg(g, ImageLoader.mountainTile1);
			break;
		case ROAD:
			switch(xv) {
			case 1:
				this.drawImg(g, ImageLoader.hRoad);
				break;
			case 2:
				this.drawImg(g, ImageLoader.vRoad);
				break;
			case 3:
				this.drawImg(g, ImageLoader.ulRoad);
				break;
			case 4:
				this.drawImg(g, ImageLoader.urRoad);
				break;
			case 5:
				this.drawImg(g, ImageLoader.blRoad);
				break;
			case 6:
				this.drawImg(g, ImageLoader.brRoad);
				break;
			}
			break;
		case DESSERT:
			switch(xv) {
			case 1:
				this.drawImg(g, ImageLoader.dessert1);
				break;
			case 2:
				this.drawImg(g, ImageLoader.dessert2);
				break;
			}
			break;
		case TOWN:
			this.drawImg(g, ImageLoader.town1);
			break;
		}
		
	}
	
	public void timerDraw(Graphics g, BufferedImage img1, BufferedImage img2) {
		if(Cycler.overworldAnimationTimer > 20) {
			this.drawImg(g, img1);
		}else {
			this.drawImg(g, img2);
		}
	}
	
	

}
