package client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerRunner
{
	static Socket clientSock;
	static Scanner getHIDInput;
	static remoteTTY tty;
	OutputStream os;
	PrintWriter pw;
	public ServerRunner()
	{
		// Auto-generated class constructor
		getHIDInput = new Scanner(System.in);
	}
	public boolean connect(String ip, int port)
	{
		try 
		{
			clientSock = new Socket(ip, port);
			tty = new remoteTTY(clientSock);
		    Thread getinputstm = new Thread(new Runnable() {
		         public void run() {
		              try {
						tty.listen();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		         }
		    });  
		    getinputstm.start();
			return true;
		}
		catch (Exception e)
		{
			System.err.println("Failed to connect to server " + ip + " on port " + port);
			e.printStackTrace();
			return false;
		}
	}
	public void beginComms() throws IOException
	{
		System.out.println("Beginning communications with server...");
		os = clientSock.getOutputStream();
		pw = new PrintWriter(os);
		for(;;)
		{
			try
			{
				System.out.print("\nSERVER>");
				String s = getHIDInput.nextLine();
				pw.print(s);
				pw.flush();
			}
			catch (Exception e)
			{
				System.err.println("Connection was interrupted: " + e);
				e.printStackTrace();
				break;
			}
		}
	}
}
