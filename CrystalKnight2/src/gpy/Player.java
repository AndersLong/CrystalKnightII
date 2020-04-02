
package gpy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import fio.ImageLoader;
import inp.KeyWatcher;
import stt.DIR;
import stt.OBJ_STATE;

public class Player extends GameObject{
	
	/**
	 * FIELDS
	 */

	private int xvspd,jumpArc;
	private int animationTimer, attackTimer, hitTimer;
	private DIR dir;
	private boolean alreadySwungSword;
	private int stamina = 20;

	/**
	 * @param x
	 * @param y
	 * @param xv
	 * @param yv
	 * @param cycler
	 * @param id
	 */
	
	public Player(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 64;
		this.h = 64;
		this.xvspd = 5;
		this.jumpArc = 15;
		dir = DIR.DOWN;
		alreadySwungSword = false;
		this.battleCollider = new Rectangle(x+w/4,y,w/2,h);

	}

	/**
	 * @param x
	 * @param y
	 * @param cycler
	 * @param id
	 */
	public Player(int x, int y,Cycler cycler, OBJ_ID id) {
		this(x, y, 0, 0, cycler,id);		
	}

	/**
	 * this function currently contains the entry point to
	 * movement
	 * collision
	 * timer
	 * and eventually a sword swinging
	 */
	public void update() {
		
		if(stt == OBJ_STATE.NORM) {
			keyMovement();
			attack();
		}
		
		move();
		collide();
		timer();
	}
	
	public void attack() {
		if(KeyWatcher.pressed.contains(' ') && !alreadySwungSword && !KeyWatcher.spaceAlreadyPressed && cycler.hud.getStamina() > 60) {
			cycler.hud.decrementStamina(60);
			cycler.removeObject(OBJ_ID.SWORD);
			alreadySwungSword = true;
			KeyWatcher.spaceAlreadyPressed = true;
			if(dcol) {
				stt = OBJ_STATE.GROUND_ATTACK;
			}else {
				stt = OBJ_STATE.JUMP_ATTACK;
			}
			attackTimer = 20;
			xv = xv/2;
			switch(dir) {
			case LEFT:
				cycler.addSword(x-48, y+16,xv,0,this,attackTimer);
				break;
			case RIGHT:
				cycler.addSword(x+64, y+16,xv,0,this,attackTimer);
				break;
			}
		
		}
		
	}

	/**
	 * contains timer data
	 */
	
	public void timer() {
		if(animationTimer>0) {
			animationTimer--;
		}else {
			animationTimer = 48;
		}
		if(attackTimer>0) {
			attackTimer--;
		}
		if(attackTimer == 0 && stt != OBJ_STATE.HIT) {
			stt = OBJ_STATE.NORM;
			alreadySwungSword = false;
		}
		if(hitTimer > 0) {
			hitTimer--;
		}
		if(hitTimer == 0 && stt == OBJ_STATE.HIT) {
			stt = OBJ_STATE.NORM;
		}
		if(this.stamina < this.cycler.hud.getMaxPlayerStamina()) {
			this.cycler.hud.incrementStamina(1);
		}
	}

	/**
	 * involves all the key input logic for movement
	 */
	
	public void keyMovement(){
		if(KeyWatcher.pressed.contains('a') && !KeyWatcher.pressed.contains('d')) {
			if(!lcol) {
				dir = DIR.LEFT;			
				this.xv = -xvspd;
			}
		}
		if(!KeyWatcher.pressed.contains('a') && KeyWatcher.pressed.contains('d')) {
			if(!rcol) {
				dir = DIR.RIGHT;
				this.xv = xvspd;

			}
		}
		if(!KeyWatcher.pressed.contains('a') && !KeyWatcher.pressed.contains('d')) {
			this.xv = 0;
		}
		if(KeyWatcher.pressed.contains('w') && dcol && cycler.hud.getStamina() > 40) {
			this.yv = -jumpArc;
			this.cycler.hud.decrementStamina(40);
		}
	}

	
	/**
	 * Contains the logic for moving the object and its collision rectangle
	 */
	
	public void move() {
		if(!dcol) {
			this.yv++;
		}
		this.x += this.xv;
		this.y += this.yv;
		this.battleCollider.x += this.xv;
		this.battleCollider.y += this.yv;
	}

