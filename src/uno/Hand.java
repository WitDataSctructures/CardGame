package uno;

import java.util.ArrayList;

import adt.Card;
import adt.Pile;

public class Hand extends Pile {
	
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 5690236836679402432L;
	
	// Default Constructor
	public Hand() {
	}
	
	// Returns an integer of how many cards currently in the player's hand.
	public int getHandSize() {
		return this.getSize();
	}
	
	// Returns the playerHand.
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
	
	// Checks if a Card parameter exists in the player's hand.
	public boolean isCardInHand(Card cardToLookFor) {
		ArrayList<Card> playerHand = getHand();
		for (int i = 0; i < playerHand.size(); i++) {
			if (cardToLookFor.isColorSame(playerHand.get(i)) && cardToLookFor.isSymbolSame(playerHand.get(i))) {
				return true;
			}
		}
		return false;
	}
	
	// Add a Card parameter to the hand.
	public void addToHand(Card cardToBeAdded) {
		// System.out.println("Adding " + cardToBeAdded);
		this.addToTop(cardToBeAdded);
	}
	
	// If the Card exists in the hand, remove it, otherwise do nothing.
	public boolean removeFromHand(Card cardToBeRemoved) {
		if (isCardInHand(cardToBeRemoved)) {
			return !remove(cardToBeRemoved).equals(null);
		}
		return false;
	}
}
