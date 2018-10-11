package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Stroke;

import ca.utoronto.utm.paint.Points;

/**
 * Subclass of {@link Rectangle}. A rectangle with equal width and height.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class Square extends Rectangle {

	/**
	 * Create new square shape
	 * 
	 * @param start
	 *            start Points of the square
	 * @param colour
	 *            color of the square
	 * @param stroke
	 *            stroke of the square
	 * @param filled
	 *            whether the square is filled
	 */
	public Square(Points start, Color colour, Stroke stroke, Boolean filled, boolean outlined, Color outlineColor) {
		super(start, colour, stroke, filled, outlined, outlineColor);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.Rectangle#calcForDraw(ca.utoronto.utm.paint.
	 * Points)
	 */
	public void calcForDraw(Points p) {
		int difference = Math.min(Math.abs(p.getX() - this.Origin.getX()), Math.abs(p.getY() - this.Origin.getY()));

		if (p.getX() < this.Origin.getX() & !(p.getY() < this.Origin.getY())) {
			this.getStart().setX(this.Origin.getX() - difference);
			this.getStart().setY(this.Origin.getY());
			this.getBottomRight().setX(this.Origin.getX());
			this.getBottomRight().setY(this.Origin.getY() + difference);
		} else if (p.getY() < this.Origin.getY() && !(p.getX() < this.Origin.getX())) {
			this.getStart().setY(this.Origin.getY() - difference);
			this.getStart().setX(this.Origin.getX());
			this.getBottomRight().setY(this.Origin.getY());
			this.getBottomRight().setX(this.Origin.getX() + difference);
		} else if (p.getY() < this.Origin.getY() && p.getX() < this.Origin.getX()) {
			this.getStart().setX(this.Origin.getX() - difference);
			this.getStart().setY(this.Origin.getY() - difference);
			this.getBottomRight().setX(this.Origin.getX());
			this.getBottomRight().setY(this.Origin.getY());
		} else {
			this.getBottomRight().setX(this.Origin.getX() + difference);
			this.getBottomRight().setY(this.Origin.getY() + difference);
		}
	}
}
/**
 * Could USe better OO, just hard to deal with so much weird stuff
 **/
