package dungeonCrawler.Network.Chat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import javax.xml.crypto.Data;


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
	 * The constructor saves the variables in this class and start the {@link Thread}
	 * which is the actually method of this class.
	 * 
	 * The Thread first creates a {@link DataInputStream} which gets the input from the {@link Client}.
	 * Then the Thread goes in a infinity loop and process the incoming messages at the end
	 * the {@see Server#broadcastMessage(String)} from the {@link Server} is called.
	 *  
	 * When the {@link Client} is no more connected the {@link ServerThread} call the {@see Server#broadcastMessage(String)} from
	 * the Server class.
	 * 
	 * @param server  as {@link Server}
	 * @param socket  as {@link Socket}
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



	


