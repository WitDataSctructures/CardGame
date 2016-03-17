package uno;

import java.util.Random;

import adt.Card;
import adt.Card.Color;
import adt.Card.Symbol;
import adt.Pile;

public class Deck extends Pile {
	
	public static Deck generateCards() {
		Deck pile = new Deck();
		for (Symbol symbol : Symbol.values()) {
			for (Color color : Color.values()) {
				// there are only four of each wild type and zeros
				boolean isZero = symbol == Symbol.ZERO && color != Color.WILD;
				boolean isWild = symbol == Symbol.WILD && color == Color.WILD;
				if (isZero || isWild) {
					pile.addToTop(new Card(color, symbol));
				} else if (symbol != Symbol.ZERO && symbol != Symbol.WILD && color != Color.WILD) {
					// Two of every other type of card
					pile.addToTop(new Card(color, symbol));
					pile.addToTop(new Card(color, symbol));
				}
			}
		}
		return pile;
	}
	
	/**
	 * removes cards from the top of the deck and places them in a random spot in the deck.
	 */
	public void shuffle(int numberOfShuffles) {
		Random random = new Random();
		Card currentCard, placeAfter, placeBefore;
		for (int i = this.getSize() * numberOfShuffles; i > 0; i--) {
			currentCard = this.drawFromTop();
			placeAfter = this.peekFromTop();
			for (int j = random.nextInt(this.getSize()); j > 0; j--) {
				placeAfter = placeAfter.getNext();
			}
			placeBefore = placeAfter.getNext();
			placeAfter.setNext(currentCard);
			currentCard.setNext(placeBefore);
		}
		
	}
}
