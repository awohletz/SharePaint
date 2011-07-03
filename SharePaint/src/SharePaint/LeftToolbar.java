package SharePaint;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftToolbar extends JPanel
{
	private JComboBox sizeChooser;
	private JComboBox colorChooser;
	private JComboBox shapeChooser;
	private JColorChooser newColorChooser;
	/**
	 * @param cv
	 */
	public LeftToolbar(final CanvasPanel cv)
	{
		this.setLayout(new GridLayout(20, 1));
		this.setSize(60, 480);
		
		
		Integer[] brushSizes = new Integer[7];
		brushSizes[0] = 2;
		brushSizes[1] = 4;
		brushSizes[2] = 6;
		brushSizes[3] = 8;
		brushSizes[4] = 10;
		brushSizes[5] = 12;
		brushSizes[6] = 14;
		
		String[] colors = new String[13]; //only colors already defined in Java are currently available.
										 //There are thirteen such colors, but right now only 11 have been added to the list.
		colors[0] = "Black";
		colors[1] = "Dark Gray";
		colors[2] = "Gray";
		colors[3] = "Light Gray";
		colors[4] = "White";
		colors[5] = "Red";
		colors[6] = "Orange";
		colors[7] = "Yellow";
		colors[8] = "Green";
		colors[9] = "Blue";
		colors[10] = "Magenta";
		
		String[] shapes = new String[2];
		shapes[0] = "Square";
		shapes[1] = "Circle";
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cv.clearAndRepaint();
			}
		});
		this.add(clearButton);
		
		sizeChooser = new JComboBox(brushSizes);
		sizeChooser.setSelectedItem(brushSizes[4]); //default value is 10 x 10
		sizeChooser.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Integer size = (Integer)sizeChooser.getSelectedItem();
				cv.setBrushSize((int)size);
			}
		});
		this.add(sizeChooser);
		
		JButton changeColors = new JButton("Set Color...");
		changeColors.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				ColorChooser cc = new ColorChooser(cv);
			}
		});
		this.add(changeColors);
		
		shapeChooser = new JComboBox(shapes);
		shapeChooser.setSelectedItem(shapes[1]);
		shapeChooser.addActionListener(new ActionListener()
		{	
			@Override
			public void actionPerformed(ActionEvent ae)
			{
//				cv.setShape((String)shapeChooser.getSelectedItem());
			}
		});
		this.add(shapeChooser);
		
		JLabel tools = new JLabel("Tools");
		JButton drawRectangle = new JButton("Rectangle");
		drawRectangle.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				cv.drawRectangle();
			}
		});
		
		JButton drawOval = new JButton("Oval");
		
		this.add(tools);
		this.add(drawRectangle);
		this.add(drawOval);
		
		
		/*addFillerLabel();
		addFillerLabel();
		addFillerLabel();
		addFillerLabel();*/
		
	}
	
	public void addFillerLabel()
	{
		JLabel fillerLabel = new JLabel("         ");
		this.add(fillerLabel);
	}


}