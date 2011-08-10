package sharePaint;

import java.io.IOException;

import sharePaint.network.ActionListener;
import sharePaint.network.Server;

public class SharePaintServer extends SharePaint
{

	@Override
	protected void initNetwork()
	{
		// connect to server
		Server con;
		try
		{
			con = new Server(2852);
			con.addConnectionListener(new ActionListener(canvas));
			con.addConnectionListener(canvas);
			new Thread(con).start();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
