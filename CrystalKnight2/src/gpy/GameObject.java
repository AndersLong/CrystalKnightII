package gpy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import stt.DIR;
import stt.OBJ_STATE;


public abstract class GameObject extends Locatable{

	protected boolean lcol,rcol,ucol,dcol;
	protected int xv,yv;
	protected Cycler cycler;
	protected OBJ_ID id;
	protected OBJ_STATE stt;
	protected Rectangle battleCollider;
	
	public GameObject(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y);
		this.xv = xv;
		this.yv = yv;
		this.cycler = cycler;
		this.id = id;
		this.stt = OBJ_STATE.NORM;
	}

	public GameObject(int x, int y, Cycler cycler, OBJ_ID id) {
		this(x, y, 0, 0, cycler, id);
	}
	
	public abstract void update();
	
	public abstract void draw(Graphics g);
	
	public void drawImg(Graphics g, BufferedImage img) {
		g.drawImage(img, x, y, w, h, null);
	}
	
	public boolean touchingInstanceOf(OBJ_ID otherId,DIR side) {
		for(GameObject b: cycler.getObjs()) {
			if(b.getId()==otherId) {
				if(touching(b,side)) {
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean touching(GameObject b,DIR side) {
		switch(side) {
		case DOWN:
			if((this.getX()+this.getW()/8>b.getX()&&this.getX()+this.getW()/8<b.getX()+b.getW()) ||  
					(this.getX()+this.getW()*7/8>b.getX()&&this.getX()+this.getW()*7/8<b.getX()+b.getW())){
				if(this.getY()+this.getH()+this.getYv()>b.getY()&&this.getY()+this.getH()+this.getYv()<b.getY()+b.getH()) {
					return true;
				}
			}
			break;
		case UP:
			if((this.getX()+this.getW()/8>b.getX()&&this.getX()+this.getW()/8<b.getX()+b.getW()) ||  
					(this.getX()+this.getW()*7/8>b.getX()&&this.getX()+this.getW()*7/8<b.getX()+b.getW())){
				if(this.getY()+this.getYv()>b.getY()&&this.getY()+this.getYv()<b.getY()+b.getH()) {
					return true;
				}
			}
			break;
		case LEFT:
			if((this.getY()+this.getH()/8>b.getY() && this.getY()+this.getH()/8<b.getY()+b.getH())
					|| (this.getY()+this.getH()*7/8>b.getY() && this.getY()+this.getH()*7/8<b.getY()+b.getH())) {
				if(this.getX()+this.getXv()>b.getX() && this.getX()+this.getXv()<b.getX()+b.getW()) {
					return true;
				}
			}
			break;
		case RIGHT:
			if(((this.getY()+this.getH()/8>b.getY()) && (this.getY()+this.getH()/8<b.getY()+b.getH()))
					|| (this.getY()+this.getH()*7/8>b.getY()) && (this.getY()+this.getH()*7/8<b.getY()+b.getH())) {
				if((this.getX()+this.getW()+this.getXv()>b.getX()) && (this.getX()+this.getW()+this.getXv()<b.getX()+b.getW())) {
					return true;
				}
			}
			break;
		}		
		return false;
	}
	
	public boolean battleCollideWith(OBJ_ID id) {
		for(GameObject b: cycler.getObjs()) {
			if(b.getId()==id) {
				if(this.battleCollider.intersects(b.battleCollider)) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isLcol() {
		return lcol;
	}
	
	public void setLcol(boolean lcol) {
		this.lcol = lcol;
	}

	public boolean isRcol() {
		return rcol;
	}

	public void setRcol(boolean rcol) {
		this.rcol = rcol;
	}

	public boolean isUcol() {
		return ucol;
	}

	public void setUcol(boolean ucol) {
		this.ucol = ucol;
	}

	public boolean isDcol() {
		return dcol;
	}

	public void setDcol(boolean dcol) {
		this.dcol = dcol;
	}

	public int getXv() {
		return xv;
	}

	public void setXv(int xv) {
		this.xv = xv;
	}

	public int getYv() {
		return yv;
	}

	public void setYv(int yv) {
		this.yv = yv;
	}

	public OBJ_ID getId() {
		return id;
	}

	public void setId(OBJ_ID id) {
		this.id = id;
	}

	public OBJ_STATE getStt() {
		return stt;
	}

	public void setStt(OBJ_STATE stt) {
		this.stt = stt;
	}	
	
	
	
}
