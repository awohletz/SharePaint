package sharePaint.actions;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;


public class PointAction implements CanvasAction, Serializable
{
	private Color color;
	private int size;
	private int x, y;
	public PointAction(Color paintColor, int brushSize, int x, int y)
	{
		color = paintColor;
		size = brushSize;
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		g.fillOval(x - 5, y - 5, size, size);
	}

}
