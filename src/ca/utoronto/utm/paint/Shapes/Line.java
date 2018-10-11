package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import ca.utoronto.utm.paint.Points;

/**
 * Creates Line Shapes.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class Line extends Shapes {

	private Points end;

	/**
	 * Line object created with the intial Points start, Color col, and Stroke
	 * stroke.
	 * 
	 * @param start
	 *            The point which indicates the initial point of the line
	 * @param col
	 *            The colour that the line will take when drawn
	 * @param stroke
	 *            The stroke that the line will take when drawn
	 */
	public Line(Points start, Color col, Stroke stroke) {
		super(start, col, stroke);
	}

	/**
	 * Draws this Line on the provided Graphics2D
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.getColor());
		g.setStroke(this.getStroke());
		g.drawLine(this.getStart().getX(), this.getStart().getY(), this.end.getX(), this.end.getY());
	}

	/**
	 * Replaces the end point for this line and notifies observers.
	 */
	@Override
	public void calcForDraw(Points p) {
		this.replaceEnd(p);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Sets/replaces the current end point for the line with the one provided by
	 * Points p
	 * 
	 * @param p
	 *            The Points which will be the new end spot for this line
	 */
	private void replaceEnd(Points p) {
		this.end = p;
	}

	/**
	 * Returns the Points that is the end of this line.
	 * 
	 * @return Returns a Points indicating the end of this line
	 */
	public Points getEnd() {
		return this.end;
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
