package fIRCBot;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class OutputThread
{
	private fIRCBot _bot;
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
