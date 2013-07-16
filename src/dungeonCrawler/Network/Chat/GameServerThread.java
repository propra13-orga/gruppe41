/**
 * 
 */
package dungeonCrawler.Network.Chat;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

import dungeonCrawler.Network.Lounge.GameServer;

/**
 * @author Mattes
 *
 */
public class GameServerThread extends Thread {

	private Server server;
	private Socket socket;
	private GameServer gs;

	/**
	 * 
	 */
	public GameServerThread(Server server, Socket socket, GameServer gs) {
		this.server = server;
		this.socket = socket;
		this.gs = gs;
		gs.addSocket(socket);
		start();
	}

	@Override
	public void run() {
		try {
			DataInputStream input = new DataInputStream(socket.getInputStream());
			
		while (true) {
			String message = input.readUTF();
//			System.out.println(message);
			gs.interpret(socket, message);
			}
		}
		catch(EOFException ie) {
		}
		catch(IOException ie) {
			ie.printStackTrace();
		}
		finally {
			server.removeConnection(socket);
			gs.removeSocket(socket);
		}
	}

}
