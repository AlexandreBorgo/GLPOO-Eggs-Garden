package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Game {
	
	public Window window;
	public HUD_game hud_game;
	
	public Garden garden = null;

	private Thread thread = null;
	private Thread thread_editor = null;
	private boolean anim = true;
	public boolean game_play = true;
	private boolean editor_play = true;
	
	private State state = State.GAME_STATE;
	
	public Game() {
		hud_game = new HUD_game(this);
		window = new Window(this);
	}

	public void play() {
		if(this.garden != null && this.garden.loaded) {
			if(this.garden.getKidsList() != null && this.garden.file_loaded) {	
				if(this.thread == null) {
					if(this.state == State.GAME_STATE) {
						this.anim = true;
						thread = new Thread(new Play());
						thread.start();
					}
					else {
						JOptionPane.showMessageDialog(this.window, "Disable in editor mode.", "Play error", JOptionPane.WARNING_MESSAGE);
					}
				}
				else if(this.thread != null) {		
					if(this.thread.isAlive()) {
						this.anim = true;		
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(this.window, "No kids loaded.", "Play error", JOptionPane.WARNING_MESSAGE);
			}
			
		}
		else {
			JOptionPane.showMessageDialog(this.window, "No garden loaded.", "Play error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void restart() {
		if(this.garden != null && this.garden.loaded) {
			if(this.garden.getKidsList() != null && this.garden.file_loaded) {	
				if(this.thread != null) {
					if(this.state == State.GAME_STATE) {
						game_play = true;
						anim = false;
						thread = new Thread(new Play());
						thread.start();
					}
					else {
						JOptionPane.showMessageDialog(this.window, "Disable in editor mode.", "Play error", JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(this.window, "Need to start before restart.", "Play error", JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(this.window, "No kids loaded.", "Play error", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(this.window, "No garden loaded.", "Play error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void editor() {
		if(this.garden != null) {
			if(this.thread_editor == null) {
				thread_editor = new Thread(new Editor());
				thread_editor.start();
			}
		}
		else {
			JOptionPane.showMessageDialog(this.window, "No garden loaded.", "Editor error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	public void pause() {
		if(this.thread != null && this.thread.isAlive()) {
			this.anim = false;		
			System.out.println("pause");
		}
		else {
			JOptionPane.showMessageDialog(this.window, "No animation to pause.", "Pause error", JOptionPane.WARNING_MESSAGE);
		}
	}
	
	class Play implements Runnable {

		public void run() {
			boolean stop;
			
			int size_x = garden.getSizeX();
			int size_y = garden.getSizeY();
			
			while(game_play) {
				if(anim) {
					stop = true;
					ArrayList<Kid> list_kids = garden.getKidsList(); 
					for(int i=0; i<list_kids.size(); i++ ) {
						String d = list_kids.get(i).getDirection();
						int px = list_kids.get(i).getPosX();
						int py = list_kids.get(i).getPosY();
						ArrayList<String> path = list_kids.get(i).getPath();
						
						if(garden.getItemAt(px, py) == Item.EGG && list_kids.get(i).getNext() == false) {
							list_kids.get(i).addEgg();
							garden.takeEgg(px, py);
							stop = false;
							list_kids.get(i).setNext(true);
						}
						else {		
							list_kids.get(i).setNext(false);
							if(path.size() > 0) {
								if(path.get(0).equals("A")) {
									if(d.equals("N")) {
										if(py > 0) {
											if(!isKidOrRockAt(px, py-1)) {
												list_kids.get(i).setPosY(py-1);
											}
										}
									}
									else if(d.equals("W")) {
										if(px > 0) {
											if(!isKidOrRockAt(px-1, py)) {
												list_kids.get(i).setPosX(px-1);
											}
										}
									}
									else if(d.equals("E")) {
										if(px < size_x-1) {
											if(!isKidOrRockAt(px+1, py)) {
												list_kids.get(i).setPosX(px+1);
											}
										}
									}
									else if(d.equals("S")) {
										if(py < size_y-1) {
											if(!isKidOrRockAt(px, py+1)) {
												list_kids.get(i).setPosY(py+1);
											}
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
						game_play = false;
					}
										
					this.callRepaint();
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
			anim = false;
			JOptionPane.showMessageDialog(null, "The animation is over.", "Animation end", JOptionPane.INFORMATION_MESSAGE);
		}
		
		public boolean isKidOrRockAt(int px, int py) {
			ArrayList<Kid> list_kids = garden.getKidsList();			
			for(int i=0; i<list_kids.size(); i++ ) {
				if(list_kids.get(i).getPosX() == px && list_kids.get(i).getPosY() == py) {								
					return true;
				}
			}
			
			Item map[][] = garden.getMap();			
			if(map[px][py] == Item.ROCK) {
				return true;
			}
			
			return false;
		}
		
		public void callRepaint() {
			if(!SwingUtilities.isEventDispatchThread()) {
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						hud_game.repaint();
					}
				});
			}
		}
	}
	
	class Editor implements Runnable {

		public void run() {
			
			while(editor_play) {
				
				this.callRepaint();
				
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}				
			}
		}
		
		public void callRepaint() {
			if(!SwingUtilities.isEventDispatchThread()) {
				
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						hud_game.repaint();
					}
				});
			}
		}
	}
	
	public State getState() {
		return this.state;
	}
	
	public void setState(State state) {
		this.state = state;
	}

	public void setAnim(boolean anim) {
		this.anim = anim;
	}
	
	public void setGamePlay(boolean game_play) {
		this.game_play = game_play;
	}
	
	public void setEditorPlay(boolean editor_play) {
		this.editor_play = editor_play;
	}
}
