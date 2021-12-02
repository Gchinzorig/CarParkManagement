import java.net.*;
import java.io.*;
public class CarParkServer {
	public static void main(String[] args) throws IOException
	{
		InetAddress ServerAddress = null;
		ServerSocket CarParkServerSocket = null;
		int ServerNumber = 4545, SharedVariable = 0;
		String ServerName = "CarParkServer";
		boolean listening = true;
		
		CarParkState carparkthread = new CarParkState(SharedVariable);
		
		try
		{
			ServerAddress = InetAddress.getLocalHost();
			CarParkServerSocket = new ServerSocket(4545);
		}catch(IOException e)
		{
			System.out.println("Cant Connect to: " + ServerName + " at " + ServerNumber);
			System.exit(-1);
		}
		System.out.println("Server is up and running..");
		
		while(listening)
		{
			new CarParkThreads(CarParkServerSocket.accept(),"CarParkServerThread1",carparkthread).start();
			new CarParkThreads(CarParkServerSocket.accept(),"CarParkServerThread2",carparkthread).start();
			new CarParkThreads(CarParkServerSocket.accept(),"CarParkServerThread3",carparkthread).start();
			new CarParkThreads(CarParkServerSocket.accept(),"CarParkServerThread4",carparkthread).start();
			new CarParkThreads(CarParkServerSocket.accept(),"CarParkServerThread5",carparkthread).start();
		}
		CarParkServerSocket.close();
	}
}
