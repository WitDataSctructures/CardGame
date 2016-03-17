package uno.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import uno.Deck;

public class Server {
	
	public static void main(String[] args) {
		try {
			Server server;
			if (args.length == 1) {
				server = new Server(Integer.parseInt(args[0]));
			} else {
				server = new Server();
			}
		} catch (IOException e) {
			System.out.println("Server crashed! :(");
			e.printStackTrace();
		}
	}
	
	private final static int DEFAULT_PORT = 9090;
	private final static int MAX_CONNECTIONS = 10;
	private final static int MIN_CONNECTIONS = 2;
	public static final String REGEX = "|";
	
	private String name;
	private ServerSocket connectionSocket;
	
	Deck pickup = new Deck();
	Deck discard = new Deck();
	
	boolean started = false;
	
	private HashMap<String, Socket> clients;
	
	public enum USER {
		SERVER, USER
	}
	
	public Server() throws IOException {
		this(DEFAULT_PORT);
	}
	
	public Server(int port) throws IOException {
		name = InetAddress.getLocalHost().getHostName();
		connectionSocket = new ServerSocket(port, 50, InetAddress.getLocalHost());
		
	}
	
	public String getServerAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
	
	public int getServerPort() throws UnknownHostException {
		return connectionSocket.getLocalPort();
	}
	
	public void listen() {
		while (true) {
			try {
				System.out.println("\nListening on: " + getServerAddress() + ":" + getServerPort());
				Socket client = connectionSocket.accept();
				System.out.println("Player connected: " + client.getLocalAddress());
				new ClientThread(client, this);
				if (clients.size() > MAX_CONNECTIONS) {
					System.out.println("No more players can connect");
					break;
				}
			} catch (IOException e) {
				System.out.println("Connection Closed");
				e.printStackTrace();
				
			}
		}
	}
	
	/**
	 * Adds client socket to the server's list of clients. Can only be done during the setup portion of the game.
	 * 
	 * @param UUID
	 *            String
	 * @param client
	 *            Socket
	 * @return <b>True</b> if successfully added to client list. <b>False</b> if user with that UUID is already connected
	 */
	public boolean addClient(String UUID, Socket client) {
		return !started && (clients.putIfAbsent(UUID, client) == null);
	}
	
	/**
	 * Returns the server's socket
	 * 
	 * @return Server Socket
	 */
	public ServerSocket getServerSocket() {
		return connectionSocket;
	}
}
