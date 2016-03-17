package uno;

import adt.Card;

public class Player {

	private String name;
	private Hand playerHand;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addToHand(Card card) {
		playerHand.addToHand(card);
	}

	public Hand getHand() {
		return playerHand;
	}
}
