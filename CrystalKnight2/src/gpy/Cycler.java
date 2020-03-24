package gpy;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;


public class Cycler {

	private ArrayList<GameObject> objects;

	public Cycler() {
		init();
	}

	public void init() {
		objects = new ArrayList<>();
		addObject(20,20,0,0,OBJ_ID.PLAYER);
		addObject(120,60,0,0,OBJ_ID.GOBLIN1);
		for(int i = 0; i < 10;i++) {
			addObject(i*64,300,64,64,OBJ_ID.BLOCK);
		}
		addObject(0*64,244,64,64,OBJ_ID.BLOCK);
		addObject(9*64,244,64,64,OBJ_ID.BLOCK);
	}

	public void update() {
		try {
			for(GameObject object:objects) {
				object.update();

			}
		}catch(ConcurrentModificationException e) {}	
	}

	public void draw(Graphics g) {
		try {
			for(GameObject object:objects) {
				object.draw(g);
			}
		}catch(ConcurrentModificationException e) {}	
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
		case SWORD:
			obj = new Sword(x,y,xv,yv,this,id);
			break;
		}
		objects.add(obj);
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


}
