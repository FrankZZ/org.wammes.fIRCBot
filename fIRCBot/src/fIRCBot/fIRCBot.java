package fIRCBot;
//Alle benodigde imports voor o.a. sockets:


//De klasse fIRCBot. Hier gaat de 'shyte down'
public class fIRCBot 
{
	private IRCSocket s;
	public fIRCBot( )
	{
		System.out.println( "fIRCBot Constructed." );
	}
	public void Connect( String host, int port )
	{
		s = new IRCSocket( this, host, port );
	}
	public void onRawLine( String currLine )
	{
		String[] splitLine = currLine.split( " " );
		if( splitLine.length > 0 )
		{
			if( splitLine[0].equalsIgnoreCase( "PING" ) )
			{
				String pong = "PONG";
				if( splitLine.length > 1 )
				{
					pong += " " + splitLine[1];
				}
				s.Write( pong );
				s.Flush( );
			}
			else if( splitLine[1].equals( "004" ) ) // we are registered
			{
				System.out.print( "\n\n\n\nBITCH\n\n\n");
				s.Write( "JOIN :#wammes.org" );
				s.Flush( );
			}
			else if( splitLine[1].equals( "353") )
			{
				//server is passing userlist of channel in following format:
				// :nl.irc.sc 353 wamBot = #wammes.org :wamBot Jim91 ~FrankZZ
			}
		}
	}
}

