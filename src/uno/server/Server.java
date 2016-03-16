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
			Server server = new Server();
			while (true) {
				server.listen();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private final int PORT = 9090;

	private String name;
	private ServerSocket serverSocket;

	public enum USER {
		SERVER, USER
	}

	public Server() throws IOException {
		name = InetAddress.getLocalHost().getHostName();
		serverSocket = new ServerSocket(PORT, 50, InetAddress.getLocalHost());
	}

	public String getServerAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}

	public int getServerPort() throws UnknownHostException {
		return serverSocket.getLocalPort();
	}

	public void listen() {
		try {
			System.out.println("Listening on: " + getServerAddress() + ":" + getServerPort());
			Socket client = serverSocket.accept();
			DataInputStream in = new DataInputStream(client.getInputStream());
			System.out.println(in.readUTF());
			DataOutputStream out = new DataOutputStream(client.getOutputStream());
			out.writeUTF("Thank you for connecting to " + serverSocket.getLocalSocketAddress() + "\nGoodbye!");
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
