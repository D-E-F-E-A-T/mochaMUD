package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;

public class ServerRunner
{
	static Socket clientSock;
	static Scanner getHIDInput;
	static remoteTTY tty;
	static double startTime;
	static double mLoadTime;
	static boolean isRun = false;
	// PrintWriter pw;
	static int commands = 0;
	static int ActivationCount = 0;
	BufferedWriter bw;
	DataOutputStream out;
	Stopwatch clock;
	ArrayList<String> macros = new ArrayList<>();
	public ServerRunner(Stopwatch clock)
	{
		// Auto-generated class constructor
		this.clock = clock;
		startTime = clock.elapsedTime();
		getHIDInput = new Scanner(System.in);
	}
	public boolean connect(String ip, int port)
	{
		try 
		{
			clientSock = new Socket(ip, port);
			System.out.println("Connected to " + ip + " in " + (clock.elapsedTime() - startTime) + "s");
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
		    System.out.println("Loaded modules in " + (clock.elapsedTime() - startTime) + "s");
		    isRun = true;
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
				String s = getHIDInput.nextLine();
				if (s.contains("!pause"))
				{
					System.out.println("Pausing game...");
					break;
				}
				else if (s.contains("!time"))
				{
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
				}
				else if (s.contains("!stats"))
				{
					System.out.println("========[ tiny{MUD} stats page ]========");
					double uptime = clock.elapsedTime();
					int getDaysU = (int) (uptime / 60 / 60 / 24);
					int getHoursU = (int) ((uptime/60/60)%24);
					int getMinsU = (int) ((uptime / 60)%60);
					int getSecsU = (int) (uptime % 60);
					System.out.println("IP     : " + clientSock.getRemoteSocketAddress());
					System.out.println("Port   : " + clientSock.getPort());
					System.out.println("Uptime : " + getDaysU + " d " + getHoursU + " hrs " + getMinsU + " mins " + getSecsU + " sec");
					System.out.println("Moves  : " + commands);
					System.out.println("========[ End of stats page ]========");
				}
				else if (s.contains("!pizza"))
				{
					// https://www.dominos.com/en/pages/order/
					System.out.println("Ordering Domino's pizza for you...");
					java.awt.Desktop.getDesktop().browse(new URI("https://www.dominos.com/en/pages/order/"));
				}
				else if (s.contains("!dice"))
				{
					Random d2 = new Random();
					Random d6 = new Random();
					Random d10 = new Random();
					Random d20 = new Random();
					System.out.println("Rolling dice for you...");
					System.out.println("D2: " + (d2.nextInt(2)+1));
					System.out.println("D6: " + (d6.nextInt(6)+1));
					System.out.println("D10: " + (d10.nextInt(10)+1));
					System.out.println("D20: " + (d20.nextInt(20)+1));
				}
				else if (s.contains("!macro"))
				{
					StringTokenizer st = new StringTokenizer(s);
					st.nextToken();
					String args = st.nextToken();
					if (args == null)
					{
						System.err.println("No macro setting equipped. Usage: !macro [macro #]");
					}
					else
					{
						int id = Integer.parseInt(args);
						loadMacros(id);
					}
				}
				else if (s.contains("!clear"))
				{
					for (int i = 0; i < 69; i++)
					{
						System.out.println("\n");
					}
				}
				else if (s.contains("!disconnect"))
				{
					tty.isclosed = true;
					tty.clientSock.close();
					clientSock.close();
					break;
				}
				else
				{
					if (ActivationCount >= 2)
					{
						bw.write(s);
						bw.newLine();
						bw.flush();
						ActivationCount++;
						commands++;
					}
					else
					{
						for(ActivationCount = 0; ActivationCount < 3; ActivationCount++)
						{
							bw.write(s);
							bw.newLine();
							bw.flush();
						}
						commands++;
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
	public void loadMacros(int macroID) throws IOException
	{
		/**
		 * Macros can be called with the command: !macro <macro #>
		 * 
		 * A macro file should come in the following form:
		 * Name: coffeeMacros.ini
		 * Location: Folder of JAR File
		 * 
		 * ===== BEGIN SAMPLE MACRO FILE =====
		 * 
		 * [Macro#]
		 * sample command here
		 * other sample command here
		 * 
		 * [Macro#]
		 * connect guest guest
		 * WHO
		 * 
		 * ====== END SAMPLE MACRO FILE ======
		 * 
		 */
		try
		{
			System.out.println("Running macro "+ macroID + "...");
			String path = Runner.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decodedPath = URLDecoder.decode(path, "UTF-8");
			File f = new File(path + "coffeeMacros.ini");
			if (!f.exists())
			{
				f.createNewFile();
			}
			boolean ff = false;
			boolean disconnectInMacro = false;
			BufferedReader br = new BufferedReader(new FileReader(decodedPath + "coffeeMacros.ini"));
			for(;;)
			{
				if (ff)
				{
					break;
				}
				String s = br.readLine();
				if (s == null)
				{
					break;
				}
				if (s.contains("["))
				{
					if (Integer.parseInt(s.substring(s.indexOf("[")+1, s.indexOf("]"))) == macroID)
					{
						for(;;)
						{
							s = br.readLine();
							// System.out.println(s);
							if (s.contains("&delay"))
							{
								StringTokenizer parser = new StringTokenizer(s);
								parser.nextToken();
								String delayTString = parser.nextToken();
								try
								{
									int tempDelay = Integer.parseInt(delayTString);
									System.out.println("Delaying " + tempDelay + " ms");
									Thread.sleep(tempDelay);
								} 
								catch (Exception e)
								{
									System.err.println("Couldn't parse delay from macro file.");
								}
							}
							else if (s.contains("&print"))
							{
								StringTokenizer parser = new StringTokenizer(s);
								parser.nextToken();
								String toPrint = parser.nextToken();
								System.out.println(toPrint);
							}
							else if (s.contains("&disconnect"))
							{
								tty.isclosed = true;
								tty.clientSock.close();
								clientSock.close();
								disconnectInMacro = true;
								break;
							}
							else
							{
								if (s == null || s.contains("["))
								{
									ff = true;
									break;
								}
								else
								{	
									bw.write(s);
									bw.newLine();
									bw.flush();
								}
							}
						}
					}
				}
				if (disconnectInMacro)
				{
					disconnectInMacro = false;
					break;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("Error loading macros: "+ e);
			e.printStackTrace();
		}
	}
}
