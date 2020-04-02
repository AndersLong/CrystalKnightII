package gpy;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import cor.Looper;
import fio.FileReader;
import stt.GAME_STATE;


public class Cycler {

	public static int overworldAnimationTimer, overworldEnemyMovementTimer;
	private String currentOverworldBit;
	int pX;
	int pY;
	private ArrayList<GameObject> objects;
	private OW_Chunk ow_chunk;
	public HUD hud;

	public Cycler() {
		init();
	}

	public void init() {
		objects = new ArrayList<>();
		overworldAnimationTimer = 0;
		overworldEnemyMovementTimer = 0;
		hud = FileReader.initPlayerStats();
	}

	public void update() {
		try {
			for(GameObject object:objects) {
				object.update();
			}
		}catch(ConcurrentModificationException e) {}	
		timer();
	}

	public void timer() {
		if(overworldAnimationTimer > 0) {
			overworldAnimationTimer--;
		}else {
			overworldAnimationTimer = 40;
		}
		if(overworldEnemyMovementTimer > 0) {
			overworldEnemyMovementTimer--;
		}else {
			overworldEnemyMovementTimer = 16;
		}
	}

	public void draw(Graphics g) {
		try {
			for(GameObject object:objects) {
				object.draw(g);
			}
		}catch(ConcurrentModificationException e) {}	
		if(Looper.state == GAME_STATE.BATTLE2D) {
			hud.draw(g);
		}
	}

	public void addObject(int x, int y, int xv, int yv, OBJ_ID id) {
		GameObject obj = null;
		switch(id) {
		case PLAYER:
			obj = new Player(x,y,xv,yv,this,id);
			break;
		case BLOCK:
			obj = new Block(x,y,xv,yv,this,id);
			break;
		case GOBLIN1:
			obj = new Goblin1(x,y,xv,yv,this,id);
			break;
		case BANDIT:
			obj = new Bandit(x,y,xv,yv,this,id);
			break;
		case PLAINS:
			obj = new Tile(x,y,xv,yv,this,id);
			break;
		case FOREST:
			obj = new Tile(x,y,xv,yv,this,id);
			break;
		case WATER:
			obj = new Tile(x,y,xv,yv,this,id);
			break;
		case MOUNTAIN:
			obj = new Tile(x,y,xv,yv,this,id);
			break;
		case ROAD:
			obj = new Tile(x,y,xv,yv,this,id);
			break;
		case DESSERT:
			obj = new Tile(x,y,xv,yv,this,id);
			break;
		case OW_PLAYER:
			obj = new OW_Player(x,y,xv,yv,this,id);
			break;
		case OW_GOB1:
			obj = new OW_Enemy(x,y,xv,yv,this,id);
			break;
		case OW_BANDIT:
			obj = new OW_Enemy(x,y,xv,yv,this,id);
			break;
		case TOWN:
			obj = new Tile(x,y,xv,yv,this,id);
			break;
		}
		objects.add(obj);
	}

	public void addSword(int x, int y, int xv, int yv, GameObject owner, int duration) {
		GameObject sword = new Sword(x,y,xv,yv,this,OBJ_ID.SWORD,owner, duration);
		objects.add(sword);
		return;
	}

	public void addObject(int x, int y, OBJ_ID id) {
		addObject(x, y, 0, 0, id);
	}

	public ArrayList<GameObject> getObjs(){
		return objects;
	}

	public void removeObject(OBJ_ID id) {
		ArrayList<GameObject> toBeRemoved = new ArrayList<>();
		for(GameObject object: objects) {
			if(object.getId() == id) {
				toBeRemoved.add(object);
			}
		}
		for(GameObject object: toBeRemoved) {
			objects.remove(object);
		}
	}

	public void removeObject(GameObject object) {
		objects.remove(object);
	}

	public GameObject getObject(OBJ_ID id) {
		for(GameObject object: objects) {
			if(object.getId() == id) {
				return object;
			}
		}
		return null;
	}

