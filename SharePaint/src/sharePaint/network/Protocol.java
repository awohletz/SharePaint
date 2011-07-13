package sharePaint.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Handles the interaction between two endpoints
 * @author AJW
 */
public interface Protocol
{

    /**
	* Perform any interaction necessary
	* @param out output to the other machine
	* @param in input from the other machine
	* @throws IOException
	*/
    public void run(PrintWriter out, BufferedReader in) throws IOException;

}
