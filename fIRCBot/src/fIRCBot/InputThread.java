package fIRCBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class InputThread implements Runnable
{
	Thread t;
	Socket s;
	BufferedReader sr;
	BufferedWriter sw;
	fIRCBot _bot;
	public InputThread( fIRCBot bot, BufferedReader SocketReader )
	{
		_bot = bot;
		sr = SocketReader;
		t = new Thread( this );
		t.start( );	
	}
	public void run( )
	{
		try
		{
			String currLine = null;
			while ((currLine = sr.readLine()) != null)
		    {
		    	System.out.println( "<< " + currLine );
				try
				{
					_bot.onRawLine( currLine );
				}
				catch ( Throwable t )
				{
					
				}
		    }
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
		
	}
}
