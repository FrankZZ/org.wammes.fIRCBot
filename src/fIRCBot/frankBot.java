package fIRCBot;

public class frankBot extends fIRCBot
{
	final String ACTION = "";
	final String COLOUR = "";
	private static frankBot b;
	public frankBot( )
	{
		//placeholder
	}
	public static void main( String args[] )
	{
		b = new frankBot( );
		b.setName( "wamBot" );
		b.addChannel( "#chillpoint" );
		b.addChannel( "#wammes.org" );
		b.Connect( "irc.gtanet.com", 6667 );
	}
	public void onUserSay( String user, String host, String channel, String message )
	{
		
	}
	public void onUserCommand( String user, String host, String channel, String[] command, String commandtext )
	{
		if( command[0].equalsIgnoreCase( "!test" ) )
		{
			if( command.length > 1 )
			{
				b.Say( channel, COLOUR + "10" + user + COLOUR + " " + command[1] );
			}
			else
			{
				b.Say( channel, COLOUR + "10" + user + COLOUR + ", use !test <param> bitch." );
			}
		}
		if( command[0].equalsIgnoreCase( "!act" ) )
		{
			if( command.length > 1 )
			{
				int idx = ( command[0].length( ) + 1 );
				if( command.length > 2 )
				{
					if( command[1].startsWith( "#" ) )
					{
						//someone uses !act <channel> [action]
						idx += ( command[1].length( ) + 1 );
						channel = command[1];
					}
				}
				System.out.println( "|" + channel + "|");
				this.Action( channel, commandtext.substring( idx ) );
			}
			else
			{
				b.Say( channel, COLOUR + "04" + user + COLOUR + ", SYNTAX: " + command[0] + " <channel> [action]" );
			}
		}
	}
	public void onConnect( )
	{
		
	}
}
