package sharePaint.network;

import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A thread pool that manages sessions
 * @author AJW
 *
 * Bibliography:
 * http://download.oracle.com/javase/tutorial/essential/concurrency/pools.html
 */
public class SessionPool
{
    private ExecutorService pool;
    private Protocol protocol;

    /**
	* Constructor
	* @param MAX_THREADS the maximum number of threads in the pool
	* @param protocol the Protocol that the Sessions should use
	*/
    public SessionPool(int MAX_THREADS, Protocol protocol)
    {
	   pool = Executors.newFixedThreadPool(MAX_THREADS);
	   this.protocol = protocol;
    }

    /**
	* Create a session
	* @param incomingSocket the Socket
	*/
    public void createSession(Socket incomingSocket)
    {
	   pool.execute(new Session(incomingSocket, protocol));
    }

    /**
	* Interrupt all running tasks
	*/
    public void stop()
    {
	   pool.shutdownNow();
    }

}
