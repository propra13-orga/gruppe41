package dungeonCrawler;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.JTextPane;

/**
 * Set up the main control element.
 * 
 * You can choose her the buttons for the direction
 * 
 * @author Hucke
 *
 */
public class Settings{
	private JDialog dialog;
	private JButton[] button =new JButton[4];
	private JButton btn_defaults;
	private JButton btn_exit;
	private JTextField[] valueField = new JTextField[5]; 
	private Container c;
	private GridBagConstraints gbc;
	
	//Key Code for the Arrows
	private int i;
	private int number;
	public int[] direction = {37,38,39,40};
	private boolean[] started = new boolean[4];
	
	
	public void setDirection(int number, int direction){
		this.direction[number]=direction;
	}
	public int getDirection(int number){
		return this.direction[number];
	}
	
	public int[] getAllDirections(){
		return this.direction;
	}
	
	public void setAllDirections(int[] direction){
		this.direction = direction;
	}
	
	public void setNumber(String name){
		
		switch (name){
		case "LEFT": this.number = 0; break; 
		case "UP": 	 this.number = 1; break;
		case "RIGHT":this.number = 2; break;
		case "DOWN": this.number = 3; break;
		}
		
	}
		
	public int getNumber(){
		return this.number;
	}
	
	public void setDefault(){
		this.direction[0]=37;
		this.direction[1]=38;
		this.direction[2]=39;
		this.direction[3]=40;
	}
	
	public int getI(){
		return i;
	}
	
	public void setI(int i){
		this.i=i;
	}

	/**
	 * Add components to the {@link GridBagLayout}. The method simplified adding components to the pane.
	 *  	
	 * @param cont The {@link Container} to which the components are added 
	 * @param gbl The definition of the {@link GridBagLayout}
	 * @param c The adding {@link Component} 
	 * @param x The column of the grid as {@link Integer}
	 * @param y The row of the grid {@link Integer}
	 * @param width The columns  a component use in the grid as {@link Integer}
	 * @param height The rows a component use in the grid as {@link Integer}
	 * @param weightx The horizontal component weight as {@link Integer}
	 * @param weighty The vertical component weight as {@link Integer}
	 */
	static void addComponent( Container cont,GridBagLayout gbl,Component c,int x, int y,int width, int height,double weightx, double weighty ){
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); 
		gbc.fill = GridBagConstraints.BOTH;
		gbc.gridx = x; gbc.gridy = y;
		gbc.gridwidth = width; gbc.gridheight = height;
		gbc.weightx = weightx; gbc.weighty = weighty;
		gbl.setConstraints( c, gbc );
		cont.add( c );
	}
	

	public void startSettings(){
		gui();
		
	}
	
	/**
	 * Fill the items in the settings
	 * @param gridx column value {@link Integer}
	 * @param gridy row value as {@link Integer}
	 * @param value the value of the default key as {@link Integer}
	 * @param name name of the key as {@link String} 
	 * @param layout the definition of the {@link GridBagLayout}
	 * @param gbc the definition of the {@link GridBagConstraints}
	 * @param dialog the {@link Component} to add this
	 */
	public void content(int gridx, int gridy,int value, String name, GridBagLayout layout, GridBagConstraints gbc, JDialog dialog){
		setNumber(name);
		valueField[getNumber()] = new JTextField();
		valueField[getNumber()].setText(this.direction[getNumber()]+"");
		valueField[getNumber()].setBackground(dialog.getContentPane().getBackground());
		valueField[getNumber()].setEditable(false);
		addComponent(c, layout, valueField[getNumber()], 7, getNumber()+2, 1, 1, 0, 0 );
		addComponent(c, layout, new JTextField(name), 6, getNumber()+2, 1, 1, 0, 0);
		
		button[getNumber()] = new JButton(name);
		addComponent(c, layout, button[getNumber()], gridx, gridy, 1, 1, 0, 0);
		System.out.println(getNumber());
		
		btn_defaults=new JButton("Defaults");
		addComponent(c, layout, btn_defaults, 1, 6, 2, 1, 0, 0);
		
		setDirection(getNumber(), value);
		
		button[getNumber()].addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent ae) {
				
				for(int i=0;i<4;i++){
					if(ae.getSource()==button[i]){
						if(started[i]==false){
							setI(i);
							button[i].addKeyListener(new KeyListener() {
					
							@Override
							public void keyTyped(KeyEvent e) {
								
							}
					
							@Override
							public void keyReleased(KeyEvent e) {
							}
					
							@Override
							public void keyPressed(KeyEvent e) {
								started[getI()]=true;
								setDirection(getI(), e.getExtendedKeyCode());
								System.out.println(getDirection(getI()));
								valueField[getI()].setText(getDirection(getI())+"");
								
							}
						});
					}
					started[i]=false;
				}
				
			}
		}
	});
	
	
		

	btn_defaults.addActionListener(new ActionListener(){
				
		public void actionPerformed(ActionEvent arg0) {
			setDefault();
			for(int n=0;n<4;n++){
				valueField[n].setText(37+n+"");
			}
					
		}
	});
				

		

		
	}
	
	/**
	 * create the Settings dialog
	 */
	public void gui(){

		
		dialog = new JDialog(dialog, "Settings");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		
		GridBagLayout layout = new GridBagLayout();
		dialog.setLayout(layout);
		c = dialog.getContentPane();
		
		addComponent(c, layout, new JTextField("Value"), 7, 1, 1, 1, 0, 0);
		addComponent(c, layout, new JTextField("Key"), 6, 1, 1, 1, 0, 0);
		

		JTextPane message = new JTextPane();
		message.setText("Settings Test");
		message.setBackground(dialog.getContentPane().getBackground());
		message.setEditable(false);

		
		addComponent(c, layout, message, 3, 0, 1, 1, 0, 0);
		
		content( 3, 2,getDirection(1), "UP"   ,layout, gbc, dialog);
		content( 4, 3,getDirection(0), "RIGHT",layout, gbc, dialog);
		content( 3, 4,getDirection(2), "DOWN" ,layout, gbc, dialog);
		content( 2, 3, getDirection(3), "LEFT" ,layout, gbc, dialog);
		
		btn_exit=new JButton("OK/Exit");
		addComponent(c, layout, btn_exit, 5, 6, 2, 1, 1.0, 0);
		
		btn_exit.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				dialog.dispose();
				
			}
			
		});
			
		//Automatic determine the size of the Dialog
		dialog.pack();
		dialog.setVisible(true);
		
	}

	
	
	
}