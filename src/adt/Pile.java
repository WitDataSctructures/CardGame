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
	 * 
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
		}
		return card;
	}

	public Card remove(Card card) {
		Card current = top;
		while (current != null) {
			if (current.compareTo(card) == 0) {
				if (current.getPrevious() != null) {
					current.getPrevious().setNext(current.getNext());
				}
				if (current.getNext() != null) {
					current.getNext().setPrevious(current.getPrevious());
				}
				return current;
			}
			current = current.getNext();
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
		String s = "{ ";
		Card current = top;
		while (current != null) {
			s += current;
			if (current.getNext() == null) {
				break;
			}
			s += ", ";
			current = current.getNext();
		}
		return s + " }";
	}
}
