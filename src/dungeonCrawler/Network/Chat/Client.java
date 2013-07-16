package dungeonCrawler.Network.Chat;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import dungeonCrawler.App;
import dungeonCrawler.Vector2d;


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

	private App app;

	private String userName;


	
	/**
	 * Setting up chat window and establish a Connection to the Server
	 * 
	 *  Setting up the chat window, like the incoming text area and the outgoing field. 
	 *  The try to connect to the Server. When a Connection is created also the Input/Output streams
	 *  will be created. And a Thread starts which listen for incoming data.
	 * 
	 * @param as {@link App}
	 * @param host The Address of the Server {@link String}
	 * @param port The port on which the Server listen {@link Integer}
	 * @param userName The user selected name for the chat {@link String}
	 */
	
	public Client(App app, String host, int port, final String userName) {
		this.app = app;
		this.userName = userName;


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
			this.send("/setname " + userName);
		} 
		catch( IOException ie ) { System.out.println(ie); 
			outputArea.append("Can't connect to the Server, please try again");
		}
		
	}
	
	public void close(){
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Send entered message
	 * 
	 * This method get the user input and send it to the Server, 
	 * after that it clear the inputField  
	 * 
	 * @param entered message as {@link String}
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
	
	public void send(String message){
		try {
			streamOut.writeUTF(message);
		} catch (IOException e) {
			outputArea.append("The message is not sent, please try again...");
			e.printStackTrace();
		}
	}
	private void teleport(String param){
		String[] params = param.split(";");
		this.app.clientGameLogic.teleportElement(Integer.parseInt(params[0]), new Vector2d(params[1]));
	}
	
	private void add(String param){
		this.app.clientGameLogic.addGameElement(param);
	}
	
	private void set(String param){
		String[] params = param.split(";");
		this.app.clientGameLogic.setGameElement(Integer.parseInt(params[0]), params[1]);
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
				case "/start":
					app.startClientGame(this);
					break;
				case "/delete":
					deleteLvl(params);
					break;
				case "/teleport":
					teleport(params);
					break;
				case "/add":
					add(params);
					break;
				case "/set":
					set(params);
					break;
				case "/line":
					writeLine(params);
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

	private void deleteLvl(String params) {
		// TODO Auto-generated method stub
		String[] param = params.split(";");
		String separator = File.separator;
		String str;
		try {
			str = 0 +  param[0];
			str = str.substring(str.length()-2);
		} catch (Exception e) {
			str =  "00";
		}
		File file = new File("Levels" + separator + "levelN" + userName + str + ".lvl");
		if(file.exists()){
			file.delete();
		}
	}

	private void writeLine(String params) {
		String[] param = params.split(";");
		String separator = File.separator;
		String str;
		try {
			str = 0 +  param[0];
			str = str.substring(str.length()-2);
		} catch (Exception e) {
			str =  "00";
		}
		File file = new File("Levels" + separator + "levelN" + userName + str + ".lvl");
		try {
			if(!file.exists())
				file.createNewFile();
			BufferedWriter buffer = new BufferedWriter(new FileWriter(file, true));
			buffer.write(param[1] + "\n");
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getUserName() {
		return userName;
	}
}

