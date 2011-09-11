package fIRCBot;
//Alle benodigde imports voor o.a. sockets:


//De klasse fIRCBot. Hier gaat de 'shyte down'
public abstract class fIRCBot
{
	private IRCSocket s;
	private String botNick = "wamBot";
	private String host;
	private int port;
	private String[] channels;

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
		s.Write( "USER FrankZZ 8 * : WamJava IRC" );
        s.Flush();	
	}
	public void onRawLine( String currLine )
	{
		int firstSpace = currLine.indexOf( " " );
		int secondSpace = currLine.indexOf( " ", ( firstSpace + 1 ) );
		if( secondSpace >= 0 )
		{
			String currCode = currLine.substring( ( firstSpace + 1 ), secondSpace );
			//String[] splitLine = currLine.split( " " );
			if( currCode.equals( "004" ) ) // we are registered
			{
				this.onConnect( );
			}
			else if( currCode.equalsIgnoreCase( "PRIVMSG" ) )
			{
				/*
				    :FrankZZ!FrankZZ@frankwammes.nl PRIVMSG #chillpoint :123
				 */
				//this.onUserSay( user, host, message );
			}
		}
	}
	/*
	 * Function to add a channel to join on connect, stored in an array. if onConnect is called we will loop through
	 */
	public void addChannel( String channel )
	{
		int idx = channels.length;
		channels[idx] = channel;
	}
	public void setName( String name )
	{
		this.botNick = name;
		System.out.println( "Set botNick to " + name );
	}
	//Static callbacks, should not be overridden (cannot, final)
	public final void onConnect( )
	{
		s.Write( "JOIN :#chillpoint" );
		s.Write( "JOIN :#wammes.org" );
		s.Flush( );
	}
	//callbacks
	public void onUserSay(String user, String host, String message)
	{
		//o.onUserSay(user, host, message);	
	}
}

