package sharePaint.actions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;


public class PointAction implements CanvasAction, Serializable
{
	private Color color;
	private int size;
	private int x, y;
	private boolean circle = true; //if true, mouse click draws circle. if false, mouse click draws square. Default is circle 
	
	public PointAction(Color paintColor, int brushSize, int x, int y, boolean circle)
	{
		color = paintColor;
		size = brushSize;
		this.x = x;
		this.y = y;
		this.circle = circle; 
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		if (circle)
		{
			g.fillOval(x - 5, y - 5, size, size);
		}
		else
			g.fillRect(x - 5, y - 5, size, size);
	}

}
