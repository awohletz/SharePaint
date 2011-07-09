package sharePaint.actions;

import java.awt.Color;
import java.awt.Graphics2D;

public class ClearAction implements CanvasAction
{

	private Color bgColor;
	private int width;
	private int height;
	
	public ClearAction(Color backgroundColor, int width, int height)
	{
		bgColor = backgroundColor;
		this.width = width;
		this.height = height;
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(bgColor);
		g.fillRect(0, 0, width, height);
	}

}
