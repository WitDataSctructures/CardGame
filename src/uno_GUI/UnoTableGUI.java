package uno_GUI;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;

import uno.*;
import uno.client.Client;
import adt.*;
import adt.Card;
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
	String[] playerNames = {" ", " ", "", "", "", "", "", "", "", ""};
	JList<String> players = new JList<String>(playerNames);
	
	DefaultListModel<Card> listModel;
	JList<Card> cardsInHand;
	int selectIndex = -1;
	
	JPanel discard = new JPanel();
	JLabel discardText = new JLabel();
	Pile discardPile = new Pile();
	
	
	String discardString = "";


	private ClientPacket packet;


	private Client client;
	
	boolean unoCalled = false;
	
	
	
	
	
	
	
	
	
	
	
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
		
		players.setBounds(0,0,150,200);
		players.setBackground(Color.LIGHT_GRAY);
		
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
		
	@Override
	public void actionPerformed(ActionEvent event) {
		
		if(event.getSource() == draw){
			
			//Draw a card from the top of the draw pile for that player
			Card drawnCard = packet.getPickupPile().drawFromTop();
			listModel.addElement(drawnCard);
			
		}
		if(event.getSource() == callUno){
			//Call/Check Uno event
			unoCalled = true;
			//whenever there is a new turn set a boolean to false.
			
		}
		if(event.getSource() == select){
			
			//Select card from list and remove it, and add it to the discard pile iff it is compatable with the
			//top card of the discard pile.
			selectIndex = cardsInHand.getSelectedIndex();
			if(selectIndex != -1 && (discardPile.peekFromTop() == null || listModel.getElementAt(selectIndex).compareTo(discardPile.peekFromTop()) == 1 || 
					listModel.getElementAt(selectIndex).compareTo(discardPile.peekFromTop()) == 0)){
				
				discardString = listModel.getElementAt(selectIndex).toString();
				discardPile.addToTop(listModel.getElementAt(selectIndex));
				listModel.remove(selectIndex);
				selectIndex = -1;
				discardText.setText(discardString);	
				
				
				//End turn
			}
		}
		
	}

	@Override
	public Card getCard() {
		// TODO Auto-generated method stub
		return discardPile.peekFromTop();
	}

	@Override
	public boolean getUno() {
		// TODO Auto-generated method stub
		//Create a boolean for when the uno button is pressed
		return unoCalled;
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
	
	//Start of a new turn
	public void setClient(Client client) {
		unoCalled = false;
		this.client = client;
		packet = client.getPacket();
		PlayerStats stats = packet.getStats();
		for(int i = 0; i < stats.getPlayers().length;i++) {
			playerNames[i] = stats.getPlayers()[i];
		}
		players.setListData(playerNames);
	}
	
	
}
