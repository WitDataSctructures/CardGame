package uno.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread {
	
	Socket client;
	Server server;
	boolean playersTurn = false;
	
	/**
	 * Middle man between each client and server
	 * 
	 * @param client
	 *            Socket
	 * @param server
	 *            Server
	 * @throws IOException
	 */
	public ClientThread(Socket client, Server server) throws IOException {
		this.client = client;
		this.server = server;
		
		DataInputStream in = new DataInputStream(client.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		String input = in.readUTF();
		String[] data = input.split(Server.REGEX);
		if (data[0].equals("N") && server.addClient(data[1], this)) { // Client is asking to be added and was successfully added
			out.writeUTF("success");
		} else {
			out.writeUTF("Failed to joing game :(");
		}
		in.close();
		out.close();
	}
	
	public ClientPacket sendPacket(ClientPacket packet) {
		try {
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
			out.writeObject(packet);
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			ClientPacket returnPacket = (ClientPacket) in.readObject();
			return returnPacket;
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
