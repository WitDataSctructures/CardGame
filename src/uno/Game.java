package uno;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import adt.Card;
import adt.Card.Color;
import adt.Card.Symbol;

public class Game {
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setup();
		game.start();
	}
	
	private final static int MIN_PLAYERS = 2, MAX_PLAYERS = 10;
	
	private int playerCount = 0;
	private ArrayList<Player> players;
	private Scanner console;
	private Deck discardPile;
	private Deck pickupPile;
	
	// Game time variables
	Player currentPlayer;
	boolean reversed = false;
	
	public Game() {
		console = new Scanner(System.in);
	}
	
	public void setup() {
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
		players = new ArrayList(playerCount);
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
		
		// Display current players
		System.out.print("The current players are: ");
		for (int i = 0; i < playerCount; i++) {
			System.out.print(players.get(i).getName());
			if (i == playerCount - 2) {
				System.out.print(" and ");
			} else if (i != playerCount - 1) {
				System.out.print(", ");
			}
		}
		
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
				} else if (symbol != Symbol.ZERO && symbol != Symbol.WILD && color != color.WILD) {
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
		// Start with a random player each time
		currentPlayer = players.get(new Random().nextInt(playerCount - 1));
		while (!finished) {
		
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
