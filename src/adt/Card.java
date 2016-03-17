package adt;

import java.io.Serializable;

public class Card implements Serializable, Comparable<Card> {
	/**
	 * generated UID
	 */
	private static final long serialVersionUID = -9003507646233425521L;

	/**
	 * Enum for Color of an UNO card with values for comparison
	 * 
	 * @author poliaf
	 *
	 */
	public static enum Color {
		RED(0), YELLOW(1), GREEN(2), BLUE(3), WILD(4);

		private final int comparisonValue;

		private Color(int val) {
			comparisonValue = val;
		}

		public int getComparisonValue() {
			return comparisonValue;
		}
	};

	/**
	 * Enum for the symbol on an UNO card with values for comparison
	 * 
	 * @author poliaf
	 *
	 */
	public static enum Symbol {
		ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), SKIP(10), REVERSE(11), DRAW_TWO(12), WILD(13), WILD_DRAW_FOUR(14);

		private final int comparisonValue;

		private Symbol(int val) {
			comparisonValue = val;
		}

		public int getComparisonValue() {
			return comparisonValue;
		}
	};

	private Color cardColor;
	private Symbol cardSymbol;
	private Card previous;
	private Card next;

	/**
	 * 
	 * @param Color
	 *            of the card
	 * @param Symbol
	 *            on the card
	 */
	public Card(Color c, Symbol s) {
		cardColor = c;
		cardSymbol = s;
	}

	/**
	 * @return Color of the card
	 */
	public Color getColor() {
		return cardColor;
	}

	/**
	 * @return Symbol of the card
	 */
	public Symbol getSymbol() {
		return cardSymbol;
	}

	/**
	 * @param previousCard
	 */
	public void setPrevious(Card previousCard) {
		previous = previousCard;
	}

	/**
	 * @param nextCard
	 */
	public void setNext(Card nextCard) {
		next = nextCard;
	}

	/**
	 * @return previous card
	 */
	public Card getPrevious() {
		return previous;
	}

	/**
	 * @return next card
	 */
	public Card getNext() {
		return next;
	}

	/**
	 * @param otherCard
	 * @return true if cards have the same color, false otherwise
	 */
	public boolean isColorSame(Card otherCard) {
		if (this.cardColor == otherCard.getColor()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param otherCard
	 * @return true if cards have the same symbol, false otherwise
	 */
	public boolean isSymbolSame(Card otherCard) {
		if (this.cardSymbol == otherCard.getSymbol()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns a string stating the color and symbol on the card
	 */
	@Override
	public String toString() {
		if (cardColor == Color.WILD){
			return (cardSymbol.toString().toLowerCase());
		}
		return (cardColor + " " + cardSymbol).toLowerCase();
	}

	/**
	 * Compares the card to another card returns 0 if the cards are the same returns -1 if the first card has a lower symbol than the second returns 1 otherwise If the symbols are the same, but the colors are not, either -1 or 1 is returned RED, YELLOW, GREEN, BLUE, WILD
	 */
	@Override
	public int compareTo(Card otherCard) {
		if (this.cardColor == otherCard.getColor() || this.cardSymbol == otherCard.getSymbol()) {
			return 0;
		} else if (this.cardSymbol.getComparisonValue() < otherCard.cardSymbol.getComparisonValue() || this.cardColor.getComparisonValue() < otherCard.cardColor.getComparisonValue()) {
			return -1;
		} else {
			return 1;
		}
	}
}
