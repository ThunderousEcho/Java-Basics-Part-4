import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

public class Assignment4 {

	public static void Go(Scanner scanner) {
		System.out.println("Nothing to see here. Run JUnit.");
	}
	
	@Test
	public void testGetSlope() {
		Line line = new Line(0, 0, 1, 2);
		assertEquals(2, line.getSlope(), 0.0001);
	}
	
	@Test
	public void testGetDistance() {
		Line line = new Line(0, 0, 3, 4);
		assertEquals(5, line.getDistance(), 0.0001);
	}
	
	@Test
	public void getDistance() {
		Line line0 = new Line(0, 0, 3, 4);
		Line line1 = new Line(0, 1, 3, 5);
		assertTrue(line0.parallelTo(line1, 0.0001));
		Line line2 = new Line(0, 1, 3, 6);
		assertFalse(line0.parallelTo(line2, 0.0001));
	}
}

class Line {
	public double ax;
	public double ay;
	public double bx;
	public double by;
	
	public Line(int ax, int ay, int bx, int by) {
		this.ax = ax;
		this.ay = ay;
		this.bx = bx;
		this.by = by;
	}

	public double getSlope() {
		return (by-ay) / (bx-ax);
	}
	
	public double getDistance() {
		double dx = bx-ax;
		double dy = by-ay;
		return Math.sqrt(dx * dx + dy * dy);
	}
	
	public boolean parallelTo(Line other, double epsilon) {
		return Math.abs(other.getSlope() - getSlope()) < epsilon;
	}
}
