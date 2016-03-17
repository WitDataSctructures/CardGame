package uno.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientThread extends Thread {
	
	Socket client;
	Server server;
	boolean playersTurn = false;
	
	/**
	 * Middle man between each client and server
	 * 
	 * @param client,
	 *            server
	 * @throws IOException
	 */
	public ClientThread(Socket client, Server server) throws IOException {
		this.client = client;
		this.server = server;
		
		DataInputStream in = new DataInputStream(client.getInputStream());
		DataOutputStream out = new DataOutputStream(client.getOutputStream());
		String[] input = in.readUTF().split(Server.REGEX);
		if (input[0].equals("N") && server.addClient(input[1], client)) { // Client is asking to be added and was successfully added
			out.writeUTF("success");
		} else {
			out.writeUTF("Failed to joing game :(");
		}
		
	}
	
	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(client.getInputStream());
			
			if (playersTurn) {
				DataOutputStream out = new DataOutputStream(client.getOutputStream());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
