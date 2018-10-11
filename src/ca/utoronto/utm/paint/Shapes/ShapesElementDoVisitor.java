package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics2D;

/**
 * Implementation of visitor design pattern. What to do whenever specific shape
 * is visited
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class ShapesElementDoVisitor implements ShapesElementVisitor {

	static Graphics2D g;

	/**
	 * @param g2d
	 *            constructor, need this to get graphics for drawing.
	 */
	public ShapesElementDoVisitor(Graphics2D g2d) {
		g = g2d;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Shapes)
	 */
	@Override
	public void visit(Shapes shapes) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Circle)
	 */
	@Override
	public void visit(Circle circle) {
		circle.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Eraser)
	 */
	@Override
	public void visit(Eraser eraser) {
		eraser.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Line)
	 */
	@Override
	public void visit(Line line) {
		line.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Polygon)
	 */
	@Override
	public void visit(Polygon polygon) {
		polygon.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Polyline)
	 */
	@Override
	public void visit(Polyline polyline) {
		polyline.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Rectangle)
	 */
	@Override
	public void visit(Rectangle rectangle) {
		rectangle.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Square)
	 */
	@Override
	public void visit(Square square) {
		square.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Squiggle)
	 */
	@Override
	public void visit(Squiggle squiggle) {
		squiggle.draw(g);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ca.utoronto.utm.paint.Shapes.ShapesElementVisitor#visit(ca.utoronto.utm.paint
	 * .Shapes.Star)
	 */
	@Override
	public void visit(Star star) {
		star.draw(g);
	}

	@Override
	public void visit(BucketFill bucket) {
		bucket.draw(g);

	}

}
