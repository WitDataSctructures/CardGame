package uno.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Server {

	private final int PORT = 9090;

	private String name;
	private ServerSocket serverSocket;

	public enum USER {
		SERVER, USER
	}

	public Server(String name) {
		try {
			name = InetAddress.getLocalHost().getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		try {
			serverSocket = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getServerAddress() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostAddress();
	}
}
