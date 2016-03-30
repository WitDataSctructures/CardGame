/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * To be used in the Graphical User Interface, to terminate the window given
 * certain conditions be met.
 * 
 * @author Jake Mathews
 * @author Ford Polia
 * @author Darrien Kennedy
 */

package uno_GUI;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * If you register an object of this class as a listener to any object of the class JFrame, the object will end the program and close the JFrame, if the user clicks the JFrame's close-window button.
 */
public class WindowDestroyer extends WindowAdapter {
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}
}
