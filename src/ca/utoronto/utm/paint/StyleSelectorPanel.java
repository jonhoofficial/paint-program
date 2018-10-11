package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
/**
 * Creates the GUI view and controller for selecting color, and stroke.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
class StyleSelectorPanel extends JPanel implements ActionListener, ChangeListener {

	private View view; // So we can talk to our parent or other components of the view

	private String[] colourLabels = { "Red", "Green", "Orange", "Cyan", "Light Gray", "White", "Blue", "Yellow",
			"Magenta", "Pink", "Gray", "Black" };

	private Color[] colourList = { Color.RED, Color.GREEN, Color.ORANGE, Color.CYAN, Color.LIGHT_GRAY, Color.WHITE,
			Color.BLUE, Color.YELLOW, Color.MAGENTA, Color.PINK, Color.GRAY, Color.BLACK };

	private JButton colour, moreColour, invertColour;

	private JTextField viewColor; // View colour selected

	private JSlider thicknessSlider, optionSlider;

	private int rValue, newRValue, gValue, aValue, newGValue, bValue, newBValue, newAValue;

	private JRadioButton chooseFillColour, chooseOutlineColour;

	private ButtonGroup colourOptionGroup;

	private Boolean state = false; // default state is to select outline Color

	/**
	 * Creates the Style selection section of the GUI
	 * 
	 * @param view
	 *            The paint gui panel
	 */
	public StyleSelectorPanel(View view) {
		// Set entire section to a flow layout
		this.view = view;
		this.setLayout(new FlowLayout());

		/*
		 * =========================================== Create different panels for the
		 * various sections and set their respective layouts.
		 * ===========================================
		 */
		JPanel fillSelector = new JPanel(); // Fill Type Selector Panel
		fillSelector.setLayout(new GridLayout(1, 0));

		JPanel colourPanel = new JPanel(); // Colour Selection Panel
		colourPanel.setLayout(new FlowLayout());

		JPanel colourOption = new JPanel(); // Colour Selection Panel
		colourOption.setLayout(new GridLayout(2, 0));

		JPanel colourSelector = new JPanel(); // Colour Selection Panel
		colourSelector.setLayout(new GridLayout(2, 7));

		JPanel colourView = new JPanel(); // Display Colour View Panel
		colourView.setLayout(new GridLayout(1, 0));

		JPanel moreColourSelect = new JPanel(); // Display Colour View Panel
		moreColourSelect.setLayout(new GridLayout(2, 0));

		JPanel thicknessSelector = new JPanel(); // Thickness Selector Panel
		thicknessSelector.setLayout(new GridLayout(1, 0));

		/*
		 * =========================================== Components for the Fill Type
		 * Selection Area Select the fill and outline colour of the object.
		 * ===========================================
		 */
		// Fill and Outline Option Slider
		optionSlider = new JSlider(JSlider.HORIZONTAL, 1, 3, 3);
		optionSlider.setName("OptionSlider");
		optionSlider.setMinorTickSpacing(1);
		optionSlider.setMajorTickSpacing(3);
		optionSlider.setPaintTicks(true);
		optionSlider.setPaintLabels(true);
		optionSlider.setSnapToTicks(true);
		optionSlider.setPreferredSize(new Dimension(240, 80));
		// Set Labels for the different slider options
		Hashtable optionLabels = new Hashtable();
		optionLabels.put(1, new JLabel("Fill"));
		optionLabels.put(2, new JLabel("Fill + Outline"));
		optionLabels.put(3, new JLabel("Outline"));
		optionSlider.setLabelTable(optionLabels);

		/*
		 * =========================================== Components for the Fill/Outline
		 * Colour Selection ===========================================
		 */
		chooseFillColour = new JRadioButton("Fill Colour");
		chooseOutlineColour = new JRadioButton("Outline Colour", true);

		colourOptionGroup = new ButtonGroup();
		colourOptionGroup.add(chooseFillColour);
		colourOptionGroup.add(chooseOutlineColour);

		// Action Listeners for the JRadioButtons
		ActionListener colourOptionListener = new ActionListener() {
			public void actionPerformed(ActionEvent actionEvent) {
				AbstractButton source = (AbstractButton) actionEvent.getSource();
				// If the fill colour radio button is selected
				if (source.getText() == "Fill Colour") {
					state = true;
					viewColor.setBackground(view.getModel().getColor());
				}
				// If the outline radio button is selected
				else {
					state = false;
					viewColor.setBorder(BorderFactory.createLineBorder(view.getModel().getOutlineColor(), 3));
				}
			}
		};
		chooseFillColour.addActionListener(colourOptionListener);
		chooseOutlineColour.addActionListener(colourOptionListener);

		/*
		 * =========================================== Components for the Colour View
		 * Area Check what colour was selected for Paint
		 * ===========================================
		 */
		viewColor = new JTextField(); // Outline colour box
		viewColor.setBackground(Color.BLACK);
		viewColor.setEditable(false);
		viewColor.setPreferredSize(new Dimension(80, 80));
		viewColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		/*
		 * =========================================== Components for the Colour
		 * Selection Area + First Row: Red, Green, Orange, Cyan, Light Gray, White +
		 * Second Row: Blue, Yellow, Magenta, Pink, Gray, Black
		 * ===========================================
		 */
		int count = 0;
		// Create the buttons for each colour
		for (Color color : colourList) {
			String label = this.colourLabels[count];

			colour = new JButton(label);
			colour.setBackground(color);
			colour.setForeground(color);
			colour.setOpaque(true);
			colour.setBorderPainted(true);
			colour.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
			colour.setPreferredSize(new Dimension(40, 40));

			colourSelector.add(colour);
			colour.addActionListener(this);
			count = count + 1;
		}

		moreColour = new JButton("More");
		moreColour.setPreferredSize(new Dimension(80, 40));
		invertColour = new JButton("Invert");
		invertColour.setPreferredSize(new Dimension(80, 40));

		/*
		 * =========================================== Components for the Thickness Area
		 * Select the line thickness of the object/shape
		 * ===========================================
		 */
		thicknessSlider = new JSlider(JSlider.HORIZONTAL, 1, 5, 2);
		thicknessSlider.setName("ThicknessSlider");
		thicknessSlider.setMinorTickSpacing(1);
		thicknessSlider.setMajorTickSpacing(4);
		thicknessSlider.setPaintTicks(true);
		thicknessSlider.setPaintLabels(true);
		thicknessSlider.setSnapToTicks(true);
		thicknessSlider.setPreferredSize(new Dimension(225, 80));
		Hashtable labels = new Hashtable();
		labels.put(1, new JLabel("Thin"));
		labels.put(5, new JLabel("Thick"));
		thicknessSlider.setLabelTable(labels);

		/*
		 * =========================================== Titled Borders to different
		 * panels ===========================================
		 */
		TitledBorder fillBorder = new TitledBorder("Fill");
		fillSelector.setBorder(fillBorder);
		TitledBorder colourViewBorder = new TitledBorder("Colour");
		colourPanel.setBorder(colourViewBorder);
		TitledBorder lineBorder = new TitledBorder("Thickness");
		thicknessSelector.setBorder(lineBorder);

		/*
		 * =========================================== Adding components to panels and
		 * listeners ===========================================
		 */
		// Adding each component to a panel area
		fillSelector.add(optionSlider);
		colourView.add(viewColor);
		colourOption.add(chooseFillColour);
		colourOption.add(chooseOutlineColour);
		moreColourSelect.add(moreColour);
		moreColourSelect.add(invertColour);
		thicknessSelector.add(thicknessSlider);

		// Adding individual panel areas into the main panel
		colourPanel.add(colourOption);
		colourPanel.add(colourView);
		colourPanel.add(colourSelector);
		colourPanel.add(moreColourSelect);

		// Add Action Listeners and Change Listener to buttons and sliders respectively
		optionSlider.addChangeListener(this);
		thicknessSlider.addChangeListener(this);
		moreColour.addActionListener(this);
		invertColour.addActionListener(this);

		// Add sections to the view
		this.add(fillSelector);
		this.add(colourPanel);
		this.add(thicknessSelector);

	}

	@Override
	/**
	 * Controller aspect of this (Sliders)
	 */
	public void stateChanged(ChangeEvent evt) {
		JSlider source = (JSlider) evt.getSource();
		if (source.getName() == "OptionSlider") {
			if (!source.getValueIsAdjusting()) {
				int value = source.getValue();
				if (value == 1) { // Fill option selected
					view.getModel().setFilled(true);
					view.getModel().setOutlined(false);
				} else if (value == 3) { // Outline option selected
					view.getModel().setFilled(false);
					view.getModel().setOutlined(true);
				} else {
					view.getModel().setOutlined(true);
					view.getModel().setFilled(true);
					// FIX OUTLINE CODE
				}
			}
		} else {
			if (!source.getValueIsAdjusting()) {
				int value = source.getValue();
				view.getModel().setStroke(value);
			}
		}
	}

	@Override
	/**
	 * Controller aspect of this (Buttons)
	 */
	public void actionPerformed(ActionEvent e) {

		// One of the 12 basic colour buttons was selected
		if (Arrays.asList("Red", "Green", "Orange", "Cyan", "Light Gray", "White", "Blue", "Yellow", "Magenta", "Pink",
				"Gray", "Black").contains(e.getActionCommand())) {
			for (int i = 0; i < colourLabels.length; i++) {
				if (colourLabels[i] == e.getActionCommand()) {
					// If the set colour option is outline
					if (!state) {
						view.getModel().setOutlineColor(colourList[i]);
						// If the set colour option is fill
					} else {
						view.getModel().setColor(colourList[i]);
					}
				}
			}
			// Update the colour viewer
			viewColor.setBackground(view.getModel().getColor());
			viewColor.setBorder(BorderFactory.createLineBorder(view.getModel().getOutlineColor(), 3));
		}

		// More Colour Button was clicked
		else if (e.getActionCommand() == "More") {
			// Open a new frame containing the more colour palette
			colourChooserPanel colourFrame = new colourChooserPanel(this);
		}

		// Invert Colour Button was clicked
		else if (e.getActionCommand() == "Invert") {
			Color chosenColour;
			// Get the colours of the outline or fill
			// If outline colour option is selected
			if (!state) {
				chosenColour = view.getModel().getOutlineColor();
				// If fill colour option is selected
			} else {
				chosenColour = view.getModel().getColor();
			}
			rValue = chosenColour.getRed();
			gValue = chosenColour.getGreen();
			bValue = chosenColour.getBlue();
			aValue = chosenColour.getAlpha();
			newRValue = 255 - rValue;
			newGValue = 255 - gValue;
			newBValue = 255 - bValue;
			newAValue = aValue;
			Color newColour = new Color(newRValue, newGValue, newBValue, newAValue);
			// Set the colours
			if (!state) {
				view.getModel().setOutlineColor(newColour);
			} else {
				view.getModel().setColor(newColour);
			}
			// Update colour previewer
			viewColor.setBackground(view.getModel().getColor());
			viewColor.setBorder(BorderFactory.createLineBorder(view.getModel().getOutlineColor(), 3));
		}
	}

	/**
	 * Set the selected custom colour of the fill or outline.
	 * 
	 * @param Color
	 *            the custom colour that was selected
	 */
	public void setCustomColour(Color customColour) {
		// Sets the custom colour
		if (!state) {
			view.getModel().setOutlineColor(customColour);
		} else {
			view.getModel().setColor(customColour);
		}
		// Update the colour preview
		viewColor.setBackground(view.getModel().getColor());
		viewColor.setBorder(BorderFactory.createLineBorder(view.getModel().getOutlineColor(), 3));
	}

}
