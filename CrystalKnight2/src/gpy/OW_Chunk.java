package gpy;

import java.util.ArrayList;

public class OW_Chunk {
	
	private ArrayList<String> rows;
	private ArrayList<GameObject> enemies;
	
	public OW_Chunk(ArrayList<String> rows, ArrayList<GameObject> enemies) {
		super();
		this.rows = rows;
		this.enemies = enemies;
	}

	public ArrayList<String> getRows() {
		return rows;
	}

	public void setRows(ArrayList<String> rows) {
		this.rows = rows;
	}

	public ArrayList<GameObject> getEnemies() {
		return enemies;
	}

	public void setEnemies(ArrayList<GameObject> enemies) {
		this.enemies = enemies;
	}
	
	

}
