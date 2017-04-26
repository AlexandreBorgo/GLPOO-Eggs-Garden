package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")
public class HUD_game extends JPanel implements MouseListener {
	
	private Game game;
	
	private Image rock;
	private Image kid;
	private Image kid_top;
	private Image kid_left;
	private Image kid_right;
	private Image egg;
	private Image ground;
	private String image_path = "images/";
	
	private Item show;
	
	public HUD_game(Game game) {
		this.game = game;
		
		try {
			this.rock = ImageIO.read(new File(this.image_path + "rock_4.png"));
			this.kid = ImageIO.read(new File(this.image_path + "kid_1.png"));
			this.kid_top = ImageIO.read(new File(this.image_path + "kid_1_top.png"));
			this.kid_left = ImageIO.read(new File(this.image_path + "kid_1_left.png"));
			this.kid_right = ImageIO.read(new File(this.image_path + "kid_1_right.png"));
			this.egg = ImageIO.read(new File(this.image_path + "egg_1.png"));
			this.ground = ImageIO.read(new File(this.image_path + "ground_1.png"));			
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(this.game.getState() == State.GAME_STATE) {
			// display garden or warning message if not garden is selected
			if(this.game.garden != null) {
				Item map[][] = this.game.garden.getMap();
				int size_x = this.game.garden.getSizeX();
				int size_y = this.game.garden.getSizeY();
				
				for(int i=0; i<size_x; i++) {
					for(int j=0; j<size_y; j++) {
						if(map[i][j] == Item.NO_ITEM) g.drawImage(this.ground, i*30, j*30, this);
						else if(map[i][j] == Item.ROCK) g.drawImage(this.rock, i*30, j*30, this);
						else if(map[i][j] == Item.EGG) {
							g.drawImage(this.ground, i*30, j*30, this);
							g.drawImage(this.egg, i*30, j*30, this);
						}
					}
				}
				
				ArrayList<Kid> list_kids = this.game.garden.getKidsList(); 
				for(int i=0; i<list_kids.size(); i++ ) {
					String d = list_kids.get(i).getDirection();
					if(d.equals("S")) {
						g.drawImage(this.kid, list_kids.get(i).getPosX()*30, list_kids.get(i).getPosY()*30, this);
					} else if(d.equals("N")) {
						g.drawImage(this.kid_top, list_kids.get(i).getPosX()*30, list_kids.get(i).getPosY()*30, this);
					} else if(d.equals("W")) {
						g.drawImage(this.kid_left, list_kids.get(i).getPosX()*30, list_kids.get(i).getPosY()*30, this);
					} else if(d.equals("E")) {
						g.drawImage(this.kid_right, list_kids.get(i).getPosX()*30, list_kids.get(i).getPosY()*30, this);
					}
				}
			}
			else {
				Font font = new Font("Courier", Font.BOLD, 20);
			    g.setFont(font);
			    g.setColor(Color.black);          
			    g.drawString("No garden loaded, you can open a garden ", 10, 100);
			    g.drawString("or create one under File menu.", 10, 125); 
			}
		}
		else {
			g.drawImage(this.ground, 0, 0, this);
			g.drawImage(this.rock, 0, 30, this);
			g.drawImage(this.ground, 0, 60, this);
			g.drawImage(this.egg, 0, 60, this);
			g.drawImage(this.ground, 0, 90, this);
			g.drawImage(this.kid, 0, 90, this);
			g.drawImage(this.ground, 0, 120, this);
			g.drawImage(this.kid_top, 0, 120, this);
			g.drawImage(this.ground, 0, 150, this);
			g.drawImage(this.kid_left, 0, 150, this);
			g.drawImage(this.ground, 0, 180, this);
			g.drawImage(this.kid_right, 0, 180, this);
			
			Item map[][] = this.game.garden.getMap();
			int size_x = this.game.garden.getSizeX();
			int size_y = this.game.garden.getSizeY();
			
			for(int i=0; i<size_x; i++) {
				for(int j=0; j<size_y; j++) {
					if(map[i][j] == Item.NO_ITEM) g.drawImage(this.ground, i*30+35, j*30, this);
					else if(map[i][j] == Item.ROCK) g.drawImage(this.rock, i*30+35, j*30, this);
					else if(map[i][j] == Item.EGG) {
						g.drawImage(this.ground, i*30+35, j*30, this);
						g.drawImage(this.egg, i*30+35, j*30, this);
					}
				}
			}
			
			ArrayList<Kid> list_kids = this.game.garden.getKidsList(); 
			for(int i=0; i<list_kids.size(); i++ ) {
				String d = list_kids.get(i).getDirection();

				if(d.equals("S")) {
					g.drawImage(this.kid, list_kids.get(i).getPosX()*30+35, list_kids.get(i).getPosY()*30, this);
				} else if(d.equals("N")) {
					g.drawImage(this.kid_top, list_kids.get(i).getPosX()*30+35, list_kids.get(i).getPosY()*30, this);
				} else if(d.equals("W")) {
					g.drawImage(this.kid_left, list_kids.get(i).getPosX()*30+35, list_kids.get(i).getPosY()*30, this);
				} else if(d.equals("E")) {
					g.drawImage(this.kid_right, list_kids.get(i).getPosX()*30+35, list_kids.get(i).getPosY()*30, this);
				}
			}
			
			Point mouse_point = MouseInfo.getPointerInfo().getLocation();
			Point panel_point = this.game.hud_game.getLocationOnScreen();
			int case_x = (int) ((mouse_point.x - 35 - panel_point.x) / 30);
			int case_y = (int) ((mouse_point.y - panel_point.y) / 30);
			
			if(mouse_point.x - 35 - panel_point.x >= 0) {
				if(case_x >= 0 && case_x <= 9 && case_y >= 0 && case_y <= 9) {
					if(this.show == Item.NO_ITEM) {
						g.drawImage(this.ground, case_x*30+35, case_y*30, this);
					} else if(this.show == Item.ROCK) {
						g.drawImage(this.rock, case_x*30+35, case_y*30, this);				
					} else if(this.show == Item.EGG) {
						g.drawImage(this.egg, case_x*30+35, case_y*30, this);
					} else if(this.show == Item.KID) {
						g.drawImage(this.kid, case_x*30+35, case_y*30, this);
					} else if(this.show == Item.KID_TOP) {
						g.drawImage(this.kid_top, case_x*30+35, case_y*30, this);
					} else if(this.show == Item.KID_LEFT) {
						g.drawImage(this.kid_left, case_x*30+35, case_y*30, this);
					} else if(this.show == Item.KID_RIGHT) {
						g.drawImage(this.kid_right, case_x*30+35, case_y*30, this);
					}
				}
			}
		}
	}
	
	public void setPreferredSize(int size_x, int size_y) {	
		int payload = 0;
		if(this.game.getState() == State.EDITOR_STATE) payload = 5;
		Dimension d = new Dimension((size_x)*30+payload,(size_y)*30);
        this.setPreferredSize(d);
	}

	public void mouseClicked(MouseEvent event) {
		if(SwingUtilities.isLeftMouseButton(event)) {			

			Point point = event.getPoint();
			
			if(point.x < 30) {
				if(point.y >= 0 && point.y < 30) {
					this.show = Item.NO_ITEM;
				} else if(point.y >= 30 && point.y < 60) {
					this.show = Item.ROCK;				
				} else if(point.y >= 60 && point.y < 90) {
					this.show = Item.EGG;
				} else if(point.y >= 90 && point.y < 120) {
					this.show = Item.KID;
				} else if(point.y >= 120 && point.y < 150) {
					this.show = Item.KID_TOP;
				} else if(point.y >= 150 && point.y < 180) {
					this.show = Item.KID_LEFT;
				} else if(point.y >= 180 && point.y < 210) {
					this.show = Item.KID_RIGHT;
				}
			}
			else {
				Point mouse_point = MouseInfo.getPointerInfo().getLocation();
				Point panel_point = this.game.hud_game.getLocationOnScreen();
				int case_x = (int) ((mouse_point.x - 35 - panel_point.x) / 30);
				int case_y = (int) ((mouse_point.y - panel_point.y) / 30);
				
				Item map[][] = this.game.garden.getMap();
				
				if(this.show == Item.NO_ITEM || this.show == Item.ROCK || this.show == Item.EGG) {
					map[case_x][case_y] = this.show;
				} else if(this.show == Item.KID || this.show == Item.KID_TOP || this.show == Item.KID_LEFT || this.show == Item.KID_RIGHT) {
					ArrayList<Kid> list_kids = this.game.garden.getKidsList();
					
					boolean add = true;
					
					for(int i=0; i<list_kids.size(); i++ ) {

						if(list_kids.get(i).getPosX() == case_x && list_kids.get(i).getPosY() == case_y) {
							add = false;							
							break;
						}
					}
					
					if(add) {
						Kid kid = null;
						if(this.show == Item.KID) {
							kid = new Kid(case_x, case_y, "S", null, "");
						} else if(this.show == Item.KID_TOP) {
							kid = new Kid(case_x, case_y, "N", null, "");
						} else if(this.show == Item.KID_LEFT) {
							kid = new Kid(case_x, case_y, "W", null, "");
						} else if(this.show == Item.KID_RIGHT) {
							kid = new Kid(case_x, case_y, "E", null, "");
						}
						
						if(kid != null) {
							list_kids.add(kid);						
							map[case_x][case_y] = Item.NO_ITEM;
						}
					}					
				}
				
				this.game.garden.setMap(map);
			}
		}
		else if(SwingUtilities.isRightMouseButton(event)) {
			this.show = Item.NO_ITEM;
		}
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
