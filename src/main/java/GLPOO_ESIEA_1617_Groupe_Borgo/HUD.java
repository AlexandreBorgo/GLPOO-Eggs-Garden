package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class HUD extends JPanel {
	
	private Game game;
	
	private Image rock;
	private Image kid;
	private Image kid_top;
	private Image kid_left;
	private Image kid_right;
	private Image egg;
	private Image ground;
	private String image_path = "images/";
	
	public HUD(Game game) {
		this.game = game;
		this.game.window.setContentPane(this);
		
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
	}
	
	public void paintComponent(Graphics g) {
		
		g.setColor(Color.white);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
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
}
