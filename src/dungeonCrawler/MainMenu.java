/**
 * 
 */
package dungeonCrawler;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private Container parent;
	private MainMenu menu;
	private App app;

	/**
	 * 
	 */
	public MainMenu(App app) {
		// TODO Auto-generated constructor stub
		parent = this.getParent();
		this.app = app;
		menu = this;
		this.initialize(); //remove if thread-problems occur
	}
	
	public void initialize(){
		start = new JButton("start");
		exit = new JButton("exit");
		this.add(start);
		this.add(exit);
		
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				app.startGame(0);
				
			}
			
		});
		
		exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
				
			}
			
		});
	}

}
