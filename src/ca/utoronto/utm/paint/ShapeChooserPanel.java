package ca.utoronto.utm.paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/
/**
 * Creates GUI view and controller for choosing shapes.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
class ShapeChooserPanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	private View view; // So we can talk to our parent or other components of the view
	private JButton[] buttons;
	private Image[] icons;

	/**
	 * @param view
	 *            view instance to modify
	 */
	public ShapeChooserPanel(View view) {
		this.view = view;

		String[] buttonLabels = { "Circle", "Rectangle", "Square", "Squiggle", "Line", "Polyline", "Polygon", "Star",
				"Eraser" }; // , "Undo"};
		this.setLayout(new GridLayout(buttonLabels.length, 1));
		buttons = new JButton[buttonLabels.length];
		icons = new Image[buttonLabels.length];

		// Buttons to be used

		for (int x = 0; x < buttons.length; x++) {
			try {

				if (System.getProperty("os.name").toLowerCase() == "linux") {
					icons[x] = ImageIO.read(new FileInputStream("/resources/" + buttonLabels[x] + ".png"))
							.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
				} else {
					icons[x] = ImageIO.read(new FileInputStream("resources/" + buttonLabels[x] + ".png"))
							.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
				}
			} catch (IOException e) {
				System.out.println("Image not found :(, the image that could not be loaded/ does not exist is: "
						+ buttonLabels[x] + ".png");

			}

			// Attempt to load images for these buttons

			buttons[x] = new JButton(new ImageIcon(icons[x]));
			if (x == 0) {
				buttons[x].setEnabled(false);
			}
			try {
				if (System.getProperty("os.name").toLowerCase() == "linux") {
					buttons[x].setToolTipText(readFile("/resources/" + buttonLabels[x] + ".txt"));
				} else {
					buttons[x].setToolTipText(readFile("resources/" + buttonLabels[x] + ".txt"));
				}
			} catch (IOException e) {
				System.out.println("TXT file not found :(, the txt file that is missing/ could not be loaded is: "
						+ buttonLabels[x] + ".txt");
			}
			buttons[x].setActionCommand(buttonLabels[x]);
			this.add(buttons[x]);
			buttons[x].addActionListener(this);
			// Initialize buttons
		}

	}

	/**
	 * Controller aspect of this
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		this.view.getModel().setCurrent(e.getActionCommand());
		this.view.getPaintPanel().shape = null;
		resetButtons(e.getActionCommand());
	}

	public void resetButtons(String s) {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(buttons[i].getActionCommand() != s);
		}
	}

	public void resetAllButtons() {
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].setEnabled(true);
		}
	}

	private String readFile(String fileName) throws IOException {
		File file = new File(fileName);
		Scanner input = new Scanner(file);
		String constructed = "";

		while (input.hasNextLine())
			constructed += input.nextLine() + "\n";

		input.close();

		return constructed;
	}
}
