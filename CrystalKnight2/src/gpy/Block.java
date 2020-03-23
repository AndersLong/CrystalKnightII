package gpy;

import java.awt.Color;
import java.awt.Graphics;

public class Block extends GameObject{

	public Block(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 64;
		this.h = 64;
	}
	
	public Block(int x, int y, Cycler cycler, OBJ_ID id) {
		super(x, y, 0, 0, cycler, id);
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
	}

	public void draw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(x, y, w, h);
		
	}
	
	

}
