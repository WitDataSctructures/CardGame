/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * The client packet is a serializable object that contains any and all data a client would need to play the game.
 * Sent back and fourth between the server instance and client instances
 * 
 * @author Jake Mathews
 * @author Ford Polia
 * @author Darrien Kennedy
 */

package uno.server;

import java.io.Serializable;

import uno.Deck;
import uno.PlayerStats;

public class ClientPacket implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 765363840836307681L;

	private String message;
	private Deck pickupPile;
	private Deck discardPile;
	private PlayerStats stats;
	private boolean discardActive;
	private Object other;

	public ClientPacket(String message, Deck pickupPile, Deck discardPile, PlayerStats stats, boolean discardActive) {
		this.message = message;
		this.pickupPile = pickupPile;
		this.discardPile = discardPile;
		this.stats = stats;
		this.discardActive = discardActive;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the pickupPile
	 */
	public Deck getPickupPile() {
		return pickupPile;
	}

	/**
	 * @param pickupPile
	 *            the pickupPile to set
	 */
	public void setPickupPile(Deck pickupPile) {
		this.pickupPile = pickupPile;
	}

	/**
	 * @return the discardPile
	 */
	public Deck getDiscardPile() {
		return discardPile;
	}

	/**
	 * @param discardPile
	 *            the discardPile to set
	 */
	public void setDiscardPile(Deck discardPile) {
		this.discardPile = discardPile;
	}

	/**
	 * @return the stats
	 */
	public PlayerStats getStats() {
		return stats;
	}

	/**
	 * @param stats
	 *            the stats to set
	 */
	public void setStats(PlayerStats stats) {
		this.stats = stats;
	}

	/**
	 * @return the other
	 */
	public Object getOther() {
		return other;
	}

	/**
	 * @param other
	 *            the other to set
	 */
	public void setOther(Object other) {
		this.other = other;
	}

	public boolean isDiscardActive() {
		return discardActive;
	}

	public void setDiscardActive(boolean discardActive) {
		this.discardActive = discardActive;
	}
}
