package uno.server;

import java.net.Socket;

public class ClientThread extends Thread {
	
	Socket client;
	
	public ClientThread(Socket client) {
		this.client = client;
	}
	
	@Override
	public void run() {
	
	}
}
