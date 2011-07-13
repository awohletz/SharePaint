package sharePaint.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runs a Protocol over a network connection
 * @author AJW
 *
 * Bibliography:
 * http://download.oracle.com/javase/tutorial/networking/sockets/clientServer.html
 */
public class Session implements Runnable
{
    private Socket socket;
    private Protocol protocol;
    private static Logger logger = Logger.getLogger(Session.class.getName());


    /**
	* Constructor
	* @param socket the Socket that the Protocol will use
	* @param protocol the Protocol
	*/
    public Session(Socket socket, Protocol protocol)
    {
	   assert socket != null;
	   assert socket.isConnected();
	   assert protocol != null;

	   this.socket = socket;
	   this.protocol = protocol;
    }

    public void run()
    {
	   assert socket.isConnected();
	   logger.info("Session running");
	   
	   try
	   {
		  PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		  BufferedReader in = new BufferedReader(
				new InputStreamReader(
				socket.getInputStream()));

		  protocol.run(out, in);

		  out.close();
		  in.close();
		  socket.close();

	   }
	   catch (IOException e)
	   {
		  logger.log(Level.SEVERE, null, e);
	   }

	   logger.info("Session closed");
	   assert socket.isClosed();
    }
}
