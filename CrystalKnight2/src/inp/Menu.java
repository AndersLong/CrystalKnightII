package inp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import cor.Looper;
import stt.BUTTON_ID;
import stt.GAME_STATE;

public class Menu implements MouseListener{
	
	ArrayList<Button> buttons;

	public Menu(ArrayList<Button> buttons) {
		this.buttons = buttons;
		buttons.add(new Button(10,Looper.HEIGHT - 128,128,64,BUTTON_ID.START));
		buttons.add(new Button(Looper.WIDTH-128-10,Looper.HEIGHT - 128,128,64,BUTTON_ID.HELP));
		buttons.add(new Button(Looper.WIDTH/2-128/2,Looper.HEIGHT - 128,128,64,BUTTON_ID.QUIT));
	}
	
	public void mouseClicked(MouseEvent e) {
		for(Button b: buttons) {
			if(b.pointWithin(e.getX(), e.getY())) {
				switch(b.getId()) {
				case START:
					Looper.state = GAME_STATE.BATTLE2D;
					break;
				case HELP:
					break;
				case QUIT:
					System.exit(1);
					break;
				}
			}
		}
		
	}

	
	public void mousePressed(MouseEvent e) {
		
		
	}

	
	public void mouseReleased(MouseEvent e) {
		
		
	}

	
	public void mouseEntered(MouseEvent e) {
		
		
	}


	public void mouseExited(MouseEvent e) {
		
		
	}

}
