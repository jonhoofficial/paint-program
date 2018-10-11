package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Points;

/**
 * Subclass of {@link Shapes} creates a rectangle with two opposite corners at
 * first click and last
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class Rectangle extends Shapes {

	protected int height;
	protected int width;
	protected Points Origin;
	protected Points bottomRight;

	/**
	 * Create new rectangle object
	 * 
	 * @param start
	 *            first coordinate of the rectangle
	 * @param colour
	 *            color of the rectangle
	 * @param stroke
	 *            stroke of the rectangle
	 * @param filled
	 *            whether it is filled
	 */
	public Rectangle(Points start, Color colour, Stroke stroke, Boolean filled, boolean outlined, Color outlineColor) {

		super(start, colour, stroke, filled, outlined, outlineColor);
		this.Origin = new Points(start.getX(), start.getY());
		this.bottomRight = new Points(start.getX(), start.getY());
	}

	/**
	 * @return width of the rectangle
	 */
	public int getWidth() {
		return this.getBottomRight().getX() - this.getStart().getX();
	}

	/**
	 * @return height of the rectangle
	 */
	public int getHeight() {
		return this.getBottomRight().getY() - this.getStart().getY();
	}

	/**
	 * @return bottom right/ end point of the rectangle
	 */
	public Points getBottomRight() {
		return this.bottomRight;

	}

	/**
	 * Set the bottom right point of the current rectangle
	 * 
	 * @param p
	 *            bottom right point
	 */
	public void setBottomRight(Points p) {
		this.bottomRight = p;
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
		int x = this.getStart().getX();
		int y = this.getStart().getY();
		int width = this.getWidth();
		int height = this.getHeight();
		if (super.getFilled()) {
			g.fillRect(x, y, width, height);
			if (super.getOutlined()) {
				g.setColor(super.getOutlineColor());
				g.drawRect(x, y, width, height);
			}
		} else if (super.getOutlined()) {
			g.setColor(super.getOutlineColor());
			g.drawRect(x, y, width, height);
		} else {
			g.drawRect(x, y, width, height);
		}
		// System.out.println(""+this.getStart().getX());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.Shapes#calcForDraw(ca.utoronto.utm.paint.Point)
	 */
	public void calcForDraw(Points p) {

		this.getBottomRight().setX(Math.max(p.getX(), this.Origin.getX()));
		this.getBottomRight().setY(Math.max(p.getY(), this.Origin.getY()));
		this.getStart().setX(Math.min(p.getX(), this.Origin.getX()));
		this.getStart().setY(Math.min(p.getY(), this.Origin.getY()));

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
/**
 * Need to Implemment an instance Origin, that is unchanging, that way when
 * calling it agian and again in constant updates, if the draw is in negative
 * quadrants, we can set bottom right ot always be origin, not changing, and
 * thus it wont keep creating empty shapes.
 */