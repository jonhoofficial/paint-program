package ca.utoronto.utm.paint;

import javax.swing.*;

import ca.utoronto.utm.paint.Shapes.*;

import ca.utoronto.utm.paint.Shapes.Polygon;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

// https://docs.oracle.com/javase/8/docs/api/java/awt/Graphics2D.html
// https://docs.oracle.com/javase/tutorial/2d/

class PaintPanel extends JPanel implements Observer, MouseMotionListener, MouseListener {

	PaintModel model; // slight departure from MVC, because of the way painting works
	private ShapeFactory shapeFactory = new ShapeFactory();

	private View view; // So we can talk to our parent or other components of the view
	Shapes shape;

	/**
	 * Initialize the paint panel
	 * 
	 * @param model
	 *            model instance to use
	 * @param view
	 *            view instance to use
	 */
	public PaintPanel(PaintModel model, View view) {
		this.setBackground(Color.WHITE);
		this.setPreferredSize(new Dimension(300, 300));
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		this.model = model;
		this.model.addObserver(this);

		this.view = view;
	}

	/**
	 * View aspect of this
	 */
	public void paintComponent(Graphics g) {
		// Use g to draw on the JPanel, lookup java.awt.Graphics in
		// the javadoc to see more of what this can do for you!!

		super.paintComponent(g); // paint background
		Graphics2D g2d = (Graphics2D) g; // lets use the advanced api
		g2d.setColor(this.model.getColor());

		DrawStack ds = this.model.getShapes();
		for (Shapes s : ds.getToDraw()) {

			s.accept(new ShapesElementDoVisitor(g2d));

		}

		g2d.setStroke(new BasicStroke(model.getStrokeWidth()));
		this.model.getPointer().draw(g2d);
		g2d.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) {
		// Not exactly how MVC works, but similar.
		this.repaint(); // Schedule a call to paintComponent
	}

	// MouseMotionListener below
	@Override
	public void mouseMoved(MouseEvent e) {
		this.model.changePointer(new Points(e.getX(), e.getY()), this.model.getStrokeWidth());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
		if (this.shape != null && e.getButton() != MouseEvent.BUTTON3) {
			this.model.changePointer(new Points(e.getX(), e.getY()), this.model.getStrokeWidth());
			this.shape.calcForDraw(new Points(e.getX(), e.getY()));
			this.shape.addToModel(this.model);
		}
	}

	// MouseListener below
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		Points start = new Points(e.getX(), e.getY());
		if ((this.shape == null)) {
			this.shape = shapeFactory.getShape(this.model.getCurrent(), start, this.model.getColor(),
					this.model.getStroke(), this.model.getFilled(), this.model.getStrokeWidth(),
					this.model.getOutlined(), this.model.getOutlineColor(), this);
		} else {
			if (e.getButton() == MouseEvent.BUTTON3) {
				this.shape = null;
			} else {
				shape.addPoint(start);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		if (this.shape != null && e.getButton() != MouseEvent.BUTTON3) {
			Points p = new Points(e.getX(), e.getY());
			this.shape.calcForDraw(p);
			this.shape.addToModel(this.model);
			this.shape.setFinished();
			if (this.shape.getFinished() == true) {
				this.shape = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
