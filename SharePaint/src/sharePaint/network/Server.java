package sharePaint.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Listens for connections
 * 
 * @author AJW
 */
public class Server implements Runnable
{
	private ServerSocket serverSocket;
	private static Logger logger = Logger.getLogger(Server.class.getName());
	private ArrayList<ConnectionListener> listeners = new ArrayList<ConnectionListener>();

	/**
	 * Constructor
	 * 
	 * @param port
	 *            the port to listen to
	 * @throws IOException
	 */
	public Server(int port) throws IOException
	{
		serverSocket = new ServerSocket(port);
		logger.info("Listening to port " + port);
	}
	
	public void addConnectionListener(ConnectionListener l)
	{
		listeners.add(l);
	}

	public void run()
	{
		try
		{
			Socket incomingSocket = serverSocket.accept();
			if (incomingSocket != null)
			{
				logger.info("Connection established with " + incomingSocket.toString());
				
				ObjectOutputStream out = new ObjectOutputStream(incomingSocket.getOutputStream());
				out.flush();
				ObjectInputStream in = new ObjectInputStream(incomingSocket.getInputStream());
				
				for(int n = 0; n < listeners.size(); n++)
				{
					listeners.get(n).clientConnected(in, out);
				}
			}	
		}
		catch (IOException ex)
		{
			logger.log(Level.SEVERE, null, ex);
		}
		finally
		{
			try
			{
				serverSocket.close();
			}
			catch (IOException e)
			{
				logger.log(Level.SEVERE, null, e);
			}
		}
	}
}
