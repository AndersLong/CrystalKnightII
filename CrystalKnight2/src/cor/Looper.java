package cor;

import java.awt.Canvas;

import gfx.Drawer;
import gfx.Updater;
import gfx.Window;
import gpy.Cycler;
import inp.KeyWatcher;
import stt.GAME_STATE;


public class Looper implements Runnable{

	public static GAME_STATE state;
	private Cycler cycler;
	private Drawer drawer;
	private Updater updater;
	private boolean running;
	private double pauseTime;
	private Canvas canvas;
	private Window window;
	public static int WIDTH,HEIGHT;
	private KeyWatcher kw;

	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	private void init() {
		this.running = true;
		this.pauseTime = 1000.0/60;
		this.canvas = new Canvas();
		this.WIDTH = 640;
		this.HEIGHT = 640;
		this.state = GAME_STATE.GAME;
		cycler = new Cycler();
		window = new Window(canvas,"CRYSTAL KNIGHT II",WIDTH,HEIGHT);
		kw = new KeyWatcher();
		canvas.requestFocus();
		canvas.addKeyListener(kw);
		drawer = new Drawer(canvas,cycler);
		updater = new Updater(cycler);
	}

	public void run() {
		this.init();
		while(running) {
			updater.update();
			drawer.draw();
			this.pause();
		}

	}

	public void pause() {
		try {
			Thread.sleep((long)pauseTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}




}
