package ca.utoronto.utm.paint.Shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.Observable;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Points;

/**
 * Abstract class to be used as Base for other shapes in PaintModel. Contains
 * basics a shape should have. Abstract methods to call through Arrays.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public abstract class Shapes extends Observable implements ShapesElement {
	private Color colour;
	private Stroke stroke;
	private Points start;
	private Boolean filled = false;
	private Boolean outlined = false;
	private Color outlineColor;
	protected Boolean finished = false;

	/**
	 * Create new shape instance
	 * 
	 * @param start
	 *            start point of the shape
	 * @param colour
	 *            color of the shape
	 * @param stroke
	 *            stroke of the shape
	 * @param fill
	 *            whether the shape is filled
	 */
	public Shapes(Points start, Color colour, Stroke stroke, Boolean fill, Boolean outlined, Color outlineColor) {
		this.colour = colour;
		this.stroke = stroke;
		this.filled = fill;
		this.start = start;
		this.outlined = outlined;
		this.outlineColor = outlineColor;
	}

	/**
	 * Create new shape instance
	 * 
	 * @param start
	 *            start point of the shape
	 * @param colour
	 *            color of the shape
	 * @param stroke
	 *            stroke of the shape
	 */
	public Shapes(Points start, Color colour, Stroke stroke) {
		this.colour = colour;
		this.stroke = stroke;
		this.start = start;
	}

	public void setColor(Color colour) {
		this.colour = colour;
	}

	public void setFilled(Boolean fill) {
		this.filled = fill;
	}

	public void addPoint(Points p) {
		return;
	}

	public boolean getFilled() {
		return this.filled;
	}

	public Color getOutlineColor() {
		return this.outlineColor;
	}

	public void setOutlined() {
		this.outlined = !this.outlined;
	}

	public Boolean getOutlined() {
		return this.outlined;
	}

	public void setStroke(Stroke stroke) {
		this.stroke = stroke;
	}

	public Stroke getStroke() {
		return this.stroke;
	}

	public Color getColor() {
		return this.colour;
	}

	public void setStart(Points p) {
		this.start = p;
	}

	public Points getStart() {
		return this.start;
	}

	public void accept(ShapesElementVisitor visitor) {
		// System.out.println("Please coach I'm begging you!");
		this.accept(visitor);
		visitor.visit(this);
	}

	/**
	 * add shape to the model
	 * 
	 * @param pm
	 *            model to modify
	 */
	public void addToModel(PaintModel pm) {
		if (!pm.getShapes().Contains(this)) {
			pm.addShape(this);
		} else {
			pm.getShapes().removeShapes(this);
			pm.addShape(this);
		}
	}

	public Boolean getFinished() {
		return this.finished;
	}

	/**
	 * Overridable function for drawing a shape
	 * 
	 * @param g
	 *            g2d instance
	 */
	public abstract void draw(Graphics2D g);

	/**
	 * Overridable function to calculate the draw
	 * 
	 * @param p
	 *            point
	 */
	public abstract void calcForDraw(Points p);

	public abstract void setFinished();

}
// Working on doing a SuperClass for better coding later, but still on trial
