/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * A class for the hands of cards to be used in a game of uno. 
 * Inherits from the Pile class.
 * 
 *  
 * @author jakem
 * @author piolaf
 * @author darrienk
 */

package uno;

import java.util.ArrayList;

import adt.Card;
import adt.Pile;

public class Hand extends Pile {
	
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 5690236836679402432L;
	
	/**
	 * Default constructor for Hands.
	 */
	public Hand() {
	}
	
	/**
	 * Returns the integer for the number of cards in a player's hand.
	 * @return int
	 * 			number of cards in a hand
	 */
	public int getHandSize() {
		return this.getSize();
	}
	
	/**
	 * Returns an arraylist consisting of copies of the cards from the hand.
	 * @return ArrayList<Card>
	 * 			an arraylist copy of the cards within a hand.
	 */
	public ArrayList<Card> getHand() {
		Card temp;
		temp = this.peekFromTop();
		ArrayList<Card> cardsInHand = new ArrayList<Card>();
		for (int i = 0; i < this.getSize(); i++) {
			cardsInHand.add(temp);
			temp = temp.getNext();
		}
		return cardsInHand;
	}
	
	/**
	 * Searches the hand and checks to see if the card provided is present in the hand.
	 * @param cardToLookFor
	 * @return boolean
	 * 			whether or not the card parameterized is actually in the hand.
	 */
	public boolean isCardInHand(Card cardToLookFor) {
		ArrayList<Card> playerHand = getHand();
		for (int i = 0; i < playerHand.size(); i++) {
			if (cardToLookFor.isColorSame(playerHand.get(i)) && cardToLookFor.isSymbolSame(playerHand.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Adds a card to the hand.
	 * @param cardToBeAdded
	 */
	public void addToHand(Card cardToBeAdded) {
		// System.out.println("Adding " + cardToBeAdded);
		this.addToTop(cardToBeAdded);
	}
	
	
	/**
	 * Removes a card from the hand only if it is in the hand
	 * @param cardToBeRemoved
	 * @return boolean
	 * 			whether or not the removal was sucessful.
	 */
	public boolean removeFromHand(Card cardToBeRemoved) {
		return !remove(cardToBeRemoved).equals(null);
	}
	
	//COPY OF GETHANDSIZE
	/**
	 * Returns the integer for the number of cards in a player's hand.
	 * @return int
	 * 			number of cards in a hand
	 */
	public int getCardCount() {
		return this.getSize();
	}
}
