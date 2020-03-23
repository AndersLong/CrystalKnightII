package gpy;

import java.awt.Color;
import java.awt.Graphics;

import inp.KeyWatcher;

public class Player extends GameObject{

	private int xvspd, yvspd, xyvspd;
	
	public Player(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 64;
		this.h = 64;
		this.xvspd = 4;
		this.yvspd = 4;
		this.xyvspd = 3;
		
	}
	
	public Player(int x, int y,Cycler cycler, OBJ_ID id) {
		this(x, y, 0, 0, cycler,id);		
	}

	public void update() {
		move();
		collide();
	}
	
	public void move() {
		if(KeyWatcher.lkd && !KeyWatcher.rkd && !lcol) {
			if(yv == 0) {
				this.xv = -this.xvspd; 
			}else {
				this.xv = -xyvspd;
			}
			
		}
		if(KeyWatcher.rkd && !KeyWatcher.lkd && !rcol) {
			if(yv == 0) {
				this.xv = this.xvspd; 
			}else {
				this.xv = xyvspd;
			}
		}
		if(!KeyWatcher.rkd && !KeyWatcher.lkd) {
			this.xv = 0;
		}
		if(KeyWatcher.ukd && !KeyWatcher.dkd && !ucol) {
			if(xv == 0) {
				this.yv = -this.yvspd; 
			}else {
				this.yv = -xyvspd;
			}
		}
		if(KeyWatcher.dkd && !KeyWatcher.ukd && !dcol) {
			if(xv == 0) {
				this.yv = this.yvspd; 
			}else {
				this.yv = xyvspd;
			}
		}
		if(!KeyWatcher.ukd && !KeyWatcher.dkd) {
			this.yv = 0;
		}
		this.x += this.xv;
		this.y += this.yv;
	}
	
	public void collide() {
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, "left")) {
			lcol = true;
			xv = 0;
		}
		else {
			lcol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, "right")) {
			rcol = true;
			xv = 0;
		}
		else {
			rcol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, "up")) {
			ucol = true;
			yv = 0;
		}
		else {
			ucol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, "down")) {
			dcol = true;
			yv = 0;
		}
		else {
			dcol = false;
		}
	}

	public void draw(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, w, h);
		
	}

}
