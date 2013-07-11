package dungeonCrawler;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.ComboBoxModel;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JComboBox;;

public class Editormenu extends JPanel {
	private static final long serialVersionUID = 1L;

	private App app;
	private Container parent;
	private JButton NewMap;
	private JComboBox editMapNr;
	private File file;
	private LinkedList<String> MapNr = new LinkedList<String>();
	
	public Editormenu(App app){
		
		int i=app.level;
		int j=1;
		Component comp = null;
		editMapNr = new JComboBox();
		this.app = app;
		parent = this.getParent();
		this.setLayout(new GridLayout(2, 2));
		NewMap = new JButton("Neu");
		this.add(NewMap);
		this.add(editMapNr);
		this.initialize();
	}
	
	
	private String convertLvltoStr(int currentLevel) {
		String str;
		try {
			str = 0 +  Integer.toString(currentLevel);
			str = str.substring(str.length()-2);
			System.out.println(str);

		} catch (Exception e) {
			return "99";
		}
		return str;
	}
	
	
		private void initialize(){
		NewMap.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO neue map erstellen
				file = new File("Levels"+File.separator+"level" + convertLvltoStr(app.level) +".lvl");
				app.level +=1;
///////////////////////////////////////////////////7
				
				
				
				   FileWriter writer = null;
				    try {
				        writer = new FileWriter(file);
				        writer.write("PLAYER,0,0,30,30");
				    } catch (IOException e1) {
				        e1.printStackTrace(); // I'd rather declare method with throws IOException and omit this catch.
				    } finally {
				        if (writer != null) try { writer.close(); } catch (IOException ignore) {}
				    }
				    System.out.printf("File is located at %s%n", file.getAbsolutePath());				
				
				
				
				
				
				
				
				
//////////////////////////////////////////////////////				
				try {
					file.createNewFile();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				app.startEdit(file);
			}
		});
		
	}
}
