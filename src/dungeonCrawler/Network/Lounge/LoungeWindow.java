package dungeonCrawler.Network.Lounge;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class LoungeWindow{

		
		JButton server;
	 
	    public LoungeWindow() {
	        super();
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
	    	serverPanel.add(server, new LoungeServer(server));
	    	tabbedPane.addTab("Server", serverPanel);
	    	
	    	c.add(tabbedPane);

	    }
	     

	   /* public static void main(String[] args) {
	            new LoungeWindow();
	            
	    }*/


}

