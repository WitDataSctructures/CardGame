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

	/**
	 * Returns a string representation of the hand
	 * 
	 * @return
	 */
	public String getHandString() {
		return playerHand.toString();
	}
}
