package sharePaint.models;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import sharePaint.actions.CanvasAction;
import sharePaint.network.ConnectionListener;

public class Canvas implements ConnectionListener
{
	private BufferedImage backbuffer;
	private Graphics2D backG;
	private ArrayList<CanvasImageListener> listeners = new ArrayList<CanvasImageListener>();
	private final int width, height;
	private ObjectOutputStream client;

	public Canvas(int width, int height)
	{
		assert width > 0;
		assert height > 0;

		backbuffer = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		backG = (Graphics2D) backbuffer.getGraphics();

		this.width = width;
		this.height = height;
	}

	public void addListener(CanvasImageListener l)
	{
		listeners.add(l);
	}

	public void removeListener(CanvasImageListener l)
	{
		listeners.remove(l);
	}

	public void draw(CanvasAction action, boolean networkSend)
	{
		action.draw(backG);

		for (int n = 0; n < listeners.size(); n++)
		{
			listeners.get(n).imageUpdated(backbuffer);
		}

		if (networkSend && client != null)
		{
			try
			{
				System.out.println("Canvas writing action");
				client.writeObject(action);
				client.flush();
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	@Override
	public void clientConnected(ObjectInputStream in, ObjectOutputStream out)
	{
		System.out.println("Client connected in Canvas");
		client = out;
	}
}
