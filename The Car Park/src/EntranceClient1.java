import java.net.*;
import java.io.*;
public class EntranceClient1 
{
	public static void main(String[] args) throws IOException 
	{
		Socket EntranceClient1Socket = null;
        PrintWriter out = null;
        BufferedReader in = null, stdln = new BufferedReader(new InputStreamReader(System.in)) ;
        int EntranceClient1SocketNumber = 4545;
        String CarParkServerName = "localhost", EntranceClient1ID = "EntranceClient1",FromUser, FromServer;
        boolean listening = true;
        
        try
        {
        	EntranceClient1Socket = new Socket(CarParkServerName,EntranceClient1SocketNumber);
        	out = new PrintWriter(EntranceClient1Socket.getOutputStream(),true);
        	in = new BufferedReader(new InputStreamReader(EntranceClient1Socket.getInputStream()));
        }catch(UnknownHostException e)
        {
        	System.err.println("Don't know about host: localhost");
            System.exit(1);
        }catch(IOException e)
        {
        	System.err.println("Couldn't get I/O for the connection to: "+ EntranceClient1SocketNumber);
            System.exit(1);
        }
        System.out.println("Entrance Client 1 is up and running...");
        System.out.println("Please input 'approaching' to attempt to enter a car.");
        
        while(listening)
        {
        	FromUser = stdln.readLine();
        	if(FromUser != null)
        	{
        		System.out.println(EntranceClient1ID + " sending " + FromUser + " to CarParkServer.");
                out.println(EntranceClient1ID+","+FromUser);
        	}
        	
        	FromServer = in.readLine();
        	System.out.println(EntranceClient1ID + " received " + FromServer + " from CarParkServer.");
        }    
	}
}
