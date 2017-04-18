package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener, MouseListener {
	
	private int width = 640;
	private int height = 580;
	private String title = "Egg Garden";
	
	private JMenuBar menu = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu action = new JMenu("Action");
	
	private JMenuItem open = new JMenuItem("Open a garden");
	private JMenuItem garden_editor = new JMenuItem("Garden editor");
	private JMenuItem play = new JMenuItem("Play");
	private JMenuItem pause = new JMenuItem("Pause");
	private JMenuItem restart = new JMenuItem("Restart");
	
	private Game game;
	
	public Window(Game game) {
		this.game = game;
		
		this.setTitle(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		
		this.file.add(open);
		this.file.add(garden_editor);
		this.action.add(play);
		this.action.add(pause);
		this.action.add(restart);
		this.menu.add(file);
		this.menu.add(action);
		this.setJMenuBar(menu);
		
		open.addActionListener(this);
		garden_editor.addActionListener(this);
		play.addActionListener(this);
		pause.addActionListener(this);
		restart.addActionListener(this);
		
		this.addMouseListener(this);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == open) {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fc.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			    this.game.garden = new Garden(this.game, file);
			}
		}
		else if(e.getSource() == this.garden_editor) {
			JOptionPane.showMessageDialog(this.game.window, "Not implemented yet.", "Garden editor", JOptionPane.WARNING_MESSAGE);
		}
		else if(e.getSource() == this.play) {
			this.game.play();
		}
		else if(e.getSource() == this.pause) {
			this.game.pause();
		}
		else if(e.getSource() == this.restart) {
			if(this.game.garden != null)
				this.game.garden = new Garden(this.game, this.game.garden.getFile());
			else {
				JOptionPane.showMessageDialog(this.game.window, "No garden to restart.", "Restart error", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
			
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
