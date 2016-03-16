package uno;

import java.util.Scanner;

import adt.Card;
import adt.Card.Color;
import adt.Card.Symbol;

public class ConsoleInput extends InputManager {
	
	private Scanner console;
	
	public ConsoleInput(){
		console = new Scanner(System.in);
	}
	
	/**
	 * Get the card the user wants to place on the discard pile
	 * @return Card from user's hand
	 */
	@Override
	public Card getCard() {
		String input = null, color, symbol;
		Color cardColor = null;
		Symbol cardSymbol = null;
		while (input == null) {
			input = console.nextLine().trim().toLowerCase();
			color = input.split(" ")[0];
			symbol = input.split(" ")[1];
			switch(color){
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
				cardColor = Color.WILD;
				break;
			}
			switch(symbol){
			case "0":
				cardSymbol = Symbol.ZERO;
				break;
			case "zero":
				cardSymbol = Symbol.ZERO;
				break;
			case "1":
				cardSymbol = Symbol.ONE;
				break;
			case "one":
				cardSymbol = Symbol.ONE;
				break;
			case "2":
				cardSymbol = Symbol.TWO;
				break;
			case "two":
				cardSymbol = Symbol.TWO;
				break;
			case "3":
				cardSymbol = Symbol.THREE;
				break;
			case "three":
				cardSymbol = Symbol.THREE;
				break;
			case "4":
				cardSymbol = Symbol.FOUR;
				break;
			case "four":
				cardSymbol = Symbol.FOUR;
				break;
			case "5":
				cardSymbol = Symbol.FIVE;
				break;
			case "five":
				cardSymbol = Symbol.FIVE;
				break;
			case "6":
				cardSymbol = Symbol.SIX;
				break;
			case "six":
				cardSymbol = Symbol.SIX;
				break;
			case "7":
				cardSymbol = Symbol.SEVEN;
				break;
			case "seven":
				cardSymbol = Symbol.SEVEN;
				break;
			case "8":
				cardSymbol = Symbol.EIGHT;
				break;
			case "eight":
				cardSymbol = Symbol.EIGHT;
				break;
			case "9":
				cardSymbol = Symbol.NINE;
				break;
			case "skip":
				cardSymbol = Symbol.SKIP;
				break;
			case "reverse":
				cardSymbol = Symbol.REVERSE;
				break;
			default:
				if (input.contains("draw")){
					if (cardColor == Color.WILD){
						cardSymbol = Symbol.WILD_DRAW_FOUR;
					}else{
						cardSymbol = Symbol.DRAW_TWO;
					}
				} else if (cardColor == Color.WILD){
					cardSymbol = Symbol.WILD;
				}
			}
		}
		return new Card(cardColor, cardSymbol);
	}
	
	/**
	 * Get whether the user typed uno
	 * @return whether the user typed uno
	 */
	@Override
	public boolean getUno() {
		// TODO Auto-generated method stub
		return false;
	}
	
	/**
	 * Get the IP of the server the user wants to connect to
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
				System.out.println("Invalid server adress. Try again.");
			}
		}
		return null;
	}
	
	/**
	 * returns whether the user wants to be the server
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
				return true;
			} else {
				input = null;
				System.out.println("Invalid input. Try again.");
			}
		}
		return false;
	}

}
