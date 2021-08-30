import java.io.IOException;

public class ThreadHelpServer extends Thread{
	int port;
	public ThreadHelpServer(int port) {
		// TODO Auto-generated constructor stub
		this.port = port;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			new Server(port).startService();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
