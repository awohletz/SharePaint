package sharePaint.controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import sharePaint.actions.ClearAction;
import sharePaint.models.Canvas;
import sharePaint.models.Pen;
import sharePaint.models.ShapeTool;

public class CanvasController implements MouseMotionListener, MouseListener
{
	private int prevX, prevY;
	private Canvas canvas;
	private Pen pen;
	private ShapeTool shapeTool;
	private boolean rectMode = false;
	private boolean ovalMode = false;
	private boolean drawMode = true;
	private boolean circle = true; 
	private final int DEFAULT_PEN_SIZE = 10;
	private final Color DEFAULT_PEN_COLOR = Color.black;
	private final Color DEFAULT_BACKGROUND_COLOR = Color.white;

	public CanvasController(Canvas cm)
	{
		canvas = cm;
		pen = new Pen(cm, DEFAULT_PEN_SIZE, DEFAULT_PEN_COLOR);
		shapeTool = new ShapeTool(cm, pen.getColor(), DEFAULT_PEN_SIZE);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		

		if (drawMode)
		{
			pen.setDrawType(circle);
			pen.drawLine(prevX, prevY, x, y);
			prevX = x;
			prevY = y;
		}
		else if (rectMode)
		{
			//RIGHT HALF
			//Quadrant 4
			if (x > prevX && y > prevY)
			{
				shapeTool.drawRect(prevX, prevY, (x-prevX), (y-prevY));
			}
			//Quadrant 1
			else if (x > prevX && y < prevY)
			{
				shapeTool.drawRect(prevX, y, (x-prevX), (prevY - y));
			}
			//LEFT HALF
			//Quadrant 3
			else if (x  < prevX && y > prevY)
			{
				shapeTool.drawRect(x, prevY, (prevX - x), (y - prevY));
			}
			//Quadrant 2
			else if (x < prevX && y < prevY)
			{
				shapeTool.drawRect(x, y, (prevX-x), (prevY - y));
			}
			
		}
		else if (ovalMode)
		{
			//RIGHT HALF
			//Quadrant 4
			if (x > prevX && y > prevY)
			{
				shapeTool.drawOval(prevX, prevY, (x-prevX), (y-prevY));
			}
			//Quadrant 1
			else if (x > prevX && y < prevY)
			{
				shapeTool.drawOval(prevX, y, (x-prevX), (prevY - y));
			}
			//LEFT HALF
			//Quadrant 3
			else if (x  < prevX && y > prevY)
			{
				shapeTool.drawOval(x, prevY, (prevX - x), (y - prevY));
			}
			//Quadrant 2
			else if (x < prevX && y < prevY)
			{
				shapeTool.drawOval(x, y, (prevX-x), (prevY - y));
			}
		}
		
		e.consume();

	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		
		pen.setDrawType(circle);
		pen.drawPoint(x, y);

		
		prevX = x;
		prevY = y;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{

	}
	
	public void setPenColor(Color c)
	{
		pen = new Pen(canvas, pen.getSize(), c);
	}

	public void setPenSize(int size)
	{
		pen = new Pen(canvas, size, pen.getColor());
	}
	
	public void setShapeToolSize(int size)
	{
		shapeTool.setPenSize(size);
	}
	
	public void setDrawType(String type) //set either rectangle or oval draw. Not to be confused with point draw type. 
	{
		if (type.equals("rect"))
		{
			shapeTool.setColor(pen.getColor());
			rectMode = true;
			drawMode = false;
		}
		else if (type.equals("oval"))
		{
			shapeTool.setColor(pen.getColor());
			ovalMode = true;
			drawMode = false;
		}
		else
		{
			drawMode = true;
		}
	}
	
	public void setPointType(boolean circle)
	{
		this.circle = circle;
	}
	
	public void toggleEraseMode(boolean on)
	{
		shapeTool.toggleEraseMode(on);
		pen.toggleEraseMode(on);
	}
	
	public void clear()
	{
		canvas.draw(new ClearAction(DEFAULT_BACKGROUND_COLOR, canvas.getWidth(), canvas.getHeight()), true);
	}
	
}
