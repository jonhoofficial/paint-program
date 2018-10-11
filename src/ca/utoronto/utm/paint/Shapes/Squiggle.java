package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Points;

/**
 * Subclass of {@link Shapes}, creates a group of dots where the mouse is
 * dragged connected by short lines.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class Squiggle extends Shapes {
	ArrayList<Points> points;

	/**
	 * Create new squiggle shape
	 * 
	 * @param start
	 *            start Points of the squiggle
	 * @param colour
	 *            color of the squiggle
	 * @param stroke
	 *            stroke of the squiggle
	 */
	public Squiggle(Points start, Color colour, Stroke stroke) {
		super(start, colour, stroke);
		points = new ArrayList<Points>();
		points.add(start);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.utoronto.utm.paint.Shapes.Shapes#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(this.getStroke());
		for (int i = 0; i < points.size() - 1; i++) {
			Points p1 = points.get(i);
			Points p2 = points.get(i + 1);
			g2d.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.Shapes#calcForDraw(ca.utoronto.utm.paint.Points)
	 */
	@Override
	public void calcForDraw(Points p) {
		points.add(p);

	}

	@Override
	public void accept(ShapesElementVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void setFinished() {
		this.finished = true;

	}

}
