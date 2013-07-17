package dungeonCrawler.Network.Lounge;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dungeonCrawler.App;
import dungeonCrawler.Network.Chat.Client;

/**
 *Draw layout and components of the LoungeClient Card for the {@see LoungeWindow}.
 *The {@see Client} will start through this Card
 * 
 * @author Hucke
 *
 */
public class LoungeClient implements ActionListener{
	
	private JButton connectButton, backButton;
	
	private JTextField ipField, portField, userName;
	
	private String clientStartString ="Client Main", chatString = "Send" ;
	
	private JPanel clientPanel, chatPanel, clientStart;
	
	private Client client;
	
	private CardLayout clientLayout;

	private App app;

	public LoungeClient(App app, JPanel c){
		this.app = app;
		init(c);
		
	}
	
	/**
	 * Initialize the client card
	 * @param c as {@link JPanel}
	 */
	private void init(JPanel c){
		
		//Client starten Karte
		ipField = new JTextField();
		ipField.setPreferredSize(new Dimension(300,24));
		ipField.setText("localhost");
		portField = new JTextField();
		portField.setText("5000");
		portField.setPreferredSize(new Dimension(100,24));
				
		JPanel serverAddr = new JPanel(new FlowLayout());
		serverAddr.add(new JLabel("Server IP: "));
		serverAddr.add(ipField);
		serverAddr.add(new JLabel("    Port: "));
		serverAddr.add(portField);
		
		JPanel userNamePanel = new JPanel(new FlowLayout()); 
		userNamePanel.add(new JLabel("Nutername:  "));
		userName = new JTextField();
		userName.setPreferredSize(new Dimension(286,24));
		userNamePanel.add(userName);
		
		
		
		connectButton = new JButton("Connect");
		connectButton.addActionListener(this);
		backButton = new JButton("Back");
		backButton.addActionListener(this);
		
		
		clientStart = new JPanel(new BorderLayout());
		clientStart.add(serverAddr, BorderLayout.PAGE_START);
		clientStart.add(userNamePanel, BorderLayout.LINE_START);
		clientStart.add(connectButton,BorderLayout.PAGE_END);
		
		chatPanel = new JPanel(new BorderLayout());

		chatPanel.add(backButton,BorderLayout.PAGE_END);
		
		clientPanel = new JPanel(new CardLayout());
		clientPanel.add(clientStart, clientStartString);
		clientPanel.add(chatPanel, chatString);
		
		clientLayout = (CardLayout)(clientPanel.getLayout());
		
		c.add(clientPanel,BorderLayout.LINE_START);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource()==connectButton){
			client = new Client(app, this, ipField.getText(), new Integer(portField.getText()),userName.getText());
			chatPanel.add(client,BorderLayout.CENTER);
			clientLayout.show(clientPanel, chatString );
		}
			
		if(arg0.getSource()==backButton){
			//clientLayout.last(clientStart);
			clientLayout.show(clientPanel, clientStartString);
			chatPanel.remove(client);
			client.close();
			client = null;
		}	
	}
	public void close(){
		clientLayout.show(clientPanel, clientStartString);
		chatPanel.remove(client);
		client = null;
	}
}
