package ca.utoronto.utm.paint.Shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import ca.utoronto.utm.paint.Points;

/**
 * Eraser, extends squiggle, constant white line
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class Eraser extends Squiggle {

	// new BasicStroke(this.strokeWidth * 4)

	/**
	 * @param start
	 *            position of erased line
	 * @param strokeWidth
	 *            the width of the erased line
	 */
	public Eraser(Points start, int strokeWidth) {
		super(start, Color.WHITE,
				new BasicStroke((strokeWidth + 2) * 4, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
	}

}
