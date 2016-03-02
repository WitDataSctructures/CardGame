package uno;

public class Card {
	
	public static enum Color {
		RED, YELLOW, GREEN, BLUE, WILD
	}
	
	public static enum Symbol {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT,
		NINE, SKIP, REVERSE, DRAW_TWO, WILD, WILD_DRAW_FOUR
	}
	
	private Color cardColor;
	private Symbol cardSymbol;
	
	public Card (Color c, Symbol s){
		cardColor = c;
		cardSymbol = s;
	}
	
	public Color getColor(){
		return cardColor;
	}
	
	public Symbol getSymbol(){
		return cardSymbol;
	}
	
	public String toString(){
		String output;
		switch (cardColor){
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
			output = "Wild Card";
			break;
		default:
			output = "";
			break;	
		}
		switch (cardSymbol){
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
}
