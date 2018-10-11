package ca.utoronto.utm.paint;

import java.awt.Graphics2D;
import java.util.ArrayList;

import ca.utoronto.utm.paint.Shapes.Shapes;

/**
 * Creates DrawStack model for PaintModel. Holds to Draw Stack, and Undo/Redo
 * Stack.
 * 
 * @author WhiskeyTangoFoxTrot
 * 
 */
public class DrawStack {
	private ArrayList<Shapes> toDraw;
	private ArrayList<Shapes> undoRedo;

	// Arrays that hold shapes that should be drawn, and that are for undo/ redo
	// actions

	/**
	 * Constructor, properly initializing arraylists
	 */
	public DrawStack() {
		this.toDraw = new ArrayList<Shapes>();
		this.undoRedo = new ArrayList<Shapes>();
	}

	/**
	 * Returns List of Shapes to draw.
	 * 
	 * @return the shapes that should be drawn on the panel
	 */
	public ArrayList<Shapes> getToDraw() {
		return this.toDraw;
	}

	/**
	 * Adds Shapes s to toDraw array
	 * 
	 * @param s
	 *            shape to add to panel/ toDraw
	 */
	public void addShapes(Shapes s) {
		this.toDraw.add(s);
		this.undoRedo.clear();
	}

	/**
	 * Empties both Arrays
	 */
	public void Clear() {
		this.toDraw.clear();
		this.undoRedo.clear();
	}

	/**
	 * removes Shapes s from toDraw array.
	 * 
	 * @param s
	 *            shape to remove from the toDraw array
	 */
	public void removeShapes(Shapes s) {
		this.toDraw.remove(s);
	}

	/**
	 * Checks if toDraw Array contains s.
	 * 
	 * @param s
	 *            shape to check for
	 * @return whether the toDraw array contains that shape
	 */
	public boolean Contains(Shapes s) {
		return toDraw.contains(s);
	}

	/**
	 * Pops last item in toDraw Array and pushes to undoRedo Array.
	 */
	public void Undo() {
		if (this.toDraw.size() > 0) {
			this.undoRedo.add(this.toDraw.get(this.toDraw.size() - 1));
			this.toDraw.remove(this.toDraw.size() - 1);
		}
	}

	/**
	 * Pops last item in undoRedo Array to toDraw Array.
	 */
	public void Redo() {
		if (this.undoRedo.size() > 0) {
			this.toDraw.add(this.undoRedo.get(this.undoRedo.size() - 1));
			this.undoRedo.remove(this.undoRedo.size() - 1);
		}
	}

	/**
	 * @param g2d
	 *            draw function for all shapes
	 */
	public void Draw(Graphics2D g2d) {
		for (Shapes i : this.toDraw) {
			i.draw(g2d);
		}
	}

}
