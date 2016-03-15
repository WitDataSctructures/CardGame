package uno;

import adt.Card;

public abstract class InputManager {

	/**
	 * Get the card the user wants to place on the discard pile
	 * @return Card from user's hand
	 */
	public abstract Card getCard();


	/**
	 * Get whether the user pressed the Uno button
	 * @return whether the user pressed the Uno button
	 */
	public abstract boolean getUno();
}
