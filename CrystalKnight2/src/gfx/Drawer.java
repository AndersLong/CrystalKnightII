package gfx;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import cor.Looper;
import gpy.Cycler;

public class Drawer {
	
	private Canvas canvas;
	private Cycler cycler;
	
	public Drawer(Canvas canvas, Cycler cycler) {
		this.canvas = canvas;
		this.cycler = cycler;
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
		case GAME:
			gameDraw(g);
			break;
		}
	}
	
	private void startDraw(Graphics g) {

	}
	
	private void helpDraw(Graphics g) {
		/*todo
		 * implement
		 */
	}
	
	private void gameDraw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, Looper.WIDTH, Looper.HEIGHT);
		cycler.draw(g);
	}
	
	private void battle2DDraw(Graphics g) {
		/*todo
		 * implement
		 */
	
	}
}
