/**
 * 
 */
package dungeonCrawler;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * @author Mattes
 *
 */
public class MainMenu extends JPanel {
	private static final long serialVersionUID = 1L;
	private JButton start;
	private JButton exit;
	private JButton credits;
	private JButton setting;
	private JButton editor;
	private Container parent;
	private MainMenu menu;
	private App app;
	private Settings settings;

	/**
	 * 
	 */
	public MainMenu(App app) {
		parent = this.getParent();
		this.app = app;
		menu = this;
		this.initialize(); //remove if thread-problems occur
	}
	
	public void initialize(){
		this.setLayout(new GridLayout(5,1));
		start = new JButton("Start");
		setting = new JButton("Settings");
		exit = new JButton("Exit");
		credits = new JButton("Credits");
		editor = new JButton("Editor");
		this.add(start);
		this.add(setting);
		this.add(editor);
		this.add(credits);
		this.add(exit);
		
		
		
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				app.startGame();
				
			}
			
		});
		
		editor.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO öffne editormenü
				app.starteditormenu();
				
			}
		});
		
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
			
		});
		
	
		setting.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent e){
			
			Settings settings =new Settings();
			settings.startSettings();
			}
		});
		
		credits.addActionListener(new ActionListener() {
			public void credits() {

				// Erstellung Array vom Datentyp Object, Hinzuf�gen der Optionen
				Object[] options = { "OK" };

				int selected = JOptionPane
						.showOptionDialog(
								null,
								"Dieses Projekt ist von:\n Eugen,\n Matthias,\n Florian,\n und Dominik\n"
										+ "Es ist bei dem Modul Programmierpraktikum im Sommersemester 2013 entstanden.\n"
										+ "Wir w�nschen euch viel Spa� beim Spielen!",
								"Credits", JOptionPane.DEFAULT_OPTION,
								JOptionPane.PLAIN_MESSAGE, null, options, null);
				//System.out.println(selected);

				// System.exit(0);

			}

			@Override
			public void actionPerformed(ActionEvent e) {
				credits();
				
			}	
		});

	}

}
