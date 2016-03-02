package uno;

public class Card implements Comparable<Card>{
	
	public static enum Color {
		RED(0), YELLOW(1), GREEN(2), BLUE(3), WILD(4);
		
		private final int comparisonValue;
		
		private Color(int val){
			comparisonValue = val;
		}
		
		public int getComparisonValue(){
			return comparisonValue;
		}
	};
	
	public static enum Symbol {
		ZERO(0), ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8),
		NINE(9), SKIP(10), REVERSE(11), DRAW_TWO(12), WILD(13), WILD_DRAW_FOUR(14);
		
		private final int comparisonValue;
		
		private Symbol(int val){
			comparisonValue = val;
		}
		
		public int getComparisonValue(){
			return comparisonValue;
		}
	};
	
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
	
	@Override
	public int compareTo(Card otherCard){
		if (this.cardColor == otherCard.getColor() &&
				this.cardSymbol == otherCard.getSymbol()){
			return 0;
		}else if (this.cardSymbol.getComparisonValue() < otherCard.cardSymbol.getComparisonValue() ||
				this.cardColor.getComparisonValue() < otherCard.cardColor.getComparisonValue()){
			return -1;
		}else{
			return 1;
		}
	}
}
