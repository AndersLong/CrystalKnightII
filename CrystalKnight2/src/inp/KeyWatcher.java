package inp;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyWatcher implements KeyListener{

	public static boolean lkd,rkd,dkd,ukd;

	public KeyWatcher() {
		lkd = false;
		rkd = false;
		dkd = false;
		ukd = false;		
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_LEFT) {
			lkd = true;
		}
		else if(e.getKeyCode() == e.VK_RIGHT) {
			rkd = true;
		}
		else if(e.getKeyCode() == e.VK_UP) {
			ukd = true;
		}
		else if(e.getKeyCode() == e.VK_DOWN) {
			dkd = true;
		}
	}


	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == e.VK_LEFT) {
			lkd = false;
		}
		else if(e.getKeyCode() == e.VK_RIGHT) {
			rkd = false;
		}
		else if(e.getKeyCode() == e.VK_UP) {
			ukd = false;
		}
		else if(e.getKeyCode() == e.VK_DOWN) {
			dkd = false;
		}
	}

	public void keyTyped(KeyEvent e) {}

}
