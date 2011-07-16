package sharePaint.actions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class RectangleAction implements CanvasAction
{
	private Color color;
	private int x, y, width, height, brushSize;

	public RectangleAction(Color color, int brushSize, int x, int y, int width, int height)
	{
		this.color = color;
		this.brushSize = brushSize;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(color);
		g.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		g.drawRect(x, y, width, height);
	}

}
