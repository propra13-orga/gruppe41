package dungeonCrawler;

//import java.awt.Color;
//import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

public class ShopSystem {
	private JDialog dialog;
	private JButton button;
//	private Container cp;
	 
	public JTextField number;
	public int times;
	private int vermoegen;
	private int price[];
	public int item_number;
	
	
	public int getprice(int item_number){
		return this.price[item_number];
	}
	
	public int getvermoegen(){
		return this.vermoegen;
	}
	
	public void setvermoegen(int vermoegen){
		this.vermoegen=vermoegen;
	}
	
	public void setprice(int item_number, int price){
		this.price[item_number]=price;
	}
	
	public void shopSystem(int vermoegen){
		startShop(vermoegen);
	}
	
	
	private void item(int gridy, String item_name,int price, String picturePath,GridBagLayout layout, GridBagConstraints gbc, JDialog dialog){
	/*	switch(item_name){
		case "hp": 
			item_number=1 ;break;
		case "mana": 
			item_number=2 ;break;
		case "weapon": 
			item_number=3 ;break;
		default: JOptionPane.showMessageDialog(null, "Item ist nicht vorgesehen." ,"Error" , 0);
		}
		*/
		/*if(item_name=="hp")item_number=1;
		else if (item_name=="mana") item_number=2;
		else if (item_name=="weapon") item_number=3;
		else JOptionPane.showMessageDialog(null, "Item ist nicht vorgesehen." ,"Error" , 0);
		*/
		
		setprice(item_number, price);
		
		System.out.println("Anfang: " + getprice(item_number));
		//Item Picture
		
		gbc.gridheight=1;
		gbc.gridx = 0;  
		gbc.gridy = gridy;
		Icon pictureInGame = new ImageIcon(picturePath); 
		JLabel pic = new JLabel(pictureInGame);
		layout.setConstraints(pic, gbc);
		dialog.add(pic);
				
		//Item description
		gbc.gridheight=1;
		gbc.gridx = 1;  
		gbc.gridy = gridy;
		JTextPane itemName = new JTextPane();
		itemName.setText(item_name);
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
		priceInGame.setText(getprice(item_number) + " Geld");
		priceInGame.setBackground(dialog.getContentPane().getBackground());
		priceInGame.setEditable(false);
		layout.setConstraints(priceInGame, gbc);
		dialog.add(priceInGame);
		
		/*//number
		gbc.gridheight=1;
		gbc.gridwidth=5;
		gbc.gridx = 4;
		gbc.gridy = gridy;
		number = new JTextField();
		times=1;
		number.setText(times +"");
		number.setBackground(Color.white);
		number.setEditable(true);
		layout.setConstraints(number, gbc);
		dialog.add(number);
		
		//Test Ausgabe
		System.out.println("out: "  + times);
		
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
		
		*/
		
		
		//buy
		gbc.gridheight=1;
		gbc.gridwidth=2;
		gbc.gridx = 20;
		gbc.gridy =gridy;
		
		button = new JButton("Kaufen?");
		layout.setConstraints(button, gbc);
		//button.addActionListener(this);
		button.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e){
				System.out.println(getvermoegen());
				System.out.println(getprice(item_number));
				if ((getvermoegen()-getprice(item_number))>0){
				    times++;
					setvermoegen(getvermoegen()-getprice(item_number));
					System.out.println("Geld: " +getvermoegen());
					System.out.println(getvermoegen());
					System.out.println(getprice(item_number));
				}
				else if ((getvermoegen()-getprice(item_number))>0){
					JOptionPane.showMessageDialog(null, "Komm noch mit mit Geld wieder..." ,"Ohne Geld gibts keine Ware!" , 0);				
				}

			}
		});
				
		dialog.add(button);
	}

	
	
	
	private void startShop(int vermoegen){
		setvermoegen(vermoegen);
		
		
		dialog = new JDialog(dialog, "Settings");
		dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);	
		
//		cp = dialog.getContentPane();
		//Set layout to Grid
		GridBagLayout layout = new GridBagLayout();
		
		dialog.setLayout(layout);
		GridBagConstraints gbc=new GridBagConstraints();
		

		//Geld
		gbc.gridx = 0;
		gbc.gridy = 0;
		JTextPane geld = new JTextPane();
		geld.setText("Geld: " + getvermoegen() );
		geld.setBackground(dialog.getContentPane().getBackground());
		geld.setEditable(false);
		layout.setConstraints(geld, gbc);
		geld.repaint();
		dialog.add(geld);
		
		
		
		//item(int gridy, String item,int price, String picturePath,GridBagLayout layout, GridBagConstraints gbc, JDialog dialog)
		
		//health
		item(2, "hp",2, "mate_h64.jpg", layout, gbc, dialog);
		//mana
		item(4, "mana",7, "mate_h64.jpg", layout, gbc, dialog);
		//weapon
		item(6, "weapon",10, "mate_h64.jpg", layout, gbc, dialog);
		
		
		dialog.pack();
		dialog.setVisible(true);
		dialog.repaint();
		
	}


}
