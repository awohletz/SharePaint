package sharePaint.actions;

import java.awt.Color;
import java.awt.Graphics2D;

public class RectangleAction implements CanvasAction
{
	private Color color;
	private int x, y, width, height;

	public RectangleAction(Color color, int x, int y, int width, int height)
	{
		this.color = color;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		g.drawRect(x, y, width, height);
	}

}
