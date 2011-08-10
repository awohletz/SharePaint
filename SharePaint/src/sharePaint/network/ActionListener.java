package sharePaint.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import sharePaint.actions.CanvasAction;
import sharePaint.models.Canvas;

public class ActionListener implements Runnable, ConnectionListener
{
	private Canvas canvas;
	private ObjectInputStream client;

	public ActionListener(Canvas canvas)
	{
		this.canvas = canvas;
	}

	@Override
	public void run()
	{
		// TODO: end loop properly when client disconnect
		while (true)
		{
			try
			{
				System.out.println("ActionListener receiving action");
				canvas.draw((CanvasAction) client.readObject(), false);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (ClassNotFoundException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void clientConnected(ObjectInputStream in, ObjectOutputStream out)
	{
		client = in;
		new Thread(this).start();
	}

}
