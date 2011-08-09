package sharePaint;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import sharePaint.network.ActionListener;

public class SharePaintClient extends SharePaint
{

	@Override
	protected void initNetwork()
	{
		Socket hostSocket = null;
		String host = "localhost";
		try
		{
			hostSocket = new Socket(host, 2852);
			ObjectOutputStream out = new ObjectOutputStream(hostSocket.getOutputStream());
			out.flush();
			ObjectInputStream in = new ObjectInputStream(hostSocket.getInputStream());
			
			
			
			new ActionListener(canvas).clientConnected(in, out);
			canvas.clientConnected(in, out);
		}
		catch (UnknownHostException e)
		{
			Logger.getLogger(SharePaintClient.class.getName()).log(
					Level.SEVERE, "Unknown host: " + host, e);
			System.exit(1);
		}
		catch (IOException e)
		{
			Logger.getLogger(SharePaintClient.class.getName()).log(
					Level.SEVERE, "Connection failure to host: " + host, e);
			System.exit(1);
		}
	}

}
