package uno;

import java.util.Scanner;

import adt.Card;
import adt.Card.Color;
import adt.Card.Symbol;

public class ConsoleInput implements InputManager {
	
	private Scanner console;
	
	public ConsoleInput() {
		console = new Scanner(System.in);
	}
	
	/**
	 * Get the card the user wants to place on the discard pile
	 * 
	 * @return Card from user's hand
	 */
	@Override
	public Card getCard() {
		String input = null, color = null, symbol = null;
		Color cardColor = null;
		Symbol cardSymbol = null;
		while (cardColor == null || cardSymbol == null) {
			while (input == null) {
				input = console.nextLine().trim().toLowerCase();
				try {
					color = input.split(" ")[0];
					symbol = input.split(" ")[1];
				} catch (ArrayIndexOutOfBoundsException e) {
					System.out.println("Invalid Input");
					input = null;
				}
			}
			switch (color) {
				case "r":
				case "red":
					cardColor = Color.RED;
					break;
				case "y":
				case "yellow":
					cardColor = Color.YELLOW;
					break;
				case "g":
				case "green":
					cardColor = Color.GREEN;
					break;
				case "b":
				case "blue":
					cardColor = Color.BLUE;
					break;
				case "w":
				case "wild":
					cardColor = Color.WILD;
					break;
				default:
					cardColor = null;
					System.out.println("Invalid card color. Try again.");
					break;
			}
			switch (symbol) {
				case "0":
				case "zero":
					cardSymbol = Symbol.ZERO;
					break;
				case "1":
				case "one":
					cardSymbol = Symbol.ONE;
					break;
				case "2":
				case "two":
					cardSymbol = Symbol.TWO;
					break;
				case "3":
				case "three":
					cardSymbol = Symbol.THREE;
					break;
				case "4":
				case "four":
					cardSymbol = Symbol.FOUR;
					break;
				case "5":
				case "five":
					cardSymbol = Symbol.FIVE;
					break;
				case "6":
				case "six":
					cardSymbol = Symbol.SIX;
					break;
				case "7":
				case "seven":
					cardSymbol = Symbol.SEVEN;
					break;
				case "8":
				case "eight":
					cardSymbol = Symbol.EIGHT;
					break;
				case "9":
				case "nine":
					cardSymbol = Symbol.NINE;
					break;
				case "skip":
					cardSymbol = Symbol.SKIP;
					break;
				case "reverse":
					cardSymbol = Symbol.REVERSE;
					break;
				default:
					if (input.contains("draw_two")) {
						cardSymbol = Symbol.DRAW_TWO;
					} else if (input.contains("draw_four")) {
						cardSymbol = Symbol.WILD_DRAW_FOUR;
					} else if (cardColor == Color.WILD) {
						cardSymbol = Symbol.WILD;
					} else {
						cardSymbol = null;
						System.out.println("Invalid card symbol. Try again.");
					}
					break;
			}
		}
		return new Card(cardColor, cardSymbol);
	}
	
	/**
	 * Get whether the user typed uno
	 * 
	 * @return whether the user typed uno
	 */
	@Override
	public String getUno() {
		String in = console.nextLine();
		if (in.toLowerCase().contains("uno")) {
			return "uno";
		}
		return in;
		
	}
	
	/**
	 * Get the IP of the server the user wants to connect to
	 * 
	 * @return the IP address of the server
	 */
	@Override
	public String getHostIP() {
		String input = null;
		while (input == null) {
			input = console.nextLine().trim().toLowerCase();
			if (input.contains(":") && input.contains(".")) {
				return input;
			} else {
				input = null;
				System.out.println("Invalid server address. Try again.");
			}
		}
		return null;
	}
	
	/**
	 * returns whether the user wants to be the server
	 * 
	 * @return true if hosting a server, false if not
	 */
	@Override
	public boolean isServer() {
		String input = null;
		while (input == null) {
			input = console.nextLine().trim().toLowerCase();
			if (input.equals("y") || input.equals("yes")) {
				return true;
			} else if (input.equals("n") || input.equals("no")) {
				return false;
			} else {
				input = null;
				System.out.println("Invalid input. Try again.");
			}
		}
		return false;
	}
	
	/**
	 * @return true or false
	 */
	@Override
	public boolean getTrueFalse() {
		String input = null;
		while (input == null) {
			input = console.nextLine().trim().toLowerCase();
			if (input.equals("y") || input.equals("yes")) {
				return true;
			} else if (input.equals("n") || input.equals("no")) {
				return false;
			} else {
				input = null;
				System.out.println("Invalid input. Try again.");
			}
		}
		return false;
	}
	
	@Override
	public Color getDesiredColor() {
		String input = null;
		Color cardColor = null;
		while (input == null) {
			input = console.nextLine().trim().toLowerCase();
			switch (input) {
				case "red":
					cardColor = Color.RED;
					break;
				case "yellow":
					cardColor = Color.YELLOW;
					break;
				case "green":
					cardColor = Color.GREEN;
					break;
				case "blue":
					cardColor = Color.BLUE;
					break;
				default:
					System.out.println("Invalid Color.");
					input = null;
					break;
			}
		}
		
		return cardColor;
	}
	
}
