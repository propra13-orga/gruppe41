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


public class Client extends JPanel implements Runnable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * Components for Chat window
	 */
	private JTextField inputField = new JTextField();
	private JTextArea outputArea = new JTextArea();
	private JScrollPane scrollOutputArea = new JScrollPane(outputArea);
	
	// The socket connecting us to the server
	private Socket socket;
	
	
	/*
	 * For the communication with the server the streams are needed
	 */
	private DataOutputStream streamOut;
	private DataInputStream streamIn;

	
	public Client( String host, int port, final String userName) {

		/*
		 * Setting up the chat window.
		 * The ActionListener is needed to send message a return hit
		 */
		setLayout( new BorderLayout() );
		outputArea.setPreferredSize(new Dimension(500,350));
		inputField.setPreferredSize(new Dimension(500,24));
		inputField.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					getMessage(userName + "# " + e.getActionCommand());
				
			}
		});	
		
		add(inputField,BorderLayout.PAGE_END);
		add(scrollOutputArea,BorderLayout.CENTER);

		/*
		 * Try connect to Server
		 * Initialize a socket and then create the Input/Output streams.
		 * The Thread is listen to all incoming data 
		 */
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
	
	/*
	 * This method get the user input and send it to the Server, 
	 * after that it clear the inputField  
	 */
	private void getMessage(String message){
			
			try {
				streamOut.writeUTF(message);
			} catch (IOException e) {
				outputArea.append("The message is not sent, please try again...");
				e.printStackTrace();
			}
			scrollOutputArea.getVerticalScrollBar().setValue(scrollOutputArea.getVerticalScrollBar().getMaximum());
			inputField.setText( "" );
	}
	
	
	public void run() {
		try {
			
			while (true) {
				String message = streamIn.readUTF();
				outputArea.append( message+"\n" );
			}
		} catch(IOException ie) { 
			outputArea.append("Can't get the new input, sorry!!!");
		}
	}
}

