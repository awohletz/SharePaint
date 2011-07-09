package sharePaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JApplet;

import sharePaint.controllers.CanvasController;
import sharePaint.models.Canvas;
import sharePaint.views.CanvasView;
import sharePaint.views.LeftToolbar;
import sharePaint.views.TopToolBar;

public class SharePaint extends JApplet
{
	private final int WIDTH = 640;
	private final int HEIGHT = 480;

	public SharePaint()
	{
	}

	public void init()
	{
		Canvas model = new Canvas(WIDTH, HEIGHT);
		CanvasController ctrl = new CanvasController(model);
		
		CanvasView canvasPanel = new CanvasView(WIDTH, HEIGHT);
		LeftToolbar lt = new LeftToolbar(ctrl);
		TopToolBar ttb = new TopToolBar();
		
		this.setLayout(new BorderLayout());
		
		this.add(canvasPanel, BorderLayout.CENTER);
		this.add(lt, BorderLayout.WEST);
		
		this.add(ttb, BorderLayout.NORTH);
		
		this.setSize(new Dimension(640, 480));
		this.setBackground(Color.white);
		
		canvasPanel.init(ctrl, model);
		ctrl.clear();
	}

}
