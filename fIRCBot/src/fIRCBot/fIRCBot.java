package fIRCBot;

import java.util.ArrayList;
import java.util.List;

//Alle benodigde imports voor o.a. sockets:


//De klasse fIRCBot. Hier gaat de 'shyte down'
public abstract class fIRCBot
{
	private IRCSocket s;
	private String botNick = "wamBot";
	private String host;
	private int port;

	private List<String> channels = new ArrayList<String>( );

	public fIRCBot( )
	{
		System.out.println( "fIRCBot Constructed." );
	}
	public void Connect( String host, int port )
	{
		this.host = host;
		this.port = port;
		s = new IRCSocket( this, this.host, this.port );
		s.Write( "NICK " + this.botNick );
		s.Write( "USER " + this.botNick + " 8 * : WamJava IRC" );
        s.Flush();	
	}
	public void onRawLine( String currLine )
	{
		int firstSpace = currLine.indexOf( " " );
		int secondSpace = currLine.indexOf( " ", ( firstSpace + 1 ) );
		String currCode = null;
		if( secondSpace > 0 )
		{
			currCode = currLine.substring( ( firstSpace + 1 ), secondSpace );
			if( currCode.equals( "004" ) ) // we are registered
			{
				this.joinChannels( );
				this.onConnect( );
			}
			else if( currCode.equalsIgnoreCase( "PRIVMSG" ) )
			{
				
				/*
				    :FrankZZ!FrankZZ@frankwammes.nl PRIVMSG #chillpoint :123
				 */
				int thirdSpace = currLine.indexOf( " ", secondSpace + 1 );
				int firstExclamationMark = currLine.indexOf( "!" );
				String user = currLine.substring( 1, firstExclamationMark );
				String host = currLine.substring( ( firstExclamationMark + 1 ), firstSpace );
				String message = currLine.substring( ( thirdSpace + 2 ) );
				String channel = currLine.substring( ( secondSpace + 1 ), thirdSpace );
				this.onUserSay( user, host, channel, message );
			}
		}
		else if( firstSpace > 0 )
		{
			currCode = currLine.substring( 0, firstSpace );
			if( currCode.equals( "PING" ) )
			{
				String pingParameters = currLine.substring( ( firstSpace ) );
				s.Write( "PONG" + pingParameters );
				s.Flush( );
			}
		}
	}
	/*
	 * Function to add a channel to join on connect, stored in an array. if onConnect is called we will loop through
	 */
	public void addChannel( String channel )
	{
		channels.add( channel );
	}
	public void setName( String name )
	{
		this.botNick = name;
		System.out.println( "Set botNick to " + name );
	}
	//Static callbacks, should not be overridden (cannot, final)
	public final void joinChannels( )
	{
		int len = channels.size( );
		for( int i = 0; i < len; i++ )
		{
			s.Write( "JOIN :" + channels.get( i ) );
		}
		s.Flush( );
	}
	//callbacks
	public void onConnect( )
	{
		/*
		 * no-op
		 */
	}
	public void onUserSay(String user, String host, String channel, String message)
	{
		/* 
		 * no-op
		 */
	}
	public boolean rawLine( String line )
	{
		if( !s.Write( line ) )
		{
			return false;
		}
		return s.Flush( );
	}
}

