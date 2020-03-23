package gpy;

import java.awt.Graphics;
import java.util.ArrayList;


public class Cycler {
	
	private ArrayList<GameObject> objects;
	
	public Cycler() {
		init();
	}
	
	public void init() {
		objects = new ArrayList<>();
		addObject(20,20,0,0,OBJ_ID.PLAYER);
		addObject(50,20,0,0,OBJ_ID.BLOCK);
	}
	
	public void update() {
		for(GameObject object:objects) {
			object.update();
		}
	}
	
	public void draw(Graphics g) {
		for(GameObject object:objects) {
			object.draw(g);
		}
	}
	
	public void addObject(int x, int y, int xv, int yv, OBJ_ID id) {
		GameObject obj;
		switch(id) {
		case PLAYER:
			obj = new Player(x,y,xv,yv,this,id);
			objects.add(obj);
			break;
		case BLOCK:
			obj = new Block(x,y,xv,yv,this,id);
			objects.add(obj);
			break;
		}
	}
	
	public void addObject(int x, int y, OBJ_ID id) {
		addObject(x, y, 0, 0, id);
	}
	
	public ArrayList<GameObject> getObjs(){
		return objects;
	}
	

}
