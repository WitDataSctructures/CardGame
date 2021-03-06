/**
 * Pile ADT to represent a pile of cards for the game of uno
 * 
 * @author Jake Mathews (mathewsj2@wit.edu)
 *
*/

package adt;

import java.io.Serializable;

public class Pile implements Serializable {
	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 717021340591068645L;
	private Card top;
	
	public void addToTop(Card card) {
		if (top != null) {
			card.setNext(top);
			top.setPrevious(card);
		}
		top = card;
	}
	
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
	
	public Card peekFromTop() {
		return top;
	}
	
	public int getSize() {
		int size = 0;
		Card current = top;
		while (current != null) {
			size++;
			current = current.getNext();
		}
		return size;
	}
	
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
