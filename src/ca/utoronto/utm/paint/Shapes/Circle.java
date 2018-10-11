package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Points;

/**
 * Subclass of {@link Shapes). Creates a circle centered at first click. with
 * radius of the last Points clicked.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class Circle extends Shapes {
	private int radius;

	/**
	 * Circle object to be made
	 * 
	 * @param start
	 *            middle Points
	 * @param colour
	 *            color that the circle will be
	 * @param stroke
	 *            stroke of the shape
	 * @param filled
	 *            whether shape is filled
	 */
	public Circle(Points start, Color colour, Stroke stroke, Boolean filled, Boolean outlined, Color outlineColor) {
		super(start, colour, stroke, filled, outlined, outlineColor);
		this.radius = 0;
	}

	/**
	 * @return radius of the circle
	 */
	public int getRadius() {
		return radius;
	}

	/**
	 * @param radius
	 *            the new radius of the circle
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Calculate the radius of the circle
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 */
	public void calculateRadius(int x, int y) {
		this.radius = (int) Math
				.sqrt(Math.pow(this.getStart().getX() - x, 2) + Math.pow(this.getStart().getY() - y, 2));
	}

	/**
	 * @return return diameter of circle
	 */
	public int getWidth() {
		return this.radius * 2;
	}

	/**
	 * @return top left Points of circle
	 */
	public Points getLeftCorner() {
		int x = this.getStart().getX() - this.radius;
		int y = this.getStart().getY() - this.radius;
		Points topleft = new Points(x, y);
		return topleft;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.utoronto.utm.paint.Shapes.Shapes#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g) {

		g.setColor(this.getColor());
		g.setStroke(this.getStroke());
		int x = this.getLeftCorner().getX();
		int y = this.getLeftCorner().getY();
		int width = this.getWidth();
		if (super.getFilled()) {
			g.fillOval(x, y, width, width);
			if (super.getOutlined()) {
				g.setColor(super.getOutlineColor());
				g.drawOval(x, y, width, width);
			}
		} else if (super.getOutlined()) {
			g.setColor(super.getOutlineColor());
			g.drawOval(x, y, width, width);
		} else {
			g.drawOval(x, y, width, width);
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
		this.calculateRadius(p.getX(), p.getY());
		this.setChanged();
		this.notifyObservers();

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
