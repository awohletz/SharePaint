package sharePaint.views;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import sharePaint.CanvasListener;
import sharePaint.controllers.CanvasController;
import sharePaint.models.Canvas;

public class CanvasView extends JPanel implements CanvasListener
{
	private BufferedImage overlay;
	private BufferedImage backbuffer;
	private Graphics2D overG;
	private int width;
	private int height;

	public CanvasView(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	public void init(CanvasController ctrl, Canvas model)
	{
		overlay = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		overG = (Graphics2D) overlay.getGraphics();
		clearOverlay();

		model.addListener(this);
		addMouseMotionListener(ctrl);
		addMouseListener(ctrl);
	}

	public void update(Graphics g)
	{
		g.drawImage(backbuffer, 0, 0, this);
	}

	public void paintComponent(Graphics g)
	{
		update(g);
	}

	public void clearOverlay()
	{
		overG.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR, 0.0f));
		overG.fillRect(0, 0, width, height);
	}

	@Override
	public void imageUpdated(BufferedImage img)
	{
		backbuffer = img;
		repaint();
	}
}
