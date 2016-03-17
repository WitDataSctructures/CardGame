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
		String[] input = in.readUTF().split(Server.REGEX);
		if (input[0].equals("N") && server.addClient(input[1], this)) { // Client is asking to be added and was successfully added
			out.writeUTF("success");
		} else {
			out.writeUTF("Failed to joing game :(");
		}
		
	}
	
	public void run() {
		try {
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			
			if (playersTurn) {
				ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
