package adt;

public class Pile {
	private Card top;
	private Card bottom;

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
		if (bottom != null) {
			s += ", " + bottom;
		}
		return s + " }";
	}
}
