package ca.utoronto.utm.paint;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.*;
import java.awt.Toolkit;

/**
 * This is the top level View+Controller, it contains other aspects of the
 * View+Controller.
 * 
 * @author arnold
 *
 */
public class View extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private PaintModel model;

	// The components that make this up
	private PaintPanel paintPanel;
	private ShapeChooserPanel shapeChooserPanel;
	private StyleSelectorPanel styleSelectorPanel;

	public View(PaintModel model) {
		super("Paint"); // set the title and do other JFrame init

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setJMenuBar(createMenuBar());

		Container c = this.getContentPane();
		this.shapeChooserPanel = new ShapeChooserPanel(this);
		c.add(this.shapeChooserPanel, BorderLayout.WEST);

		this.styleSelectorPanel = new StyleSelectorPanel(this);
		c.add(this.styleSelectorPanel, BorderLayout.SOUTH);

		this.setModel(model);

		this.paintPanel = new PaintPanel(model, this);

		setUpHotkeys(this.getModel());

		c.add(this.paintPanel, BorderLayout.CENTER);

		this.pack();
		this.setVisible(true);
		getModel().setOutlined(true);
	}

	// Some functions for action commands

	// End of functions for action commands

	public PaintPanel getPaintPanel() {
		return paintPanel;
	}

	public ShapeChooserPanel getShapeChooserPanel() {
		return shapeChooserPanel;
	}

	public StyleSelectorPanel getStyleSelectorPanell() {
		return styleSelectorPanel;
	}

	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		JMenu menu;
		JMenuItem menuItem;

		// FILE MENU
		menu = new JMenu("File");

		menuItem = new JMenuItem("New");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Exit");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		// EDIT MENU
		menu = new JMenu("Edit");

		menuItem = new JMenuItem("Undo (CTRL/CMD + Z)");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Redo (CTRL/CMD + SHIFT + Z)");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		// TOOLS MENU
		menu = new JMenu("Tools");

		menuItem = new JMenuItem("Circle Mode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Rectangle Mode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Square Mode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Squiggle Mode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Line Mode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Polyline Mode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Polygon Mode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Star Shape Mode");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menu.addSeparator();// -------------

		menuItem = new JMenuItem("Eraser");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		// COLOUR MENU
		menu = new JMenu("Colour");

		menuItem = new JMenuItem("More Colour Palette");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		// WINDOW MENU
		menu = new JMenu("Window");

		menuItem = new JMenuItem("Maximize");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuItem = new JMenuItem("Minimize");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		// BETA MENU
		menu = new JMenu("Beta Features");

		menuItem = new JMenuItem("Flood Fill");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		menu.addSeparator();// -------------
		menuItem = new JMenuItem("About Beta Features");
		menuItem.addActionListener(this);
		menu.add(menuItem);

		menuBar.add(menu);

		return menuBar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "New") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Circle");
			this.getShapeChooserPanel().resetButtons("Circle");
			this.getModel().New();
		}

		if (e.getActionCommand() == "Exit") {
			// Dialogue box appears confirming program exit
			int exitConfirmation = JOptionPane.showConfirmDialog(null, "Are you sure you want to quit the program?"
					+ System.lineSeparator() + "Any unsaved changed will be lost.", "Quit Paint",
					JOptionPane.YES_NO_OPTION);
			// If the selection is yes (to quit game)
			if (exitConfirmation == JOptionPane.YES_OPTION) {
				System.exit(0); // Quit the game
			}
		}

		else if (e.getActionCommand() == "Undo (CTRL/CMD + Z)") {
			this.getModel().Undo();
		} else if (e.getActionCommand() == "Redo (CTRL/CMD + SHIFT + Z)") {
			this.getModel().Redo();
		}

		else if (e.getActionCommand() == "Maximize") {
			this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		} else if (e.getActionCommand() == "Minimize") {
			this.setState(this.ICONIFIED);
		}

		else if (e.getActionCommand() == "Flood Fill") {
			// Dialogue box appears with warning about flood fill
			int floodConfirmation = JOptionPane.showConfirmDialog(null,
					"You are about to activate the 'Flood Fill' mode." + System.lineSeparator() + System.lineSeparator()
							+ "Flood Fill currently works only on very small enclosed shapes." + System.lineSeparator()
							+ "Bigger enclosed shapes will take time to fill in or not finish filling in."
							+ System.lineSeparator()
							+ "If not enclosed, a line will be drawn to the edge of the canvas."
							+ System.lineSeparator() + System.lineSeparator()
							+ "Are you sure you want to use the flood fill option.",
					"Use Flood Fill", JOptionPane.OK_CANCEL_OPTION);

			// If the user selects yes to use flood fill
			if (floodConfirmation == JOptionPane.YES_OPTION) {
				JOptionPane.showMessageDialog(null, "Flood Fill Mode Activated." + System.lineSeparator()
						+ "To turn off flood fill, select one of the options from the sidebar or in the tools menu.");
				this.getModel().setCurrent("Bucket");
				this.getShapeChooserPanel().resetAllButtons();
			}
		}

		else if (e.getActionCommand() == "About Beta Features") {
			JOptionPane.showMessageDialog(null,
					"Beta Features are implemented features that are not considered stable for official release and"
							+ System.lineSeparator()
							+ "is still being improved upon. Use at your own risk and note there may be bugs when using these features.");
		}

		else if (e.getActionCommand() == "More Colour Palette") {
			colourChooserPanel colourFrame = new colourChooserPanel(styleSelectorPanel);
		}

		else if (e.getActionCommand() == "Circle Mode") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Circle");
			this.getShapeChooserPanel().resetButtons("Circle");
		}

		else if (e.getActionCommand() == "Rectangle Mode") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Rectangle");
			this.getShapeChooserPanel().resetButtons("Rectangle");
		}

		else if (e.getActionCommand() == "Square Mode") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Square");
			this.getShapeChooserPanel().resetButtons("Square");
		}

		else if (e.getActionCommand() == "Squiggle Mode") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Squiggle");
			this.getShapeChooserPanel().resetButtons("Squiggle");
		}

		else if (e.getActionCommand() == "Line Mode") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Line");
			this.getShapeChooserPanel().resetButtons("Line");
		}

		else if (e.getActionCommand() == "Polyline Mode") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Polyline");
			this.getShapeChooserPanel().resetButtons("Polyline");
		}

		else if (e.getActionCommand() == "Polygon Mode") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Polygon");
			this.getShapeChooserPanel().resetButtons("Polygon");
		}

		else if (e.getActionCommand() == "Star Shape Mode") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Star");
			this.getShapeChooserPanel().resetButtons("Star");
		}

		else if (e.getActionCommand() == "Eraser") {
			this.getPaintPanel().shape = null;
			this.getModel().setCurrent("Eraser");
			this.getShapeChooserPanel().resetButtons("Eraser");
		}

	}

	/**
	 * Sets up hotkeys that binds to commands on the given PaintModel m.
	 * 
	 * @param m
	 *            The PaintModel that will be manipulated with the hotkeys.
	 */
	private void setUpHotkeys(PaintModel m) {
		String UNDO = "UndoAction";
		String REDO = "RedoAction";

		Action undoAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				m.Undo();
			}
		};
		Action redoAction = new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				m.Redo();
			}
		};

		this.paintPanel.getActionMap().put(UNDO, undoAction);
		this.paintPanel.getActionMap().put(REDO, redoAction);

		InputMap[] maps = new InputMap[] { this.paintPanel.getInputMap(JComponent.WHEN_FOCUSED),
				this.paintPanel.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT),
				this.paintPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW), };
		for (InputMap i : maps) {
			i.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), UNDO);
			i.put(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
					Toolkit.getDefaultToolkit().getMenuShortcutKeyMask() | java.awt.Event.SHIFT_MASK), REDO);
		}
	}

	public PaintModel getModel() {
		return model;
	}

	public void setModel(PaintModel model) {
		this.model = model;
	}
}
