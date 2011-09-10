package fIRCBot;

public class Main extends fIRCBot 
{
	private static fIRCBot b;
	public static void main( String[] args )
	{
		b = new fIRCBot( );
		b.Connect( "irc.sc", 6667 );
	}
}
