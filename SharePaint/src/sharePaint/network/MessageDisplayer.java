package sharePaint.network;

import java.io.PrintStream;

/**
 * Displays a Message
 * @author AJW
 *
 * Bibliography:
 * http://www.ibm.com/developerworks/library/j-thread.html
 */
public class MessageDisplayer
{
    private PrintStream out;

    /**
	* Constructor
	* @param out the PrintStream to write the message on
	*/
    public MessageDisplayer(PrintStream out)
    {
	   this.out = out;
    }

    /**
	* Display a message
	* @param msg the message
	*/
    public synchronized void display(Message msg)
    {
	   out.println("Client: " + msg.getClient());
	   out.println("Action: " + msg.getAction());
	   out.println("Message: " + msg.getMessage());
	   out.flush();
    }
    
}
