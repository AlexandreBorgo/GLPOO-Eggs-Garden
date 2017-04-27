package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class EggEdit extends JPanel {
	
	public JTextField amount;
	
	EggEdit(int nb) {

		this.amount = new JTextField("" + nb);		
		
		this.setLayout(new GridLayout(3,0));
		this.add(new JLabel("Amount of egg :"));
		this.add(amount);	
	}
}