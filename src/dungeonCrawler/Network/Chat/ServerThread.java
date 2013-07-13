package dungeonCrawler.Network.Chat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;


/**
 * Listen on incoming messages and broadcast all incoming to connected clients 
 * 
 * @author Hucke
 *
 */
public class ServerThread extends Thread{

	private Server server;
	private Socket socket;

	
	/**
	 * The constructor saves the variables in this class and start the Thread
	 * which is the actually method of this class.
	 * 
	 * The Thread first creates a DataInputStream which gets the input from the Client.
	 * Then the Thread goes in a infinity loop and process the incoming messages at the end
	 * the method broadcastMessage from the Server class is called.
	 *  
	 * When the client is no more connected the Thread  call the method removeConnection from
	 * the Server class.
	 * 
	 * @param server The server on which the thread should run
	 * @param socket The port the server is listen on
	 */
	public ServerThread( Server server, Socket socket ) {
		this.server = server;
		this.socket = socket;
		start();
	}

	public void run() {
		try {
			DataInputStream input = new DataInputStream(socket.getInputStream());
			
		while (true) {
			String message = input.readUTF();
			System.out.println(message);
			
			server.broadcastMessage(message);
			}
		}
		catch(EOFException ie) {
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
		finally {
			server.removeConnection(socket);
		}
	}
}



	


