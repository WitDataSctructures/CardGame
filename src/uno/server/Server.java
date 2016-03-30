/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * The server is an entry point for anyone who wants to run a server instance
 * 
 * @author Jake Mathews
 * @author Ford Polia
 * @author Darrien Kennedy
 */

package uno.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import adt.Card;
import uno.ConsoleInput;
import uno.Deck;
import uno.PlayerStats;

public class Server {

	public static void main(String[] args) {
		try {
			@SuppressWarnings("unused")
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
	public static final String REGEX = " ";

	private ServerSocket connectionSocket;

	private Deck pickup;
	private Deck discard;

	boolean started = false;

	private HashMap<String, ClientThread> clients;

	public Server() throws IOException {
		this(DEFAULT_PORT);
	}

	public Server(int port) throws IOException {
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
				System.out.println("Player connected: " + client.getRemoteSocketAddress());
				new ClientThread(client, this);
				if (clients.size() >= MAX_CONNECTIONS) {
					System.out.println("No more players can connect");
					break;
				} else if (clients.size() >= MIN_CONNECTIONS) {
					System.out.println("\nThere are " + clients.size() + " players. Would you like to start the game now? (Y/N)");
					if (console.getTrueFalse()) {
						System.out.println("Starting game!");
						break;
					}
				}
			} catch (IOException e) {
				System.out.println("Connection Closed");
				e.printStackTrace();

			} catch (ClassNotFoundException e) {
				System.out.println("Connection closed. Not a proper packet.");
				e.printStackTrace();
			}
		}
		startGame();
	}

	/**
	 * Starts a game of UNO. Should only be called if the <i>listen()</i> method has already been called
	 */
	private void startGame() {
		boolean error = false;
		started = true;
		pickup = Deck.generateCards();
		pickup.shuffle(10);
		discard = new Deck();
		discard.addToTop(pickup.drawFromTop());
		// Hand out initial cards
		System.out.println("Dishing out cards");
		for (int i = 0; i < 7; i++) {
			for (ClientThread client : clients.values()) {
				// System.out.println("TopCard(3) = " + pickup.peekFromTop());
				ClientPacket packet = new ClientPacket("dish_card", pickup, discard, null, false);
				ClientPacket returnedPacket = client.sendPacket(packet);
				System.out.println("sending");
				if (returnedPacket.getMessage().equals("success")) {
					pickup = returnedPacket.getPickupPile();
				} else {
					System.out.println("Failed to hand out cards [" + packet.getMessage() + "]");
					error = true;
					break;
				}
			}
		}
		if (!error) {
			System.out.println("Finsihed dishing out cards");
		}

		// Play the game
		String[] playerNames = new String[clients.size()];
		playerNames = clients.keySet().toArray(playerNames);
		int[] counts = new int[playerNames.length];
		for (int i = 0; i < counts.length; i++) {
			counts[i] = 7;
		}
		PlayerStats stats = new PlayerStats(playerNames, counts, playerNames[0]);
		boolean discardActive = false;
		boolean normalDirection = true;

		// Card counting
		String previousPlayer = playerNames[0];
		boolean previousUno = false;
		while (!error) {
			for (ClientThread client : clients.values()) {
				@SuppressWarnings("unused")
				ClientPacket update = client.sendPacket(new ClientPacket("update", pickup, discard, stats, discardActive));
			}
			ClientPacket results = clients.get(stats.getActivePlayer()).sendPacket(new ClientPacket("turn", pickup, discard, stats, discardActive));

			// Count cards
			pickup = results.getPickupPile();
			discard = results.getDiscardPile();
			System.out.println("Discard\n" + discard.toString());
			discardActive = results.isDiscardActive();
			stats = results.getStats();
			// View message
			boolean uno = false;

			if (pickup.getSize() <= 5) {
				Card temp = discard.drawFromTop();
				for (int i = 0; i < 5; i++) {
					discard.addToTop(pickup.drawFromTop());
				}
				discard.shuffle(10);
				pickup = discard;
				discard = new Deck();
				discard.addToTop(temp);
			}

			switch (results.getMessage()) {
				default:
					System.out.println("Tell everyone that " + stats.getActivePlayer() + " says " + results.getMessage());
				case "success":
					System.out.println("Recieved success");
					if (stats.getPlayersCardCount(stats.getActivePlayer()) == 0) {
						System.out.println(stats.getActivePlayer() + " WINS!\nThanks for playing!");
						System.exit(0);
					}
					if (discardActive && discard.peekFromTop().getSymbol().equals(Card.Symbol.REVERSE)) {
						discardActive = false;
						normalDirection = !normalDirection;
					}
					break;
				// case "uno":
				// System.out.println("Recieved uno");
				// // Check other player count
				// if (stats.getPlayersCardCount(previousPlayer) == 1 && previousUno) {
				// uno = true;
				// }
				// previousUno = false;
				// if (stats.getPlayersCardCount(stats.getActivePlayer()) == 1) {
				// previousUno = true;
				// }
				// break;
			}

			previousPlayer = stats.getActivePlayer();
			// View discard
			if (!uno) {
				if (discardActive && results.getDiscardPile().peekFromTop().getSymbol().equals(Card.Symbol.SKIP)) {
					if (normalDirection) {
						stats.setActivePlayer(getNextPlayer(stats.getActivePlayer()));
					} else {
						stats.setActivePlayer(getPreviousPlayer(stats.getActivePlayer()));
					}
					discardActive = false;
				}
				if (normalDirection) {
					stats.setActivePlayer(getNextPlayer(stats.getActivePlayer()));
				} else {
					stats.setActivePlayer(getPreviousPlayer(stats.getActivePlayer()));
				}
			}

		}
	}

	private String getNextPlayer(String activePlayer) {
		String[] playerNames = new String[clients.size()];
		playerNames = clients.keySet().toArray(playerNames);
		for (int i = 0; i < playerNames.length; i++) {
			if (playerNames[i].equals(activePlayer)) {
				if (i + 1 < playerNames.length) {
					return playerNames[i + 1];
				} else {
					return playerNames[0];
				}
			}
		}
		return playerNames[0];
	}

	private String getPreviousPlayer(String activePlayer) {
		String[] playerNames = new String[clients.size()];
		playerNames = clients.keySet().toArray(playerNames);
		for (int i = playerNames.length - 1; i >= 0; i--) {
			if (playerNames[i].equals(activePlayer)) {
				if (i - 1 >= 0) {
					return playerNames[i - 1];
				} else {
					return playerNames[playerNames.length - 1];
				}
			}
		}
		return playerNames[0];
	}

	/**
	 * Adds a client to the server's list of clients. Can only be done during the setup portion of the game.
	 * 
	 * @param UID
	 *            String
	 * @param client
	 *            ClientThread
	 * @return <b>True</b> if successfully added to client list. <b>False</b> if user with that UID is already connected
	 */
	public boolean addClient(String UID, ClientThread client) {
		boolean good = !started && (clients.putIfAbsent(UID, client) == null);
		if (good) {
			System.out.println("Client UID [" + UID + "]");
		} else {
			System.out.println("Could not add client");
		}
		return good;
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
