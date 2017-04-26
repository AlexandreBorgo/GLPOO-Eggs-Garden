package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.peer.WindowPeer;
import java.io.File;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener {
	
	private int width = 640;
	private int height = 580;
	private String title = "Egg Garden";
	
	private JMenuBar menu = new JMenuBar();
	private JMenu file = new JMenu("File");
	private JMenu action = new JMenu("Action");
	
	private JMenuItem open = new JMenuItem("Open...");
	private JMenuItem garden_editor = new JMenuItem("New garden");
	private JMenuItem play = new JMenuItem("Play");
	private JMenuItem pause = new JMenuItem("Pause");
	private JMenuItem restart = new JMenuItem("Restart");
	private JMenuItem save = new JMenuItem("Save as..."); 
		
	private Game game;
	
	public Window(Game game) {
		this.game = game;
		
		this.setTitle(title);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
        this.setResizable(false);
        
        
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);		
		this.addWindowListener(new ExitListener());
		
		this.file.add(open);
		this.file.addSeparator();
		this.file.add(garden_editor);
		this.file.add(save);
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
		save.addActionListener(this);

		this.setContentPane(this.game.hud_game);
		
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == open) {
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fc.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			    this.game.setState(State.GAME_STATE);
			    this.game.garden = new Garden(this.game, file);	
				this.game.hud_game.setPreferredSize(this.game.garden.getSizeX(), this.game.garden.getSizeY());
		        this.game.window.setContentPane(this.game.hud_game);
		        this.game.window.pack();
		        this.game.hud_game.repaint();
			}
		}
		else if(e.getSource() == this.garden_editor) {
			JTextField size_x_field = new JTextField(5);
			JTextField size_y_field = new JTextField(5);
			
			 JPanel panel = new JPanel();
			 panel.add(new JLabel("Nombre de cases horizontales :"));
			 panel.add(size_x_field);
			 panel.add(Box.createHorizontalStrut(15));
			 panel.add(new JLabel("Nombre de cases verticales :"));
			 panel.add(size_y_field);
			 
			 boolean keep_asking = true;
			 int size_x = 0;
			 int size_y = 0;
			 
			 while(keep_asking) {
				 int result = JOptionPane.showConfirmDialog(null, panel, "Size of the new garden ?", JOptionPane.OK_CANCEL_OPTION);
				 
				 if (result == JOptionPane.OK_OPTION) {
					 try {
						 size_x = Integer.parseInt(size_x_field.getText()); 
						 size_y = Integer.parseInt(size_y_field.getText());
						 
						 if(size_x > 0 && size_y > 0) {
							 keep_asking = false;
						 }
					 }
					 catch(NumberFormatException exception) {					 
						 continue;
					 }
			     }
				 else {
					 return;
				 }
			 }
			 
			 this.game.setState(State.EDITOR_STATE);
			 this.game.garden = new Garden(this.game, size_x, size_y);
			 this.game.hud_game.setPreferredSize(1+size_x, Integer.max(size_y, 7));
			 this.pack();
			 this.game.hud_game.repaint();
		     this.game.editor();			 
		}
		else if(e.getSource() == this.play) {
			this.game.play();
		}
		else if(e.getSource() == this.pause) {
			this.game.pause();
		}
		else if(e.getSource() == this.restart) {
			if(this.game.garden != null) {
				this.game.garden = new Garden(this.game, this.game.garden.getFile());
				this.game.setAnim(false);
			}
			else {
				JOptionPane.showMessageDialog(this.game.window, "No garden to restart.", "Restart error", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource() == this.save) {
			if(this.game.getState() == State.EDITOR_STATE) {
				if(this.game.garden != null) {
					boolean saved = false;
					
					if(saved) {
						JOptionPane.showMessageDialog(this.game.window, "Garden saved.", "Save garden", JOptionPane.INFORMATION_MESSAGE);						
					} else {
						JOptionPane.showMessageDialog(this.game.window, "Failed to save the garden.", "Save garden", JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this.game.window, "Empty garden.", "Save garden", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this.game.window, "Not in editor mode.", "Save garden", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	class ExitListener implements WindowListener {

		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowClosing(WindowEvent e) {
			game.setGamePlay(false);
			game.setEditorPlay(false);
			System.exit(0);
		}

		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
