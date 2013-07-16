package dungeonCrawler.Network.Chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

import dungeonCrawler.App;
import dungeonCrawler.Network.Lounge.GameServer;

/**
 * The chat Server 
 * @author Hucke
 *
 */
public class Server{
	
	private ServerSocket serverSocket;

	/**
	 * The {@link Hashtable} will be use for {@link DataOutputStream} it contain
	 * the variable from the {@link DataOutputStream} and refer to the client.
	 */
	private Hashtable<Socket,DataOutputStream> outputStreams = new Hashtable<Socket,DataOutputStream>();

	private boolean running = false;

	private App app;
	
	public boolean isRunning() {
		return running;
	}
	
	public void send(Socket socket, String message){
		DataOutputStream output = outputStreams.get(socket);
		try {
			output.writeUTF(message);
		} catch( IOException ie ){ 
			System.out.println( ie ); 
		}
	}


	/**
	 * Constructor of the class gives the listen method the port of the Server
	 * 
	 * @param port the port as {@link Integer} the Server run on
	 * @throws IOException as {@link IOException}
	 */
	public Server(App app, int port) throws IOException {
		this.app = app;
		listen(port);
	}

	
	/**
	 * Create a server and listen to new clients
	 * 
	 * A server will be created and the infinite-loop waits for new clients.
	 * When a new client connects it will create a new {@link DataOutputStream} and 
	 * fill the {@link Hashtable} with the new client and the matching {@link DataOutputStream}
	 * variable 
	 * 
	 * @param port The port as {@link Integer} the server will start on
	 * @throws IOException as {@link IOException}
	 */
	private void listen(int port) throws IOException {
		

		serverSocket = new ServerSocket(port);
		GameServer gs = new GameServer(app, this);
		System.out.println("Server Started");
		this.running  = true;

		while (true) {

			Socket client = serverSocket.accept();

			System.out.println("Connection from " + client);
			
			DataOutputStream output = new DataOutputStream(client.getOutputStream());
			outputStreams.put(client, output);
			
			new GameServerThread(this, client, gs);
		}
	}
	

	
	/**
	 * Return the {@link Hashtable} with info of the clients {@link Socket} and matching {@link DataOutputStream}
	 * 
	 * @return as {@link Enumeration}
	 */
	Enumeration<DataOutputStream> getOutputStreams() {
		return outputStreams.elements();
	}

	
	/**
	 * Send the incoming messages to all connected clients
	 * 
	 * The title of the method describes all...
	 * synchronized is use to make a dependency to the method {@see Server#removeConnection(Socket)}
	 *  
	 * The use of synchronized here is that a {@link Thread} might closed by {@see Server#removeConnection(Socket)}, 
	 * so the list isn't correct and we get {@link IOException}. 
	 * 
	 * The for-loop send a incoming message to all entries in the {@link Hashtable}
	 *  
	 * @param message The message as {@link String} a {@link Client} has send
	 */
	public void broadcastMessage( String message ){

		synchronized(outputStreams) {
			
			for (Enumeration<DataOutputStream> e = getOutputStreams();e.hasMoreElements(); ) {

				DataOutputStream output = (DataOutputStream)e.nextElement();
				
				try {
					output.writeUTF(message);
				} catch( IOException ie ){ 
					System.out.println( ie ); 
				}
			}
		}
	}
	
	

	
	/**
	 * Remove a {@link Client} connection
	 * 
	 * This method keeps the connection list clean. It is synchronized to {@see Server#broadcastMessage(String)},
	 * because we will keep a clean connection list, so we don't get exceptions because a client 
	 * is removed but {@see Server#broadcastMessage(String)} will send a message to the disconnected client.
	 * This method runs in the {@link ServerThread}
	 * 
	 *  
	 * @param s The Socket that will be close
	 */
	public void removeConnection( Socket s ) {

		synchronized( outputStreams ) {
			System.out.println( "Removing connection to "+s );
			
			outputStreams.remove( s );
			
			try {
				s.close();
			} catch( IOException ie ) {
				System.out.println("Error closing " + s);
				ie.printStackTrace();
			}
		}
	}


	/**
	 * Start the constructor with a given port.
	 * 
	 * @param port The port as {@link Integer} on which the server should listen
	 */
	public void startServer(int port){
		

		try {
			new Server(app, port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

	
	



