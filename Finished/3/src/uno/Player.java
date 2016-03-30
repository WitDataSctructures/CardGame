package uno;

import adt.Card;

public class Player {
	
	private String name;
	private Hand playerHand;
	
	public Player(String name) {
		this.name = name;
		playerHand = new Hand();
	}
	
	public String getName() {
		return name;
	}
	
	public void addToHand(Card card) {
		card.setNext(null);
		card.setPrevious(null);
		playerHand.addToHand(card);
	}
	
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
	 * 
	 * @return
	 */
	public String getHandString() {
		return playerHand.toString();
	}
	
	public int getCardCount() {
		return playerHand.getCardCount();
	}
}
