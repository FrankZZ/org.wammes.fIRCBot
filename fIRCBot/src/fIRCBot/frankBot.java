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
		b.Connect( "irc.gtanet.com", 6667 );
	}
	public void onUserSay( String user, String host, String message )
	{
		System.out.println( "TESTTTTTTTT");
		System.out.println( user + "--" + host + " says " + message );
	}
}
