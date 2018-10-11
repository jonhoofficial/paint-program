package ca.utoronto.utm.paint.Shapes;

/**
 * @author WhiskeyTangoFoxtrot
 *
 */
public interface ShapesElementVisitor {
	/**
	 * @param shapes
	 *            shape to visit
	 */
	void visit(Shapes shapes);

	/**
	 * @param circle
	 *            to visit
	 */
	void visit(Circle circle);

	/**
	 * @param eraser
	 *            to visit
	 */
	void visit(Eraser eraser);

	/**
	 * @param line
	 *            to visit
	 */
	void visit(Line line);

	/**
	 * @param polygon
	 *            to visit
	 */
	void visit(Polygon polygon);

	/**
	 * @param polyline
	 *            to visit
	 */
	void visit(Polyline polyline);

	/**
	 * @param rectangle
	 *            to visit
	 */
	void visit(Rectangle rectangle);

	/**
	 * @param square
	 *            to visit
	 */
	void visit(Square square);

	/**
	 * @param squiggle
	 *            to visit
	 */
	void visit(Squiggle squiggle);

	/**
	 * @param star
	 *            to visit
	 */
	void visit(Star star);

	void visit(BucketFill bucket);

}
