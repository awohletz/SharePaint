package sharePaint.models;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import sharePaint.CanvasListener;
import sharePaint.actions.CanvasAction;

public class Canvas
{
	private BufferedImage backbuffer;
	private Graphics2D backG;
	private ArrayList<CanvasListener> listeners = new ArrayList<CanvasListener>();
	private int width, height;
	
	public Canvas(int width, int height)
	{
		backbuffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		backG = (Graphics2D) backbuffer.getGraphics();
		
		this.width = width;
		this.height = height;
	}
	
	public void addListener(CanvasListener l)
	{
		listeners.add(l);
	}
	
	public void removeListener(CanvasListener l)
	{
		listeners.remove(l);
	}

	public void draw(CanvasAction action)
	{
		action.draw(backG);
		
		for(int n = 0; n < listeners.size(); n++)
		{
			listeners.get(n).imageUpdated(backbuffer);
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
}
