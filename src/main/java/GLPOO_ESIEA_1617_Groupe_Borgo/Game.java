package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Game {
	
	public Window window;
	public HUD hud;
	
	public Garden garden = null;
	
	private Thread thread;
	private boolean anim = true;
	
	public Game() {
		window = new Window(this);
		hud = new HUD(this);
	}

	public void play() {
		if(this.garden != null) {
			if(this.thread == null) {
				thread = new Thread(new Play());
				thread.start();
			}
			else if(this.thread != null) {				
				if(this.thread.isAlive()) {
					this.anim = true;
				}
			}
		}
		else {
			JOptionPane.showMessageDialog(this.window, "No garden loaded.", "Play error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void pause() {
		if(this.thread != null && this.thread.isAlive()) {
			this.anim = false;
		}
		else {
			JOptionPane.showMessageDialog(this.window, "No animation to pause.", "Pause error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	class Play implements Runnable {

		public void run() {
			boolean play = true;
			boolean stop;
			
			int size_x = garden.getSizeX();
			int size_y = garden.getSizeY();
			
			while(play) {
				if(anim) {
					stop = true;
					ArrayList<Kid> list_kids = garden.getKidsList(); 
					for(int i=0; i<list_kids.size(); i++ ) {
						String d = list_kids.get(i).getDirection();
						int px = list_kids.get(i).getPosX();
						int py = list_kids.get(i).getPosY();
						ArrayList<String> path = list_kids.get(i).getPath();
						
						if(garden.getItemAt(px, py) == Item.EGG) {
							list_kids.get(i).addEgg();
							garden.takeEgg(px, py);
							stop = false;							
						}
						else {		
							if(path.size() > 0) {
								if(path.get(0).equals("A")) {
									if(d.equals("N")) {
										if(py > 0) {
											list_kids.get(i).setPosY(py-1);
										}	
									}
									else if(d.equals("W")) {
										if(px > 0) {
											list_kids.get(i).setPosX(px-1);
										}	
									}
									else if(d.equals("E")) {
										if(px < size_y-1) {
											list_kids.get(i).setPosX(px+1);
										}	
									}
									else if(d.equals("S")) {
										if(py < size_y-1) {
											list_kids.get(i).setPosY(py+1);
										}							
									}
								}
								else if(path.get(0).equals("G")) {
									if(d.equals("N")) list_kids.get(i).setDirection("W");
									else if(d.equals("W")) list_kids.get(i).setDirection("S");
									else if(d.equals("E")) list_kids.get(i).setDirection("N");
									else if(d.equals("S")) list_kids.get(i).setDirection("E");
								}
								else if(path.get(0).equals("D")) {
									if(d.equals("N")) list_kids.get(i).setDirection("E");
									else if(d.equals("W")) list_kids.get(i).setDirection("N");
									else if(d.equals("E")) list_kids.get(i).setDirection("S");
									else if(d.equals("S")) list_kids.get(i).setDirection("W");							
								}
								
								path.remove(0);
								list_kids.get(i).setPath(path);
								
								stop = false;
							}
						}
					}
					
					if(stop) {
						play = false;
					}
										
					callRepaint();
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
			anim = false;
		}
		
		public void callRepaint() {
			if(!SwingUtilities.isEventDispatchThread()) {
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						hud.repaint();
					}
				});
			}
		}
	}
}
