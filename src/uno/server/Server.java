package uno.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

	public static void main(String[] args) {
		try {
			Server server;
			if (args.length == 1) {
				server = new Server(Integer.parseInt(args[0]));
			} else {
				server = new Server();
			}
			while (true) {
				server.listen();
			}
		} catch (IOException e) {
			System.out.println("Server crashed! :(");
			e.printStackTrace();
		}
	}

	private final int DEFAULT_PORT = 9090;

	private String name;
	private ServerSocket serverSocket;

	public enum USER {
		SERVER, USER
	}

	public Server() throws IOException {
		name = InetAddress.getLocalHost().getHostName();
		serverSocket = new ServerSocket(DEFAULT_PORT, 50, InetAddress.getLocalHost());
	}

	public Server(int port) throws IOException {
		name = InetAddress.getLocalHost().getHostName();
		serverSocket = new ServerSocket(port, 50, InetAddress.getLocalHost());
	}

	public String getServerAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	public int getServerPort() throws UnknownHostException {
		return serverSocket.getLocalPort();
	}

	public void listen() {
		try {
			System.out.println("\nListening on: " + getServerAddress() + ":" + getServerPort());
			Socket client = serverSocket.accept();
			System.out.println("Client connected: " + client.getLocalAddress());
			DataInputStream in = new DataInputStream(client.getInputStream());
			System.out.println(in.readUTF());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			out.writeUTF("Thank you for connecting to " + serverSocket.getLocalSocketAddress() + "\nGoodbye!");
			client.close();
		} catch (IOException e) {
			System.out.println("Connection Closed");
			e.printStackTrace();

		}
	}
}
