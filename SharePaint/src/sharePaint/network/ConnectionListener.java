package sharePaint.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Listens for connections and reports any to a SessionPool
 * @author AJW
 */
public class ConnectionListener implements Runnable
{
    private SessionPool pool;
    private volatile boolean listening;
    private ServerSocket serverSocket;
    private static Logger logger = Logger.getLogger(ConnectionListener.class.getName());

    /**
	* Constructor
	* @param pool a SessionPool
	* @param port the port to listen to
	* @throws IOException
	*/
    public ConnectionListener(SessionPool pool, int port) throws IOException
    {
	   this.pool = pool;
	   this.listening = false;

	   serverSocket = new ServerSocket(port);
	   serverSocket.setSoTimeout(1000); //will stop every 1000ms to see if listening flag is true
	   logger.info("Listening to port " + port);
    }

    /**
	* Cease listening for connections
	*/
    public void stop()
    {
	   logger.fine("ConnectionListener stopped");
	   listening = false;
    }

    public void run()
    {
	   try
	   {
		  listening = true; 
		  while (listening)
		  {
			 try
			 {
				Socket incomingSocket = serverSocket.accept();
				if (incomingSocket != null)
				{
				    logger.info("Connection established with " + incomingSocket.toString());
				    pool.createSession(incomingSocket);
				}
			 }
			 catch (SocketTimeoutException e)
			 {
				//serverSocket.accept will throw this every 1000ms to see if listening flag is true
			 }
		  }
		  serverSocket.close();
	   }
	   catch (IOException ex)
	   {
		  logger.log(Level.SEVERE, null, ex);
	   }
    }
}
