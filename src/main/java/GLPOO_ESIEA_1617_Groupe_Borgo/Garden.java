package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Garden {
	private int size_x;
	private int size_y;
	
	private int nb_eggs;
	private int nb_kids;
	
	private Item map[][] = null;
	private int egg_map[][] = null;
	
	private ArrayList<Kid> list_kids;
	
	private Game game;
	
	private File garden_file = null;
	
	public Garden(Game game, int size_x, int size_y) {
		this.game = game;
		
		this.size_x = size_x;
		this.size_y = size_y;
		
		this.map = new Item[size_x][size_y];
		this.egg_map = new int[size_x][size_y];
		for(int i=0; i<this.size_x; i++) {
			for(int j=0; j<this.size_y; j++) {
				this.map[i][j] = Item.NO_ITEM;
				this.egg_map[i][j] = 0;
			}
		}
		
		this.list_kids = new ArrayList<Kid>();       
	}
	
	public Garden(Game game, File file) {
		this.game = game;		
		this.garden_file = file;
		
		this.size_x = 0;
		this.size_y = 0;
		
		this.list_kids = new ArrayList<Kid>();
		
		try {
			BufferedReader  buffer = new BufferedReader(new FileReader(file));			
			String line = "";
			
			while ((line = buffer.readLine()) != null) {				
				if(line.charAt(0) == 'J') {
					this.size_x = Character.getNumericValue(line.charAt(2) + line.charAt(3));					
					this.size_y = Character.getNumericValue(line.charAt(5) + line.charAt(6));

					this.map = new Item[size_x][size_y];
					this.egg_map = new int[size_x][size_y];
					for(int i=0; i<this.size_x; i++) {
						for(int j=0; j<this.size_y; j++) {
							this.map[i][j] = Item.NO_ITEM;
							this.egg_map[i][j] = 0;
						}
					}
				}
				else if(line.charAt(0) == 'C') {
					if(this.map != null) {
						int px = Character.getNumericValue(line.charAt(2));
						int py = Character.getNumericValue(line.charAt(4));
						this.map[px-1][py-1] = Item.EGG;
						this.egg_map[px-1][py-1]++;
					}
				}
				else if(line.charAt(0) == 'R') {
					if(this.map != null) {
						int px = Character.getNumericValue(line.charAt(2));
						int py = Character.getNumericValue(line.charAt(4));
						this.map[px-1][py-1] = Item.ROCK;
					}
				}
				else if(line.charAt(0) == 'E') {
					if(this.map != null) {
						int px = Character.getNumericValue(line.charAt(2));
						int py = Character.getNumericValue(line.charAt(4));
						String d = Character.toString(line.charAt(6));
						
						int i = 8;
						ArrayList<String> path = new ArrayList<String>();
						String c = "";
						while(!(c = Character.toString((line.charAt(i)))).equals(" ")) {
							path.add(c);
							i++;
						}
						
						i++;
						String name = "";
						while(i < line.length() && !(c = Character.toString((line.charAt(i)))).equals(" ")) {
							name += c;
							i++;
						}
						
						this.list_kids.add(new Kid(px, py, d, path, name));
					}
				}
			}
			
			buffer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.game.hud_game.repaint();
	}
	
	public Item[][] getMap() {
		return this.map;
	}
	
	public void setMap(Item[][] map) {
		this.map = map;
	}
	
	public int getSizeX() {
		return this.size_x;
	}
	
	public int getSizeY() {
		return this.size_y;
	}
	
	public ArrayList<Kid> getKidsList() {
		return this.list_kids;
	}
	
	public Item getItemAt(int px, int py) {		
		return this.map[px][py];
	}
	
	public void takeEgg(int px, int py) {
		this.egg_map[px][py]--;
		
		if(this.egg_map[px][py] <= 0) {
			this.map[px][py] = Item.NO_ITEM;
		}
	}
	
	public File getFile() {
		return this.garden_file;
	}
}
