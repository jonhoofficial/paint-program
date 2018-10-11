package ca.utoronto.utm.paint.Shapes;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import ca.utoronto.utm.paint.PaintModel;
import ca.utoronto.utm.paint.Points;

/**
 * Subclass of {@link Shapes}, create a non-standard shape using a group of
 * points designated by mouse
 * 
 * @author WhiskeyTangoFoxtrot
 */
public class Polygon extends Shapes {

	private ArrayList<Integer> xPoints;
	private ArrayList<Integer> yPoints;
	private int size;

	/**
	 * create a new Polygon shape
	 * 
	 * @param start
	 *            first Points of the shape
	 * @param colour
	 *            color of the shape
	 * @param stroke
	 *            how thick each line will be
	 * @param fill
	 *            whether the shape is filled or not
	 */
	public Polygon(Points start, Color colour, Stroke stroke, Boolean fill, Boolean outlined, Color outlineColor) {
		super(start, colour, stroke, fill, outlined, outlineColor);
		this.xPoints = new ArrayList<Integer>();
		this.yPoints = new ArrayList<Integer>();
		this.size = 0;
		this.startCalc(start);
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
		if (super.getFilled()) {
			g.fillPolygon(boxIntoArray(xPoints), boxIntoArray(yPoints), this.getSize());
			if (super.getOutlined()) {
				g.setColor(super.getOutlineColor());
				g.drawPolygon(boxIntoArray(xPoints), boxIntoArray(yPoints), this.getSize());
			}
		} else if (super.getOutlined()) {
			g.setColor(super.getOutlineColor());
			g.drawPolygon(boxIntoArray(xPoints), boxIntoArray(yPoints), this.getSize());
		} else {
			g.drawPolygon(boxIntoArray(xPoints), boxIntoArray(yPoints), this.getSize());
		}

	}

	/**
	 * @param p
	 */
	public void startCalc(Points p) {
		if (this.size > 1) {
			this.changePoint(p);
		} else {
			this.addPoint(p);
		}
		this.setChanged();
		this.notifyObservers();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.Shapes#calcForDraw(ca.utoronto.utm.paint.Points)
	 */
	@Override
	public void calcForDraw(Points p) {
		if (this.size > 1) {
			this.changePoint(p);
		} else {
			this.addPoint(p);
		}
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * set Points to new Points
	 * 
	 * @param p
	 *            new Points
	 */
	private void changePoint(Points p) {
		this.xPoints.set(this.size - 1, p.getX());
		this.yPoints.set(this.size - 1, p.getY());
	}

	/**
	 * @return x coordinates
	 */
	public int[] getXPoints() {
		return boxIntoArray(this.xPoints);
	}

	/**
	 * @return y coordinates
	 */
	public int[] getYPoints() {
		return boxIntoArray(this.yPoints);
	}

	/**
	 * Add a Points
	 * 
	 * @param p
	 *            new Points to add
	 */
	@Override
	public void addPoint(Points p) {
		this.xPoints.add(p.getX());
		this.yPoints.add(p.getY());
		this.size++;
	}

	/**
	 * @return size of the polygon
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Convert array to list
	 * 
	 * @param list
	 *            array to convert
	 * @return list
	 */
	private int[] boxIntoArray(ArrayList<Integer> list) {
		int[] ret = new int[this.size];
		for (int i = 0; i < list.size(); i++) {
			ret[i] = list.get(i);
		}
		return ret;
	}

	/**
	 * @return return number of lines
	 */
	public int getNumLines() {
		return this.size - 1;
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

	public void resetLists() {
		this.xPoints = new ArrayList<Integer>();
		this.yPoints = new ArrayList<Integer>();
		this.size = 0;
	}

	public ArrayList<Integer> getXPointsList() {
		return this.xPoints;
	}

	public ArrayList<Integer> getYPointsList() {
		return this.yPoints;
	}

	@Override
	public void setFinished() {

	}

}