	public OBJ_ID getIdOfTileAt(int x, int y) {
		for(GameObject object: objects) {
			if(object.getX() == x && object.getY() == y) {
				return object.getId();
			}
		}
		return null;
	}

	public void setNewData(String newOverworldBit, int pX, int pY) {
		this.setPCoords(pX, pY);
		this.currentOverworldBit = newOverworldBit;
		this.ow_chunk = FileReader.getLevel(this, currentOverworldBit);
		this.loadLevel();
	}
	
	public void loadLevel() {
		this.objects.clear();
		for(int i = 0; i < ow_chunk.getRows().size(); i++) {
			String row = ow_chunk.getRows().get(i);
			int tileD = 32;
			String[] rowBits = row.split(",");
			for(int j = 0; j < rowBits.length; j++) {
				switch(Integer.parseInt(rowBits[j])) {
				case 1:
					this.addObject(j*tileD, i*tileD, OBJ_ID.PLAINS);
					break;
				case 2:
					this.addObject(j*tileD, i*tileD, OBJ_ID.FOREST);
					break;
				case 3:
					this.addObject(j*tileD, i*tileD, OBJ_ID.WATER);
					break;
				case 4:
					this.addObject(j*tileD, i*tileD, OBJ_ID.MOUNTAIN);
					break;
				case 5: //horizontal
					this.addObject(j*tileD, i*tileD,1,0, OBJ_ID.ROAD);
					break;
				case 6: //vertical
					this.addObject(j*tileD, i*tileD,2,0, OBJ_ID.ROAD);
					break;
				case 7: // up left junction
					this.addObject(j*tileD, i*tileD,3,0, OBJ_ID.ROAD);
					break;
				case 8: // up right junction
					this.addObject(j*tileD, i*tileD,4,0, OBJ_ID.ROAD);
					break;
				case 9: // down left junction
					this.addObject(j*tileD, i*tileD,5,0, OBJ_ID.ROAD);
					break;
				case 10: // down right junction
					this.addObject(j*tileD, i*tileD,6,0, OBJ_ID.ROAD);
					break;
				case 11: // cacti
					this.addObject(j*tileD, i*tileD,1,0, OBJ_ID.DESSERT);
					break;
				case 12: // down right junction
					this.addObject(j*tileD, i*tileD,2,0, OBJ_ID.DESSERT);
					break;
				case 13: // down right junction
					this.addObject(j*tileD, i*tileD,2,0, OBJ_ID.TOWN);
					break;
				
				}
			}
		}
		this.overworldEnemyMovementTimer = 0;
		for(GameObject obj: ow_chunk.getEnemies()) {
			OW_Enemy owe = (OW_Enemy)obj;
			owe.reset();
			while(obj.getX()%32 != 0){				
				obj.setX(obj.getX()-1);
			}
			while(obj.getY()%32!=0) {
				obj.setY(obj.getY()-1);
			}
			objects.add(obj);
		}
		this.addObject(pX, pY, OBJ_ID.OW_PLAYER);
	}

	public void setLevel(String level) {
		this.currentOverworldBit = level;
	}

	public String getLevel() {
		return this.currentOverworldBit;
	}

	public GameObject getSword(GameObject owner) {
		for(GameObject obj: objects) {
			if(obj.getId() == OBJ_ID.SWORD){
				Sword s = (Sword)obj;
				if(s.getOwner() == owner) {
					return obj;
				}
			}
		}
		return null;
	}
	
	public void setPCoords(int pX, int pY) {
		this.pX = pX;
		this.pY = pY;
	}
	
	
	public boolean baddiesAllDead() {
		for(GameObject obj: objects) {
			if(obj.isBattleEnemy()) {
				return false;
			}
		}
		return true;
	}

	public OW_Chunk getOw_chunk() {
		return ow_chunk;
	}

	public void setOw_chunk(OW_Chunk ow_chunk) {
		this.ow_chunk = ow_chunk;
	}
	
	
}
