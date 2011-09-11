package fIRCBot;

/*imports voor de sockets etc.*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IRCSocket
{
	public Socket s;
	private String sHost;
	private int iPort;
	@SuppressWarnings("unused")
	private Thread t;
	private BufferedReader socketReader;
	private BufferedWriter socketWriter;
	@SuppressWarnings("unused")
	private InputThread _inputThread;
	@SuppressWarnings("unused")
	private OutputThread _outputThread;
	fIRCBot _bot;

	
	public IRCSocket( fIRCBot bot, String host, int port )
	{
		_bot = bot;
		sHost = host;
		iPort = port;
		System.out.println( "IRCSocket Constructed.");
		try
		{
			s = new Socket( sHost, iPort );
			socketReader = new BufferedReader
		    (
		    		new InputStreamReader( s.getInputStream( ) )
		    );
			socketWriter = new BufferedWriter
		    (
		    		new OutputStreamWriter( s.getOutputStream( ) )
		    );
		}
		catch( UnknownHostException e )
		{
			e.printStackTrace( );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
		System.out.println( "IRCSocket Connected.");
		System.out.println( "Constructing InputThread..." );
		/*
		 * New thread for socketReader
		 */
		_inputThread = new InputThread( _bot, socketReader );
		/*
		 * new thread for socketWriter
		 * not used yet
		 */
		//_outputThread = new OutputThread( _bot, socketWriter );
	}

	public boolean Write( String data )
	{
		try
		{
			socketWriter.write( data + "\n\r" );
			System.out.println( ">> " + data );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
			return false;
		}
		return true;
	}
	public boolean Flush( )
	{
		try
		{
			socketWriter.flush( );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
			return false;
		}
		return true;
	}

}
