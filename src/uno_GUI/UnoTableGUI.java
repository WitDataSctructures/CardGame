package uno_GUI;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import uno.*;
import adt.*;
import adt.Card;
import sun.awt.resources.awt;
import uno.server.*;

public class UnoTableGUI extends JFrame implements InputManager, ActionListener{
	
	
	
	
	
	
	private static final long serialVersionUID = 1L;

	
	//Variables
	Container unoGame;
	
	JLabel emptyLabel = new JLabel();
	
	JButton draw = new JButton("Draw a Card");
	JButton callUno = new JButton("Call Uno!");
	JButton select = new JButton("Play Card");
		
	int playerCount = 2;
	String[] playerNames = {"Player1", "Player2", " ", " ", " ", " ", " ", " ", " ", " "};
	JList<String> players = new JList<String>(playerNames);
	
	DefaultListModel<Card> listModel;
	JList<Card> cardsInHand;
	int selectIndex = -1;
	
	JPanel discard = new JPanel();
	JLabel discardText = new JLabel();
	Card discardCard = null;
	Pile discardPile;
	
	
	String discardString = "";
	
	
	
	
	
	
	
	
	
	
	
	
	
	public UnoTableGUI(){
		
		super("Lab 04 - UNO Card Game");
		
		unoGame = getContentPane();
		
		setSize(1000, 400);
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null);
		setFocusable(true);
		addWindowListener(new WindowDestroyer());
			
		
		//Set bounds for Buttons
		draw.setBounds(270, 125, 150, 200);
		callUno.setBounds(30, 250, 120, 60);
		select.setBounds(700, 250, 120, 60);
		
		//Button Listeners
		draw.addActionListener(this);
		callUno.addActionListener(this);
		select.addActionListener(this);
		
		
		
		getPlayerInfo();
		
		//Card List setup
		listModel = new DefaultListModel<Card>();
		cardsInHand = new JList<Card>(listModel);
		cardsInHand.setBounds(850,0,145,310);
		cardsInHand.setBackground(Color.LIGHT_GRAY);
		
		discard.setBounds(500, 125, 150, 200);
		discard.setBackground(Color.WHITE);
		discard.add(discardText);
		
		
		unoGame.add(cardsInHand);
		unoGame.add(players);
		unoGame.add(draw);
		unoGame.add(callUno);
		unoGame.add(select);
		unoGame.add(discard);
		unoGame.add(emptyLabel);
		
	}
	
	public static void main(String[] args){
		new UnoTableGUI();
	}
	
	public void getPlayerInfo(){
		
		//player.name + ":  " + player.getSize + " cards";
		playerNames[0] = "Name of P1: " + "3 cards";
		playerNames[1] = "Player2";
		players.setBounds(0,0,150,200);
		players.setBackground(Color.LIGHT_GRAY);
		
		switch(playerCount){
		case 3:
			playerNames[2] = "Player3";
			break;
		case 4:
			playerNames[2] = "Player3";
			playerNames[3] = "Player4";
			break;
		case 5:
			playerNames[2] = "Player3";
			playerNames[3] = "Player4";
			playerNames[4] = "Player5";
			break;
		case 6:
			playerNames[2] = "Player3";
			playerNames[3] = "Player4";
			playerNames[4] = "Player5";
			playerNames[5] = "Player6";
			break;
		case 7:
			playerNames[2] = "Player3";
			playerNames[3] = "Player4";
			playerNames[4] = "Player5";
			playerNames[5] = "Player6";
			playerNames[6] = "Player7";
			break;
		case 8:
			playerNames[2] = "Player3";
			playerNames[3] = "Player4";
			playerNames[4] = "Player5";
			playerNames[5] = "Player6";
			playerNames[6] = "Player7";
			playerNames[7] = "Player8";
			break;
		case 9:
			playerNames[2] = "Player3";
			playerNames[3] = "Player4";
			playerNames[4] = "Player5";
			playerNames[5] = "Player6";
			playerNames[6] = "Player7";
			playerNames[7] = "Player8";
			playerNames[8] = "Player9";
			break;
		case 10:
			playerNames[2] = "Player3";
			playerNames[3] = "Player4";
			playerNames[4] = "Player5";
			playerNames[5] = "Player6";
			playerNames[6] = "Player7";
			playerNames[7] = "Player8";
			playerNames[8] = "Player9";
			playerNames[9] = "Player10";
			break;
		default:
			break;	
		}
	}

	

	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == draw){
			
			//Draw a card from the top of the draw pile for that player
			Card drawnCard = new Card(Card.Color.BLUE, Card.Symbol.NINE);
			listModel.addElement(drawnCard);
			
			//For Testing...
			
			Card drawnCard2 = new Card(Card.Color.BLUE, Card.Symbol.EIGHT);
			Card drawnCard3 = new Card(Card.Color.RED, Card.Symbol.FOUR);
			listModel.addElement(drawnCard2);
			listModel.addElement(drawnCard3);
			
			Card drawnCard4 = new Card(Card.Color.WILD, Card.Symbol.WILD);
			listModel.addElement(drawnCard4);
			
			//
			
		}
		if(event.getSource() == callUno){
			//Call/Check Uno event
			
			//whenever there is a new turn set a boolean to false.
			
		}
		if(event.getSource() == select){
			
			//Select card from list and remove it, and add it to the discard pile iff it is compatable with the
			//top card of the discard pile.
			selectIndex = cardsInHand.getSelectedIndex();
			if(selectIndex != -1 && (discardCard == null || listModel.getElementAt(selectIndex).compareTo(discardCard) == 1 || 
					listModel.getElementAt(selectIndex).compareTo(discardCard) == 0)){
				
				discardCard = listModel.getElementAt(selectIndex);
				discardString = listModel.getElementAt(selectIndex).toString();
				listModel.remove(selectIndex);
				selectIndex = -1;
				discardText.setText(discardString);	
				
				//discardPile.addToTop(listModel.getElementAt(selectIndex+1));
				//End turn
			}
		}
		
	}

	@Override
	public Card getCard() {
		// TODO Auto-generated method stub
		return discardCard;
	}

	@Override
	public boolean getUno() {
		// TODO Auto-generated method stub
		//Create a boolean for when the uno button is pressed
		return false;
	}
	//Returns string which user types into the IP textbox
	@Override
	public String getHostIP() {
		String input = null;
		while (input == null) {
			input = JOptionPane.showInputDialog("Enter the IP address and port of the server:", "192.168.0.0:9090").trim();
			if (input.contains(":") && input.contains(".")) {
				return input;
			} else {
				input = null;
				System.out.println("Invalid server address. Try again.");
			}
		}
		return null;
	}

	@Override
	public boolean isServer() {
		return false;
	}

	@Override
	public boolean getTrueFalse() {
		return false;
	}
	//Return Color for what the user wants their wild card to change the color to.
	@Override
	public adt.Card.Color getDesiredColor() {
		Object[] options = { "Red", "Yellow", "Green", "Blue" };
		int color = JOptionPane.showOptionDialog(null, "Click OK to continue", "Warning",
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
		null, options, options[0]);
		
		switch (color) {
		case 0:
			return Card.Color.RED;
		case 1:
			return Card.Color.YELLOW;
		case 2:
			return Card.Color.GREEN;
		case 3:
			return Card.Color.BLUE;
		default:
			return null;
		}
	}
	
	
}
