package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.util.ArrayList;

public class Kid {
	private String name;
	private ArrayList<String> path;
	private int nb_eggs;
	private int pos_x;
	private int pos_y;
	private String direction;
	
	public Kid(int pos_x, int pos_y, String direction, ArrayList<String> path, String name) {
		this.name = name;
		this.path = path;
		this.pos_x = pos_x;
		this.pos_y = pos_y;
		this.direction = direction;
	}
	
	public int getPosX() {
		return this.pos_x;
	}
	
	public int getPosY() {
		return this.pos_y;
	}
	
	public void setPosX(int pos_x) {
		this.pos_x = pos_x;
	}
	
	public void setPosY(int pos_y) {
		this.pos_y = pos_y;
	}
	
	public String getDirection() {
		return this.direction;
	}
	
	public void setDirection(String d) {
		this.direction = d;
	}
	
	public ArrayList<String> getPath() {
		return this.path;
	}
	
	public void setPath(ArrayList<String> p) {
		this.path = p;
	}
	
	public void addEgg() {
		this.nb_eggs++;
	}
}
