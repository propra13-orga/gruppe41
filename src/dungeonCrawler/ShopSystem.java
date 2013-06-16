package dungeonCrawler;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ShopSystem{
	private JDialog dialog;
	private JButton button;
	private Container cp;
	//private Icon picture; 
	public JTextField number;
	public int times;
	
	
	private int item(int gridy, String item,int price, String picturePath,GridBagLayout layout, GridBagConstraints gbc, JDialog dialog){
		
		//Item Picture
		
		gbc.gridheight=1;
		gbc.gridx = 0;  
		gbc.gridy = gridy;
		Icon pictureInGame = new ImageIcon(picturePath); 
		JLabel pic = new JLabel(pictureInGame);
		//pic.setSize(5, 5);
		layout.setConstraints(pic, gbc);
		dialog.add(pic);
				
		//Item description
		gbc.gridheight=1;
		gbc.gridx = 1;  
		gbc.gridy = gridy;
		JTextPane itemName = new JTextPane();
		itemName.setText(item);
		itemName.setBackground(dialog.getContentPane().getBackground());
		itemName.setEditable(false);
		layout.setConstraints(itemName, gbc);
		dialog.add(itemName);
		
		//Price
		gbc.gridheight=1;
		gbc.gridwidth=2;
		gbc.gridx = 2;
		gbc.gridy = gridy;
		JTextPane priceInGame = new JTextPane();
		priceInGame.setText(price + " Geld");
		priceInGame.setBackground(dialog.getContentPane().getBackground());
		priceInGame.setEditable(false);
		layout.setConstraints(priceInGame, gbc);
		dialog.add(priceInGame);
		
		//number
		gbc.gridheight=1;
		gbc.gridwidth=5;
		gbc.gridx = 4;
		gbc.gridy = gridy;
		number = new JTextField();
		number.setText("1");
		//number.setBounds(0, 0, 128, 64);
		number.setBackground(Color.white);
		number.setEditable(true);
		layout.setConstraints(number, gbc);
		dialog.add(number);
		number.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				times = Integer.parseInt(number.getText());
				System.out.println("inner" + times);
			}
			
		});
		System.out.println("out" + times);
		//Price
		gbc.gridheight=1;
		gbc.gridx = 12;
		gbc.gridy = gridy;
		JTextPane priceTimes;
		int numberPrice= price*times;
		priceTimes = new JTextPane();
		priceTimes.setSize(128, 64);
		priceTimes.setText("Gesammt Preis: " + numberPrice);
		priceInGame.setBackground(dialog.getContentPane().getBackground());
		layout.setConstraints(priceTimes, gbc);
		
		System.out.println(times);
		System.out.println(numberPrice);
		dialog.add(priceTimes);
		
		
		//buy
		gbc.gridheight=1;
		gbc.gridwidth=2;
		gbc.gridx = 20;
		gbc.gridy =gridy;
		button = new JButton("Kaufen?");
		layout.setConstraints(button, gbc);
		dialog.add(button);
		return times;
		
	}
	
	public void shopSystem(){
		startShop();
	}
	
	private void startShop(){
		dialog = new JDialog(dialog, "Settings");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		
		cp = dialog.getContentPane();
		//Set layout to Grid
		GridBagLayout layout = new GridBagLayout();
		
		dialog.setLayout(layout);
		GridBagConstraints gbc=new GridBagConstraints();
		//gbc.insets = new Insets(7, 7, 7, 7); 
		//Horizontal filling the Grid
		//gbc.fill=GridBagConstraints.VERTICAL; 
		
		
		//item(int gridy, String item,int price, String picturePath,GridBagLayout layout, GridBagConstraints gbc, JDialog dialog)
		item(0, "Mate",2, "mate_h64.jpg", layout, gbc, dialog);
		item(2, "Mate",2, "mate_h64.jpg", layout, gbc, dialog);
		
		dialog.pack();
		dialog.setVisible(true);
		dialog.repaint();
	}

}
