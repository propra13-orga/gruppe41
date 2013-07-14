package dungeonCrawler.Network.Chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


/**
 * This class creates a chat client to a server with some parts of the GUI 
 * 
 * @author Hucke
 *
 */
public class Client extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Components for Chat window
	 */
	private JTextField inputField = new JTextField();
	private JTextArea outputArea = new JTextArea();
	private JScrollPane scrollOutputArea = new JScrollPane(outputArea);
	
	private Socket socket;
	
	
	/**
	 * For the communication with the server the streams are needed
	 */
	private DataOutputStream streamOut;
	private DataInputStream streamIn;


	
	/**
	 * Setting up chat window and establish a Connection to the Server
	 * 
	 *  Setting up the chat window, like the incoming text area and the outgoing field. 
	 *  The try to connect to the Server. When a Connection is created also the Input/Output streams
	 *  will be created. And a Thread starts which listen for incoming data.
	 * 
	 * @param host The Address of the Server
	 * @param port The port on which the Server listen
	 * @param userName The user selected name for the chat 
	 */
	
	public Client( String host, int port, final String userName) {


		setLayout( new BorderLayout() );
		outputArea.setPreferredSize(new Dimension(500,350));
		inputField.setPreferredSize(new Dimension(500,24));
		inputField.addActionListener( new ActionListener() {
			//is needed for sending with return key
			public void actionPerformed(ActionEvent e) {
				
					getMessage(/*userName + "# " +*/ e.getActionCommand());
				
			}
		});	
		
		add(inputField,BorderLayout.PAGE_END);
		add(scrollOutputArea,BorderLayout.CENTER);

		try {

			socket = new Socket(host,port);
			streamIn = new DataInputStream(socket.getInputStream());
			streamOut = new DataOutputStream(socket.getOutputStream());

			new Thread(this).start();
		} 
		catch( IOException ie ) { System.out.println(ie); 
			outputArea.append("Can't connect to the Server, please try again");
		}
		
	}
	
	/**
	 * Send entered message
	 * 
	 * This method get the user input and send it to the Server, 
	 * after that it clear the inputField  
	 * 
	 * @param message the entered message
	 */
	private void getMessage(String message){
			
			try {
				streamOut.writeUTF(message);
			} catch (IOException e) {
				outputArea.append("The message is not sent, please try again...");
				e.printStackTrace();
			}
			scrollOutputArea.getVerticalScrollBar().setValue(scrollOutputArea.getVerticalScrollBar().getMaximum());
			System.out.println("Client: " + message);
			inputField.setText( "" );
	}
	
	/**
	 * The Thread in a infinity-loop it waits for incoming messages and
	 * append it to the incoming area
	 */	
	public void run() {
		try {
			
			while (true) {
				String message = streamIn.readUTF();
				String msg = message.trim();
				int space = msg.indexOf(" ");
				if(space < 0) space = msg.length();
				String cmd = msg.substring(0, space);
				String params = msg.substring(space).trim();
				switch(cmd){
				case "/chat":
					if(params.length()>0)
						outputArea.append( params+"\n" );
					break;
				default:
					System.out.println("msg:" + msg);
					break;
				}
			}
		} catch(IOException ie) { 
			outputArea.append("Can't get the new input, sorry!!!");
		}
	}
}

