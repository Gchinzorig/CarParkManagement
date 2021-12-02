import java.io.*;
import java.net.*;

public class EntranceClient2 {
	public static void main(String[] args) throws IOException 
	{
		Socket EntranceClient2Socket = null;
        PrintWriter out = null;
        BufferedReader in = null, stdln = new BufferedReader(new InputStreamReader(System.in));
        int EntranceClient2SocketNumber = 4545;
        String CarParkServerName = "localhost", EntranceClient2ID = "EntranceClient2", FromUser, FromServer;
        boolean listening = true;
        
        try
        {
        	EntranceClient2Socket = new Socket(CarParkServerName,EntranceClient2SocketNumber);
        	out = new PrintWriter(EntranceClient2Socket.getOutputStream(),true);
        	in = new BufferedReader(new InputStreamReader(EntranceClient2Socket.getInputStream()));
        }catch(UnknownHostException e)
        {
        	System.err.println("Don't know about host: localhost ");
            System.exit(1);
        }catch(IOException e)
        {
        	System.err.println("Couldn't get I/O for the connection to: "+ EntranceClient2SocketNumber);
            System.exit(1);
        }
        System.out.println("Entrance Client 2 is up and running.");
        System.out.println("Please input 'approaching' to attempt to enter a car.");
        
        while(listening)
        {
        	FromUser = stdln.readLine();
        	if(FromUser != null)
        	{
        		System.out.println(EntranceClient2ID + " sending " + FromUser + " to ActionServer");
                out.println(FromUser);
        	}
        	
        	FromServer = in.readLine();
        	System.out.println(EntranceClient2ID + " received " + FromServer + " from CarParkServer");
        }
        
        
        
	}

}
