package ca.utoronto.utm.paint.Shapes;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import ca.utoronto.utm.paint.Points;

/**
 * This is where all calculations for bucketfill are done
 * 
 * @author WhiskeyTangoFoxtrot
 *
 */
public class FillAlgorithm {
	static private int origin;
	// Starting point of the algorithm

	/**
	 * @param bf
	 *            bucketFill object to reference
	 * @param p
	 *            points to parse
	 * @param jp
	 *            JPanel reference
	 */
	public void Fill(BucketFill bf, Points p, JPanel jp) {

		BufferedImage bi = new BufferedImage(jp.getWidth(), jp.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.createGraphics();
		jp.print(g);
		g.dispose();
		origin = bi.getRGB(p.getX(), p.getY());
		// Convert to buffered image type

		Rrecurse(origin, bf, new Point(p.getX() + 1, p.getY()), bi);
		Lrecurse(origin, bf, new Point(p.getX(), p.getY()), bi);
		// Recursive calls to attempt fill

	}

	/**
	 * Recursive call on left side
	 * 
	 * @param origin
	 *            Starting point of current iteration of algorithm
	 * @param bf
	 *            bucketFill object to reference
	 * @param p
	 *            points to parse
	 * @param bi
	 *            bufferedImage to check
	 */

	public void Lrecurse(int origin, BucketFill bf, Point p, BufferedImage bi) {
		Point p1 = new Point((int) p.getX(), (int) p.getY());
		// New point
		while ((bi.getRGB((int) p1.getX(), (int) p1.getY()) == origin) && (!bf.getPoints().contains(p1))
				&& p1.getY() > 0 && p1.getY() < bi.getHeight()) {
			bf.calcForDraw(new Points((int) p1.getX(), (int) p1.getY()));
			// Calculate how to draw bucketFill
			if (!bf.getPoints().contains(new Point((int) p1.getX() - 1, (int) p1.getY())) && p1.getX() > 0
					&& p1.getX() < bi.getWidth()) {

				Lrecurse(origin, bf, new Point((int) p1.getX() - 1, (int) p1.getY()), bi);
				// Recurse left

			}
			p1 = new Point((int) p1.getX(), (int) p1.getY() + 1);
			// New point to perform action on
		}
		p1 = new Point((int) p.getX(), (int) p.getY() - 1);
		// New point to perform action on
		while ((bi.getRGB((int) p1.getX(), (int) p1.getY()) == origin) && (!bf.getPoints().contains(p1))
				&& p1.getY() > 0 && p1.getY() < bi.getHeight()) {
			bf.calcForDraw(new Points((int) p1.getX(), (int) p1.getY()));
			// Calculate how to draw bucketFill
			if (!bf.getPoints().contains(new Point((int) p1.getX() - 1, (int) p1.getY())) && p1.getX() > 0
					&& p1.getX() < bi.getWidth()) {

				Lrecurse(origin, bf, new Point((int) p1.getX() - 1, (int) p1.getY()), bi);
				// Recurse left
			}
			p1 = new Point((int) p1.getX(), (int) p1.getY() - 1);
			// New point to perform action on

		}
		return;
	}

	/**
	 * Recursive call on right side
	 * 
	 * @param origin
	 *            Starting point of current iteration of algorithm
	 * @param bf
	 *            bucketFill object to reference
	 * @param p
	 *            points to parse
	 * @param bi
	 *            bufferedImage to check
	 */
	public void Rrecurse(int origin, BucketFill bf, Point p, BufferedImage bi) {
		Point p1 = new Point((int) p.getX(), (int) p.getY());
		while ((bi.getRGB((int) p1.getX(), (int) p1.getY()) == origin) && (!bf.getPoints().contains(p1))
				&& p1.getY() > 0 && p1.getY() < bi.getHeight()) {
			bf.calcForDraw(new Points((int) p1.getX(), (int) p1.getY()));
			// Calculate how to draw the bucketFill
			if (!bf.getPoints().contains(new Point((int) p1.getX() + 1, (int) p1.getY())) && p1.getX() > 0
					&& p1.getX() < bi.getWidth()) {
				Rrecurse(origin, bf, new Point((int) p1.getX() + 1, (int) p1.getY()), bi);
				// Recurses right
			}
			p1 = new Point((int) p1.getX(), (int) p1.getY() + 1);
			// New point to perform action on
		}

		p1 = new Point((int) p.getX(), (int) p.getY() - 1);
		// New point to perform action on
		while ((bi.getRGB((int) p1.getX(), (int) p1.getY()) == origin) && (!bf.getPoints().contains(p1))
				&& p1.getY() > 0 && p1.getY() < bi.getHeight()) {
			bf.calcForDraw(new Points((int) p1.getX(), (int) p1.getY()));
			// Calculate how to draw the bucketFill
			if (!bf.getPoints().contains(new Point((int) p1.getX() + 1, (int) p1.getY())) && p1.getX() > 0
					&& p1.getX() < bi.getWidth()) {

				Rrecurse(origin, bf, new Point((int) p1.getX() + 1, (int) p1.getY()), bi);
				// Recurses right
			}
			p1 = new Point((int) p1.getX(), (int) p1.getY() - 1);
			// New point to perform action on

		}
		return;
	}
}
