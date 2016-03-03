package uno;

public class Pile {
	private Card top;
	private Card bottom;

	public Pile() {

	}

	public void addToTop(Card card) {
		if (top == null) {
			top = card;
			top.setPrevious(bottom);
		} else {
			top.setPrevious(card);
			card.setNext(top);
			card.setPrevious(bottom);
			top = card;
		}
	}

	public void addToBottom(Card card) {
		if (bottom == null) {
			bottom = card;
			bottom.setNext(top);
		} else {
			bottom.setNext(card);
			card.setNext(top);
			card.setPrevious(bottom);
			bottom = card;
		}
	}

	public Card drawFromTop() {
		return null;
	}

	public Card drawFromBottom() {
		return null;
	}

	public Card peekFromTop() {
		return null;
	}

	public Card peekFromBottom() {
		return null;
	}

	public int getSize() {
		return 0;
	}
}
