import java.net.*;
import java.io.*;
public class CarParkState {

	private CarParkState mySharedObject;
	private int NumberOfCarsInPark, myQueue1, myQueue2, threadsWaiting = 0;
	private String myThreadName;
	private boolean accessing = false;
	boolean FreeSpace = false;


	CarParkState(int SharedVariable )
	{
		NumberOfCarsInPark = SharedVariable;
	}


	public synchronized void acquireLock() throws InterruptedException
	{
		Thread me = Thread.currentThread();
		System.out.println(me.getName()+ " is attemping to get a lock");
		threadsWaiting++;

		while(accessing)
		{
			System.out.println(me.getName() + " is waiting to get a lock");
			wait();
		}

		threadsWaiting--;
		accessing = true;
		System.out.println(me.getName() + " has gottent the lock");
	}

	public synchronized void releaseLock() throws InterruptedException
	{
		accessing = false;
		notifyAll();
		Thread me = Thread.currentThread();
		System.out.println(me.getName() + " released a lock");
	}


	public synchronized String processInput(String ThreadName, String Input)
	{
		//ArrayList<String> Communication = new ArrayList<String>();
		System.out.println(ThreadName + " recieved: " + Input);
		String theOutput= null;
		String[] inputarray = Input.split(",");
		Input = inputarray[1];
		String ClientID = inputarray[0];


		if(Input.equalsIgnoreCase("approaching"))
		{
			if(NumberOfCarsInPark ==5)
			{
				System.out.println("The Car Park is Full.");

				if(ClientID.equalsIgnoreCase("EntranceClient1"))
				{
					myQueue1++;
					theOutput = "Car Park is Full. One car has been added into Entrance client 1's queue. The queue contains " + myQueue1 + " car(s)";
					System.out.println("one car has been added in the Entrance Client 1's queue. The queue contains " + myQueue1 + "car(s).");
				}
				else if(ClientID.equalsIgnoreCase("EntranceClient2"))
				{
					myQueue2++;
					theOutput = "Car Park is Full. One car has been added into Entrance client 2's queue. The queue contains " + myQueue2 + " car(s)";
					System.out.println("one car has been added in the Entrance Client 2's queue. The queue contains " + myQueue2 + "car(s).");
				}

			}
			else if(NumberOfCarsInPark < 5)
			{
				if((ClientID.equalsIgnoreCase("EntranceClient1") && myQueue1 == 0) || (ClientID.equalsIgnoreCase("EntranceClient2") && myQueue2 == 0))
				{
					NumberOfCarsInPark++;
					theOutput = "One car has entered the parking space from " + ClientID + ". The Car Park contains " + NumberOfCarsInPark + "car(s).";
					System.out.println("One car has entered the parking space from " + ClientID + ". The Car Park contains " + NumberOfCarsInPark + "car(s).");
				}
				else if((ClientID.equalsIgnoreCase("EntranceClient1") && myQueue1 > 0))
				{
					NumberOfCarsInPark++;
					myQueue1--;
					theOutput = "One car has entered from Entrance 1's queue. There are " + myQueue1 + " car(s) remaining in the queue";
					System.out.println("One car has entered from Entrance 1's queue. There are " + myQueue1 + " car(s) remaining in the queue");
				}
				else if(ClientID.equalsIgnoreCase("EntranceClient2") && myQueue2 > 0)
				{
					NumberOfCarsInPark++;
					myQueue2--;
					theOutput = "One car has entered from Entrance 2's queue. There are " + myQueue2 + " car(s) remaining in the queue";
					System.out.println("One car has entered from Entrance 2's queue. There are " + myQueue2 + " car(s) remaining in the queue");
				}
			}
		}
		else if((ClientID.equalsIgnoreCase("ExitClient1") || ClientID.equalsIgnoreCase("ExitClient2")) && Input.equalsIgnoreCase("exit"))
		{
			if(NumberOfCarsInPark > 0)
			{
				NumberOfCarsInPark --;
				theOutput = "The car has left the parking, " + NumberOfCarsInPark +" is left in the parking space.";
				System.out.println("The car has left the parking, " + NumberOfCarsInPark +" is left in the parking space.");
			}
			else
			{
				theOutput = "There is no more car left to leave the parking space";
				System.out.println("There is no more car left to leave the parking space");
			}
		}
		else 
		{
			System.out.println("Invalid input from client");
			theOutput = "The server only understands 'approaching' or 'exit'";
		}
		return theOutput;
	}
}
