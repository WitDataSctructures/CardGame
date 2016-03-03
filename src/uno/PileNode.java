package uno;

public class PileNode {
	private PileNode next;
	private PileNode previous;
	private Card card;

	public PileNode(PileNode next, PileNode previous, Card card) {
		this.next = next;
		this.previous = previous;
		this.card = card;
	}
}
