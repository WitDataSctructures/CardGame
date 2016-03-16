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
	
	public Server() throws IOException {
		name = InetAddress.getLocalHost().getHostName();
		serverSocket = new ServerSocket(PORT);
	}
	
	public String getServerAddress() throws UnknownHostException {
		return serverSocket.getInetAddress().getHostAddress();
	}
}
