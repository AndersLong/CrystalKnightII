package gpy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import fio.ImageLoader;
import stt.DIR;

public class Goblin1 extends GameObject{

	private DIR dir;
	private int xvspd,animationTimer;

	public Goblin1(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 64;
		this.h = 64;
		this.dir = DIR.LEFT;
		this.xvspd = 1;
		this.animationTimer = 40;
		this.battleCollider = new Rectangle(x+w/4,y,w/2,h);
	}

	public Goblin1(int x, int y, Cycler cycler, OBJ_ID id) {
		this(x, y, 0, 0, cycler, id);
	}

	public void update() {
		timer();
		ai();
		move();
		collide();
	}

	public void timer() {
		if(animationTimer>0) {
			animationTimer--;
		}else {
			animationTimer = 40;
		}
	}

	public void ai() {

		if(dir == DIR.LEFT) {
			this.xv = -this.xvspd;
		}
		if(dir == DIR.RIGHT) {
			this.xv = this.xvspd;
		}
		if(lcol) {
			dir = DIR.RIGHT;
		}
		if(rcol) {
			dir = DIR.LEFT;
		}
	}


	public void move() {

		if(!dcol) {
			this.yv++;
		}

		this.x += this.xv;
		this.y += this.yv;
		this.battleCollider.x += this.xv;
		this.battleCollider.y += this.yv;

	}


	public void collide() {
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, DIR.LEFT)) {
			lcol = true;
			xv = 0;
		}
		else {
			lcol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, DIR.RIGHT)) {
			rcol = true;
			xv = 0;
		}
		else {
			rcol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, DIR.UP)) {
			ucol = true;
			yv = 0;
		}
		else {
			ucol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, DIR.DOWN)) {
			dcol = true;
			yv = 0;
		}
		else {
			dcol = false;
		}		
	}

	public void draw(Graphics g) {
		switch(dir) {
		case LEFT:
			if(animationTimer > 20) {
				this.drawImg(g,ImageLoader.gob1left1);
			}else {
				this.drawImg(g, ImageLoader.gob1left2);
			}
			break;
		case RIGHT:
			if(animationTimer > 20) {
				this.drawImg(g,ImageLoader.gob1right1);
			}else {
				this.drawImg(g, ImageLoader.gob1right2);
			}
			break;
		}	
		g.setColor(Color.RED);
		g.drawRect(this.battleCollider.x, this.battleCollider.y, this.battleCollider.width, this.battleCollider.height);

		for(GameObject go: cycler.getObjs()) {
			if(go.getId() == OBJ_ID.SWORD) {
				Sword s = (Sword)go;
				if(s.getOwner().getId() == OBJ_ID.PLAYER) {
					if(this.battleCollider.intersects(go.battleCollider)) {
						cycler.removeObject(this);
					}
				}
			}
		}
	}


}
