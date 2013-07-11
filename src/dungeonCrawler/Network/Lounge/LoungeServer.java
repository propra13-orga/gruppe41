package dungeonCrawler.Network.Lounge;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dungeonCrawler.Network.Chat.Server;



public class LoungeServer implements ActionListener,Runnable{
	private JButton startServer_btn = null, stopServer_btn = null, cancel_btn = null;

	private JPanel serverPanel = null;
	
	private JTextField portNumber = null;
	
	private String startServerString = "Starte den Server", stopServerString = "Stoppe den Server";
	
	private JLabel message;

	public LoungeServer(JPanel c) {
		fillServerCard(c);
	}
	
	private void fillServerCard(JPanel c){
		startServer_btn = new JButton("Server Starten");
		startServer_btn.addActionListener(this);
		stopServer_btn = new JButton("ServerStoppen");
		stopServer_btn.addActionListener(this);
		cancel_btn = new JButton("Abbrechen");
		cancel_btn.addActionListener(this);

		
		JPanel buttonPanelStart = new JPanel(new FlowLayout());
		buttonPanelStart.add(cancel_btn);
		buttonPanelStart.add(startServer_btn);
			
		JPanel buttonPanelStop = new JPanel(new FlowLayout());
		buttonPanelStop.add(cancel_btn);
		buttonPanelStop.add(stopServer_btn);
		
		//Start Server Card
		JPanel startServerPanel = new JPanel();
		startServerPanel.setLayout(new BorderLayout());
		startServerPanel.add(new JLabel("Wollen Sie einen Server starten?"),BorderLayout.PAGE_START);
		
		JPanel portPanel = new JPanel(new FlowLayout());
		portPanel.add(new JLabel("Bitte geben Sie einen Port ein auf den der Server laufen soll:   "));
		portNumber = new JTextField("5000");
		portNumber.setPreferredSize(new Dimension(45,24));
		portPanel.add(portNumber);
		
		startServerPanel.add(portPanel, BorderLayout.LINE_START);
		startServerPanel.add(buttonPanelStart,BorderLayout.PAGE_END);
		
		//Stop Server Card
		JPanel stopServerPanel = new JPanel();
		stopServerPanel.setLayout(new BorderLayout());
		
		message = new JLabel();
		message.setText("Der Server wurde auf Port " + portNumber.getText()+ " gestartet");
		stopServerPanel.add(message,BorderLayout.PAGE_START);
		
		
		//JPanel Cards
		
		serverPanel =new JPanel(new CardLayout());
		serverPanel.add(startServerPanel, startServerString);
		serverPanel.add(stopServerPanel, stopServerString);
		c.add(serverPanel,BorderLayout.LINE_END);
				
	}

	//Window	
	public void actionPerformed(ActionEvent e) {
		CardLayout serverLayout = (CardLayout)(serverPanel.getLayout());
		Thread thread = new Thread(this);
		if(e.getSource()==startServer_btn){
				thread.start();
				serverLayout.show(serverPanel, stopServerString);
		
		}
		if(e.getSource()==stopServer_btn){
			try {
				thread.wait();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		
			serverLayout.show(serverPanel, startServerString);
		}
	}

	public void run() {
		try {			
			Server server = new Server(new Integer(portNumber.getText()));
			server.startServer(new Integer(portNumber.getText()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
