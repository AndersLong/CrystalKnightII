package inp;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import cor.Looper;
import fio.FileReader;
import gpy.Cycler;
import stt.BUTTON_ID;
import stt.GAME_STATE;

public class Menu implements MouseListener{

	ArrayList<Button> buttons;
	Cycler cycler;

	public Menu(ArrayList<Button> buttons, Cycler cycler) {
		this.buttons = buttons;
		buttons.add(new Button(10,Looper.HEIGHT - 128,128,64,BUTTON_ID.START));
		buttons.add(new Button(Looper.WIDTH-128-10,Looper.HEIGHT - 128,128,64,BUTTON_ID.HELP));
		buttons.add(new Button(Looper.WIDTH/2-128/2,Looper.HEIGHT - 128,128,64,BUTTON_ID.QUIT));
		this.cycler = cycler;
	}

	public void mouseClicked(MouseEvent e) {
		try {
			for(Button b: buttons) {
				if(b.pointWithin(e.getX(), e.getY())) {
					switch(b.getId()) {
					case START:
						cycler.setNewData("overworld_5_5",320, 320);
						cycler.setOw_chunk(FileReader.getLevel(cycler, cycler.getLevel()));
						cycler.loadLevel();
						Looper.state = GAME_STATE.OVERWORLD;
						break;
					case HELP:
						Looper.state = GAME_STATE.HELP;
						buttons.clear();
						buttons.add(new Button(0,Looper.HEIGHT-64,128,64,BUTTON_ID.BACK));
						break;
					case QUIT:
						System.exit(1);
						break;
					case BACK:
						this.buttons.remove(b);
						Looper.state = GAME_STATE.START;
						buttons.add(new Button(10,Looper.HEIGHT - 128,128,64,BUTTON_ID.START));
						buttons.add(new Button(Looper.WIDTH-128-10,Looper.HEIGHT - 128,128,64,BUTTON_ID.HELP));
						buttons.add(new Button(Looper.WIDTH/2-128/2,Looper.HEIGHT - 128,128,64,BUTTON_ID.QUIT));
						break;
					}
				}
			}
		}catch(ConcurrentModificationException cme) {}

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
