package adt;

import adt.Card.Color;
import adt.Card.Symbol;

public class Tester {

	public static void main(String[] args) {
		System.out.println("Add to top");
		Pile pile = new Pile();
		print(pile);
		pile.addToTop(new Card(Color.BLUE, Symbol.EIGHT));
		print(pile);
		pile.addToTop(new Card(Color.RED, Symbol.EIGHT));
		print(pile);
		pile.addToTop(new Card(Color.YELLOW, Symbol.EIGHT));
		print(pile);
		pile.addToTop(new Card(Color.YELLOW, Symbol.ONE));
		print(pile);

		System.out.println("Remove from top");
		print(pile);
		System.out.println("Drawing = " + pile.drawFromTop());
		print(pile);
		System.out.println("Drawing = " + pile.drawFromTop());
		print(pile);
		System.out.println("Drawing = " + pile.drawFromTop());
		print(pile);
		System.out.println("Drawing = " + pile.drawFromTop());
		print(pile);
		System.out.println("Drawing = " + pile.drawFromTop());
		print(pile);

	}

	private static void print(Pile pile) {
		System.out.println(pile.toString() + "			Size = " + pile.getSize());
	}

}
