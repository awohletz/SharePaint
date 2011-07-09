package sharePaint.models;

import java.awt.Color;

import sharePaint.actions.RectangleAction;

public class ShapeTool
{
	private Canvas canvas;
	private Color color;
	
	public ShapeTool(Canvas canvas, Color color)
	{
		this.canvas = canvas;
		this.color = color;
	}

	public void drawRect(int x, int y, int width, int height)
	{
		canvas.draw(new RectangleAction(color, x, y, width, height));
	}
	
	public void drawOval(int x, int y, int width, int height)
	{
		
	}
	
}
