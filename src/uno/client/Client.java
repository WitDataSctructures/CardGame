package uno.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;

import adt.Card;
import uno.ConsoleInput;
import uno.Deck;
import uno.InputManager;
import uno.Player;
import uno.PlayerStats;
import uno.server.ClientPacket;
import uno.server.Server;
import uno_GUI.UnoTableGUI;

public class Client {
	public static void main(String[] args) {
		if (args.length == 1) {
			new Client(args[0]);
		} else {
			System.out.println("Invalid usage. Proper usage: uno [player_name]");
		}
	}

	Socket server;
	InputManager input; // TODO: Change to InputManager
	Player player;
	// String playerName;

	public Client(String name) {
		// Get input
		input = new UnoTableGUI();
		player = new Player(name);
		while (server == null){
			System.out.print("Please enter server address: ");
			// Connect to a server
			String serverIP = ":";
			try{
				serverIP = input.getHostIP();
			} catch (NullPointerException e){
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
					Deck drawPile = packet.getPickupPile();
					switch (message) {
						case "dish_card":
							// Server is trying to dish us out one card
							// System.out.println("Drawn " + packet.getPickupPile().peekFromTop());
							player.addToHand(drawPile.drawFromTop());
							packet.setMessage("success");
							packet.setPickupPile(drawPile);
							out.writeObject(packet); // Return the deck with one less card and new message
							break;
						case "draw_two":
							//User draws two cards and passes their turn							
							// System.out.println("Drawn " + packet.getPickupPile().peekFromTop());
							player.addToHand(drawPile.drawFromTop());
							player.addToHand(drawPile.drawFromTop());
							packet.setMessage("success");
							packet.setPickupPile(drawPile);
							out.writeObject(packet);
							break;
						case "draw_four":
							//User draws four cards and passes their turn
							// System.out.println("Drawn " + packet.getPickupPile().peekFromTop());
							player.addToHand(drawPile.drawFromTop());
							player.addToHand(drawPile.drawFromTop());
							player.addToHand(drawPile.drawFromTop());
							player.addToHand(drawPile.drawFromTop());
							packet.setMessage("success");
							packet.setPickupPile(drawPile);
							out.writeObject(packet);
							break;
						case "turn":
							Deck discardPile = packet.getDiscardPile();
							if(packet.isDiscardActive()) {
								if(discardPile.peekFromTop().getSymbol().equals(Card.Symbol.DRAW_TWO)) {
									player.addToHand(drawPile.drawFromTop());
									player.addToHand(drawPile.drawFromTop());
									packet.setMessage("success");
									packet.setPickupPile(drawPile);
									out.writeObject(packet);
									break;
								} else if(discardPile.peekFromTop().getSymbol().equals(Card.Symbol.WILD_DRAW_FOUR)) {
									player.addToHand(drawPile.drawFromTop());
									player.addToHand(drawPile.drawFromTop());
									player.addToHand(drawPile.drawFromTop());
									player.addToHand(drawPile.drawFromTop());
									packet.setMessage("success");
									packet.setPickupPile(drawPile);
									out.writeObject(packet);
									break;
								}
								
								((UnoTableGUI) input).setPacket(packet);
							}
							
							break;
						default:
							packet.setMessage("no_command");
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
				System.out.println("Your cards:\n\t" + player.getHandString());
			}
		}
	}
}
