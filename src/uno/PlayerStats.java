/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * Stores statistics for the players found within a card game such as their names,
 * the number of cards in their hand and whose turn it is currently.
 * 
 * @author Jake Mathews
 * @author Ford Polia
 * @author Darrien Kennedy
 */

package uno;

import java.io.Serializable;

public class PlayerStats implements Serializable {
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -8436295657678335081L;

	private String[] playerNames;
	private int[] cardCount;
	private String activePlayer;

	public PlayerStats(String[] playerNames, int[] cardCount, String activePlayer) {
		this.playerNames = playerNames;
		this.cardCount = cardCount;
		this.activePlayer = activePlayer;
	}

	/**
	 * Returns a string array of all the players names
	 * 
	 * @return <b>String[]</b> Array of player names
	 */
	public String[] getPlayers() {
		return playerNames;
	}

	/**
	 * Returns and array of the card count of each player in the same order <i>getPlayer()</i> returns the names
	 * 
	 * @return <b>int[]</b> Array of player card count
	 */
	public int[] getAllCardCount() {
		return cardCount;
	}

	/**
	 * Returns the card count of a specific player. Returns -1 if player does not exist.
	 * 
	 * @param playerName
	 *            String
	 * @return <b>Integer</b> -1 if player does not exist
	 */
	public int getPlayersCardCount(String playerName) {
		for (int i = 0; i < playerNames.length; i++) {
			if (playerNames[i].equals(playerName)) {
				return cardCount[i];
			}
		}
		return -1;
	}

	/**
	 * Inserts the player card count into an array of integers.
	 * 
	 * @param playerName
	 * @param count
	 * @return boolean, whether or not process was sucessful
	 */
	public boolean setPlayersCardCount(String playerName, int count) {
		for (int i = 0; i < playerNames.length; i++) {
			if (playerNames[i].equals(playerName)) {
				cardCount[i] = count;
				return true;
			}
		}
		return false;
	}

	/**
	 * Returns the name of the player who's turn it is. Also known as the 'active player'
	 * 
	 * @return <b>String</b> The name of the active player
	 */
	public String getActivePlayer() {
		return activePlayer;
	}

	/**
	 * Sets the names of the players within the game.
	 * 
	 * @param playerNames
	 *            the playerNames to set
	 */
	public void setPlayerNames(String[] playerNames) {
		this.playerNames = playerNames;
	}

	/**
	 * Sets the number of cards in a given player's hand.
	 * 
	 * @param cardCount
	 *            the cardCount to set
	 */
	public void setCardCount(int[] cardCount) {
		this.cardCount = cardCount;
	}

	/**
	 * Keeps track of which player's turn it is currently.
	 * 
	 * @param activePlayer
	 *            the activePlayer to set
	 */
	public void setActivePlayer(String activePlayer) {
		this.activePlayer = activePlayer;
	}
}
