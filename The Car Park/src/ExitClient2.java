import java.io.*;
import java.net.*;

public class ExitClient2 {
	public static void main(String[] args) throws IOException 
	{
		Socket ExitClient2Socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int ExitClient2SocketNumber = 4545;
        String CarParkServerName = "localhost";
        String ExitClient2ID = "ExitClient2";
        boolean listening = true;
        
        try
        {
        	ExitClient2Socket = new Socket(CarParkServerName,ExitClient2SocketNumber);
        	out = new PrintWriter(ExitClient2Socket.getOutputStream(),true);
        	in = new BufferedReader(new InputStreamReader(ExitClient2Socket.getInputStream()));
        }catch(UnknownHostException e)
        {
        	System.err.println("Don't know about host: localhost ");
            System.exit(1);
        }catch(IOException e)
        {
        	System.err.println("Couldn't get I/O for the connection to: "+ ExitClient2SocketNumber);
            System.exit(1);
        }
        System.out.println("Exit Client 2 is up and running...");
        System.out.println("Please enter 'exit' to remove a car.");
        
        BufferedReader stdln = new BufferedReader(new InputStreamReader(System.in));
        String FromUser, FromServer;
        
        while(listening)
        {
        	FromUser = stdln.readLine();
        	if(FromUser != null)
        	{
        		System.out.println(ExitClient2ID + " sending " + FromUser + " to CarParkServer");
                out.println(ExitClient2ID+ ","+FromUser);
        	}
        	
        	FromServer = in.readLine();
        	System.out.println(ExitClient2ID + " received " + FromServer + " from CarParkServer");
        }
        
        
	}
}
