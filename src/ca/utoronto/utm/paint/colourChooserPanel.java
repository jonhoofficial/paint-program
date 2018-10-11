package ca.utoronto.utm.paint;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
/**
 * Creates the GUI view and controller for selecting color, and stroke.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
class colourChooserPanel extends JFrame implements ActionListener {

	private JFrame colourFrame; // Frame Creation

	private Color[] redList = { // List of Red Colours
			new Color(255, 0, 0, 255), new Color(153, 0, 0, 255), new Color(204, 0, 0, 255),
			new Color(224, 102, 102, 255), new Color(234, 153, 153, 255), new Color(244, 204, 204, 255) };

	private Color[] orangeList = { // List of Orange Colours
			new Color(255, 153, 0, 255), new Color(204, 102, 0, 255), new Color(230, 145, 56, 255),
			new Color(246, 178, 107, 255), new Color(249, 203, 156, 255), new Color(252, 229, 205, 255) };

	private Color[] yellowList = { // List of Yellow Colours
			new Color(255, 255, 0, 255), new Color(191, 144, 0, 255), new Color(241, 194, 50, 255),
			new Color(255, 217, 102, 255), new Color(255, 229, 153, 255), new Color(255, 242, 204, 255) };

	private Color[] greenList = { // List of Green Colours
			new Color(0, 255, 0, 255), new Color(56, 118, 29, 255), new Color(106, 168, 79, 255),
			new Color(147, 196, 125, 255), new Color(182, 215, 168, 255), new Color(217, 234, 211, 255), };

	private Color[] cyanList = { // List of Cyan Colours
			new Color(0, 255, 255, 255), new Color(19, 79, 92, 255), new Color(69, 129, 142, 255),
			new Color(118, 165, 175, 255), new Color(162, 196, 201, 255), new Color(208, 224, 227, 255), };

	private Color[] lightBlueList = { // List of Light Blue Colours
			new Color(74, 134, 232, 255), new Color(17, 85, 204, 255), new Color(60, 120, 216, 255),
			new Color(109, 158, 235, 255), new Color(164, 194, 244, 255), new Color(201, 218, 248, 255), };

	private Color[] blueList = { // List of Blue Colours
			new Color(0, 0, 255, 255), new Color(11, 83, 148, 255), new Color(61, 133, 198, 255),
			new Color(111, 168, 220, 255), new Color(159, 197, 232, 255), new Color(207, 226, 243, 255) };

	private Color[] purpleList = { // List of Purple Colours
			new Color(153, 0, 255, 255), new Color(53, 28, 117, 255), new Color(103, 78, 167, 255),
			new Color(142, 124, 195, 255), new Color(180, 167, 214, 255), new Color(217, 210, 233, 255) };

	private Color[] magentaList = { // List of Magenta Colours
			new Color(255, 0, 255, 255), new Color(116, 27, 71, 255), new Color(166, 77, 121, 255),
			new Color(194, 123, 160, 255), new Color(213, 166, 189, 255), new Color(234, 209, 220, 255) };

	private Color[] greyscaleList = { // List of Greyscale Colours
			new Color(255, 255, 255, 255), new Color(0, 0, 0, 255), new Color(67, 67, 67, 255),
			new Color(102, 102, 102, 255), new Color(153, 153, 153, 255), new Color(217, 217, 217, 255) };

	// Colour list categories for grid creation
	private Color[][] colourList = { redList, orangeList, yellowList, greenList, cyanList, lightBlueList, blueList,
			purpleList, magentaList, greyscaleList };

	private JButton moreColour, previewColour, cancel, selectColour;

	private JTextField viewColor, rColour, gColour, bColour;

	private JLabel instructionText, rText, gText, bText, blank, customMsg;

	private Color selectedColour;

	private int rValue, gValue, bValue, aValue;

	private StyleSelectorPanel stylePanel;
	private JTextField aColour;
	private JLabel aText;
	private JLabel swing_sucks;

	/**
	 * Creates the Style selection section of the GUI
	 * 
	 * @param view
	 *            The paint gui panel
	 */
	public colourChooserPanel(StyleSelectorPanel stylePanel) {
		// Set entire section to a flow layout
		super("More Colours");

		colourFrame = new JFrame("More Colours");
		this.stylePanel = stylePanel;

		/*
		 * =========================================== 
		 * Create different panels for the
		 * various sections and set their respective layouts.
		 * ===========================================
		 */
		JPanel entirePanel = new JPanel(); // Entire panel
		entirePanel.setLayout(new GridLayout(0, 1));

		JPanel moreColourPanel = new JPanel(); // Colour Grid Panel
		moreColourPanel.setLayout(new FlowLayout());

		JPanel bottomPanel = new JPanel(); // Bottom Half Panel
		bottomPanel.setLayout(new GridLayout(2, 1));

		JPanel customColourPanel = new JPanel(); // Custom Colours Panel
		customColourPanel.setLayout(new GridLayout(3, 3));

		JPanel previewSelectPanel = new JPanel(); // Preview and Selection Panel
		previewSelectPanel.setLayout(new FlowLayout());

		JPanel previewPanel = new JPanel(); // Preview Panel
		previewPanel.setLayout(new FlowLayout());

		JPanel selectPanel = new JPanel(); // Select Panel
		selectPanel.setLayout(new GridLayout(2, 1));

		/*
		 * =================================== 
		 * Colour Selection Grid
		 * ===================================
		 */
		int count = 0;
		// Create the buttons for each colour and place them into a grid
		while (count < 6) {
			for (Color[] category : colourList) {
				moreColour = new JButton("newColour");
				moreColour.setBackground(category[count]);
				moreColour.setForeground(category[count]);
				moreColour.setOpaque(true);
				moreColour.setBorderPainted(true);
				moreColour.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
				moreColour.setPreferredSize(new Dimension(30, 30));
				moreColourPanel.add(moreColour);
				moreColour.addActionListener(this);
			}
			count = count + 1;
		}
		/*
		 * =================================== 
		 * Custom Colour Values
		 * ===================================
		 */
		rText = new JLabel("Red [0-255] : ");
		rText.setHorizontalAlignment(SwingConstants.CENTER);
		gText = new JLabel("Green [0-255] : ");
		gText.setHorizontalAlignment(SwingConstants.CENTER);
		bText = new JLabel("Blue [0-255] : ");
		bText.setHorizontalAlignment(SwingConstants.CENTER);
		aText = new JLabel("Alpha [0-255] : ");
		aText.setHorizontalAlignment(SwingConstants.CENTER);

		rColour = new JTextField(5);
		rColour.setText("0");
		gColour = new JTextField(5);
		gColour.setText("0");
		bColour = new JTextField(5);
		bColour.setText("0");
		aColour = new JTextField();
		aColour.setText("255");

		customMsg = new JLabel("");
		blank = new JLabel("");
		swing_sucks = new JLabel("");
		previewColour = new JButton("Preview");
		// previewColour.setPreferredSize(new Dimension(200, 155));

		/*
		 * =================================== 
		 * View and Select Colour
		 * ===================================
		 */
		viewColor = new JTextField(); // Create Colour Preview
		viewColor.setBackground(Color.BLACK);
		viewColor.setEditable(false);
		viewColor.setPreferredSize(new Dimension(100, 100));
		viewColor.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
		cancel = new JButton("Cancel");
		cancel.setPreferredSize(new Dimension(200, 45));
		selectColour = new JButton("Confirm Selection");
		selectColour.setPreferredSize(new Dimension(200, 45));

		/*
		 * ===================================
		 *  Putting together the GUI
		 * ===================================
		 */
		// Create title borders around each section
		entirePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		TitledBorder defaultColourBorder = new TitledBorder("");
		moreColourPanel.setBorder(defaultColourBorder);
		TitledBorder customColourBorder = new TitledBorder("");
		customColourPanel.setBorder(customColourBorder);

		// Add Components to respective panel
		customColourPanel.add(rText);
		customColourPanel.add(gText);
		customColourPanel.add(bText);
		customColourPanel.add(aText);

		customColourPanel.add(rColour);
		customColourPanel.add(gColour);
		customColourPanel.add(bColour);
		customColourPanel.add(aColour);

		customColourPanel.add(customMsg);
		customColourPanel.add(blank);
		customColourPanel.add(swing_sucks);

		customColourPanel.add(previewColour);

		previewPanel.add(viewColor);
		selectPanel.add(selectColour);
		selectPanel.add(cancel);

		// Add Action Listeners to buttons
		previewColour.addActionListener(this);
		cancel.addActionListener(this);
		selectColour.addActionListener(this);

		// Add sections to the view
		bottomPanel.add(customColourPanel);
		bottomPanel.add(previewSelectPanel);
		previewSelectPanel.add(previewPanel);
		previewSelectPanel.add(selectPanel);
		entirePanel.add(moreColourPanel);
		entirePanel.add(bottomPanel);
		colourFrame.getContentPane().add(entirePanel);

		colourFrame.setVisible(true);
		colourFrame.setPreferredSize(new Dimension(385, 490));
		colourFrame.pack();
		colourFrame.setResizable(false);
	}

	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// If one of the colours in the colour grid is selected
		if (e.getActionCommand() == "newColour") {
			selectedColour = ((JButton) e.getSource()).getBackground();
			rValue = selectedColour.getRed();
			gValue = selectedColour.getGreen();
			bValue = selectedColour.getBlue();
			aValue = selectedColour.getAlpha();
			rColour.setText(Integer.toString(rValue));
			gColour.setText(Integer.toString(gValue));
			bColour.setText(Integer.toString(bValue));
			aColour.setText(Integer.toString(aValue));
			viewColor.setBackground(selectedColour);

		}
		// If the custom colour preview button is selected
		else if (e.getActionCommand() == "Preview") {
			customMsg.setText("");
			// Try setting the values of the custom colour
			try {
				rValue = Integer.parseInt(rColour.getText());
				gValue = Integer.parseInt(gColour.getText());
				bValue = Integer.parseInt(bColour.getText());
				aValue = Integer.parseInt(aColour.getText());
				// If the r,g,b,a values entered are within 0~255
				if ((rValue >= 0 && rValue <= 255) && (gValue >= 0 && gValue <= 255) && (bValue >= 0 && bValue <= 255)
						&& (aValue >= 0 && aValue <= 255)) {
					selectedColour = new Color(rValue, gValue, bValue, aValue);
					viewColor.setBackground(selectedColour);

				}
				// Values less than 0 and greater than 255
				else {
					customMsg.setText("<html><strong>Invalid Input</strong></html>");
				}
			}
			// Catech values that are not numbers/digits
			catch (NumberFormatException nfe) {
				customMsg.setText("<html><strong>Invalid Input</strong></html>");
			}
		}

		// If the button selected is cancel
		else if (e.getActionCommand() == "Cancel") {
			colourFrame.setVisible(false);
			colourFrame.dispose();
		}

		// If the button selected is confirm
		else if (e.getActionCommand() == "Confirm Selection") {
			selectedColour = viewColor.getBackground();
			this.stylePanel.setCustomColour(selectedColour);
			colourFrame.setVisible(false);
			colourFrame.dispose();
		}

	}

}
