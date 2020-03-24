package gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import cor.Looper;
import gpy.Cycler;
import inp.Button;
import stt.BUTTON_ID;

public class Drawer {
	
	private Canvas canvas;
	private Cycler cycler;
	private ArrayList<Button> buttons;
	
	public Drawer(Canvas canvas, Cycler cycler, ArrayList<Button> buttons) {
		this.canvas = canvas;
		this.cycler = cycler;
		this.buttons = buttons;
	}

	public void draw() {
		if(canvas.getBufferStrategy()==null) {
			canvas.createBufferStrategy(3);
		}
		BufferStrategy bufferStrategy = canvas.getBufferStrategy();
		Graphics g = bufferStrategy.getDrawGraphics();
		stateDraw(g);
		bufferStrategy.show();
		g.dispose();		
	}
	
	private void stateDraw(Graphics g) {
		switch(Looper.state) {
		case START:
			startDraw(g);
			break;
		case HELP:
			helpDraw(g);
			break;
		case BATTLE2D:
			battle2DDraw(g);
			break;
		}
	}
	
	private void startDraw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, Looper.WIDTH, Looper.HEIGHT);
		for(Button b: buttons) {
			b.draw(g);
		}
	}
	
	private void helpDraw(Graphics g) {
		/*todo
		 * implement
		 */
	}
	
	private void overworldDraw(Graphics g) {
		
	}
	
	private void battle2DDraw(Graphics g) {
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, Looper.WIDTH, Looper.HEIGHT);
		cycler.draw(g);
	
	}
}
