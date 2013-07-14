/**
 * 
 */
package dungeonCrawler.Network.Lounge;

import java.awt.LayoutManager;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JPanel;

import dungeonCrawler.App;
import dungeonCrawler.Network.Chat.Server;

/**
 * @author Mattes
 *
 */
public class GameServer extends JPanel {
	private static final long serialVersionUID = 1L;
	private Server server;
	private HashMap<Socket, State> sockets = new HashMap<Socket, State>();
	private App app;

	/**
	 * 
	 */
	public GameServer(App app, dungeonCrawler.Network.Chat.Server server) {
		this.server = server;
		this.app = app;
	}
	
	private void chat(String message){
		if(message.length()>0)
			server.broadcastMessage("/chat " + message);
	}
	private void start(){
		boolean ready = true;
		for(State s: sockets.values()){
			ready &= s.isReady();
		}
		if(ready){
			startGame();
		} else {
			chat("Not everyone ready");
		}
	}
	
	private void startGame() {
		app.startServerGame();
		
	}

	public HashMap<Socket, State> getSockets() {
		return sockets;
	}

	private void ready(Socket socket, String params){
		sockets.get(socket).setReady(true);
	}

	public void interpret(Socket socket, String message) {
		// TODO Auto-generated method stub
		String msg = message.trim();
		int space = msg.indexOf(" ");
		if(space < 0) space = msg.length();
		String cmd = msg.substring(0, space);
		String params = msg.substring(space).trim();
		switch(cmd){
		case "/chat":
			chat(params);
			break;
		case "/ready":
			ready(socket, params);
			break;
		case "/start":
			start();
			break;
		default:
			chat(msg);
			break;
		}
	}
	public void addSocket(Socket socket){
		sockets.put(socket, new State());
	}
	public void removeSocket(Socket socket){
		sockets.remove(socket);
	}

	
//	/**
//	 * @param arg0
//	 */
//	public GameServer(LayoutManager arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param arg0
//	 */
//	public GameServer(boolean arg0) {
//		super(arg0);
//		// TODO Auto-generated constructor stub
//	}
//
//	/**
//	 * @param arg0
//	 * @param arg1
//	 */
//	public GameServer(LayoutManager arg0, boolean arg1) {
//		super(arg0, arg1);
//		// TODO Auto-generated constructor stub
//	}

}
