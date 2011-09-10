package fIRCBot;

/*imports voor de sockets etc.*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IRCSocket extends fIRCBot
{
	public Socket s;
	private String sHost;
	private int iPort;
	private Thread t;
	private BufferedReader socketReader;
	private BufferedWriter socketWriter;
	private InputThread _inputThread;
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
			this.identify();
			
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
		//New thread for socketReader
		_inputThread = new InputThread( _bot, socketReader );
		//_outputThread = new OutputThread( _bot, socketWriter );

	}

	public void Write( String data )
	{
		try
		{
			socketWriter.write( data + "\n\r" );
			System.out.println( ">> " + data );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
	}
	public void Flush( )
	{
		try
		{
			socketWriter.flush( );
		}
		catch( IOException e )
		{
			e.printStackTrace( );
		}
	}
	public void identify( )
	{
		//this.Write( "PASS *" );
		this.Write( "NICK wamBot" );
		this.Write( "USER FrankZZ 8 * : WamJava IRC" );
        //this.Write( "JOIN :#WammesNET" );
        this.Flush();
	}
}
