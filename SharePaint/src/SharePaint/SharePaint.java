package SharePaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JApplet;

public class SharePaint extends JApplet
{
	private final int WIDTH = 640;
	private final int HEIGHT = 480;

	public SharePaint()
	{
	}

	public void init()
	{
		
		CanvasPanel canvasPanel = new CanvasPanel(WIDTH, HEIGHT);
		LeftToolbar lt = new LeftToolbar(canvasPanel);
		TopToolBar ttb = new TopToolBar();
		
		this.setLayout(new BorderLayout());
		
		this.add(canvasPanel, BorderLayout.CENTER);
		this.add(lt, BorderLayout.WEST);
		
		this.add(ttb, BorderLayout.NORTH);
		
		this.setSize(new Dimension(640, 480));
		this.setBackground(Color.white);
		
		canvasPanel.init();
	}

}
