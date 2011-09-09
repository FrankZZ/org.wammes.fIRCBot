package fIRCBot;

/*imports voor de sockets etc.*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class IRCSocket extends fIRCBot implements Runnable
{
	public Socket s;
	private String sHost;
	private int iPort;
	private Thread t;
	public BufferedReader socketReader;
	public BufferedWriter socketWriter;
	
	public IRCSocket( String host, int port )
	{
		sHost = host;
		iPort = port;
		System.out.println( "IRCSocket Constructed.");
		t = new Thread( this );
		t.start( );
	}

	public void Connect()
	{

	}
	public void run( )
	{
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
			String currLine = null;
			while ((currLine = socketReader.readLine()) != null)
		    {
		    	System.out.println( "<< " + currLine );
				String[] splitLine = currLine.split( " " );
				this.processLine( splitLine );
		    }
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
