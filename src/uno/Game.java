package uno;

import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		Game game = new Game();
		game.setup();
		game.start();
	}

	private final static int MIN_PLAYERS = 2, MAX_PLAYERS = 10;

	private int playerCount = 0;
	private Player[] players;
	private Scanner console;
	private Pile discardPile;
	private Pile pickupPile;

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
		players = new Player[playerCount];
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
			players[i] = new Player(name);
		}
		System.out.print("The current players are: ");
		for (int i = 0; i < playerCount; i++) {
			System.out.print(players[i].getName());
			if (i == playerCount - 2) {
				System.out.print(" and ");
			} else if (i != playerCount - 1) {
				System.out.print(", ");
			}
		}

		// Create/shuffle pickup pile
		discardPile = new Pile();
		pickupPile = new Pile();
		// Dish out cards
	}

	private void start() {
		// While the game isn't finished, keep going around the table
		boolean finished = false;
		while (!finished) {
			for (int i = 0; i < playerCount; i++) {

			}
		}
	}

}
