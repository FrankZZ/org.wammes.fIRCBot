package fIRCBot;

import java.io.BufferedReader;
import java.io.IOException;

public class InputThread implements Runnable
{
	private Thread t;
	private BufferedReader sr;
	private fIRCBot _bot;

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
					t.printStackTrace();
				}
		    }
		}
		catch ( IOException e )
		{
			e.printStackTrace();
		}
	}
}
