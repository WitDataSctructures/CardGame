package uno;

public class Pile {
	private Card top;
	private Card bottom;

	public Pile() {

	}

	public void addToTop(Card card) {
		if (top == null) {
			top = card;
		}
		card.setNext(card);
	}

	public void addToBottom(Card card) {
		if (bottom == null) {
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
}
