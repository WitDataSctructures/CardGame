package uno.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import adt.Card;
import adt.Card.Color;
import uno.ConsoleInput;
import uno.Deck;
import uno.InputManager;
import uno.Player;
import uno.PlayerStats;
import uno.server.ClientPacket;
import uno.server.Server;

public class Client {
	public static void main(String[] args) {
		if (args.length == 1) {
			new Client(args[0]);
		} else if (args.length == 2) {
			new Client(args[0], args[1]);
		} else {
			System.out.println("Invalid usage. Proper usage: uno [player_name]");
		}
	}
	
	Socket server;
	InputManager input; // TODO: Change to InputManager
	Player player;
	// String playerName;
	private ClientPacket packet;
	
	public Client(String name) {
		// Get input
		// input = new UnoTableGUI();
		input = new ConsoleInput();
		player = new Player(name);
		while (server == null) {
			System.out.print("Please enter server address: ");
			// Connect to a server
			String serverIP = ":";
			try {
				serverIP = input.getHostIP();
			} catch (NullPointerException e) {
				System.exit(0);
			}
			String address = serverIP.split(":")[0];
			int port = Integer.parseInt(serverIP.split(":")[1]);
			try {
				server = new Socket(address, port);
			} catch (UnknownHostException | ConnectException e) {
				System.out.println("Could not find server @ " + serverIP);
				e.printStackTrace();
				server = null;
			} catch (IOException e) {
				System.out.println("Invalid port number");
				e.printStackTrace();
				server = null;
			}
		}
		
		if (server != null) { // If everything is all set
			try {
				run();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Client(String name, String ipAddress) {
		// Get input
		// input = new UnoTableGUI();
		input = new ConsoleInput();
		player = new Player(name);
		while (server == null) {
			System.out.print("Please enter server address: ");
			// Connect to a server
			String serverIP = ipAddress;
			String address = serverIP.split(":")[0];
			int port = Integer.parseInt(serverIP.split(":")[1]);
			try {
				server = new Socket(address, port);
			} catch (UnknownHostException | ConnectException e) {
				System.out.println("Could not find server @ " + serverIP);
				e.printStackTrace();
				server = null;
			} catch (IOException e) {
				System.out.println("Invalid port number");
				e.printStackTrace();
				server = null;
			}
		}
		
		if (server != null) { // If everything is all set
			try {
				run();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void run() throws IOException, ClassNotFoundException {
		ObjectInputStream in = new ObjectInputStream(server.getInputStream());
		ObjectOutputStream out = new ObjectOutputStream(server.getOutputStream());
		
		System.out.println("Attempting to connect to " + server.getRemoteSocketAddress());
		out.writeObject(new ClientPacket("connect" + Server.REGEX + player.getName(), null, null, null, false));
		ClientPacket result = (ClientPacket) in.readObject();
		if (result.getMessage().equals("success")) {
			System.out.println("Connected to server @ " + server.getRemoteSocketAddress());
		} else {
			System.out.println(result);
			server = null;
		}
		
		while (true) {
			// System.out.println(in);
			if (in.available() > 0 || true) {
				try {
					ClientPacket packet = (ClientPacket) in.readObject();
					String message = packet.getMessage();
					System.out.println("Message = [" + message + "]");
					Deck pickupPile = packet.getPickupPile();
					Deck discardPile = packet.getDiscardPile();
					PlayerStats stats = packet.getStats();
					switch (message) {
						case "dish_card":
							// Server is trying to dish us out one card
							// System.out.println("Drawn " + packet.getPickupPile().peekFromTop());
							player.addToHand(pickupPile.drawFromTop());
							packet.setMessage("success");
							packet.setPickupPile(pickupPile);
							out.writeObject(packet); // Return the deck with one less card and new message
							break;
						case "turn":
							if (packet.isDiscardActive()) {
								if (discardPile.peekFromTop().getSymbol().equals(Card.Symbol.DRAW_TWO)) {
									System.out.println("Draw_Two card recieved, drawing two cards");
									player.addToHand(pickupPile.drawFromTop());
									player.addToHand(pickupPile.drawFromTop());
									packet.setMessage("success");
									packet.setPickupPile(pickupPile);
									packet.setDiscardActive(false);
									out.writeObject(packet);
									break;
								} else if (discardPile.peekFromTop().getSymbol().equals(Card.Symbol.WILD_DRAW_FOUR)) {
									System.out.println("Wild_Draw_Four Recieved, drawing four cards");
									player.addToHand(pickupPile.drawFromTop());
									player.addToHand(pickupPile.drawFromTop());
									player.addToHand(pickupPile.drawFromTop());
									player.addToHand(pickupPile.drawFromTop());
									packet.setMessage("success");
									packet.setPickupPile(pickupPile);
									packet.setDiscardActive(false);
									out.writeObject(packet);
									break;
								}
							} else {
								System.out.println("The card on the discard pile is a " + discardPile.peekFromTop());
								System.out.println("Would you like to draw a card? (Y/N)");
								if (input.getTrueFalse()) {
									System.out.println("You picked up a " + pickupPile.peekFromTop());
									player.addToHand(pickupPile.drawFromTop());
								} else {
									System.out.println("What card would you like to play?");
									Card card = null;
									while (card == null) {
										card = input.getCard();
										System.out.println("In card = " + card);
										if (card != null && (card.isColorSame(discardPile.peekFromTop()) || card.isSymbolSame(discardPile.peekFromTop())) && player.removeFromHand(card)) {
											System.out.println("You placed a " + card.toString());
											discardPile.addToTop(card);
											// player.removeFromHand(card);
											if (card.getSymbol().equals(Card.Symbol.DRAW_TWO) || card.getSymbol().equals(Card.Symbol.WILD_DRAW_FOUR) || card.getSymbol().equals(Card.Symbol.WILD) || card.getSymbol().equals(Card.Symbol.SKIP)) {
												packet.setDiscardActive(true);
												System.out.println("isActive = " + packet.isDiscardActive());
											}
										} else {
											System.out.println("You cannot play that card. Try again");
											card = null;
										}
									}
									
									if (packet.isDiscardActive() && (discardPile.peekFromTop().getSymbol().equals(Card.Symbol.WILD) || discardPile.peekFromTop().getSymbol().equals(Card.Symbol.WILD_DRAW_FOUR))) {
										System.out.println("What color would you like you wild to be?");
										Color color = input.getDesiredColor();
										Card dis = discardPile.drawFromTop();
										dis.changeColor(color);
										discardPile.addToTop(dis);
										if (dis.getSymbol().equals(Card.Symbol.WILD)) {
											packet.setDiscardActive(false);
										}
									}
								}
							}
							packet.setDiscardPile(discardPile);
							packet.setPickupPile(pickupPile);
							packet.setMessage("success");
							System.out.println("Thanks. Send a message to others? (Y/N)");
							if (input.getTrueFalse()) {
								System.out.println("What is it?");
								packet.setMessage(input.getUno());
							}
							System.out.println("Player Card Count = " + player.getCardCount());
							if (stats.setPlayersCardCount(player.getName(), player.getCardCount())) {
								packet.setStats(stats);
							}
							out.reset();
							out.writeObject(packet);
							break;
						case "update":
							// String actPlayer = "";
							// String[] playNames = new String[packet.getStats().getPlayers().length];
							// int[] cardCount = new int[packet.getStats().getAllCardCount().length];
							//
							// for (int i = 0; i < playNames.length; i++) {
							// playNames = packet.getStats().getPlayers();
							// actPlayer = packet.getStats().getActivePlayer();
							// cardCount[i] = packet.getStats().getAllCardCount()[i];
							// }
							// PlayerStats pStats = new PlayerStats(playNames, cardCount, actPlayer);
							//
							packet.setMessage("success");
							// stats.setPlayersCardCount(stats.getActivePlayer(), player.getCardCount());
							// packet.setStats(stats);
							out.reset();
							out.writeObject(packet);
							System.out.println("Player: " + player.getName());
							System.out.println("Your cards:\n" + player.getHandString());
							break;
						default:
							packet.setMessage("no_command");
							out.reset();
							out.writeObject(packet);
							System.out.println("Unkown packet message");
							break;
					}
				} catch (IOException e) {
					System.out.println("Failed to fetch data from server");
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					System.out.println("Packet recieved was not a packet");
					e.printStackTrace();
				}
			}
		}
		
	}
	
	// TODO: Delete after UnoTableGUI is gone
	public ClientPacket getPacket() {
		return packet;
	}
}
