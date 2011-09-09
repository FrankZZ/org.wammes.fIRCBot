package fIRCBot;

public class Main 
{
	private static fIRCBot b;
	public static void main( String[] args )
	{
		b = new fIRCBot( );
		b.Connect( "irc.sc", 6667 );
	}
}
