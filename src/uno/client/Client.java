package uno.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import uno.ConsoleInput;
import uno.server.Server;

public class Client {
	public static void main(String[] args) {
		if (args.length == 1) {
			Client client = new Client(args[0]);
		} else {
			System.out.println("Invalid usage. Proper usage: uno [player_name]");
		}
	}
	
	Socket server;
	ConsoleInput in;
	// String playerName;
	
	public Client(String name) {
		// Get console input
		in = new ConsoleInput();
		
		// Connect to a server
		String serverIP = in.getHostIP();
		String address = serverIP.split(":")[0];
		int port = Integer.parseInt(serverIP.split(":")[1]);
		try {
			server = new Socket(address, port);
			DataOutputStream out = new DataOutputStream(server.getOutputStream());
			out.writeUTF("N" + Server.REGEX + name);
			String result = new DataInputStream(server.getInputStream()).readUTF();
			if (result.equals("success")) {
				System.out.println("Connected to server @ " + serverIP);
			} else {
				System.out.println(result);
				server = null;
			}
		} catch (UnknownHostException e) {
			System.out.println("Could not find server @ " + serverIP);
			e.printStackTrace();
			server = null;
		} catch (IOException e) {
			System.out.println("Invalid port number");
			e.printStackTrace();
			server = null;
		}
		if (server != null) { // If everything is all set
			run();
		}
	}
	
	private void run() {
		while (true) {
		
		}
	}
}
