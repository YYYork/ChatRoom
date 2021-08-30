import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server {
	private ServerSocket socket;
	private HashSet<Socket> allSocket;
	private int port;

	public Server(int port) {
		this.port = port;
		try {
			socket = new ServerSocket(port);
			allSocket = new HashSet<>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void startService() throws IOException {
		System.out.println("服务器开启...");
		while (true) {
			Socket client = socket.accept();
			allSocket.add(client);
			Thread t = new ServerThread(client);
			t.start();
		}
	}

	private class ServerThread extends Thread {
		Socket client;

		public ServerThread(Socket client) {
			// TODO Auto-generated constructor stub
			this.client = client;
		}

		@Override
		public void run() {
			sendMessageToAll("有用户进入聊天室。");
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(client.getInputStream()));
				while (true) {
					String message = br.readLine();
					if(message.contains("%EXIT%")) {//发送 %EXIT%:名字
						sendMessageToAll(message.split(":")[1]+"用户退出了聊天室。");
						allSocket.remove(client);
						client.close();
						break;
					}
					sendMessageToAll(message);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		private void sendMessageToAll(String message) {
			BufferedWriter bw = null;
			try {
				Console.console.appendMessage(message+"\n");
				for (Socket socket : allSocket) {
					bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
					bw.write(message);
					bw.newLine();
					bw.flush();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
