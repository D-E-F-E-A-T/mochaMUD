package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Runner 
{
	/**
	 * 
	 *  Copyright (c) 2017 _c0da_ (Victor Du)
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
	static Stopwatch clock;
	static Scanner lc;
	static ArrayList<String> favorites = new ArrayList<String>();
	@SuppressWarnings("static-access")
	public static void main (String[] args)
	{
		clock = new Stopwatch();
		sr = new ServerRunner(clock);
		System.out.println("Welcome to coffee{MUD} v0.2 (C) Victor Du");
		System.out.println("This software is open source and freely redistributable.");
		if (getHoliday() == 0)
		{
			// draw default banner
			System.out.println("\n");
			System.out.println("              _____  _____               .____     _____   ____ ___________     ____. ");
			System.out.println("  ____  _____/ ____\\/ ____\\____   ____   |   _|   /     \\ |    |   \\______ \\   |_   | ");
			System.out.println("_/ ___\\/  _ \\   __\\\\   __\\/ __ \\_/ __ \\  |  |    /  \\ /  \\|    |   /|    |  \\    |  | ");
			System.out.println("\\  \\__(  <_> )  |   |  | \\  ___/\\  ___/  |  |   /    Y    \\    |  / |    `   \\   |  | ");
			System.out.println(" \\___  >____/|__|   |__|  \\___  >\\___  > |  |_  \\____|__  /______/ /_______  /  _|  | ");
			System.out.println("     \\/                       \\/     \\/  |____|         \\/      v0.2b      \\/  |____| ");
			System.out.println("\n");
		}
		else if (getHoliday() == 1)
		{
			// draw spoopy halloween banner
			System.out.println("\n");
			System.out.println(" ▄████▄   ▒█████    █████▒ █████▒▓█████ ▓█████  ███▄ ▄███▓ █    ██ ▓█████▄ ");
			System.out.println("▒██▀ ▀█  ▒██▒  ██▒▓██   ▒▓██   ▒ ▓█   ▀ ▓█   ▀ ▓██▒▀█▀ ██▒ ██  ▓██▒▒██▀ ██▌");
			System.out.println("▒▓█    ▄ ▒██░  ██▒▒████ ░▒████ ░ ▒███   ▒███   ▓██    ▓██░▓██  ▒██░░██   █▌");
			System.out.println("▒▓▓▄ ▄██▒▒██   ██░░▓█▒  ░░▓█▒  ░ ▒▓█  ▄ ▒▓█  ▄ ▒██    ▒██ ▓▓█  ░██░░▓█▄   ▌");
			System.out.println("▒ ▓███▀ ░░ ████▓▒░░▒█░   ░▒█░    ░▒████▒░▒████▒▒██▒   ░██▒▒▒█████▓ ░▒████▓ ");
			System.out.println("░ ░▒ ▒  ░░ ▒░▒░▒░  ▒ ░    ▒ ░    ░░ ▒░ ░░░ ▒░ ░░ ▒░   ░  ░░▒▓▒ 0.2b  ▒▒▓  ▒ ");
			System.out.println("  ░  ▒     ░ ▒ ▒░  ░ [HAPPY░HALLOWEEN!]  ░ ░ ░  ░░  ░      ░░░▒░ ░ ░  ░ ▒  ▒ ");
			System.out.println("░        ░ ░ ░ ▒   ░ ░    ░ ░       ░      ░   ░      ░    ░░░ ░ ░  ░ ░  ░ ");
			System.out.println("░ ░          ░ ░                    ░  ░   ░  ░       ░      ░        ░    ");
			System.out.println("░                                                                   ░   ");
			System.out.println("\n");
		}
		else if (getHoliday() == 2)
		{
			System.out.println("\n");
			System.out.println("   ,--.\n" + 
					"  ()   \\\n" + 
					"   /    \\\n" + 
					" _/______\\_               ___   ___               ____ _______ _______ _____ ____ \n" + 
					"(__________) .----.-----.'  _|.'  _|.-----.-----.|   _|   |   |   |   |     \\_   | \n" + 
					"(/  @  @  \\) |  __|  _  |   _||   _||  -__|  -__||  | |       |   |   |  --  ||  |\n" + 
					"(`._,()._,') |____|_____|__|  |__|  |_____|_____||  |_|__|_|__|_______|_____/_|  |\n" + 
					"(  `-'`-'  )                                     |____|      v0.2b          |____|\n" + 
					" \\        /\n" + 
					"  \\,,,,,,/                                              ░      ");
			System.out.println("\n");
		}
		System.out.println("Try connecting to a server with the command: connect <serverIP> <serverport>");
		System.out.println("Example command: \" connect furrymuck.com 8888 \" or \" connect batmud.bat.org 23 \"");
		System.out.println("\n\n Please enter a command. \n\n");
		try {
			loadFavorites();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		showFavorites();
		lc = new Scanner(System.in);
		for(;;)
		{
			if (shouldOrderPizza())
			{
				System.out.println("\n\n Whew, you've been playing for a while. Maybe take a break and order some pizza?");
				System.out.println("To order pizza, run \" pizza \".\n\n");
			}
			System.out.print("\nLOCAL>");
			String input = lc.nextLine();
			if (input.contains("connect") && checkValidInput(input))
			{
				StringTokenizer st = new StringTokenizer(input);
				st.nextToken();
				String ip = st.nextToken();
				int port = Integer.parseInt(st.nextToken());
				try {
					addFavorite(ip, port);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			if (input.contains("goto") && checkValidInput(input))
			{
				StringTokenizer st = new StringTokenizer(input);
				st.nextToken();
				int id = Integer.parseInt(st.nextToken());
				if (!(id >= favorites.size()))
				{
					StringTokenizer st2 = new StringTokenizer(favorites.get(id));
					String ip = st2.nextToken();
					int port = Integer.parseInt(st2.nextToken());
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
			}
			if (input.contains("delserv") && checkValidInput(input))
			{
				StringTokenizer st = new StringTokenizer(input);
				st.nextToken();
				int id = Integer.parseInt(st.nextToken());
				if (!(id >= favorites.size()))
				{
					StringTokenizer st2 = new StringTokenizer(favorites.get(id));
					String ip = st2.nextToken();
					int port = Integer.parseInt(st2.nextToken());
					favorites.remove(id);
				}
			}
			if (input.matches("clear"))
			{
				for (int i = 0; i < 69; i++)
				{
					System.out.println("\n");
				}
			}
			if (input.matches("time"))
			{
				System.out.println("========[ coffee{MUD} time page ]========");
				double seconds = clock.getTime();
				int getDays = (int) (seconds / 60 / 60 / 24);
				int getHours = (int) ((seconds/60/60)%24);
				int getMins = (int) ((seconds / 60)%60);
				int getSecs = (int) (seconds % 60);
				//double getSeconds = seconds / 60 / 60 / 24
				System.out.println("UTC: " + getDays + " d " + getHours + " hrs " + getMins + " mins " + getSecs + " sec");
				double uptime = clock.elapsedTime();
				int getDaysU = (int) (uptime / 60 / 60 / 24);
				int getHoursU = (int) ((uptime/60/60)%24);
				int getMinsU = (int) ((uptime / 60)%60);
				int getSecsU = (int) (uptime % 60);
				System.out.println("Uptime: " + getDaysU + " d " + getHoursU + " hrs " + getMinsU + " mins " + getSecsU + " sec");
				System.out.println("========[ End of time page ]========");
			}
			if (input.matches("stats"))
			{
				if (sr.isRun)
				{
					System.out.println("========[ coffee{MUD} stats page ]========");
					double uptime = clock.elapsedTime();
					int getDaysU = (int) (uptime / 60 / 60 / 24);
					int getHoursU = (int) ((uptime/60/60)%24);
					int getMinsU = (int) ((uptime / 60)%60);
					int getSecsU = (int) (uptime % 60);
					System.out.println("IP     : " + sr.clientSock.getRemoteSocketAddress());
					System.out.println("Port   : " + sr.clientSock.getPort());
					System.out.println("MCP v. : " + sr.tty.mcpVer);
					System.out.println("Uptime : " + getDaysU + " d " + getHoursU + " hrs " + getMinsU + " mins " + getSecsU + " sec");
					System.out.println("Moves  : " + sr.commands);
					System.out.println("========[ End of stats page ]========");
				}
				else
				{
					System.out.println("You haven't started a game yet!");
				}
			}
			if (input.matches("pizza"))
			{
				// https://www.dominos.com/en/pages/order/
				System.out.println("Ordering Domino's pizza for you...");
				try {
					java.awt.Desktop.getDesktop().browse(new URI("https://www.dominos.com/en/pages/order/"));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (input.matches("resume"))
			{
				if (sr.isRun)
				{
					try {
						sr.beginComms();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else
				{
					System.out.println("You haven't started a game yet!");
				}
			}
			if (input.contains("help"))
			{
				String option = input.substring(4);
				System.out.println(option);
				if (option.matches(""))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("help - usage : help [command]");
					System.out.println("        [  LIST OF COMMANDS   ]        ");
					System.out.println("help");
					System.out.println("connect");
					System.out.println("goto");
					System.out.println("delserv");
					System.out.println("clear");
					System.out.println("stats");
					System.out.println("time");
					System.out.println("!pause");
					System.out.println("!disconnect");
					System.out.println("resume");
					System.out.println("pizza");
					System.out.println("!pizza");
					System.out.println("antiafk");
					System.out.println("exit");
					System.out.println("        [ END LIST OF COMMANDS ]        ");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("help"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("help - a command to run for help on a command.");
					System.out.println("SYNOPSIS");
					System.out.println("help [command]");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("goto"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("goto - connect to a saved server");
					System.out.println("SYNOPSIS");
					System.out.println("goto [server ID]");
					System.out.println("DESCRIPTION");
					System.out.println("This command connects to a specified MUD server with a set ID.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("delserv"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("delserv - delete a saved server");
					System.out.println("SYNOPSIS");
					System.out.println("delserv [server ID]");
					System.out.println("DESCRIPTION");
					System.out.println("This command deletes a saved MUD server on tiny{MUD}.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("connect"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("connect - connect to a MUD server.");
					System.out.println("SYNOPSIS");
					System.out.println("connect [IP of server] [port of server]");
					System.out.println("DESCRIPTION");
					System.out.println("This command connects to a MU* server. The server IP field supports IPv4 and IPv6 and the port supports TLS tunneling.");
					System.out.println("example : \" connect furrymuck.com 8888 \"");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("time"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("time - show uptime of program");
					System.out.println("SYNOPSIS");
					System.out.println("usage : time");
					System.out.println("DESCRIPTION");
					System.out.println("This command shows the amount of time coffee{MUD} has been running for, as well as\n the current UTC time.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("resume"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("resume - resume a paused game");
					System.out.println("SYNOPSIS");
					System.out.println("usage : resume");
					System.out.println("DESCRIPTION");
					System.out.println("This command will resume a previously paused game in coffee{MUD}.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("stats"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("stats - show statistics of current game");
					System.out.println("SYNOPSIS");
					System.out.println("usage : stats");
					System.out.println("DESCRIPTION");
					System.out.println("This command shows various user/client-side statistics for cofffee{MUD}. This includes the uptime of coffee{MUD}, commands run\n and current server.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("dice"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("!dice - roll a pair of virtual dice");
					System.out.println("SYNOPSIS");
					System.out.println("usage : !dice");
					System.out.println("DESCRIPTION");
					System.out.println("This command rolls multiple dice: a D2, D6, D10 and D20.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("pizza"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("pizza - order pizza mid-game");
					System.out.println("SYNOPSIS");
					System.out.println("usage : pizza");
					System.out.println("DESCRIPTION");
					System.out.println("This command opens an ordering menu for Domino's pizza, so you can order pizza for pick-up.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("pause"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("!pause - pause your MUD session");
					System.out.println("SYNOPSIS");
					System.out.println("usage : !pause");
					System.out.println("DESCRIPTION");
					System.out.println("This command pauses your game, not disconnecting and points you back to the LOCAL menu.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("clear"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("!clear - clear screen");
					System.out.println("SYNOPSIS");
					System.out.println("usage : !clear");
					System.out.println("DESCRIPTION");
					System.out.println("This command clears your screen.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("disconnect"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("!disconnect - disconnect from the MUD server.");
					System.out.println("SYNOPSIS");
					System.out.println("usage : !disconnect");
					System.out.println("DESCRIPTION");
					System.out.println("This command disconnects you from the MUD server and points you back to the LOCAL menu.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("antiafk"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("!antiafk - prevent getting kicked for AFK");
					System.out.println("SYNOPSIS");
					System.out.println("usage : !antiafk");
					System.out.println("DESCRIPTION");
					System.out.println("This command will prevent you from being kicked for inactivity. It sends a keep-alive message every 5 minutes.");
					System.out.println("==========[ End of help page ]=========");
				}
				if (option.contains("exit"))
				{
					System.out.println("========[ coffee{MUD} help page ]========");
					System.out.println("NAME");
					System.out.println("exit - close tiny{MUD}");
					System.out.println("SYNOPSIS");
					System.out.println("usage : exit");
					System.out.println("DESCRIPTION");
					System.out.println("This command exits tiny{MUD}.");
					System.out.println("Examples: ");
					System.out.println("Is someone hurting your feelings? Use this command to quit the game!");
					System.out.println("==========[ End of help page ]=========");
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
		if (str.contains("goto"))
		{
			try
			{
				st.nextToken();
				if (!isInteger(st.nextToken()))
				{
					System.out.println("You didn't enter a favorite number!");
					System.out.println("goto usage : goto <favorite ID>");
					return false;
				}
			} 
			catch (Exception e)
			{
				System.out.println("You didn't enter a favorite number!");
				System.out.println("goto usage : goto <favorite ID>");
				return false;
			}
		}
		if (str.contains("delserv"))
		{
			try
			{
				st.nextToken();
				if (!isInteger(st.nextToken()))
				{
					System.out.println("You didn't enter a number to remove!");
					System.out.println("delserv usage : delserv <favorite ID>");
					return false;
				}
			} 
			catch (Exception e)
			{
				System.out.println("You didn't enter a number to remove!");
				System.out.println("delserv usage : delserv <favorite ID>");
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
	public static boolean shouldOrderPizza()
	{
		double uptime = clock.elapsedTime();
		int getHoursU = (int) ((uptime/60/60)%24);
		if (getHoursU >= 1)
		{
			return true;
		}
		return false;
	}
	public static void loadFavorites() throws UnsupportedEncodingException, IOException
	{
		String path = Runner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		String decodedPath = URLDecoder.decode(path, "UTF-8");
		File f = new File(path + "favorites.ini");
		if (!f.exists())
		{
			f.createNewFile();
		}
		System.out.println("Loading saved servers from: " + path + "favorites.ini");
		BufferedReader br = new BufferedReader(new FileReader(path + "favorites.ini"));
		for(;;)
		{
			String s = br.readLine();
			if (s == null)
			{
				break;
			}
			favorites.add(s);
		}
		br.close();
	}
	public static void showFavorites()
	{
		if (favorites.size() != 0)
		{
			System.out.println("Saved servers: ");
			for (int i = 0; i < favorites.size(); i++)
			{
				System.out.println((i) + ") "+ favorites.get(i));
			}
			System.out.println("\n");
		}
	}
	public static void addFavorite(String ip, int port) throws IOException
	{
		try
		{
			String path = Runner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			PrintWriter pw = new PrintWriter(new FileWriter(path + "favorites.ini", true));
			if (!favorites.contains(ip + " " + port))
			{
				System.out.println("You haven't visited this server before. Would you like to save it for quick access later? (Y/N)");
				String ch = lc.nextLine();
				if (ch.contains("y") || ch.contains("Y")) { favorites.add(ip + " " + port);  pw.println(ip + " " + port); pw.close(); System.out.println("Server saved.");}	
			}
		}
		catch (Exception e)
		{
			System.err.println("addFavorite encountered an exception: " + e);
			e.printStackTrace();
		}
	}
	public static int getHoliday()
	{
		/**
		 * returns current holiday, if any.
		 * 0 = no holiday
		 * 1 = halloween
		 * 2 = christmas
		 */
		Calendar cal = Calendar.getInstance(); 
		cal.setTimeInMillis((long) clock.getTime() * 1000) ; 
		int month = cal.get(Calendar.MONTH) + 1; 
		int date = cal.get(Calendar.DAY_OF_MONTH);
		// System.out.println(month + " " + date);
		if (month == 10 && date == 31)
		{
			return 1;
		}
		else if (month == 12 && (date == 24 || date == 25))
		{
			return 2;
		}
		return 0;
	}
}
