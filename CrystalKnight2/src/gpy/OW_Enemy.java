package gpy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import fio.ImageLoader;
import stt.DIR;
import stt.OBJ_STATE;

public class OW_Enemy extends GameObject{

	private DIR dir;
	private Random r;
	private OW_Player p;
	private boolean hitPlayer;

	public OW_Enemy(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 32;
		this.h = 32;
		this.dir = dir.LEFT;
		this.stt = OBJ_STATE.NORM;
		r = new Random();
		this.battleCollider = new Rectangle(x,y,w,h);
		this.hitPlayer = false;
	}

	public OW_Enemy(int x, int y, Cycler cycler, OBJ_ID id) {
		this(x,y,0,0,cycler,id);
	}


	public void update() {
		move();
		ai();
	}

	public void move() {
		this.x += this.xv;
		this.y += this.yv;
		this.battleCollider.x = x;
		this.battleCollider.y = y;
	}

	public void ai() {
		if(Cycler.overworldEnemyMovementTimer == 4) {
			switch(stt) {
			case NORM:
				int direction = r.nextInt(4);
				switch(direction) {
				case 0:
					if(isEnterable(cycler.getIdOfTileAt(x-32, y))) {
						this.xv = -8;
						this.dir = DIR.LEFT;
						break;						
					}
				case 1:
					if(isEnterable(cycler.getIdOfTileAt(x+32, y))) {
						this.xv = 8;
						this.dir = DIR.RIGHT;
						break;						
					}
				case 2:
					if(isEnterable(cycler.getIdOfTileAt(x, y-32))) {
						this.yv = -8;
						break;						
					}
				case 3:
					if(isEnterable(cycler.getIdOfTileAt(x, y+32))) {
						this.yv = 8;
						break;						
					}				
				}
				break;
			case AGGRO:

				if(p.getX()<this.x && isEnterable(cycler.getIdOfTileAt(x-32, y))) {
					this.xv = -8;
					this.dir = DIR.LEFT;
				}else if(p.getX()>=this.x+this.w && isEnterable(cycler.getIdOfTileAt(x+32, y))) {
					this.xv = 8;
					this.dir = DIR.RIGHT;
				}
				if(xv == 0) {
					if(p.getY()<this.y && isEnterable(cycler.getIdOfTileAt(x, y-32))) {
						this.yv = -8;
					}else if(p.getY()>=this.y+this.h && isEnterable(cycler.getIdOfTileAt(x, y+32))) {
						this.yv = 8;
					}
				}
				
			}

		}
		if(Cycler.overworldEnemyMovementTimer == 0) {
			this.xv = 0;
			this.yv = 0;
			p = (OW_Player)cycler.getObject(OBJ_ID.OW_PLAYER);
			if(this.playerInRange()) {
				this.stt = OBJ_STATE.AGGRO;
			}else {
				this.stt = OBJ_STATE.NORM;
			}
		}
	}


	public void draw(Graphics g) {
		switch(id) {
		case OW_GOB1:
			switch(dir) {
			case LEFT:
				if(cycler.overworldAnimationTimer > 20) {
					this.drawImg(g, ImageLoader.owGob1Left1);
				}else {
					this.drawImg(g, ImageLoader.owGob1Left2);
				}
				break;
			case RIGHT:
				if(cycler.overworldAnimationTimer > 20) {
					this.drawImg(g, ImageLoader.owGob1Right1);
				}else {
					this.drawImg(g, ImageLoader.owGob1Right2);
				}
				break;
			}
			break;
		case OW_BANDIT:
			switch(dir) {
			case LEFT:
				if(cycler.overworldAnimationTimer > 20) {
					this.drawImg(g, ImageLoader.owBanditLeft1);
				}else {
					this.drawImg(g, ImageLoader.owBanditLeft2);
				}
				break;
			case RIGHT:
				if(cycler.overworldAnimationTimer > 20) {
					this.drawImg(g, ImageLoader.owBanditRight1);
				}else {
					this.drawImg(g, ImageLoader.owBanditRight2);
				}
				break;
			}
			break;
		}

	}

	private boolean isEnterable(OBJ_ID id) {
		if(id == OBJ_ID.PLAINS || id == OBJ_ID.ROAD || id == OBJ_ID.FOREST) {
			return true;
		}
		return false;
	}

	private boolean playerInRange() {
		OW_Player p = (OW_Player)cycler.getObject(OBJ_ID.OW_PLAYER);
		if(Math.abs(p.getX() - this.x) < 128 && Math.abs(p.getY() - this.y) < 128){
			return true;
		}else {
			return false;
		}
	}

	public boolean isHitPlayer() {
		return hitPlayer;
	}

	public void setHitPlayer(boolean hitPlayer) {
		this.hitPlayer = hitPlayer;
	}
	
	public void reset() {
		hitPlayer = false;
		xv = 0;
		yv = 0;
	}
}
