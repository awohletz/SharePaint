package sharePaint.actions;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;


public class LineAction implements CanvasAction, Serializable
{

	private Color paintColor;
	private int brushSize;
	private int x1, y1, x2, y2;
	private boolean circle = true;
	
	public LineAction(Color pc, int size, int x1, int y1, int x2, int y2, boolean circle)
	{
		paintColor = pc;
		brushSize = size;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.circle = circle; 
	}

	@Override
	public void draw(Graphics2D g)
	{
		g.setColor(paintColor);
		if (circle)
			g.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
		else
			g.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_SQUARE, BasicStroke.JOIN_BEVEL));
		
		g.drawLine(x1, y1, x2, y2);
	}

}
