package uno.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import uno.ConsoleInput;
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
	
	ConsoleInput console;
	
	private final static int DEFAULT_PORT = 9090;
	private final static int MAX_CONNECTIONS = 10;
	private final static int MIN_CONNECTIONS = 2;
	public static final String REGEX = "|";
	
	private String name;
	private ServerSocket connectionSocket;
	
	Deck pickup;
	Deck discard;
	
	boolean started = false;
	
	private HashMap<String, ClientThread> clients;
	
	public enum USER {
		SERVER, USER
	}
	
	public Server() throws IOException {
		this(DEFAULT_PORT);
	}
	
	public Server(int port) throws IOException {
		name = InetAddress.getLocalHost().getHostName();
		connectionSocket = new ServerSocket(port, 50, InetAddress.getLocalHost());
		console = new ConsoleInput();
		clients = new HashMap<String, ClientThread>();
		listen();
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
				if (clients.size() >= MAX_CONNECTIONS) {
					System.out.println("No more players can connect");
					break;
				} else if (clients.size() >= MIN_CONNECTIONS) {
					System.out.println("There are " + clients.size() + " players. Would you like to start the game now? (Y/N)");
					if (console.getTrueFalse()) {
						break;
					}
				}
			} catch (IOException e) {
				System.out.println("Connection Closed");
				e.printStackTrace();
				
			}
		}
		startGame();
	}
	
	/**
	 * Starts a game of UNO. Should only be called if the <i>listen()</i> method has already been called
	 */
	private void startGame() {
		pickup = Deck.generateCards();
		pickup.shuffle(10);
		discard = new Deck();
		for (int i = 0; i < 7; i++) {
			for (ClientThread client : clients.values()) {
			
			}
		}
		while (true) {
		
		}
	}
	
	/**
	 * Adds a client to the server's list of clients. Can only be done during the setup portion of the game.
	 * 
	 * @param UUID
	 *            String
	 * @param client
	 *            ClientThread
	 * @return <b>True</b> if successfully added to client list. <b>False</b> if user with that UUID is already connected
	 */
	public boolean addClient(String UUID, ClientThread client) {
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
