package dungeonCrawler.Network.Chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

/**
 * The chat Server 
 * @author Hucke
 *
 */
public class Server{
	
	private ServerSocket serverSocket;

	/**
	 * The Hashtable will be use for DataOutputStream it contain
	 * the variable from the DataOutputStream and refer to the client.
	 */
	private Hashtable<Socket,DataOutputStream> outputStreams = new Hashtable<Socket,DataOutputStream>();
	
	/**
	 * Constructor of the class gives the listen method the port of the Server
	 * 
	 * @param port the port the Server run on
	 * @throws IOException 
	 */
	public Server(int port) throws IOException {
		listen(port);
	}

	
	/**
	 * Create a server and listen to new clients
	 * 
	 * A server will be created and the infinite-loop waits for new clients.
	 * When a new client connects it will create a new DataOutputStream and 
	 * fill the Hashtable with the new client and the matching DataOutputStream
	 * variable 
	 * 
	 * @param port The port the server will start on
	 * @throws IOException
	 */
	private void listen(int port) throws IOException {
		

		serverSocket = new ServerSocket(port);
		System.out.println("Server Started");

		while (true) {

			Socket client = serverSocket.accept();

			System.out.println("Connection from " + client);
			
			DataOutputStream output = new DataOutputStream(client.getOutputStream());
			outputStreams.put(client, output);
			
			new ServerThread(this, client);
		}
	}
	

	
	/**
	 * Return the Hashtable with info of the clients and matching out DataOuttputSream as Enumeration
	 * 
	 * @return
	 */
	Enumeration<DataOutputStream> getOutputStreams() {
		return outputStreams.elements();
	}

	
	/**
	 * Send the incoming messages to all connected clients
	 * 
	 * The title of the method describes all...
	 * synchronized is use to make a dependency to the method removeConnection
	 *  
	 * The use of synchronized here is that a Thread might closed by removeConnection, 
	 * so the list isn't correct and we get exceptions. 
	 * 
	 * The for-loop send a incoming message to all entries in the Hashtable
	 *  
	 * @param message The message a client has send
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
	 * Remove a client connection
	 * 
	 * This method keeps the connection list clean. It is synchronized to broadcastMessage,
	 * because we will keep a clean connection list, so we don't get exceptions because a client 
	 * is removed but broadcastMessage will send a message to the disconnected client.
	 * This method runs in the ServerThread class
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
	 * @param port The port on which the server should listen
	 */
	public void startServer(int port){
		

		try {
			new Server(port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

	
	



