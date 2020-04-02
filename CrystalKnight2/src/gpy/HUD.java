package gpy;

import java.awt.Color;
import java.awt.Graphics;

public class HUD {
	private int maxPlayerHP,maxPlayerStamina,hp,stamina;

	public HUD(int maxPlayerHP, int maxPlayerStamina, int hp, int stamina) {
		this.maxPlayerHP = maxPlayerHP;
		this.maxPlayerStamina = maxPlayerStamina;
		this.hp = hp;
		this.stamina = stamina;
	}

	public int getMaxPlayerHP() {
		return maxPlayerHP;
	}

	public void setMaxPlayerHP(int maxPlayerHP) {
		this.maxPlayerHP = maxPlayerHP;
	}

	public int getMaxPlayerStamina() {
		return maxPlayerStamina;
	}

	public void setMaxPlayerStamina(int maxPlayerStamina) {
		this.maxPlayerStamina = maxPlayerStamina;
	}

	public int getHp() {
		return hp;
	}

	public void decrementHp(int drop) {
		this.hp -= drop;
	}
	
	public void incrementHp(int up) {
		this.hp += up;
		if(this.hp > this.maxPlayerHP) {
			this.hp = this.maxPlayerHP;
		}
	}

	public int getStamina() {
		return stamina;
	}

	public void incrementStamina(int up) {
		this.stamina += up;
		if(this.stamina > this.maxPlayerStamina) {
			this.stamina = this.maxPlayerStamina;
		}
	}
	
	public void decrementStamina(int down) {
		this.stamina -= down;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(20, 20, stamina, 10);
	}



}
