package sharePaint.views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sharePaint.controllers.CanvasController;

public class ColorChooser extends JFrame
{
	private JColorChooser cc = new JColorChooser();
	private final ColorChooser CC = this;
	private CanvasController ctrl;

	public ColorChooser(CanvasController ctrl)
	{
		add(cc, BorderLayout.CENTER);
		this.ctrl = ctrl;
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		add(buildButtonPanel(), BorderLayout.SOUTH);
		setSize(500, 300);
		setVisible(true);
	}

	public JPanel buildButtonPanel()
	{
		JPanel panel = new JPanel(new GridLayout(1, 2));

		JButton okay = new JButton("OK");
		panel.add(okay);
		okay.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				ctrl.setPenColor(cc.getColor());
				CC.dispose();
			}
		});

		JButton cancel = new JButton("Cancel");
		panel.add(cancel);
		cancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0)
			{
				CC.dispose();
			}
		});
		return panel;
	}
}
