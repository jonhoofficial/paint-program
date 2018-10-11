package ca.utoronto.utm.paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

import ca.utoronto.utm.paint.Points;

/**
 * Action performed for bucketFill "shape" type. This is what calls and performs
 * all actions for the bucket fill
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class BucketFill extends Shapes {
	ArrayList<Point> points = new ArrayList<Point>();
	Color original;
	FillAlgorithm fa = new FillAlgorithm();
	// Declarations of variables

	/**
	 * @param start
	 *            starting point of the shape
	 * @param colour
	 *            color of the shape
	 * @param stroke
	 *            stroke of the shape
	 * @param jp
	 *            jpanel reference
	 */
	public BucketFill(Points start, Color colour, Stroke stroke, JPanel jp) {
		super(start, colour, stroke);
		try {
			fa.Fill(this, start, jp);
		} catch (Exception e) {

		}
	}

	public ArrayList<Point> getPoints() {
		return this.points;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.Shapes#calcForDraw(ca.utoronto.utm.paint.Points)
	 */
	@Override
	public void calcForDraw(Points p) {
		points.add(new Point(p.getX(), p.getY()));

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.utoronto.utm.paint.Shapes.Shapes#draw(java.awt.Graphics2D)
	 */
	@Override
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.getColor());
		g2d.setStroke(new BasicStroke(1));
		if (points.size() > 1) {
			for (Point i : points) {
				g2d.drawLine((int) i.getX(), (int) i.getY(), (int) i.getX(), (int) i.getY());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.utoronto.utm.paint.Shapes.Shapes#setFinished()
	 */
	@Override
	public void setFinished() {
		this.finished = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ca.utoronto.utm.paint.Shapes.Shapes#accept(ca.utoronto.utm.paint.Shapes.
	 * ShapesElementVisitor)
	 */
	@Override
	public void accept(ShapesElementVisitor visitor) {
		visitor.visit(this);
	}
}
