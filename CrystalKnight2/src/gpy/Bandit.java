package gpy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import fio.ImageLoader;
import stt.DIR;
import stt.OBJ_STATE;

public class Bandit extends GameObject{

	//FIELDS

	private DIR dir;
	private int xvspd,animationTimer,attackTimer, hitTimer, hp;
	private boolean alreadyAttacked;

	/**
	 * @param x
	 * @param y
	 * @param xv
	 * @param yv
	 * @param cycler
	 * @param id
	 * Constructors
	 */

	public Bandit(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 64;
		this.h = 64;
		this.stt = OBJ_STATE.NORM;
		this.xvspd = 2;
		this.dir = DIR.LEFT;
		this.battleCollider = new Rectangle(x+w/4,y,w/2,h);
		this.attackTimer = 0;
		this.alreadyAttacked = false;
		this.hp = 4;
	}

	public Bandit(int x, int y, Cycler cycler, OBJ_ID id) {
		this(x, y, 0, 0, cycler, id);
	}

	/**
	 * This is the main update function
	 */
	public void update() {
		timer();
		ai();
		collide();
		move();

	}

	public void ai() {
		Player p;
		switch(stt) {
		case NORM:
			this.xvspd = 2;
			p = (Player)cycler.getObject(OBJ_ID.PLAYER);
			if(Math.abs(p.getX() + p.getW() - this.x) < 200){
				this.stt = OBJ_STATE.AGGRO;
			}else if(Math.abs(p.getX() - this.x + this.w) < 200) {
				this.stt = OBJ_STATE.AGGRO;
			}
			if(lcol) {
				dir = DIR.RIGHT;
			}
			if(rcol) {
				dir = DIR.LEFT;
			}
			break;
		case AGGRO:
			p = (Player)cycler.getObject(OBJ_ID.PLAYER);
			if(this.x > (p.getX() + p.getW()/2) && this.x < (p.getX() + p.getW() + 32) && dir == DIR.LEFT) {
				attack();
			}else if(this.x + this.w < p.getX()+ p.getW()/2 && (this.x + this.w) > (p.getX() - 32) && dir == DIR.RIGHT) {
				attack();
			}else if(p.getX() + p.getW() < this.x){
				this.dir = DIR.LEFT;
			}else if(p.getX() > this.x + this.w) {
				this.dir = DIR.RIGHT;
			}
			if(lcol || rcol) {
				xv = 0;
			}
			break;
		case GROUND_ATTACK:

			break;
		case HIT:

			break;
		}
		if(dir == DIR.LEFT && !lcol) {
			this.xv = -this.xvspd;
		}else if(dir == DIR.LEFT && lcol){
			this.xv = 0;
		}
		if(dir == DIR.RIGHT && !rcol) {
			this.xv = this.xvspd;
		}else if(dir == DIR.RIGHT && rcol){
			this.xv = 0;
		}
		if(dir == DIR.NONE) {
			this.xv = 0;
		}

	}

	public void attack() {

		if(!alreadyAttacked) {
			alreadyAttacked = true;
			this.stt = OBJ_STATE.GROUND_ATTACK;
			this.attackTimer = 70;
			this.xvspd = 4;
			switch(dir) {
			case LEFT:
				cycler.addSword(x-32, y+10, xv, yv, this, attackTimer);
				break;
			case RIGHT:
				cycler.addSword(x+w, y+10, xv, yv, this, attackTimer);
				break;
			}

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


		for(GameObject obj: cycler.getObjs()) {
			if(obj.getId() == OBJ_ID.SWORD) {
				Sword s = (Sword)obj;
				if(s.getOwner().getId() == OBJ_ID.PLAYER) {
					if(this.battleCollider.intersects(obj.battleCollider)) {
						if(hitTimer <= 0) {
							if(hp > 0) {
								GameObject swordOwner = s.getOwner();
								if(swordOwner.getX() > this.x + this.w/2) {
									this.dir = DIR.LEFT;
								}else {
									this.dir = DIR.RIGHT;
								}
								this.stt = OBJ_STATE.HIT;
								this.hitTimer = 30;
								this.xvspd = 3;
								this.yv = -10;
								this.attackTimer = 0;
								this.alreadyAttacked = false;
								this.hp--;
							}
							if(hp == 0) {
								cycler.removeObject(this);
							}
							if(cycler.getSword(this) != null) {
								cycler.removeObject(cycler.getSword(this));
							}
						}
					}
				}	
			}
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, DIR.LEFT)) {
			this.lcol = true;
			this.xv = 0;
		}else {
			this.lcol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, DIR.RIGHT)) {
			this.rcol = true;
			this.xv = 0;
		}else {
			this.rcol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, DIR.UP)) {
			this.ucol = true;
			this.yv = 0;
		}else {
			this.ucol = false;
		}
		if(this.touchingInstanceOf(OBJ_ID.BLOCK, DIR.DOWN)) {
			this.dcol = true;
			this.yv = 0;
		}else {
			this.dcol = false;
		}
	}

	public boolean isHostile(GameObject obj) {
		switch(obj.getId()) {
		case SWORD:
			Sword sword = (Sword)obj;
			if(sword.getOwner().getId() != this.id) {
				return true;
			}
			break;
		}
		return false;
	}


	public void timer() {
		if(animationTimer>0) {
			animationTimer--;
		}else {
			animationTimer = 40;
		}
		if(attackTimer > 0) {
			this.attackTimer--;
		}else if(attackTimer == 0 && (stt == OBJ_STATE.GROUND_ATTACK || stt == OBJ_STATE.NORM)){
			stt = OBJ_STATE.NORM;
			this.alreadyAttacked = false;
		}
		if(this.hitTimer > 0 && stt == OBJ_STATE.HIT) {
			this.hitTimer--;
		}else if(hitTimer <= 0 && stt == OBJ_STATE.HIT){
			this.stt = OBJ_STATE.NORM;
		}
	}

	public void draw(Graphics g) {
		if(this.stt == OBJ_STATE.NORM || this.stt == OBJ_STATE.AGGRO) {
			switch(this.dir) {
			case LEFT:
				if(animationTimer > 20) {
					this.drawImg(g, ImageLoader.banditLeft1);
				}else {
					this.drawImg(g, ImageLoader.banditLeft2);
				}
				break;
			case RIGHT:
				if(animationTimer > 20) {
					this.drawImg(g, ImageLoader.banditRight1);
				}else {
					this.drawImg(g, ImageLoader.banditRight2);
				}
				break;
			}		
		}else if(this.stt == OBJ_STATE.GROUND_ATTACK) {
			switch(this.dir) {
			case LEFT:
				if(attackTimer > 60) {
					g.drawImage(ImageLoader.banditAttackLeft1,x,y,w,h,null);
				}else if(attackTimer <= 60 && attackTimer > 50) {
					g.drawImage(ImageLoader.banditAttackLeft2,x-w,y,w*2,h,null);
				}else {
					g.drawImage(ImageLoader.banditAttackLeft3,x-w,y,w*2,h,null);
				}
				break;
			case RIGHT:
				if(attackTimer > 60) {
					g.drawImage(ImageLoader.banditAttackRight1,x,y,w,h,null);
				}else if(attackTimer <= 60 && attackTimer > 50) {
					g.drawImage(ImageLoader.banditAttackRight2,x,y,w*2,h,null);
				}else {
					g.drawImage(ImageLoader.banditAttackRight3,x,y,w*2,h,null);
				}
				break;
			}		

		}
		g.setColor(Color.RED);
		g.drawRect(this.battleCollider.x, this.battleCollider.y, this.battleCollider.width, this.battleCollider.height);


	}


}
