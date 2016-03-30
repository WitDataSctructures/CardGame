/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * A class for the players to be found in a card game.
 * 
 * 
 * @author Jake Mathews
 * @author Ford Polia
 * @author Darrien Kennedy
 */

package uno;

import adt.Card;

public class Player {

	private String name;
	private Hand playerHand;

	/**
	 * Constructs a player with the name provided by the arguments and gives them a new hand.
	 * 
	 * @param name
	 */
	public Player(String name) {
		this.name = name;
		playerHand = new Hand();
	}

	/**
	 * Retrieves the name of a given player.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * When provided a card input, adds this card to the player's hand.
	 * 
	 * @param card
	 */
	public void addToHand(Card card) {
		card.setNext(null);
		card.setPrevious(null);
		playerHand.addToHand(card);
	}

	/**
	 * Removes a given card from the player's hand, first checks to see if the card exists in the player's hand and then removes it. Otherwise, returns false.
	 * 
	 * @param card
	 * @return boolean whether the removal was sucessful or not
	 */
	public boolean removeFromHand(Card card) {
		for (Card c : playerHand.getHand()) {
			if ((c.isColorSame(card) && c.isSymbolSame(card))) {
				return playerHand.removeFromHand(c);
			}
		}
		return false;
	}

	/**
	 * Returns a string representation of the hand
	 */
	public String getHandString() {
		return playerHand.toString();
	}

	/**
	 * Returns an integer for the number of cards in the player's hand.
	 */
	public int getCardCount() {
		return playerHand.getCardCount();
	}
}
