package sharePaint.controllers;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import sharePaint.actions.ClearAction;
import sharePaint.models.Canvas;
import sharePaint.models.Pen;

public class CanvasController implements MouseMotionListener, MouseListener
{
	private int prevX, prevY;
	private Canvas canvas;
	private Pen pen;
	private final int DEFAULT_PEN_SIZE = 10;
	private final Color DEFAULT_PEN_COLOR = Color.black;
	private final Color DEFAULT_BACKGROUND_COLOR = Color.white;

	public CanvasController(Canvas cm)
	{
		canvas = cm;
		pen = new Pen(cm, DEFAULT_PEN_SIZE, DEFAULT_PEN_COLOR);
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

		pen.drawLine(prevX, prevY, x, y);
		
		e.consume();
		prevX = x;
		prevY = y;

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

	public void clear()
	{
		canvas.draw(new ClearAction(DEFAULT_BACKGROUND_COLOR, canvas.getWidth(), canvas.getHeight()));
	}
	
}
