package gpy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Sword extends GameObject{
	
	Player player;

	public Sword(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 48;
		this.h = 16;
		this.battleCollider = new Rectangle(x,y,w,h);
		this.player = (Player)cycler.getObject(OBJ_ID.PLAYER);
	}
	
	public Sword(int x, int y, Cycler cycler, OBJ_ID id) {
		this(x,y,0,0,cycler,id);
	}

	public void update() {
		this.xv = player.getXv();
		this.yv = player.getYv();
		this.x += xv;
		this.y += yv;
		this.h ++;
		this.battleCollider.height = this.h;
		this.battleCollider.x = x;
		this.battleCollider.y = y;
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(battleCollider.x, battleCollider.y, battleCollider.width, battleCollider.height);
	}
	

}
