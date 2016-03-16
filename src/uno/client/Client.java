package uno.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import uno.ConsoleInput;

public class Client {
	public static void main(String[] args) {
		if (args.length == 1) {
			Client client = new Client();
		} else {
			System.out.println("Invalid usage. Proper usage: uno [player_name]");
		}
	}

	Socket server;
	ConsoleInput in;

	public Client() {
		in = new ConsoleInput();
		String serverIP = in.getHostIP();
		String address = serverIP.split(":")[0];
		int port = Integer.parseInt(serverIP.split(":")[1]);
		try {
			server = new Socket(address, port);
			String test = "test";
			DataOutputStream out = new DataOutputStream(server.getOutputStream());
			out.writeUTF(test);
			System.out.println(new DataInputStream(server.getInputStream()).readUTF());
		} catch (UnknownHostException e) {
			System.out.println("Could not find server @ " + serverIP);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Invalid port number");
			e.printStackTrace();
		}
	}
}
