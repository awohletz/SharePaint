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
	private JComboBox shapeChooser;
	private JCheckBox drawRectangle;
	private JCheckBox drawOval;
	private CanvasController controller;

	/**
	 * @param cv
	 */
	public LeftToolbar(final CanvasController ctrl)
	{
		this.setLayout(new GridLayout(20, 1));
		this.setSize(60, 480);
		controller = ctrl;
		
		Integer[] brushSizes = new Integer[7];
		brushSizes[0] = 2;
		brushSizes[1] = 4;
		brushSizes[2] = 6;
		brushSizes[3] = 8;
		brushSizes[4] = 10;
		brushSizes[5] = 12;
		brushSizes[6] = 14;

		String[] shapes = new String[2];
		shapes[0] = "Circle";
		shapes[1] = "Square";

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

		// BRUSH SIZE CHOOSER
		
		//BRUSH SIZE CHOOSER

		sizeChooser = new JComboBox(brushSizes);
		sizeChooser.setSelectedItem(brushSizes[4]); // default value is 10 x 10
		sizeChooser.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				Integer size = (Integer) sizeChooser.getSelectedItem();
				ctrl.setPenSize((int) size);
				ctrl.setShapeToolSize((int) size);
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

		// SHAPE CHOOSER JCOMBOBOX
		shapeChooser = new JComboBox(shapes);
		shapeChooser.setSelectedItem(shapes[0]);
		shapeChooser.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				if (shapeChooser.getSelectedIndex() == 0) // circle
					ctrl.setPointType(true);
				else if (shapeChooser.getSelectedIndex() == 1) // square
					ctrl.setPointType(false);
			}
		});
		this.add(shapeChooser);

		JLabel tools = new JLabel("Tools");

		drawRectangle = new JCheckBox("Draw Rectangle");

		RectangleItemListener ril = new RectangleItemListener();
		OvalItemListener oil = new OvalItemListener();

		drawRectangle = new JCheckBox("Draw Rectangle");
		drawRectangle.addItemListener(ril);

		drawOval = new JCheckBox("Draw Oval");
		drawOval.addItemListener(oil);

		this.add(tools);
		this.add(drawRectangle);
		this.add(drawOval);

		/*
		 * addFillerLabel(); addFillerLabel(); addFillerLabel();
		 * addFillerLabel();
		 */

	}

	public void addFillerLabel()
	{
		JLabel fillerLabel = new JLabel("         ");
		this.add(fillerLabel);
	}

	private class RectangleItemListener implements ItemListener
	{
		
		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				drawOval.setSelected(false);
				controller.setDrawType("rect");
			}
			else
			{
				controller.setDrawType("draw");
			}
		}
	}
	
	private class OvalItemListener implements ItemListener
	{

		@Override
		public void itemStateChanged(ItemEvent e)
		{
			if (e.getStateChange() == ItemEvent.SELECTED)
			{
				drawRectangle.setSelected(false);
				controller.setDrawType("oval");
			}
			else
			{
				controller.setDrawType("draw");
			}
		}
	}

}
