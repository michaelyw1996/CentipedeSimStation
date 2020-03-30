/*
 * Edit History:
 * Michael Wong,3/30: Initialized with professor code. 
 */

package SimStation;


import java.io.*;

public class Console {
	protected BufferedReader stdin =
			new BufferedReader(
					new InputStreamReader(System.in));
	protected PrintWriter stdout =
			new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(System.out)), true);
	protected PrintWriter stderr =
			new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(System.err)), true);

	public void controlLoop() {
		while(true) {
			try {
				stdout.print("-> ");
				stdout.flush(); // force the write
				String cmmd = stdin.readLine();
				if (cmmd == null) {
					stdout.println("type \"help\" for commands");
					continue;
				}
				cmmd = cmmd.trim(); // trim white space

				if (cmmd.equalsIgnoreCase("quit")) break;
				if (cmmd.equalsIgnoreCase("help")) {
					stdout.println("Sorry, no help is available");
					continue;
				}
				if (cmmd.equalsIgnoreCase("about")) {
					stdout.println("All rights reserved");
					continue;
				}
				stdout.println(execute(cmmd));
			} catch(Exception e) {
				stderr.println(e.getMessage());
			}
		} // while
		stdout.println("bye");
	} // controlLoop

	// override in a subclass:
	protected String execute(String cmmd) throws Exception {
		if (cmmd.equalsIgnoreCase("throw")) {
			throw new Exception("exception intentionally thrown");
		}
		return "echo: " + cmmd;
	}
	/*
	   public static void main(String[] args) {
	      Console ui = new Console();
	      ui.controlLoop();
	   }
	 */
} // Console
