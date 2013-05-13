package dungeonCrawler;

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





public class Settings extends JDialog {
	private JButton btn_up;
	private JButton btn_right;
	private JButton btn_down;
	private JButton btn_left;
	private JButton btn_defaults;
	private JButton btn_exit;
	//Key Code for the Arrows
	private int up=38, right=39, down=40, left=37;
	
	public Settings(){
		setTitle("Settings");
		//Set layout to Grid
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc=new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); 
		//Horizontal filling the Grid
		gbc.fill=GridBagConstraints.HORIZONTAL; 
		//setResizable(false);
		
		
		gbc.gridx = 3;  
		gbc.gridy = 0;
		JTextPane message = new JTextPane();
		message.setText("Settings Test");
		message.setBackground(getContentPane().getBackground());
		message.setEditable(false);
		layout.setConstraints(message, gbc);
		add(message);
		
		//Up Button
		gbc.gridx=3;
		gbc.gridy=2;
		btn_up=new JButton("UP");
		btn_up.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				up=e.getExtendedKeyCode();
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
		layout.setConstraints(btn_up, gbc);
		add(btn_up);
		
		//End Up Button

		//right Button
		gbc.gridx=4;
		gbc.gridy=3;		
		btn_right=new JButton("RIGHT");
		btn_right.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				right=e.getExtendedKeyCode();			
			}
			
			@Override
			public void keyPressed(KeyEvent e) {				
			}
		});
		
		layout.setConstraints(btn_right, gbc);
		add(btn_right);
		//End right Button
		
		//down Button
		gbc.gridx=3;
		gbc.gridy=4;
		btn_down=new JButton("DOWN");
		
		btn_down.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				down=e.getExtendedKeyCode();			
			}
			
			@Override
			public void keyPressed(KeyEvent e) {				
			}
		});
		layout.setConstraints(btn_down, gbc);
		add(btn_down);
		//End down Button
		
		//Left Button
		gbc.gridx=2;
		gbc.gridy=3;
		btn_left=new JButton("LEFT");		
		btn_left.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				left=e.getExtendedKeyCode();			
			}
			
			@Override
			public void keyPressed(KeyEvent e) {				
			}
		});
		layout.setConstraints(btn_left, gbc);
		add(btn_left);
		//End left Button
		
		//Defaults Button
		gbc.gridx=1;
		gbc.gridy=6;
		gbc.gridwidth=2;
		gbc.weightx = 1.0;
		btn_defaults=new JButton("Defaults");
		layout.setConstraints(btn_defaults, gbc);
		add(btn_defaults);
		btn_defaults.addActionListener(new ActionListener(){
		
			public void actionPerformed(ActionEvent arg0) {
				up=38;
				right=39;
				down=40;
				left=37;
				//Test Output
				//System.out.println(up + right + down + left);
				
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
		add(btn_exit);
		btn_exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				dispose();
				
			}
			
		});
		
		//End Exit Button
		
		//Key Column
		gbc.gridx = 6;  
		gbc.gridy = 1;
		JTextPane key = new JTextPane();
		key.setText("Key");
		key.setBackground(getContentPane().getBackground());
		key.setEditable(false);
		layout.setConstraints(key, gbc);
		add(key);
		
		gbc.gridx = 6;  
		gbc.gridy = 2;
		JTextPane key_up = new JTextPane();
		key_up.setText("UP");
		key_up.setBackground(getContentPane().getBackground());
		key_up.setEditable(false);
		layout.setConstraints(key_up, gbc);
		add(key_up);

		
		gbc.gridx = 6;  
		gbc.gridy = 3;
		JTextPane key_right = new JTextPane();
		key_right.setText("RIGHT");
		key_right.setBackground(getContentPane().getBackground());
		key_right.setEditable(false);
		layout.setConstraints(key_right, gbc);
		add(key_right);
		

		
		gbc.gridx = 6;  
		gbc.gridy = 4;
		JTextPane key_down = new JTextPane();
		key_down.setText("DOWN");
		key_down.setBackground(getContentPane().getBackground());
		key_down.setEditable(false);
		layout.setConstraints(key_down, gbc);
		add(key_down);
				
		gbc.gridx = 6;  
		gbc.gridy = 5;
		JTextPane key_left = new JTextPane();
		key_left.setText("LEFT");
		key_left.setBackground(getContentPane().getBackground());
		key_left.setEditable(false);
		layout.setConstraints(key_left, gbc);
		add(key_left);
		
		
		
			
		
		
		//Value Column
		gbc.gridx=7;
		gbc.gridy=1;
		JTextPane value = new JTextPane();
		value.setText("Value");
		//Text background to window background
		value.setBackground(getContentPane().getBackground());
		value.setEditable(false);
		layout.setConstraints(value, gbc);
		add(value);
	
		
		
		gbc.gridx=7;
		gbc.gridy=2;
		JTextPane value_up = new JTextPane();
		value_up.setText(up +"");
		//Text background to window background
		value_up.setBackground(getContentPane().getBackground());
		value_up.setEditable(false);
		layout.setConstraints(value_up, gbc);
		add(value_up);
		
		gbc.gridx=7;
		gbc.gridy=3;
		JTextPane value_right = new JTextPane();
		value_right.setText(Integer.toString(right));
		//Text background to window background
		value_right.setBackground(getContentPane().getBackground());
		value_right.setEditable(false);
		layout.setConstraints(value_right, gbc);
		add(value_right);
		
		gbc.gridx=7;
		gbc.gridy=4;
		JTextPane value_down = new JTextPane();
		value_down.setText(Integer.toString(down));
		//Text background to window background
		value_down.setBackground(getContentPane().getBackground());
		value_down.setEditable(false);
		layout.setConstraints(value_down, gbc);
		add(value_down);
		
		
		gbc.gridx=7;
		gbc.gridy=5;
		JTextPane value_left = new JTextPane();
		value_left.setText(Integer.toString(left));
		//Text background to window background
		value_left.setBackground(getContentPane().getBackground());
		value_left.setEditable(false);
		layout.setConstraints(value_left, gbc);
		add(value_left);
		

		
		//Automatic determine the size of the Dialog
		
		pack();		
		
	}
	
	
}