	/**
	 * Manages collision of player object so far only contains logic
	 * with block collision 
	 */
	
	public void collide() {
		
		if(this.x < 0 || this.x + this.w > 640) {
			if(cycler.baddiesAllDead()) {
				for(GameObject obj: cycler.getOw_chunk().getEnemies()) {
					OW_Enemy e = (OW_Enemy)obj;
					if(e.isHitPlayer()) {
						cycler.getOw_chunk().getEnemies().remove(obj);
					}
				}
			}
			cycler.loadLevel();
		}
		for(GameObject obj: cycler.getObjs()) {
			if(isHostile(obj)) {
				if(this.battleCollider.intersects(obj.battleCollider)) {
					if(hitTimer <= 0) {
						if(cycler.hud.getHp() > 0) {
							if(obj.getX() < this.x + this.w /2 ) {
								this.dir = DIR.RIGHT;
								this.xv = 5;
							}else {
								this.xv = -5;
							}
							this.stt = OBJ_STATE.HIT;
							this.yv = -7;
							this.hitTimer = 30;
							this.attackTimer = 0;
							this.alreadySwungSword = false;
							this.cycler.hud.decrementHp(1);
						}
						if(this.cycler.hud.getHp() == 0) {
							System.out.println("dead");
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
		case GOBLIN1:
			return true;
		case BANDIT:
			return true;
		case SWORD:
			Sword sword = (Sword)obj;
			if(sword.getOwner().getId() != this.id) {
				return true;
			}
			break;
		}
		return false;
	}
	
	public void hostileCollision() {
		
	}
	

	/**
	 * Contains all the logical operators for the sub-draw logic methods
	 */
	
	
	public void draw(Graphics g) {
		if(dir == DIR.RIGHT) {
			if(dcol) {
				if(stt == OBJ_STATE.NORM) {
					if(xv!=0) {
						walkingRightDraw(g);
					}else {
						stillRightDraw(g);
					}
				}else if(stt == OBJ_STATE.GROUND_ATTACK) {
					rightAttack1Draw(g);
				}else if(stt == OBJ_STATE.JUMP_ATTACK) {
					rightJumpAttackDraw(g);
				}
			}else {
				if(stt == OBJ_STATE.NORM) {
					this.drawImg(g, ImageLoader.playerRight3);
				}else if(stt == OBJ_STATE.JUMP_ATTACK){
					rightJumpAttackDraw(g);
				}
			}
		}else if(dir == DIR.LEFT) {
			if(dcol) {
				if(stt == OBJ_STATE.NORM) {
					if(xv!=0) {
						walkingLeftDraw(g);
					}else {
						stillLeftDraw(g);
					}
				}else if(stt == OBJ_STATE.GROUND_ATTACK) {
					leftAttack1Draw(g);
				}else if(stt == OBJ_STATE.JUMP_ATTACK) {
					leftJumpAttackDraw(g);
				}
			}else {
				if(stt == OBJ_STATE.NORM) {
					this.drawImg(g, ImageLoader.playerLeft3);
				}else if(stt == OBJ_STATE.JUMP_ATTACK){
					leftJumpAttackDraw(g);
				}
			}
		}
		g.setColor(Color.RED);
		g.drawRect(this.battleCollider.x, this.battleCollider.y, this.battleCollider.width, this.battleCollider.height);
	}

	/**
	 * handles the drawing of walking right
	 * @param g
	 */
	
	public void walkingRightDraw(Graphics g) {
		if(animationTimer>40) {
			this.drawImg(g, ImageLoader.playerRight1);
		}
		else if(animationTimer<=40 && animationTimer>32) {
			this.drawImg(g, ImageLoader.playerRight2);
		}
		else if(animationTimer<=32 && animationTimer>24) {
			this.drawImg(g, ImageLoader.playerRight3);
		}
		else if(animationTimer<=24 && animationTimer>16) {
			this.drawImg(g, ImageLoader.playerRight4);
		}
		else if(animationTimer<=16 && animationTimer>8) {
			this.drawImg(g, ImageLoader.playerRight5);
		}
		else if(animationTimer<=8) {
			this.drawImg(g, ImageLoader.playerRight4);
		}
	}
	
	/**
	 * facing right
	 * @param g
	 */

	public void stillRightDraw(Graphics g) {
		if(animationTimer > 24) {
			this.drawImg(g, ImageLoader.playerRight1);
		}else {
			this.drawImg(g, ImageLoader.playerRight6);
		}
	}
	
	/**
	 * handles the drawing of walking left
	 * @param g
	 */

	public void walkingLeftDraw(Graphics g) {
		if(animationTimer>40) {
			this.drawImg(g, ImageLoader.playerLeft1);
		}
		else if(animationTimer<=40 && animationTimer>32) {
			this.drawImg(g, ImageLoader.playerLeft2);
		}
		else if(animationTimer<=32 && animationTimer>24) {
			this.drawImg(g, ImageLoader.playerLeft3);
		}
		else if(animationTimer<=24 && animationTimer>16) {
			this.drawImg(g, ImageLoader.playerLeft4);
		}
		else if(animationTimer<=16 && animationTimer>8) {
			this.drawImg(g, ImageLoader.playerLeft5);
		}
		else if(animationTimer<=8) {
			this.drawImg(g, ImageLoader.playerLeft4);
		}
	}

	/**
	 * facing left
	 * @param g
	 */
	
	public void stillLeftDraw(Graphics g) {
		if(animationTimer > 24) {
			this.drawImg(g, ImageLoader.playerLeft1);
		}else {
			this.drawImg(g, ImageLoader.playerLeft6);
		}
	}

	public void rightAttack1Draw(Graphics g) {
		if(attackTimer>17) {
			g.drawImage(ImageLoader.playerRightAttack1, x, y, w, h, null);
		}
		else if(attackTimer<=17 && attackTimer>14) {
			g.drawImage(ImageLoader.playerRightAttack2, x, y, w*2, h, null);
		}
		else if(attackTimer<=14 && attackTimer>11) {
			g.drawImage(ImageLoader.playerRightAttack3, x, y, w*2, h, null);
		}
		else if(attackTimer<=11) {
			g.drawImage(ImageLoader.playerRightAttack4, x, y, w*2, h, null);
		}
	}

	public void leftAttack1Draw(Graphics g) {
		if(attackTimer>17) {
			g.drawImage(ImageLoader.playerLeftAttack1, x, y, w, h, null);
		}
		else if(attackTimer<=17 && attackTimer>14) {
			g.drawImage(ImageLoader.playerLeftAttack2, x-w, y, w*2, h, null);
		}
		else if(attackTimer<=14 && attackTimer>11) {
			g.drawImage(ImageLoader.playerLeftAttack3, x-w, y, w*2, h, null);
		}
		else if(attackTimer<=11) {
			g.drawImage(ImageLoader.playerLeftAttack4, x-w, y, w*2, h, null);
		}
	}
	
	public void rightJumpAttackDraw(Graphics g) {
		if(attackTimer>17) {
			g.drawImage(ImageLoader.playerRightAttack21, x, y, w, h, null);
		}
		else if(attackTimer<=17 && attackTimer>14) {
			g.drawImage(ImageLoader.playerRightAttack22, x, y, w*2, h, null);
		}
		else if(attackTimer<=14 && attackTimer>11) {
			g.drawImage(ImageLoader.playerRightAttack23, x, y, w*2, h, null);
		}
		else if(attackTimer<=11) {
			g.drawImage(ImageLoader.playerRightAttack24, x, y, w*2, h, null);
		}
	}
	
	public void leftJumpAttackDraw(Graphics g) {
		if(attackTimer>17) {
			g.drawImage(ImageLoader.playerLeftAttack21, x, y, w, h, null);
		}
		else if(attackTimer<=17 && attackTimer>14) {
			g.drawImage(ImageLoader.playerLeftAttack22, x-w, y, w*2, h, null);
		}
		else if(attackTimer<=14 && attackTimer>11) {
			g.drawImage(ImageLoader.playerLeftAttack23, x-w, y, w*2, h, null);
		}
		else if(attackTimer<=11) {
			g.drawImage(ImageLoader.playerLeftAttack24, x-w, y, w*2, h, null);
		}
		
	}
}