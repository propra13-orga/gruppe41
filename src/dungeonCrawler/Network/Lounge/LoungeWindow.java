package dungeonCrawler.Network.Lounge;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import dungeonCrawler.App;

/**
 * Construction of the Lounge with added content in tabbed pane
 *  
 * @author Hucke 
 *
 */
public class LoungeWindow{

		
		JButton server;
		private App app;
	 
	    public LoungeWindow(App app) {
	        super();
	    	this.app = app;
            JFrame gui = new JFrame("Dungeon Crawler Lounge");
	        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        gui.setPreferredSize(new Dimension(600, 500));
	        fillLounge(gui.getContentPane());
	        
	        gui.pack();
	        gui.setVisible(true);
	    }
	 
	    public void fillLounge(Container c) {
	    	JTabbedPane tabbedPane = new JTabbedPane();
	    	JPanel server =new JPanel();
	    	JPanel client = new JPanel();
	    	
	    	
	    	JPanel clientPanel = new JPanel();
	    	clientPanel.add(client, new LoungeClient(client));
	    	tabbedPane.addTab("Client", clientPanel);
	    	
	    	JPanel serverPanel = new JPanel();
	    	serverPanel.add(server, new LoungeServer(app, server));
	    	tabbedPane.addTab("Server", serverPanel);
	    	
	    	c.add(tabbedPane);

	    }
}

