package dungeonCrawler.Network.Chat;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;

public class Server{
	
	// The ServerSocket we'll use for accepting new connections
	private ServerSocket serverSocket;

	/*
	 * The Hashtable will be use for DataOutputStream it contain
	 * the variable from the DataOutputStream and refer to the client.
	 */
	private Hashtable<Socket,DataOutputStream> outputStreams = new Hashtable<Socket,DataOutputStream>();
	
	
	public Server(int port) throws IOException {
		listen(port);
	}
	
	
	
	private void listen(int port) throws IOException {
		
		/*
		 * Create a SeverSocket/start the Server
		 */
		serverSocket = new ServerSocket(port);
		System.out.println("Server Started");
		
		/*
		 * The infinite loop waits for clients to Connect, 
		 * when a Client connect it creates a DataOutputStream.
		 * 
		 * Then the Hashtable outputStreams will get the variable
		 * from DataOutputStream and connect it to the client.
		 */
		while (true) {

			Socket client = serverSocket.accept();

			System.out.println("Connection from " + client);
			
			DataOutputStream output = new DataOutputStream(client.getOutputStream());
			outputStreams.put(client, output);
			
			new ServerThread(this, client);
		}
	}
	
	/*
	 * Here we get a list of all clients which are connected 
	 */
	Enumeration getOutputStreams() {
		return outputStreams.elements();
	}

	/*
	 * The title of the method describes all...
	 * The use of synchronized here is that a Thread might 
	 * closed by removeConnection, so the list is no more correct
	 * 
	 * In the for-loop the will be send to all entries in the Enumeration
	 * getOutputStreams()
	 */
	public void broadcastMessage( String message ){

		synchronized(outputStreams) {
			
			for (Enumeration e = getOutputStreams();e.hasMoreElements(); ) {

				DataOutputStream output = (DataOutputStream)e.nextElement();
				
				try {
					output.writeUTF(message);
				} catch( IOException ie ){ 
					System.out.println( ie ); 
				}
			}
		}
	}
	
	
	/*
	 * Like before we need a clean list of the clients which are connected to us.
	 * So every time the class ServerThread remove a connection the list have to be
	 * updated. And this is of course synchronized with the method broadcastMessage.
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

	/*
	 * The startServer(int port) method get the Port from
	 * the class ServerLounge and only start the constructor
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

	
	



