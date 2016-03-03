package adt;

public class Card implements Comparable<Card> {
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
	public Card(Color c, Symbol s, Card next, Card previous) {
		if (s == Symbol.WILD || s == Symbol.WILD_DRAW_FOUR) {
			cardColor = Color.WILD;
		} else {
			cardColor = c;
		}
		cardSymbol = s;
		this.next = next;
		this.previous = previous;
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
		String output;
		switch (cardColor) {
			case RED:
				output = "Red";
				break;
			case YELLOW:
				output = "Yellow";
				break;
			case GREEN:
				output = "Green";
				break;
			case BLUE:
				output = "Blue";
				break;
			case WILD:
				// will be overwritten later
				output = "Wild Card";
				break;
			default:
				output = "";
				break;
		}
		switch (cardSymbol) {
			case ZERO:
				output += " Zero";
				break;
			case ONE:
				output += " One";
				break;
			case TWO:
				output += " Two";
				break;
			case THREE:
				output += " Three";
				break;
			case FOUR:
				output += " Four";
				break;
			case FIVE:
				output += " Five";
				break;
			case SIX:
				output += " Six";
				break;
			case SEVEN:
				output += " Seven";
				break;
			case EIGHT:
				output += " Eight";
				break;
			case NINE:
				output += " Nine";
				break;
			case SKIP:
				output += " Skip";
				break;
			case REVERSE:
				output += " Reverse";
				break;
			case DRAW_TWO:
				output += " Draw Two";
				break;
			case WILD:
				output = "Wild Card";
				break;
			case WILD_DRAW_FOUR:
				output = "Wild Draw Four";
				break;
			default:
				break;
		}
		return output;
	}

	/**
	 * Compares the card to another card returns 0 if the cards are the same returns -1 if the first card has a lower symbol than the second returns 1 otherwise If the symbols are the same, but the colors are not, either -1 or 1 is returned RED, YELLOW, GREEN, BLUE, WILD
	 */
	@Override
	public int compareTo(Card otherCard) {
		if (this.cardColor == otherCard.getColor() && this.cardSymbol == otherCard.getSymbol()) {
			return 0;
		} else if (this.cardSymbol.getComparisonValue() < otherCard.cardSymbol.getComparisonValue() || this.cardColor.getComparisonValue() < otherCard.cardColor.getComparisonValue()) {
			return -1;
		} else {
			return 1;
		}
	}
}
