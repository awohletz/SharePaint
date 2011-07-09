package sharePaint.views;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TopToolBar extends JPanel
{

	public TopToolBar()
	{
		setLayout(new GridLayout(1, 2)); 
		setSize(new Dimension(640, 60));
		JLabel test = new JLabel("This is a test of the top toolbar!");
		this.add(test);
		
		JButton print = new JButton("Print...");
		print.setToolTipText("Print the current image");
		add(print);
	}
}
