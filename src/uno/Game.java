package uno;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import adt.Card;
import adt.Card.Color;
import adt.Card.Symbol;
import uno.server.Server;

public class Game {
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Game game = new Game();
	}
	
	// Input manager
	private Scanner console;
	
	// Game setup
	private final static int MIN_PLAYERS = 2, MAX_PLAYERS = 10;
	private int playerCount = 0;
	private ArrayList<Player> players;
	
	// Game time variables
	private Deck discardPile;
	private Deck pickupPile;
	Player currentPlayer;
	boolean reversed = false;
	
	// Networking
	private Server server;
	private Socket socket;
	
	public Game() {
		console = new Scanner(System.in);
		if (connectToServer()) {
		
		}
	}
	
	/**
	 * Attempt to connect to an existing server or create one.
	 * 
	 * @return <b>True</b> if a server was successfully created or connected to.
	 *         <b>False</b> if the player does not want to host and cannot
	 *         connect to server.
	 */
	@SuppressWarnings("unused")
	private boolean connectToServer() {
		boolean success = true;
		try {
			server = getServer();
			if (server != null) {
				System.out.println("You are the game host!");
				System.out.println("Give the following address to anyone who wants to join your game.");
				System.out.println("    Address = " + server.getServerAddress());
			} else {
				System.out.println("You are the not the game host. Enter an adress from a host to join their game.");
				// TODO: Replace console reads with input manager
				String input = null;
				Server server = null;
				while (input == null) {
					input = console.nextLine().trim().toLowerCase();
					if (input.contains(":") && input.contains(".")) {
						String address = input.split(":")[0];
						String port = input.split(":")[1];
						try {
							socket = new Socket(address, Integer.parseInt(port));
						} catch (Exception e) {
							System.out.println("Could not connect to host @ " + input);
							e.printStackTrace();
							success = false;
						}
					} else {
						input = null;
						System.out.println("Invalid server adress. Try again.");
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			success = false;
		}
		return success;
	}
	
	/**
	 * Gets the server instance.
	 * 
	 * @return <b>Server</b> instance. <b>Null</b> if player does not want to be
	 *         host.
	 * @throws IOException
	 */
	private Server getServer() throws IOException {
		System.out.println("Do you want to host a game? (Y/N)");
		String input = null;
		Server server = null;
		// TODO: Replace console reads with input manager
		while (input == null) {
			input = console.nextLine().trim().toLowerCase();
			if (input.equals("y") || input.equals("yes")) {
				server = new Server();
			} else if (input.equals("n") || input.equals("no")) {
				server = null;
			} else {
				input = null;
				System.out.println("Invalid input. Try again.");
			}
		}
		return server;
	}
	
	public void setupGame() {
		// Display current players
		
		// Create/fill pickup pile
		discardPile = new Deck();
		pickupPile = new Deck();
		
		for (Symbol symbol : Symbol.values()) {
			for (Color color : Color.values()) {
				// there are only four of each wild type and zeros
				boolean isZero = symbol == Symbol.ZERO && color != Color.WILD;
				boolean isWild = symbol == Symbol.WILD && color == Color.WILD;
				if (isZero || isWild) {
					pickupPile.addToTop(new Card(color, symbol));
				} else if (symbol != Symbol.ZERO && symbol != Symbol.WILD && color != Color.WILD) {
					// Two of every other type of card
					pickupPile.addToTop(new Card(color, symbol));
					pickupPile.addToTop(new Card(color, symbol));
				}
			}
		}
		
		// Shuffle pickup pile
		pickupPile.shuffle();
		
		// Dish out cards
		for (int i = 0; i < 7; i++) {
			// Each player get 7 cards
			for (Player player : players) {
				player.addToHand(pickupPile.drawFromTop());
			}
		}
		
		// Add card to discard pile to start game
		discardPile.addToTop(pickupPile.drawFromTop());
	}
	
	private void start() {
		// While the game isn't finished, keep going around the table
		boolean finished = false;
		while (!finished) {
		
		}
	}
	
	private void setupPlayers() {
		// Determine how many players there are
		do {
			System.out.print("How many players are there? ");
			playerCount = Integer.parseInt(console.nextLine());
			if (playerCount < MIN_PLAYERS || playerCount > MAX_PLAYERS) {
				playerCount = 0;
				System.out.println("You must have at least 2 players but no more than 10 players\n");
			}
		} while (playerCount == 0);
		
		// Create players array and give players names
		players = new ArrayList<Player>(playerCount);
		System.out.println("Give each player a name in the order in which you want them to go.");
		for (int i = 0; i < playerCount; i++) {
			System.out.println("	Player " + (i + 1) + " of " + playerCount);
			String name = "";
			do {
				System.out.print("	Name: ");
				name = console.next().trim();
				System.out.print("");
				if (name.isEmpty() || name.length() > 12) {
					name = "";
					System.out.println("A valid name must be between 1 and 12 characters long");
				}
			} while (name.isEmpty());
			players.set(i, new Player(name));
		}
	}
	
	private Player getNextPlayer() {
		Player next = null;
		int currentIndex = players.indexOf(currentPlayer);
		if (!reversed) { // Its normal direction
			if (currentIndex != playerCount - 1) {
				next = players.get(currentIndex + 1);
			} else {
				next = players.get(0);
			}
		} else { // Game is reversed
			if (currentIndex == 0) {
				next = players.get(playerCount - 1);
			} else {
				next = players.get(currentIndex - 1);
			}
		}
		return next;
	}
	
}
