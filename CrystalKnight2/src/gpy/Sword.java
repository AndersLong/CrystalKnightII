package gpy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Sword extends GameObject{
	
	GameObject owner;
	private int duration;
	

	public Sword(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id, GameObject owner, int duration) {
		super(x, y, xv, yv, cycler, id);
		this.w = 48;
		this.h = 16;
		this.battleCollider = new Rectangle(x,y,w,h);
		this.owner = owner;
		this.duration = duration;
	}
	
	public Sword(int x, int y, Cycler cycler, OBJ_ID id, GameObject owner, int duration) {
		this(x,y,0,0,cycler,id,owner,duration);
	}

	public void update() {
		this.xv = owner.getXv();
		this.yv = owner.getYv();
		this.x += xv;
		this.y += yv;
		this.battleCollider.x = x;
		this.battleCollider.y = y;
		duration--;
		if(duration == 0) {
			cycler.removeObject(this);
		}
		System.out.println(this.getOwner().getXv());
	}

	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect(battleCollider.x, battleCollider.y, battleCollider.width, battleCollider.height);
	}
	
	public GameObject getOwner() {
		return owner;
	}
	

}
