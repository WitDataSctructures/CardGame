package uno;

import java.util.Scanner;

import adt.Card;

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
		// TODO Auto-generated method stub
		return null;
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
