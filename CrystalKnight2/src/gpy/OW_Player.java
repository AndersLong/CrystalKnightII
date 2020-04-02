package gpy;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import cor.Looper;
import fio.FileReader;
import fio.ImageLoader;
import inp.KeyWatcher;
import stt.DIR;
import stt.GAME_STATE;

public class OW_Player extends GameObject{

	private int animationTimer;
	private int moveTimer;
	private boolean moving;
	private DIR dir;
	private Random r;
	private boolean encounterAllowed;
	private int encounterCountdown;

	public OW_Player(int x, int y, int xv, int yv, Cycler cycler, OBJ_ID id) {
		super(x, y, xv, yv, cycler, id);
		this.w = 32;
		this.h = 32;
		this.animationTimer = 32;
		this.moving = false;
		this.dir = DIR.LEFT;
		this.r = new Random();
		this.encounterCountdown = 20;
		this.battleCollider = new Rectangle(x,y,w,h);
	}

	public OW_Player(int x, int y, Cycler cycler, OBJ_ID id) {
		this(x, y, 0, 0, cycler, id);
	}

	public void update() {
		timer();	
		move();
	}

	private void collision() {
		for(GameObject obj: cycler.getObjs()) {
			if(obj.isOWEnemy()) {
				if(this.battleCollider.intersects(obj.battleCollider)){
					OW_Enemy e = (OW_Enemy)obj;
					e.setHitPlayer(true);
					this.encounter(e.getId());
				}
			}
		}
	}

	private void move() {
		if(!moving) {
			if(KeyWatcher.pressed.contains('a')) {
				dir = DIR.LEFT;
				if(isEnterable(cycler.getIdOfTileAt(x-32, y))) {
					xv=-6;
					moveTimer = 5;
					moving = true;
				}else if(x-32 < 0) {
					this.loadNextChunk(DIR.LEFT);
				}
			}else if(KeyWatcher.pressed.contains('w')) {
				if(isEnterable(cycler.getIdOfTileAt(x, y-32))) {
					yv=-6;
					moveTimer = 5;
					moving = true;
				}else if(y-32 < 0){
					this.loadNextChunk(DIR.UP);
				}
			}else if(KeyWatcher.pressed.contains('d')) {
				dir = DIR.RIGHT;
				if(isEnterable(cycler.getIdOfTileAt(x+32, y))) {
					xv=6;
					moveTimer = 5;
					moving = true;
				}else if(x+32 >= Looper.WIDTH){
					this.loadNextChunk(DIR.RIGHT);
				}
			}else if(KeyWatcher.pressed.contains('s')) {
				if(isEnterable(cycler.getIdOfTileAt(x, y + 32))) {
					yv=6;
					moveTimer = 5;
					moving = true;
				}else if(y+32 >= Looper.HEIGHT){
					this.loadNextChunk(DIR.DOWN);
				}
			}
		}
		x += xv;
		y += yv;
		if(this.moveTimer==2){
			this.xv = this.xv*2/3;
			this.yv = this.yv*2/3;
		}
		this.battleCollider.x = x;
		this.battleCollider.y = y;
	}


	private void timer() {
		if(this.animationTimer > 0) {
			this.animationTimer--;
		}else {
			animationTimer = 40;
		}
		if(moveTimer == 1) {
			encounterAllowed = true;
		}
		if(moveTimer > 0) {
			moveTimer--;
		}else {
			xv = 0;
			yv = 0;
			moving = false;
			if(encounterAllowed || Cycler.overworldEnemyMovementTimer == 0) {
				collision();
				encounterAllowed = false;
			}
		}
	}

	public void draw(Graphics g) {
		switch(dir) {
		case LEFT:
			if(animationTimer > 20) {
				this.drawImg(g, ImageLoader.owPlayerLeft1);
			}else {
				this.drawImg(g, ImageLoader.owPlayerLeft2);
			}
			break;
		case RIGHT:
			if(animationTimer > 20) {
				this.drawImg(g, ImageLoader.owPlayerRight1);
			}else {
				this.drawImg(g, ImageLoader.owPlayerRight2);
			}
			break;

		}


	}

	private void encounter(OBJ_ID enemyId) {
		while(x%32!=0) {
			x++;
		}
		while(y%32!=0) {
			y++;
		}
		cycler.setPCoords(x, y);
		switch(enemyId) {
		case OW_GOB1:
			FileReader.loadBattleScenario(cycler, "plains1");
			break;
		case OW_BANDIT:
			FileReader.loadBattleScenario(cycler, "plains2");
			break;
		}
		
		Looper.state = GAME_STATE.BATTLE2D;
	}

	private boolean isEnterable(OBJ_ID id) {
		if(id == OBJ_ID.PLAINS || id == OBJ_ID.ROAD || id == OBJ_ID.FOREST) {
			return true;
		}
		return false;
	}

	public void loadNextChunk(DIR dir) {
		String newLevel = "overworld";
		String oldLevel = cycler.getLevel();
		String[] bits = oldLevel.split("_");
		int xCoord = Integer.parseInt(bits[1]);
		int yCoord = Integer.parseInt(bits[2]);
		switch(dir) {
		case LEFT:
			newLevel += ("_" + (xCoord-1) + "_" + yCoord);
			cycler.setNewData(newLevel, x + 608, y);
			break;
		case RIGHT:
			newLevel += ("_" + (xCoord+1) + "_" + yCoord);
			cycler.setNewData(newLevel, 0, y);
			break;
		case UP:
			newLevel += ("_" + xCoord + "_" + (yCoord-1));
			cycler.setNewData(newLevel, x, y+608);
			break;
		case DOWN:
			newLevel += ("_" + xCoord + "_" + (yCoord+1));
			cycler.setNewData(newLevel, x, 0);
			break;

		}
	}
}
