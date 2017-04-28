package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Garden {
	private int size_x;
	private int size_y;
	
	private Item map[][] = null;
	private int egg_map[][] = null;
	
	private ArrayList<Kid> list_kids;
	
	private Game game;

	private File garden_file = null;
	private File kid_file = null;

	public boolean loaded = true;
	public boolean file_loaded = true;
	
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
		
		try {			
			BufferedReader  buffer = new BufferedReader(new FileReader(file));			
			String line = "";
			
			while ((line = buffer.readLine()) != null) {				
				
				String[] data = line.split(" ");
				
				if(data[0].equals("J")) {
					if(data.length >= 3) {
						if(this.map == null) {
							try {
								this.size_x = Integer.valueOf(data[1]);	
								this.size_y = Integer.valueOf(data[2]);
		
								this.map = new Item[size_x][size_y];
								this.egg_map = new int[size_x][size_y];
								for(int i=0; i<this.size_x; i++) {
									for(int j=0; j<this.size_y; j++) {
										this.map[i][j] = Item.NO_ITEM;
										this.egg_map[i][j] = 0;
									}
								}
							} catch(NumberFormatException e) {
								this.loaded = false;
							}
						}
					} else {
						this.loaded = false;
					}
				}
				else if(data[0].equals("C")) {
					if(this.map != null) {
						if(data.length >= 3) {
							try {
								String[] pos = data[1].split("-");
								
								int px = Integer.valueOf(pos[0]);							
								int py = Integer.valueOf(pos[1]);
								int nb = Integer.valueOf(data[2]);
								
								if(px >= 0 && px < this.size_x && py >= 0 && py < this.size_y && nb >= 1) {
									this.map[px][py] = Item.EGG;
									this.egg_map[px][py] = nb;
								} else {
									this.loaded = false;
								}
							} catch(NumberFormatException e) {
								this.loaded = false;
							} catch(ArrayIndexOutOfBoundsException e) {
								this.loaded = false;
							}
							
							
						} else {
							this.loaded = false;
						}
					} else {
						this.loaded = false;
					}
				}
				else if(data[0].equals("R")) {
					if(this.map != null) {
						if(data.length >= 2) {
							try {
								String[] pos = data[1].split("-");							
								int px = Integer.valueOf(pos[0]);
								int py = Integer.valueOf(pos[1]);
								this.map[px][py] = Item.ROCK;
							} catch(NumberFormatException e) {
								this.loaded = false;
							} catch(ArrayIndexOutOfBoundsException e) {
								this.loaded = false;
							}
						} else {
							this.loaded = false;
						}
					} else {
						this.loaded = false;
					}
				}
			}
			
			if(this.size_x == 0 || this.size_y == 0) {
				this.loaded = false;
				JOptionPane.showMessageDialog(this.game.window, "This garden file can't be used.\nPlease checkout Help > Graden file to correct your garden file.", "Error in garden file", JOptionPane.ERROR_MESSAGE);
			}
			
			
			buffer.close();	
			
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this.game.window, "File does not exist.", "Load error", JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.game.hud_game.repaint();
	}
	
	public void save(File file_g, File file_k) {
		try {
			FileWriter fw = new FileWriter(file_g);
			FileWriter fwK = new FileWriter(file_k);
			
			Item map[][] = this.game.garden.getMap();
			int[][] egg_map = this.game.garden.getEggMap();
			int size_x = this.game.garden.getSizeX();
			int size_y = this.game.garden.getSizeY();
			
			fw.write("J " + size_x + " " + size_y + "\r\n");								
			
			for(int i=0; i<size_x; i++) {
				for(int j=0; j<size_y; j++) {
					if(map[i][j] == Item.ROCK) {
						fw.write("R " + i + "-" + j + "\r\n");
					} else if(map[i][j] == Item.EGG) {
						fw.write("C " + i + "-" + j + " " + egg_map[i][j] + "\r\n");
					}
				}
			}
			
			ArrayList<Kid> list_kids = this.game.garden.getKidsList(); 
			for(int i=0; i<list_kids.size(); i++ ) {
				
				int pos_x = list_kids.get(i).getPosX();
				int pos_y = list_kids.get(i).getPosY();
				String d = list_kids.get(i).getDirection();
				String name = list_kids.get(i).getName();
				
				if(name.equals("")) {
					name = "Jules";
				}
				
				String path_s = "";
				ArrayList<String> path_a = list_kids.get(i).getPath();
				
				if(path_a != null) {
					for(int j=0; j<path_a.size(); j++) {
						path_s = path_s + path_a.get(j);
					}
				}
			
				fwK.write("E " + pos_x + "-" + pos_y + " " + d + " " + path_s + " " + name + "\r\n");
			}

			fw.close();
			fwK.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public void openkid(File file) {
		
		this.kid_file = file;
		
		this.list_kids = new ArrayList<Kid>();		

		try {

			BufferedReader buffer;
			buffer = new BufferedReader(new FileReader(file));
					
			String line = "";
			
			while ((line = buffer.readLine()) != null) {				
				
				String[] data = line.split(" ");
			
				if(data[0].equals("E")) {
					if(this.map != null) {
						if(data.length >= 5) {							
							try {
								String[] pos = data[1].split("-");
								int px = Integer.valueOf(pos[0]);
								int py = Integer.valueOf(pos[1]);
								String d = data[2];
								
								if(px < this.size_x && py < this.size_y) {	
									if(!d.equals("E") && !d.equals("W") && !d.equals("S") && !d.equals("N")) {
										this.file_loaded = false;
									}
									
									ArrayList<String> path = new ArrayList<String>();
									String[] c = data[3].split("");
									
									for(int i=0; i<c.length; i++) {
										if(c[i].equals("A") || c[i].equals("D") || c[i].equals("G")) {
											path.add(c[i]);
										}
										else {
											this.file_loaded = false;
										}
									}
									
									String name = data[4];
									
									this.map[px][py] = Item.NO_ITEM;
														
									this.list_kids.add(new Kid(px, py, d, path, name));	
								}								
							} catch(NumberFormatException e) {
								this.file_loaded = false;
							} catch(ArrayIndexOutOfBoundsException e) {
								this.file_loaded = false;
							}
						} else {
							this.file_loaded = false;
						}
						
					} else {
						this.file_loaded = false;
					}
				}
			}
				buffer.close();
				this.file_loaded = true;
			
		} catch (FileNotFoundException e1) {
			this.file_loaded = false;
		}  catch (IOException e) {
			this.file_loaded = false;
		}
		
	}
	
	public Item[][] getMap() {
		return this.map;
	}
	
	public int[][] getEggMap() {
		return this.egg_map;
	}
	
	public void setMap(Item[][] map) {
		this.map = map;
	}
	
	public void setEggMap(int[][] egg_map) {
		this.egg_map = egg_map;
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
	
	public Kid getKitAt(int px, int py) {
		for(Kid k : this.list_kids) {
			if(k.getPosX() == px && k.getPosY() == py) {
				return k;
			}
		}
		return null;
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

	public File getKidFile() {
		return this.kid_file;
	}
	
	public void setFile(File file) {
		this.garden_file = file;
	}
	
	public boolean getLoad() {
		return this.loaded;
	}
	
	public Item getCase(int case_x, int case_y) {
		return this.map[case_x][case_y];
	}
}
