/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * A tester for the Pile class.
 * 
 * 
 * @author Jake Mathews
 * @author Ford Polia
 * @author Darrien Kennedy
 */

package adt;

import adt.Card.Color;
import adt.Card.Symbol;

/**
 * Makes sure the Pile class and all of its methods are functioning as planned.
 */
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
