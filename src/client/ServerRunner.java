package client;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerRunner
{
	static Socket clientSock;
	static Scanner getHIDInput;
	static remoteTTY tty;
	// PrintWriter pw;
	static int ActivationCount = 0;
	BufferedWriter bw;
	DataOutputStream out;
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
		bw = new BufferedWriter(new OutputStreamWriter(clientSock.getOutputStream()));
		for(;;)
		{
			try
			{
				System.out.print("\nSERVER>");
				String s = getHIDInput.nextLine();
				if (s.matches("ragequit")) { break; }
				if (ActivationCount >= 2)
				{
					bw.write(s);
		            bw.newLine();
		            bw.flush();
		            ActivationCount++;
				}
				else
				{
					for(ActivationCount = 0; ActivationCount < 3; ActivationCount++)
					{
						bw.write(s);
			            bw.newLine();
			            bw.flush();
					}
				}
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
