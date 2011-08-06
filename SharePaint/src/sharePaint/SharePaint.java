package sharePaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JApplet;

import sharePaint.controllers.CanvasController;
import sharePaint.models.Canvas;
import sharePaint.network.ActionListener;
import sharePaint.network.Server;
import sharePaint.views.CanvasView;
import sharePaint.views.LeftToolbar;
import sharePaint.views.TopToolBar;

public abstract class SharePaint extends JApplet
{
	private final int WIDTH = 640;
	private final int HEIGHT = 480;
	protected Canvas canvas;

	@Override
	public void init()
	{
		canvas = new Canvas(WIDTH, HEIGHT);
		CanvasController ctrl = new CanvasController(canvas);

		CanvasView canvasPanel = new CanvasView(WIDTH, HEIGHT);
		LeftToolbar lt = new LeftToolbar(ctrl);
		TopToolBar ttb = new TopToolBar();

		this.setLayout(new BorderLayout());

		this.add(canvasPanel, BorderLayout.CENTER);
		this.add(lt, BorderLayout.WEST);

		this.add(ttb, BorderLayout.NORTH);

		this.setSize(new Dimension(640, 480));
		this.setBackground(Color.white);

		canvasPanel.init(ctrl, canvas);
		ctrl.clear();

		initNetwork();
	}

	protected abstract void initNetwork();
}
