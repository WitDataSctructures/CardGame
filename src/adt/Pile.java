/**
 * Pile ADT to represent a pile of cards for the game of uno
 * 
 * @author Jake Mathews (mathewsj2@wit.edu)
 *
*/

package adt;

public class Pile {
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
					current.getPrevious().setNext(card);
				}
				if (current.getNext() != null) {
					current.getNext().setPrevious(card);
				}
				card.setNext(current.getNext());
				break;
			}
		}
		return current;
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
