package uno;

import adt.Card;
import adt.Card.Color;

public interface InputManager {

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
	
	/**
	 * Get the IP of the server the user wants to connect to
	 * @return the IP address of the server
	 */
	public abstract String getHostIP();
	
	/**
	 * returns whether the user wants to be the server
	 * @return true if hosting a server, false if not
	 */
	public abstract boolean isServer();
	
	/**
	 * @return true or false
	 */
	public abstract boolean getTrueFalse();
	
	public abstract Color getDesiredColor();
}
