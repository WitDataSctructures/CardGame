/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * Pile ADT to represent a pile of cards for the game of uno
 * 
 * 
 * @author jakem
 * @author piolaf
 * @author darrienk
 */


package adt;

import java.io.Serializable;

public class Pile implements Serializable {
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 717021340591068645L;
	private Card top;
	
	/**
	 * Takes a given card and places it on top of a pile.
	 * 
	 * @param card
	 * 			card to be placed on top of a pile
	 */
	public void addToTop(Card card) {
		if (top != null) {
			card.setNext(top);
			top.setPrevious(card);
		}
		top = card;
	}
	
	/**
	 * Takes the top card from the pile and returns it.
	 * 
	 * @return card
	 * 			the card from the top of the pile.
	 */
	public Card drawFromTop() {
		Card card = top;
		if (top != null) {
			top = top.getNext();
			top.setPrevious(card.getPrevious());
			card.setNext(null);
			card.setPrevious(null);
		}
		return card;
	}
	
	/**
	 * Attempts to remove a card from a pile.
	 * 
	 * @param card
	 * 			the card to be removed
	 * @return card
	 * 			the card removed from the pile.
	 */
	public Card remove(Card card) {
		Card current = top;
		while (current != null) {
			if (current.isColorSame(card) && current.isSymbolSame(card)) {
				if (current.getPrevious() != null) {
					current.getPrevious().setNext(current.getNext());
				}
				if (current.getNext() != null) {
					current.getNext().setPrevious(current.getPrevious());
				}
				if (current.equals(top)) {
					top = current.getNext();
				}
				current.setNext(null);
				current.setPrevious(null);
				return current;
			} else {
				current = current.getNext();
			}
		}
		return null;
	}
	
	/**
	 * Returns a copy of the top card from a pile of cards.
	 * 
	 * @return Card
	 * 			A copy of the card on the top of the pile.
	 */
	public Card peekFromTop() {
		return top;
	}
	
	/**
	 * Retrieves a card count for the number of cards in a given pile.
	 * 
	 * @return size
	 * 			the number of cards in a given pile
	 */
	public int getSize() {
		int size = 0;
		Card current = top;
		while (current != null) {
			size++;
			current = current.getNext();
		}
		return size;
	}
	
	/**
	 * Creates a string and populates it with each of the cards within the pile.
	 * 
	 * @return s
	 * 			A string consisting of all of the cards within the pile.
	 */
	@Override
	public String toString() {
		String s = "";
		Card current = top;
		while (current != null) {
			s += "\t" + current + "\n";
			if (current.getNext() == null) {
				break;
			}
			current = current.getNext();
		}
		return s;
	}
}
