package dungeonCrawler;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextPane;





public class Settings {
	private JDialog dialog;
	private JButton button;
	private JButton btn_defaults;
	private JButton btn_exit;
	
	//Key Code for the Arrows
	private int up=38, right=39, down=40, left=37;
	
	//return Value Control Buttons
	public int direction;
	
	public Container contentpane;
	
	
	//paint method
	public void paint(Graphics g){
		
	}
	
	//Constructor
	public void startSettings(){
		gui();
		
	}
	
	//Steuerung Buttons 
	public int button_direction( int gridx, int gridy,int value, String name, GridBagLayout layout, GridBagConstraints gbc, JDialog dialog){
		
		gbc.gridx=gridx;
		gbc.gridy=gridy;
		button=new JButton(name);
		direction=value;
		button.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				direction=e.getExtendedKeyCode();
				System.out.println("In der Schleife Released"+direction);
				System.out.println("Alle" + up + right + down + left);
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				direction=e.getExtendedKeyCode();
				System.out.println("In der Schleife Pressed"+direction);
			}
		});
		layout.setConstraints(button, gbc);
		dialog.add(button);
		System.out.println("Ausserhalb der Schleife"+direction);
		return direction;
		
	}
	
	public void key_column(int gridx, int gridy, String name,GridBagLayout layout, GridBagConstraints gbc, JDialog dialog){
		gbc.gridx = gridx;  
		gbc.gridy = gridy;
		JTextPane key = new JTextPane();
		key.setText("Key");
		key.setBackground(dialog.getContentPane().getBackground());
		key.setEditable(false);
		layout.setConstraints(key, gbc);
		dialog.add(key);
	}
	
	public void value_column(int gridx, int gridy,int key, GridBagLayout layout, GridBagConstraints gbc, JDialog dialog){
		gbc.gridx=gridx;
		gbc.gridy=gridy;
		JTextPane value = new JTextPane();
		if(key>0){
		    value.setText(key +"");}
		else if(key==0){
			value.setText("Value");
		}
		//Text background to window background
		value.setBackground(dialog.getContentPane().getBackground());
		value.setEditable(false);
		layout.setConstraints(value, gbc);
		dialog.add(value);
	}
	
	
	
	public void gui(){
		dialog = new JDialog(dialog, "Settings");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		
		contentpane = dialog.getContentPane();
		//Set layout to Grid
		GridBagLayout layout = new GridBagLayout();
		
		dialog.setLayout(layout);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); 
		//Horizontal filling the Grid
		gbc.fill=GridBagConstraints.HORIZONTAL; 
		
		//Buttons direction
		up    = button_direction( 3, 2,38, "UP"   ,layout, gbc, dialog);
		right = button_direction( 4, 3,39, "RIGHT",layout, gbc, dialog);
		down  = button_direction( 3, 4,40, "DOWN" ,layout, gbc, dialog);
		left  = button_direction( 2, 3, 37, "LEFT" ,layout, gbc, dialog);
		
		//Defaults Button
		gbc.gridx=1;
		gbc.gridy=6;
		gbc.gridwidth=2;
		gbc.weightx = 1.0;
		btn_defaults=new JButton("Defaults");
		layout.setConstraints(btn_defaults, gbc);
		dialog.add(btn_defaults);
		btn_defaults.addActionListener(new ActionListener(){
				
			public void actionPerformed(ActionEvent arg0) {
				up=38;
				right=39;
				down=40;
				left=37;
					
				}
					
			});
				
		// End Defaults Button
		
		//Exit Button
		gbc.gridx=5; //Don't know why but only with this selection the value are displayed
		gbc.gridy=6;
		gbc.gridwidth=2;
		gbc.weightx = 1.0;
		btn_exit=new JButton("OK/Exit");
		layout.setConstraints(btn_exit, gbc);
		dialog.add(btn_exit);
		btn_exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
				
			}
			
		});
	//End Exit Button
		
		//up=38; right=39; down=40; left=37;
		//key and value column
		key_column(6, 1, "Key",  layout, gbc, dialog);
		value_column(7, 1, 0,    layout, gbc, dialog);
		key_column(6, 2, "UP",   layout, gbc, dialog);
		value_column(7, 2, up,   layout, gbc, dialog);
		key_column(6, 3, "RIGHT",layout, gbc, dialog);
		value_column(7, 3, right,layout, gbc, dialog);
		key_column(6, 4, "DOWN", layout, gbc, dialog);
		value_column(7, 4, down, layout, gbc, dialog);
		key_column(6, 5, "LEFT", layout, gbc, dialog);
		value_column(7, 5, left, layout, gbc, dialog);
		
		
		
		
		
		
		
		gbc.gridx = 3;  
		gbc.gridy = 0;
		JTextPane message = new JTextPane();
		message.setText("Settings Test");
		message.setBackground(dialog.getContentPane().getBackground());
		message.setEditable(false);
		layout.setConstraints(message, gbc);
		dialog.add(message);
		
		
		
	
		
		
		
			
		
		
		
		contentpane = dialog.getContentPane();

		
		//Automatic determine the size of the Dialog
		
		dialog.pack();
		contentpane.repaint();
		dialog.setVisible(true);
		
	}
	
	
}

