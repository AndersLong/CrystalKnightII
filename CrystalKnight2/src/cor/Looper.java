package cor;

import java.awt.Canvas;
import java.util.ArrayList;

import fio.ImageLoader;
import gfx.Drawer;
import gfx.Updater;
import gfx.Window;
import gpy.Cycler;
import inp.Button;
import inp.KeyWatcher;
import inp.Menu;
import stt.GAME_STATE;


public class Looper implements Runnable{

	public static GAME_STATE state;
	private Cycler cycler;
	private Drawer drawer;
	private ImageLoader il;
	private Updater updater;
	private Menu mw;
	private ArrayList<Button> buttons;
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
		this.state = GAME_STATE.START;
		buttons = new ArrayList<Button>();
		cycler = new Cycler();
		window = new Window(canvas,"CRYSTAL KNIGHT II",WIDTH,HEIGHT);
		il = new ImageLoader();
		il.init();
		kw = new KeyWatcher();
		mw = new Menu(buttons);
		canvas.requestFocus();
		canvas.addKeyListener(kw);
		canvas.addMouseListener(mw);
		drawer = new Drawer(canvas,cycler,buttons);
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
