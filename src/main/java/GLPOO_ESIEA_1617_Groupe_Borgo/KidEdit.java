package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class KidEdit extends JPanel {
	
	private Kid kid = null;
	public JTextField name;
	public JTextField path;
	public JTextField direction;
	
		KidEdit(Kid kid) {
		this.kid = kid;

		this.name = new JTextField(this.kid.getName());
		
		String path_s = "";
		ArrayList<String> path_a = this.kid.getPath();
		
		if(path_a != null) {
			for(int j=0; j<path_a.size(); j++) {
				path_s = path_s + path_a.get(j);
			}
		}
		
		this.path = new JTextField(path_s);
		this.direction = new JTextField(this.kid.getDirection());	
		
		this.setLayout(new GridLayout(3,0));
		this.add(new JLabel("Name :"));
		this.add(name);
		this.add(new JLabel("Path :"));
		this.add(path);
		this.add(new JLabel("Direction :"));
		this.add(direction);
	
	}
}
