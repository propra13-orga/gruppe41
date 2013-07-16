/**
 * 
 */
package dungeonCrawler.Network.Lounge;

import java.awt.LayoutManager;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JPanel;

import dungeonCrawler.App;
import dungeonCrawler.Vector2d;
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
	
	public void send(Socket socket, String message){
		server.send(socket, message);
	}
	
	public void sendWithout(Socket socket, String message){
		for(Socket sock: sockets.keySet()){
			if(!sock.equals(socket)){
				server.send(sock, message);
			}
		}
	}
	
	private void setname(Socket socket, String name){

		for(Socket sock: sockets.keySet()){
			if(sock.equals(socket)){
				sockets.get(sock).setName(name);
			}
		}
	}
	
	public void sendAll(String message){
		server.broadcastMessage(message);
	}
	
	public void broadcastMessage(String message){
		server.broadcastMessage(message);
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
		app.startServerGame(this);
		
	}

	public HashMap<Socket, State> getSockets() {
		return sockets;
	}

	private void ready(Socket socket, String params){
		sockets.get(socket).setReady(true);
	}
	
	private void teleport(String param){
		String[] params = param.split(";");
//		System.out.println("/teleport " + param);
		this.app.serverGameLogic.teleportElement(Integer.parseInt(params[0]), new Vector2d(params[1]));
	}
	
	private void move(String param){
		String[] params = param.split(";");
//		System.out.println("/move	 " + Integer.parseInt(params[0]) + "	" + new Vector2d(params[1]));
		this.app.serverGameLogic.moveElement(Integer.parseInt(params[0]), new Vector2d(params[1]));
	}
	
	private void add(String param){
		this.app.serverGameLogic.addGameElement(param);
	}
	
	private void set(String param){
		String[] params = param.split(";");
		this.app.serverGameLogic.setGameElement(Integer.parseInt(params[0]), params[1]);
	}
	private void sendList(){
		String list = "";
		for(State st: sockets.values()){
			list += st.getName() + ", ";
		}
		sendAll("/chat " + list);
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
		case "/teleport":
			teleport(params);
//			System.out.println(msg);
			break;
		case "/setname":
			setname(socket, params);
			break;
		case "/list":
			sendList();
			break;
		case "/move":
			move(params);
//			System.out.println("!	" + msg);
			break;
		case "/add":
			add(params);
			break;
		case "/set":
			set(params);
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
