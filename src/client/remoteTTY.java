package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class remoteTTY
{
	Socket clientSock;
	StringTokenizer st;
	Scanner sc;
	OutputStream os;
	PrintWriter pw;
	double mcpVer = 0.0;
	public remoteTTY(Socket clientSock)
	{
		this.clientSock = clientSock;
	}
	public void listen() throws IOException
	{
		String receiveMessage;
		os = clientSock.getOutputStream();
		pw = new PrintWriter(os);
		InputStream istream = clientSock.getInputStream();
   	    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
   	    boolean f = false;
		for(;;)
		{
			try
			{	
				if (!f)
				{
					for(int cls = 0; cls < 42; cls++)
					{
						System.out.println("~");
					}
				}
				while((receiveMessage = receiveRead.readLine()) != null)
				{
					f = !f;
					if (receiveMessage.contains("#$#mcp version:"))
					{
						Matcher m = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(receiveMessage);
						if (m.find())
						{
							mcpVer = Double.parseDouble(m.group(1));
							System.out.println("\n Server MCP Version: " + mcpVer);
							System.out.println("Sending ACK request to server...\n");
							pw.print(",V^iS.E47@@e}Jl\"yW\n" + 
									" +");
							pw.flush();
						}
					}
					else
					{
						System.out.println(receiveMessage);
					}
				}
			}
			catch (Exception e)
			{
				System.err.println("remoteTTY was interrupted: " + e);
				e.printStackTrace();
			}
		}
	}
}
