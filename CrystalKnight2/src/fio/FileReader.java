package fio;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import gpy.Cycler;
import gpy.GameObject;
import gpy.HUD;
import gpy.OBJ_ID;
import gpy.OW_Chunk;
import gpy.OW_Enemy;
/**
 * 
 * @author Ander
 * This class handles all file IO,
 * for example, it loads levels from txt files, and will be able
 * to read from and write to the save file
 */

public class FileReader {
	
	/**
	 * @param cycler
	 * @param level
	 * This function is goidng to be responsible for loading levels from the
	 * levels.txt folder
	 */
	
	public static OW_Chunk getLevel(Cycler cycler, String level) {
		Scanner s = new Scanner(FileReader.class.getResourceAsStream("/levels.txt"));
		while(s.hasNextLine()) {
			String line = s.nextLine().trim();
			if(line.contentEquals(level)) {
				break;
			}
		}
		ArrayList<String> rows = new ArrayList<>();
		ArrayList<GameObject> enemies = new ArrayList<>();
		Random r = new Random();
		int tileD = 32;
		for(int i = 0; i < 20; i++) {
			String line = s.nextLine();
			rows.add(line);
		}
		String enemyData = s.nextLine();
		OW_Chunk ow_chunk = new OW_Chunk(rows, enemies);
		if(enemyData.equals("NO_ENEMIES")) {
			return ow_chunk;
		}
		String[] difEnemies = enemyData.split(" ");
		for(String enemy: difEnemies) {
			if(!enemy.isBlank()) {
				String[] bits = enemy.split(":");
				String id = bits[0];
				int num = Integer.parseInt(bits[1]);
				for(int i = 0; i < num; i++) {
					int xCoord = r.nextInt(20);
					int yCoord = r.nextInt(20);
					OBJ_ID objId = null;
					switch(id) {
					case "OW_GOB1":
						objId = OBJ_ID.OW_GOB1;
						break;
					case "OW_BANDIT":
						objId = OBJ_ID.OW_BANDIT;
						break;
					
					}
					GameObject ow_enemy = new OW_Enemy(tileD * xCoord, tileD * yCoord, cycler, objId);
					enemies.add(ow_enemy);					
				}
			}
		}
		
		return ow_chunk;		
	}
	
	
	public static void loadBattleScenario(Cycler cycler, String level) {
		cycler.getObjs().clear();
		final int tileD = 64;
		Scanner s = new Scanner(FileReader.class.getResourceAsStream("/scenarios.txt"));
		while(s.hasNext()) {
			String line = s.nextLine().trim();
			if(line.contentEquals(level)) {
				break;
			}
		}
		for(int i = 0; i < 10; i++) {
			String[] rowBits = s.nextLine().split(",");
			for(int j = 0; j < rowBits.length; j++) {
				switch(Integer.parseInt(rowBits[j])) {
				case 1:
					cycler.addObject(j*tileD, i*tileD, OBJ_ID.BLOCK);
					break;
				case 2:
					cycler.addObject(j*tileD, i*tileD, OBJ_ID.PLAYER);
					break;
				case 3:
					cycler.addObject(j*tileD, i*tileD, OBJ_ID.GOBLIN1);
					break;
				case 4:
					cycler.addObject(j*tileD, i*tileD, OBJ_ID.BANDIT);
					break;

				}
			}
		}		
	}
	
	public static HUD initPlayerStats() {
		try(Scanner s = new Scanner(new File("savefile.txt"))){
			int maxHP = Integer.parseInt(s.nextLine());
			int maxStam = Integer.parseInt(s.nextLine());
			int hp = Integer.parseInt(s.nextLine());
			int stam = Integer.parseInt(s.nextLine());
			return new HUD(maxHP, maxStam, hp, stam);
		}catch(IOException e) {
			return null;
		}
	}

}
