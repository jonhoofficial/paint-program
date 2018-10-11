package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import ca.utoronto.utm.paint.Points;
import java.util.ArrayList;

/**
 * Creates Star Shapes.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class Star extends Polygon {

	private int radius;
	final private Points[] BASE_POINTS = new Points[] { new Points(5, 9), new Points(6, 6), new Points(10, 6),
			new Points(7, 4), new Points(9, 0), new Points(5, 2), new Points(1, 0), new Points(3, 4), new Points(0, 6),
			new Points(4, 6) };

	/**
	 * Star shape created at it's origin Points start. If fill is true, then the
	 * shape will be filled with colour when drawn.
	 * 
	 * @param start
	 *            The origin Points where the shape will radiate out from
	 * @param col
	 *            The color the shape will take when filled
	 * @param stroke
	 *            The stroke thickness of this star
	 * @param fill
	 *            Boolean determining whether or not this shape is filled when drawn
	 * @param outlined
	 *            Boolean determining whether or not this shape will be drawn with
	 *            an outline
	 * @param outlineCol
	 *            The colour of the outline this shape will be drawn with
	 */
	public Star(Points start, Color col, Stroke stroke, boolean fill, boolean outlined, Color outlineCol) {
		super(start, col, stroke, fill, outlined, outlineCol);
		this.radius = 1;
		this.setPoints();
	}

	/**
	 * Calculates the radius based on the provided Points p, then sets the new
	 * points for this Star shape. Then notifies observers.
	 */
	@Override
	public void calcForDraw(Points p) {
		this.calculateRadius(p.getX(), p.getY());
		this.setPoints();
		this.setChanged();
		this.notifyObservers();
	}

	@Override
	public void startCalc(Points p) {
		return;
	}

	/**
	 * Sets this Stars' Points from the BASE_POINTS and scales based on this Shapes'
	 * radius.
	 */
	private void setPoints() {
		this.resetLists();
		for (Points p : this.BASE_POINTS) {
			Points newP = this.calcFromOrigin(p);
			super.addPoint(newP);
		}
	}

	/**
	 * Takes a Points p (from BASE_POINTS), and calculates its new location based on
	 * this Star's radius, as well as the position of the origin.
	 * 
	 * @param p
	 * @return
	 */
	private Points calcFromOrigin(Points p) {
		return new Points(this.getStart().getX() + (5 - p.getX()) * this.radius,
				this.getStart().getY() + (5 - p.getY()) * this.radius);
	}

	/**
	 * Draws this shape on the provided {@link Graphics2D}
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(this.getColor());
		g.setStroke(this.getStroke());
		if (super.getFilled()) {
			g.fillPolygon(this.getXPoints(), this.getYPoints(), super.getSize());
			if (super.getOutlined()) {
				g.setColor(super.getOutlineColor());
				g.drawPolygon(this.getXPoints(), this.getYPoints(), super.getSize());
			}
		} else if (super.getOutlined()) {
			g.setColor(super.getOutlineColor());
			g.drawPolygon(this.getXPoints(), this.getYPoints(), super.getSize());
		} else {
			g.drawPolygon(this.getXPoints(), this.getYPoints(), super.getSize());
		}
	}

	/**
	 * Calculates the radius of this Star based on the provided X and Y coordinates.
	 * 
	 * @param x
	 *            The X coordinate on the plane
	 * @param y
	 *            The Y coordinate on the plane
	 */
	public void calculateRadius(int x, int y) {
		this.radius = (int) Math
				.sqrt(Math.pow(this.getStart().getX() - x, 2) + Math.pow(this.getStart().getY() - y, 2));
		this.radius = this.radius / 2;
	}

	@Override
	public void setFinished() {
		this.finished = true;
	}

	@Override
	public void accept(ShapesElementVisitor visitor) {
		visitor.visit(this);
	}

}
