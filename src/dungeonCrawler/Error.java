package dungeonCrawler;

import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;

/**instance of an error message
 * 
 * @author Tissen
 *
 */
public class Error extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public Error(String message) {
		this.message = message;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Meldung");
		this.setModal(true);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setSize(250, 90);
		this.setLocation(150, 100);
		this.setResizable(false);
		this.add(new JLabel(this.message));
	}
	
	public void show() {
		this.setVisible(true);
	}
	
}
