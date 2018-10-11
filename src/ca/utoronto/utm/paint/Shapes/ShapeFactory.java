package ca.utoronto.utm.paint.Shapes;

import ca.utoronto.utm.paint.Points;
import java.awt.Stroke;

import javax.swing.JPanel;

import java.awt.Color;

/**
 * Used to determine shape type, and return proper object
 * 
 * @author whiskeyTangoFoxtrot
 *
 */
public class ShapeFactory {

	public Shapes getShape(String shapeType, Points start, Color col, Stroke stroke, boolean fill, int strokeWidth,
			boolean outlined, Color outlineColor, JPanel jp) {
		if (shapeType == null) {
			return null;
		}
		if (shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle(start, col, stroke, fill, outlined, outlineColor);
		}
		if (shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle(start, col, stroke, fill, outlined, outlineColor);
		}
		if (shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square(start, col, stroke, fill, outlined, outlineColor);
		}
		if (shapeType.equalsIgnoreCase("LINE")) {
			return new Line(start, col, stroke);
		}
		if (shapeType.equalsIgnoreCase("POLYLINE")) {
			return new Polyline(start, col, stroke);
		}
		if (shapeType.equalsIgnoreCase("SQUIGGLE")) {
			return new Squiggle(start, col, stroke);
		}
		if (shapeType.equalsIgnoreCase("POLYGON")) {
			return new Polygon(start, col, stroke, fill, outlined, outlineColor);
		}
		if (shapeType.equalsIgnoreCase("ERASER")) {
			return new Eraser(start, strokeWidth);
		}
		if (shapeType.equalsIgnoreCase("STAR")) {
			return new Star(start, col, stroke, fill, outlined, outlineColor);
		}
		if (shapeType.equalsIgnoreCase("BUCKET")) {
			return new BucketFill(start, col, stroke, jp);
		}
		return null;
	}

}
