package sharePaint.models;

import java.awt.Color;

import sharePaint.actions.RectangleAction;

public class ShapeTool
{
	private Canvas canvas;
	private Color color;
	private int brushSize = 10;
	private int formerX, formerY, formerWidth, formerHeight;
	
	public ShapeTool(Canvas canvas, Color color, int brushSize)
	{
		this.canvas = canvas;
		this.color = color;
		this.brushSize = brushSize;
	}
	
	public void setPenSize(int size)
	{
		brushSize = size;
	}
	
	public void drawRect(int x, int y, int width, int height)
	{
		
		canvas.draw(new RectangleAction(color, brushSize, x, y, width, height), true);
	}
	
	public void eraseFormerRectangle()
	{
		canvas.draw(new RectangleAction(Color.WHITE, brushSize, formerX, formerY, formerWidth, formerHeight), true);
	}
	
	public void setFormerRectCharacteristics(int formerX, int formerY, int formerWidth, int formerHeight)
	{
		this.formerX = formerX; 
		this.formerY = formerY;
		this.formerWidth = formerWidth;
		this.formerWidth = formerHeight;
	}
	
	public void drawOval(int x, int y, int width, int height)
	{
		
	}
	
	public void setColor(Color c)
	{
		color = c;
	}
	
}
