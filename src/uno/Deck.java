/**
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 *
 * A class for the deck of cards used within a card game.
 * 
 * @author Jake Mathews
 * @author Ford Polia
 * @author Darrien Kennedy
 */

package uno;

import java.io.Serializable;
import java.util.Random;

import adt.Card;
import adt.Card.Color;
import adt.Card.Symbol;
import adt.Pile;

public class Deck extends Pile implements Serializable {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -7864976685987399548L;

	/**
	 * Generate all of the cards to be found within a deck of uno cards and return this deck.
	 * 
	 * @return Deck a deck consisting of all legal uno cards.
	 */
	public static Deck generateCards() {
		Deck pile = new Deck();
		for (Symbol symbol : Symbol.values()) {
			for (Color color : Color.values()) {
				// there are only four of each wild type and zeros
				boolean isZero = symbol == Symbol.ZERO && color != Color.WILD;
				boolean isWild = symbol == Symbol.WILD && color == Color.WILD;
				boolean isDrawFour = symbol == Symbol.WILD_DRAW_FOUR && color == Color.WILD;
				if (isZero || isWild || isDrawFour) {
					pile.addToTop(new Card(color, symbol));
				} else if (symbol != Symbol.ZERO && symbol != Symbol.WILD && symbol != Symbol.WILD_DRAW_FOUR && color != Color.WILD) {
					// Two of every other type of card
					pile.addToTop(new Card(color, symbol));
					pile.addToTop(new Card(color, symbol));
				}
			}
		}
		return pile;
	}

	/**
	 * Removes cards from the top of the deck and places them in a random spot in the deck.
	 * 
	 * @param numberOfShuffles
	 *            the number of times for the deck to be shuffled.
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
