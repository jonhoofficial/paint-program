package ca.utoronto.utm.paint.Shapes;

import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;

import ca.utoronto.utm.paint.Points;

/**
 * Subclass of {@link Shapes}, creates a group of straight lines connected to
 * one another.
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class Polyline extends Shapes {

	private ArrayList<Integer> xPoints;
	private ArrayList<Integer> yPoints;
	private int size;

	/**
	 * @param start
	 *            start coordinate of polyline
	 * @param col
	 *            color of the line
	 * @param stroke
	 *            stroke of the line
	 */
	public Polyline(Points start, Color col, Stroke stroke) {
		super(start, col, stroke);
		this.xPoints = new ArrayList<Integer>();
		this.yPoints = new ArrayList<Integer>();
		this.size = 0;
		this.calcForDraw(start);
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
		g.drawPolyline(this.boxIntoArray(xPoints), this.boxIntoArray(yPoints), this.size);
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
	 * @return x coordinates
	 */
	public ArrayList<Integer> getXPointsList() {
		return this.xPoints;
	}

	/**
	 * @return y coordinates
	 */
	public ArrayList<Integer> getYPointsList() {
		return this.yPoints;
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
	 * @return size of the polyline
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

	@Override
	public void accept(ShapesElementVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public void setFinished() {

	}

}
