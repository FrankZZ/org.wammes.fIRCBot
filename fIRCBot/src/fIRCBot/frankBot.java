package fIRCBot;

public class frankBot extends fIRCBot
{
	private static frankBot b;
	public frankBot( )
	{
		//placeholder
	}
	public static void main( String[] args )
	{
		b = new frankBot( );
		b.setName( "wamBot" );
		b.addChannel( "#chillpoint" );
		b.addChannel( "#wammes.org" );
		b.Connect( "irc.gtanet.com", 6667 );
	}
	public void onUserSay( String user, String host, String channel, String message )
	{
		System.out.println( "nick: " + user + " host: " + host + " says " + message + " in " + channel );
	}
}
