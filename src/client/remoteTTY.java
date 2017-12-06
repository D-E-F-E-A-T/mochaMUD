package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
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
	BufferedWriter bw;
	double mcpVer = 0.0;
	boolean isclosed = false;
	public remoteTTY(Socket clientSock)
	{
		this.clientSock = clientSock;
	}
	public void listen() throws IOException
	{
		String receiveMessage;
		os = clientSock.getOutputStream();
		bw = new BufferedWriter(new OutputStreamWriter(clientSock.getOutputStream()));
		InputStream istream = clientSock.getInputStream();
   	    BufferedReader receiveRead = new BufferedReader(new InputStreamReader(istream));
		for(;;)
		{
			try
			{	
				if (isclosed)
				{
					break;
				}
				while((receiveMessage = receiveRead.readLine()) != null)
				{
					if (receiveMessage.contains("#$#mcp version:"))
					{
						Matcher m = Pattern.compile("(?!=\\d\\.\\d\\.)([\\d.]+)").matcher(receiveMessage);
						if (m.find())
						{
							mcpVer = Double.parseDouble(m.group(1));
							System.out.println("\n Server MCP Version: " + mcpVer);
							System.out.println("Sending ACK request to server...\n");
							bw.write(",V^iS.E47@@e}Jl\"yW\n" + 
									" +");
							bw.newLine();
							bw.flush();
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
