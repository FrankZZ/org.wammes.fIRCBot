package fIRCBot;

import java.io.BufferedWriter;
//import java.io.IOException;

public class OutputThread
{
	@SuppressWarnings("unused")
	private fIRCBot _bot;
	@SuppressWarnings("unused")
	private BufferedWriter sw;
	public OutputThread( fIRCBot bot, BufferedWriter SocketWriter )
	{
		sw = SocketWriter;
		_bot = bot;
	}
	
	public void run( )
	{
		
	}
}
