/****************************
 * Comp 2071
 * Lab 04 - Lists
 * Due: March 17th, 2016
 * Group #: 12
 * 
 * Holds the input and output streams for each player
 * Has a sendPacket() method for easy communication
 * 
 * @author Jake Mathews
 * @author Ford Polia
 * @author Darrien Kennedy
 */

package uno.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread {

	Socket client;
	Server server;
	boolean playersTurn = false;

	ObjectOutputStream out;
	ObjectInputStream in;

	/**
	 * Middle man between each client and server
	 * 
	 * @param client
	 *            Socket
	 * @param server
	 *            Server
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public ClientThread(Socket client, Server server) throws IOException, ClassNotFoundException {
		this.client = client;
		this.server = server;
		out = new ObjectOutputStream(client.getOutputStream());
		in = new ObjectInputStream(client.getInputStream());
		ClientPacket input = (ClientPacket) in.readObject();
		String[] data = input.getMessage().split(Server.REGEX);
		if (data[0].equals("connect") && server.addClient(data[1], this)) { // Client is asking to be added and was successfully added
			input.setMessage("success");
		} else {
			input.setMessage("failed");
		}
		out.writeObject(input);
	}

	public ClientPacket sendPacket(ClientPacket packet) {
		try {
			out.reset();
			out.writeObject(packet);
			return (ClientPacket) in.readObject();
		} catch (IOException e) {
			System.out.println("Failed to write/read packet");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			System.out.println("Packet returned was not a packet");
			e.printStackTrace();
		}
		return null;
	}
}
