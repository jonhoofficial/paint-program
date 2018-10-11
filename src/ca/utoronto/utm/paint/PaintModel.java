package ca.utoronto.utm.paint;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.Observable;

import ca.utoronto.utm.paint.Shapes.Circle;
import ca.utoronto.utm.paint.Shapes.Shapes;

public class PaintModel extends Observable {

	// Notice with abstract superclass we don't need dumb extra arrays.

	private Circle pointer = new Circle(new Points(0, 0), Color.LIGHT_GRAY, new BasicStroke(1), false, false,
			Color.GRAY);
	private DrawStack drawStack = new DrawStack();
	private String current = "Circle";
	private Color color = Color.black;
	private Boolean filled = false;
	private Boolean outlined = false;
	private Color outlineColor = Color.BLACK;
	private int strokeWidth = 2;
	private Stroke stroke = new BasicStroke(2, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

	/**
	 * Adds shape onto array for later drawing.
	 * 
	 * @param s
	 *            a shape to be added to shapes array
	 */
	public void addShape(Shapes s) {
		drawStack.addShapes(s);
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Remove the last shape object made
	 */
	public void Undo() {
		this.drawStack.Undo();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Add the last shape object removed
	 */
	public void Redo() {
		this.drawStack.Redo();
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Returns Array holding all shapes, for {@link Graphics2D} to draw.
	 * 
	 * @return return the current shapes array
	 */
	public DrawStack getShapes() {
		return this.drawStack;
	}

	/**
	 * 
	 * @param p
	 * @param w
	 */
	public void changePointer(Points p, int w) {
		this.pointer.setColor(Color.LIGHT_GRAY);
		this.pointer.setStart(p);
		this.pointer.setRadius(w + 1);
		if (this.current == "Eraser") {
			this.pointer.setRadius(w * 4);
		}
		if (this.current == "Bucket") {
			this.pointer.setColor(
					new Color(Color.LIGHT_GRAY.getRed(), Color.LIGHT_GRAY.getGreen(), Color.LIGHT_GRAY.getBlue(), 0));
		}
		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * @return
	 */
	public Circle getPointer() {
		return this.pointer;
	}

	/**
	 * 
	 */
	public void New() {
		this.drawStack.Clear();
		this.setChanged();
		this.notifyObservers();
	}

	public void setCurrent(String s) {
		this.current = s;

	}

	public String getCurrent() {
		return this.current;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public int getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(int strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public Boolean getFilled() {
		return filled;
	}

	public void setFilled(Boolean filled) {
		this.filled = filled;
	}

	public Boolean getOutlined() {
		return outlined;
	}

	public void setOutlined(Boolean outlined) {
		this.outlined = outlined;
	}

	public Color getOutlineColor() {
		return outlineColor;
	}

	public void setOutlineColor(Color outlineColor) {
		this.outlineColor = outlineColor;
	}

	public Stroke getStroke() {
		return stroke;
	}

	public void setStroke(int w) {
		this.strokeWidth = w;
		this.stroke = new BasicStroke(w, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
	}
}
