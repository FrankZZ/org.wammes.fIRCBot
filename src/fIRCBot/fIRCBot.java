package fIRCBot;
/*import java.awt.EventQueue;
import java.lang.reflect.InvocationTargetException;
*/import java.util.ArrayList;
import java.util.List;

//Alle benodigde imports voor o.a. sockets:


//De klasse fIRCBot. Hier gaat de 'shyte down'
public abstract class fIRCBot
{
	final String ACTION = "";
	final String COLOUR = "";
	private IRCSocket s;
	private String botNick = "wamBot";
	private String host;
	private int port;
	private topWindow frame;
	private List<String> channels = new ArrayList<String>( );

	public fIRCBot( )
	{
		System.out.println( "fIRCBot Constructed." );
		//setup window
		frame = new topWindow( this );
		frame.setVisible( true );
	}
	public void Connect( String host, int port )
	{
		this.host = host;
		this.port = port;
		s = new IRCSocket( this, this.host, this.port );
		this.rawLine( "NICK " + this.botNick );
		this.rawLine( "USER " + this.botNick + " 8 * : WamJava IRC" );
	}
	public void onRawLine( String currLine )
	{
		this.print( "<< " + currLine + "\n" );
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
				if( message.startsWith( "!" ) )
				{
					String[] command = message.split( " " );
					this.onUserCommand( user, host, channel, command, message );
				}
				else
				{
					this.onUserSay( user, host, channel, message );	
				}
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
			if( currCode.equals( "ERROR" ) )
			{
				//do something
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
			this.rawLine( "JOIN :" + channels.get( i ) );
		}
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
	/*
	 * @function onUserCommand
	 * @param user: the username of the sender
	 * @param host: host of the sender
	 * @param channel: recipient
	 * @param commands[]: a splitted array of the commandtext
	 * @param commandtext: the whole message
	 * @author: Frank <frank@wammes.org>
	 */
	public void onUserCommand( String user, String host, String channel, String[] command, String commandtext )
	{
		/*
		 * no-op
		 */
	}
	public boolean rawLine( String line )
	{
		this.print( ">> " + line + "\n" );
		if( !s.Write( line ) )
		{
			return false;
		}
		return s.Flush( );
	}
	public boolean Say( String channel, String message )
	{
		return this.rawLine( "PRIVMSG " + channel + " :" + message );
	}
	public boolean Action( String channel, String action )
	{
		return this.Say( channel, ACTION + "ACTION " + action + this.ACTION );
	}
	public void print( String msg )
	{
		frame.append( msg );
	}
}

