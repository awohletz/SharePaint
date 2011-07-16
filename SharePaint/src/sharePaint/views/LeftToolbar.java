package sharePaint.views;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import sharePaint.actions.RectangleAction;
import sharePaint.controllers.CanvasController;


public class LeftToolbar extends JPanel
{
	private JComboBox sizeChooser;
	private JComboBox colorChooser;
	private JComboBox shapeChooser;
	private JColorChooser newColorChooser;
	/**
	 * @param cv
	 */
	public LeftToolbar(final CanvasController ctrl)
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
		
		
		String[] shapes = new String[2];
		shapes[0] = "Square";
		shapes[1] = "Circle";
		
		JButton clearButton = new JButton("Clear");
		clearButton.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ctrl.clear();
			}
		});
		this.add(clearButton);
		
		//BRUSH SIZE CHOOSER
		sizeChooser = new JComboBox(brushSizes);
		sizeChooser.setSelectedItem(brushSizes[4]); //default value is 10 x 10
		sizeChooser.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent e)
			{
				Integer size = (Integer)sizeChooser.getSelectedItem();
				ctrl.setPenSize((int)size);
				ctrl.setShapeToolSize((int)size);
			}
		});
		this.add(sizeChooser);
		
		JButton changeColors = new JButton("Set Color...");
		changeColors.addActionListener(new ActionListener()
		{
			
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				ColorChooser cc = new ColorChooser(ctrl);
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
		
		JCheckBox drawRectangle = new JCheckBox("Draw Rectangle");
		drawRectangle.addItemListener(new ItemListener()
		{
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if (e.getStateChange() == ItemEvent.SELECTED)
				{
					ctrl.setDrawType("rect");
				}
				else
				{
					ctrl.setDrawType("draw");
				}
			}
		});
		
		JCheckBox drawOval = new JCheckBox("Draw Oval");
		drawOval.addItemListener(new ItemListener()
		{
			
			@Override
			public void itemStateChanged(ItemEvent e)
			{
				if(e.getStateChange() == ItemEvent.SELECTED)
				{
					ctrl.setDrawType("oval");
				}
				else 
				{
					ctrl.setDrawType("draw");
				}
				
			}
		});
		
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