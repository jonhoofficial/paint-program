package ca.utoronto.utm.paint.Shapes;

/**
 * interface for visitor design pattern
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public interface ShapesElement {
	/**
	 * @param visitor
	 *            visitor for accept functions
	 */
	void accept(ShapesElementVisitor visitor);
}
