package gfx;

import cor.Looper;
import gpy.Cycler;

public class Updater {
	
	private Cycler cycler;
	
	public Updater(Cycler cycler) {
		this.cycler = cycler;
	}
	
	public void update() {
		stateUpdate();
	}
	
	public void stateUpdate() {
		switch(Looper.state) {
		case START:
			break;
		case HELP:
			break;
		case BATTLE2D:
			cycler.update();
			break;	
		case OVERWORLD:
			cycler.update();
			break;
		}
	}

}
