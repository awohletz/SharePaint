package SharePaint;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class CanvasPanel extends JPanel implements MouseMotionListener,
		MouseListener
{
	private int prevX, prevY;
	private BufferedImage backbuffer;
	private BufferedImage overlay;
	private Graphics2D overG;
	private Graphics2D backG;
	private int width;
	private int height;
	private int shapeWidth;
	private int shapeHeight;
	private int brushSize = 10; // default brushSize is 10
	private Color paintColor = Color.BLACK; // default paint color is black

	private boolean drawRect = false; // these flags are used for the shape-draw
										// functions
	private boolean drawOval = false;
	private boolean endShape = false;

	public CanvasPanel(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public void init()
	{

		backbuffer = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_ARGB);
		backG = (Graphics2D) backbuffer.getGraphics();
		overlay = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		overG = (Graphics2D) overlay.getGraphics();

		clear(backG, false);
		// clear(overG, true);

		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void setBrushSize(int size)
	{
		brushSize = size;
	}

	public void setColor(Color newColor)
	{
		paintColor = newColor;
	}

	public void drawRectangle()
	{
		drawRect = true;
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		// repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		backG.setColor(paintColor);
		backG.setStroke(new BasicStroke(brushSize, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND));
		shapeWidth = e.getX() - prevX;
		shapeHeight = e.getY() - prevY;

		if (!drawRect && !drawOval)
		{
			backG.drawLine(prevX, prevY, x, y);
			e.consume();
			prevX = x;
			prevY = y;
		}

		repaint();

	}

	public void update(Graphics g)
	{
		g.drawImage(backbuffer, 0, 0, this);

		if (drawRect || drawOval)
		{
			g.drawImage(overlay, 0, 0, this);
		}
	}

	public void paintComponent(Graphics g)
	{
		if (drawRect == true)
		{
			if (!endShape)
			{
				overG.setColor(paintColor);
				overG.drawRect(prevX, prevY, shapeWidth, shapeHeight);
			}
			else
			{
				clear(overG, true);
				backG.setColor(paintColor);
				backG.drawRect(prevX, prevY, shapeWidth, shapeHeight);
			}

		}
		update(g);
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
		int x = e.getX();
		int y = e.getY();
		if (!drawRect && !drawOval)
		{
			backG.setColor(paintColor);
			backG.fillOval(x - 5, y - 5, brushSize, brushSize);
			repaint();
		}
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
		endShape = false;
		overlay = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		overG = (Graphics2D) overlay.getGraphics();
		prevX = x;
		prevY = y;
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (drawRect || drawOval)
		{
			endShape = true;
			repaint();
		}

	}

	public void clearAndRepaint()
	{
		clear(backG, false);
		repaint();
	}

	public void clear(Graphics2D g, boolean transparent)
	{
		if (transparent)
		{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR,
					0.0f));
		}
		else
		{
			g.setColor(Color.WHITE);
		}
		g.fillRect(0, 0, width, height);
	}
}
