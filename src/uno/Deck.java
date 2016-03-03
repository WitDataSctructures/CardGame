package uno;

import adt.Card;
import adt.Pile;

import java.util.Random;

public class Deck extends Pile {
	
	final static int NUM_OF_SHUFFLES = 2;

	/**
	 * removes cards from the top of the deck and places them in
	 * a random spot in the deck.
	 */
	public void shuffle() {
		Random random = new Random();
		Card currentCard, placeAfter, placeBefore;
		for (int i = this.getSize() * 2; i > 0; i--){
			currentCard = this.drawFromTop();
			placeAfter = this.peekFromTop();
			for (int j = random.nextInt(this.getSize()); j > 0; j--){
				placeAfter = placeAfter.getNext();
			}
			placeBefore = placeAfter.getNext();
			placeAfter.setNext(currentCard);
			currentCard.setNext(placeBefore);
		}
		
		
	}
}
