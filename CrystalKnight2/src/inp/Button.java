package inp;

import java.awt.Graphics;

import fio.ImageLoader;
import gpy.Locatable;
import stt.BUTTON_ID;


/**
 * This contains the data associated with a generic button
 * @author Ander
 * @version 1.0
 */

public class Button extends Locatable{

	
	BUTTON_ID id;
	
	public Button(int x, int y, int w, int h, BUTTON_ID id) {
		super(x, y);
		this.w = w;
		this.h = h;
		this.id = id;
	}
	
	public boolean pointWithin(int x, int y) {
		if(x > this.x && x < this.x + this.w) {
			if(y > this.y && y<this.y + this.h) {
				return true;
			}
		}
		return false;
	}
	
	public BUTTON_ID getId() {
		return id;
	}
	
	public void draw(Graphics g) {
		switch(id) {
		case START:
			g.drawImage(ImageLoader.startButton,x,y,w,h,null);
			break;
		case HELP:
			g.drawImage(ImageLoader.helpButton,x,y,w,h,null);
			break;
		case QUIT:
			g.drawImage(ImageLoader.quitButton,x,y,w,h,null);
			break;
			
		}
	}


}
