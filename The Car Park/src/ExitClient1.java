import java.io.*;
import java.net.*;

public class ExitClient1 
{
	public static void main(String[] args) throws IOException 
	{
		Socket ExitClient1Socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        int ExitClient1SocketNumber = 4545;
        String CarParkServerName = "localhost";
        String ExitClient1ID = "ExitClient1";
        boolean listening = true;
        
        try
        {
        	ExitClient1Socket = new Socket(CarParkServerName,ExitClient1SocketNumber);
        	out = new PrintWriter(ExitClient1Socket.getOutputStream(),true);
        	in = new BufferedReader(new InputStreamReader(ExitClient1Socket.getInputStream()));
        }catch(UnknownHostException e)
        {
        	System.err.println("Don't know about host: localhost ");
            System.exit(1);
        }catch(IOException e)
        {
        	System.err.println("Couldn't get I/O for the connection to: "+ ExitClient1SocketNumber);
            System.exit(1);
        }
        System.out.println("Exit Client 1 is up and running...");
        System.out.println("Please enter 'exit' to remove a car.");
        
        BufferedReader stdln = new BufferedReader(new InputStreamReader(System.in));
        String FromUser, FromServer;
        
        while(listening)
        {
        	FromUser = stdln.readLine();
        	if(FromUser != null)
        	{
        		System.out.println(ExitClient1ID + " sending " + FromUser + " to CarParkServer.");
                out.println(ExitClient1ID +","+FromUser);
        	}
        	
        	FromServer = in.readLine();
        	System.out.println(ExitClient1ID + " received " + FromServer + " from CarParkServer.");
        }
	}

}
