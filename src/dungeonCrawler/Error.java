package dungeonCrawler;

import javax.swing.JDialog;
import javax.swing.JLabel;

/**Instance of an error message
 * @author Tissen
 */
public class Error extends JDialog {
	
	private static final long serialVersionUID = 1L;
	String message;
	
	/**Constructor
	 * @param message to be shown as {@link String}
	 */
	public Error(String message) {
		this.message = message;
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setTitle("Meldung");
		this.setSize(300, 200);
		this.setModal(true);
		this.setLocation(150, 100);
		this.setResizable(false);
		JLabel label = new JLabel("<html>" + this.message + "</html>");
		this.add(label);
	}
	
	/**Shows massage window
	 */
	public void showMe() {
		this.setVisible(true);
	}
	
}
