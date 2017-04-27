package GLPOO_ESIEA_1617_Groupe_Borgo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Box;
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
	private JMenu help = new JMenu("Help");

	private JMenuItem open = new JMenuItem("Open...");
	private JMenuItem load = new JMenuItem("Load from editor");
	private JMenuItem garden_editor = new JMenuItem("New garden");
	private JMenuItem open_edit = new JMenuItem("Edit a garden");
	private JMenuItem play = new JMenuItem("Play");
	private JMenuItem pause = new JMenuItem("Pause");
	private JMenuItem restart = new JMenuItem("Restart");
	private JMenuItem saveas = new JMenuItem("Save as..."); 
	private JMenuItem save = new JMenuItem("Save"); 
	private JMenuItem help_file = new JMenuItem("Help about File menu"); 
	private JMenuItem help_action = new JMenuItem("Help about Action menu"); 
	private JMenuItem help_editor = new JMenuItem("Help about Editor"); 
	private JMenuItem help_garden = new JMenuItem("Help about Garden file"); 
		
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
		this.file.add(load);
		this.file.addSeparator();
		this.file.add(garden_editor);
		this.file.add(open_edit);
		this.file.add(save);
		this.file.add(saveas);
		this.action.add(play);
		this.action.add(pause);
		this.action.add(restart);
		this.help.add(help_file);
		this.help.add(help_action);
		this.help.add(help_editor);
		this.help.add(help_garden);
		this.menu.add(file);
		this.menu.add(action);
		this.menu.add(help);
		this.setJMenuBar(menu);

		open.addActionListener(this);
		load.addActionListener(this);
		open_edit.addActionListener(this);
		garden_editor.addActionListener(this);
		play.addActionListener(this);
		pause.addActionListener(this);
		restart.addActionListener(this);
		saveas.addActionListener(this);
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
				
				if(file.exists()) {
				    this.game.setState(State.GAME_STATE);
				    this.game.garden = new Garden(this.game, file);	
				    
				    if(!this.game.garden.getLoad()) {
				    	this.game.garden = null;
				    }
				    				    
				    if(this.game.garden != null) {
						this.game.hud_game.setPreferredSize(this.game.garden.getSizeX(), this.game.garden.getSizeY());
				        this.game.window.pack();
				        this.game.hud_game.repaint();
				    }
				}
				else {
					JOptionPane.showMessageDialog(this.game.window, "File does not exist.", "Open error", JOptionPane.WARNING_MESSAGE);
				}
			}
		}
		else if(e.getSource() == load) {
			if(this.game.getState() == State.EDITOR_STATE) {
				if(this.game.garden != null) {
					if(this.game.garden.getFile() != null) {
						Object[] options = {"Continue", "Cancel"};
						int n = JOptionPane.showOptionDialog(this, "The garden must be saved before loaded. Do you want to quit ?", "Quit editor mode ?", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
						
						if(n == 0) {
								
								if(this.game.garden.getFile().exists()) {
								    this.game.setState(State.GAME_STATE);
								    this.game.garden = new Garden(this.game, this.game.garden.getFile());	
									this.game.hud_game.setPreferredSize(this.game.garden.getSizeX(), this.game.garden.getSizeY());
							        this.game.window.pack();
							        this.game.hud_game.repaint();
								}
								else {
									JOptionPane.showMessageDialog(this.game.window, "File does not exist.", "Open error", JOptionPane.WARNING_MESSAGE);
								}
							
						}
					}
					else {
						JOptionPane.showMessageDialog(this.game.window, "The garden must be saved before loaded.", "Load error", JOptionPane.WARNING_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(this.game.window, "No garden.", "Load error", JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(this.game.window, "Not in editor mode.", "Load error", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource() == this.garden_editor) {
			JTextField size_x_field = new JTextField(5);
			JTextField size_y_field = new JTextField(5);
			
			 JPanel panel = new JPanel();
			 panel.add(new JLabel("Horizontal size :"));
			 panel.add(size_x_field);
			 panel.add(Box.createHorizontalStrut(15));
			 panel.add(new JLabel("Vertical size :"));
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
		else if(e.getSource() == this.open_edit) {
			
			JFileChooser fc = new JFileChooser();
			fc.setCurrentDirectory(new File(System.getProperty("user.home")));
			int result = fc.showOpenDialog(this);
			
			if (result == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				
				if(file.exists()) {					
					this.game.setState(State.EDITOR_STATE);
					this.game.garden = new Garden(this.game, file);	
					this.game.hud_game.setPreferredSize(1+this.game.garden.getSizeX(), Integer.max(this.game.garden.getSizeY(), 7));
					this.pack();
					this.game.hud_game.repaint();
				    this.game.editor();				    
				}
				else {
					JOptionPane.showMessageDialog(this.game.window, "File does not exist.", "Load error", JOptionPane.WARNING_MESSAGE);
				}
			}			 
		}
		else if(e.getSource() == this.play) {
			this.game.play();
		}
		else if(e.getSource() == this.pause) {
			this.game.pause();
		}else if(e.getSource() == this.save) {
			if(this.game.getState() == State.EDITOR_STATE) {
				if(this.game.garden != null) {
					if(this.game.garden.getFile() != null) {
						try {
							FileWriter fw = new FileWriter(this.game.garden.getFile());
							
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
								
								String path_s = "";
								ArrayList<String> path_a = list_kids.get(i).getPath();
								
								if(path_a != null) {
									for(int j=0; j<path_a.size(); j++) {
										path_s = path_s + path_a.get(j);
									}
								}
							
								fw.write("E " + pos_x + "-" + pos_y + " " + d + " " + path_s + " " + name + "\r\n");
							}
							
							JOptionPane.showMessageDialog(this, "Garden saved.", "Save garden", JOptionPane.INFORMATION_MESSAGE);
							
							fw.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(this, "No file selected.", "Save garden", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(this, "Empty garden.", "Save garden", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "No garden to save.", "Save garden", JOptionPane.ERROR_MESSAGE);
			}
		}
		else if(e.getSource() == this.restart) {
			if(this.game.garden != null) {
				if(this.game.getState() == State.GAME_STATE) {
					this.game.garden = new Garden(this.game, this.game.garden.getFile());
					this.game.setAnim(false);
				}
				else {
					JOptionPane.showMessageDialog(this, "Disable in editor mode.", "Restart error", JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(this, "No garden to restart.", "Restart error", JOptionPane.WARNING_MESSAGE);
			}
		}
		else if(e.getSource() == this.saveas) {
			if(this.game.getState() == State.EDITOR_STATE) {
				if(this.game.garden != null) {
					
					JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(new File(System.getProperty("user.home")));
					int result = fc.showSaveDialog(this);
					
					if (result == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						
						boolean keep = true;
						
						if(!file.exists()) {
							try {
								file.createNewFile();
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(this, "Impossible to create new file.", "Save garden", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							Object[] options = {"Yes", "No"};
							int n = JOptionPane.showOptionDialog(this, "Do you want to replace it ?", "File already exists", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
							
							if(n != 0) {
								keep = false;
							}
						}					
							
							if(keep) {								
								try {
									FileWriter fw = new FileWriter(file);
									
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
										
										String path_s = "";
										ArrayList<String> path_a = list_kids.get(i).getPath();
										
										if(path_a != null) {
											for(int j=0; j<path_a.size(); j++) {
												path_s = path_s + path_a.get(j);
											}
										}
									
										fw.write("E " + pos_x + "-" + pos_y + " " + d + " " + path_s + " " + name + "\r\n");
									}
									
									JOptionPane.showMessageDialog(this, "Garden saved.", "Save garden", JOptionPane.INFORMATION_MESSAGE);		
									this.game.garden.setFile(file);
									
									fw.close();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
							}
						
					}
					else {
						JOptionPane.showMessageDialog(this, "Unknown error.", "Save error", JOptionPane.WARNING_MESSAGE);
					}	
				} else {
					JOptionPane.showMessageDialog(this, "Empty garden.", "Save garden", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Not in editor mode.", "Save garden", JOptionPane.ERROR_MESSAGE);
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
