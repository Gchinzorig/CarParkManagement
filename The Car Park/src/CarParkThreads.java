import java.net.*;
import java.io.*;
public class CarParkThreads extends Thread {

	private Socket CarParkSocket = null;
	private CarParkState mySharedCarParkStateObject;
	private String myCarParkServerThreadName;
	private int mySharedVariable;

	public CarParkThreads(Socket socket, String CarParkServerThreadName, CarParkState SharedObject )
	{	
		super(CarParkServerThreadName);
		this.CarParkSocket = socket;
		mySharedCarParkStateObject = SharedObject;
		myCarParkServerThreadName = CarParkServerThreadName;
	}


	public void run() {
		try {
			System.out.println(myCarParkServerThreadName + " initialising");
			PrintWriter out = new PrintWriter(CarParkSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(CarParkSocket.getInputStream()));
			String inputLine, outputLine;

			while ((inputLine = in.readLine()) != null) {
				// Get a lock first
				try { 
					mySharedCarParkStateObject.acquireLock();
					outputLine = mySharedCarParkStateObject.processInput(myCarParkServerThreadName, inputLine);
					out.println(outputLine);
					mySharedCarParkStateObject.releaseLock();
				} 
				catch(InterruptedException e) {
					System.err.println("Failed to get lock when reading:"+e);
				}
			}

			out.close();
			in.close();
			CarParkSocket.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
