package client;

import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Runner 
{
	/**
	 * Copyright (c) 2017 _c0da_ (Victor Du)
	 *
	 *	Permission is hereby granted, free of charge, to any person obtaining a copy
	 *	of this software and associated documentation files (the "Software"), to deal
	 *	in the Software without restriction, including without limitation the rights
	 *	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	 *	copies of the Software, and to permit persons to whom the Software is
	 *	furnished to do so, subject to the following conditions:
	 *  
	 *	The above copyright notice and this permission notice shall be included in all
	 *	copies or substantial portions of the Software.
	 *
	 *	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	 *	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	 *	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	 *	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	 *	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	 *	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	 *	SOFTWARE.
	 */
	static ServerRunner sr;
	public static void main (String[] args)
	{
		sr = new ServerRunner();
		System.out.println("Welcome to coffee{MUD} v0.1 by Victor Du");
		System.out.println("This software is open source and freely redistributable.");
		System.out.println("Try connecting to a server with the command: connect <serverIP> <serverport>");
		System.out.println("Example command: \" connect furrymuck.com 8888 \"");
		System.out.println("\n\n Please enter a command. \n\n");
		Scanner lc = new Scanner(System.in);
		for(;;)
		{
			System.out.print("\nLOCAL>");
			String input = lc.nextLine();
			if (input.contains("connect") && checkValidInput(input))
			{
				StringTokenizer st = new StringTokenizer(input);
				st.nextToken();
				String ip = st.nextToken();
				int port = Integer.parseInt(st.nextToken());
				if (sr.connect(ip, port))
				{
					try {
						sr.beginComms();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			if (input.matches("exit"))
			{
				System.out.println("Exiting coffee{MUD}...");
				System.exit(0);
			}
		}
	}
	public static boolean checkValidInput(String str)
	{
		StringTokenizer st = new StringTokenizer(str);
		if (str.contains("connect"))
		{
			try
			{
				st.nextToken();
				st.nextToken();
				if (!isInteger(st.nextToken()))
				{
					System.out.println("You didn't enter a port number!");
					System.out.println("connect usage : connect <server IP> <server port>");
					return false;
				}
			} 
			catch (Exception e)
			{
				System.out.println("Input for 'connect' is not correct");
				System.out.println("connect usage : connect <server IP> <server port>");
				return false;
			}
		}
		return true;
	}
	public static boolean isInteger(String s)
	{
	    try 
	    { 
	        Integer.parseInt(s); 
	    } 
	    catch(NumberFormatException e)
	    { 
	        return false; 
	    } 
	    catch(NullPointerException e) 
	    {
	        return false;
	    }
	    return true;
	}
}
