package project;



public class NearestInsertionRoute {
	
	// TODO
	// You must use a linked list structure to store the
	// points in the route.  They should appear in the list
	// in the order determined by the insertion heuristic.
	
	// You will need to define a Node class here inside this class
	// and you will need to at least have one field in the class
	// that will be a reference to the first point in the route (list).
	//-----------------------------------------------------------------------
	private int len; // length variable
	
	private Point first;
	
	public class Point { // You get a node! And YOU get a node! EVERYBODY GETS A NODE!!
		
		private double posX;
		private double posY;
		private Point next;
		

	}

	/**
	 * Creates an empty route.
	 */
	public NearestInsertionRoute() { 
		len = 0;
	}
	
	
	/**
	 * Inserts a the point into this route immediately after the point
	 * already in the route that is closest to the new point.  If there is
	 * more than one point along the route that is at the minimal distance
	 * from the new point, the new point is inserted immediately after
	 * the earliest such point along the route.
	 * Note the first point inserted into the empty route is the start
	 * location and will always remain at the front of the list.
	 * 
	 * @param x the x value of the point being inserted
	 * @param y the y value of the point being inserted
	 */
	public void insert(double x, double y) {
		// TODO
		if (len == 0) {
			len++;
			Point newPoint = new Point();
			newPoint.posX = x;
			newPoint.posY = y;
			first = newPoint;
		}
		else {
			len++;
			Point newPoint = new Point();
			newPoint.posX = x;
			newPoint.posY = y;
			double distance = distCalc(newPoint, first);
			for (Point iter = first; iter != null; iter = iter.next) {
				if (distCalc(newPoint, iter) < distance) {
					distance = distCalc(newPoint, iter);
				}
			}
			for (Point iter = first; iter != null; iter = iter.next) {
				if (distCalc(newPoint, iter) == distance) {
					newPoint.next = iter.next;
					iter.next = newPoint;
					break;
				}
			}
		}
	}
	public double distCalc(Point firstPoint, Point otherPoint) {
			double y2 = otherPoint.posY;
			double y1 = firstPoint.posY;
			double x2 = otherPoint.posX;  //wrote this method to avoid having to type all this multiple times
			double x1 = firstPoint.posX;
			return Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
		}

	
	/**
	 * Returns the overall length of the route that begins
	 * at the first point inserted into the route, visits
	 * every other point in the route and then returns to
	 * the starting point.
	 * 
	 * @return the overall length of the route
	 */
	public double length() {
		// TODO
		if (len == 1) {
			return 0;
		} 
		else {
		double lengthCalc = 0;
		for (Point iter = first; iter != null; iter = iter.next) {
				if (iter.next == null) {
					lengthCalc += distCalc(first, iter); //called to find distance moved while returning to the start at the last node
					return lengthCalc;
				}
				lengthCalc += distCalc(iter, iter.next);
			}

		return lengthCalc;
		}
	}
	

	/**
	 * Returns a String representation of the route as
	 * a list of points separated by a comma and space.
	 * Each point is represented by a String that consists
	 * of an open parenthesis immediately followed by the
	 * x value of the point, immediately followed by a comma
	 * and space, then followed by the y value of the point
	 * which is then immediately followed by a close
	 * parenthesis.  For example, here is a route consisting
	 * of three points.
	 * <p>
	 * {@code "(14.0, 19.0), (24.0, 19.0), (24.0, 20.0)"}
	 * <p>
	 * If the route is empty (no points) then the String
	 * {@code "<empty>"} is returned.
	 */
	@Override
	public String toString() {
		// TODO
		if (len == 0) {
			return "<empty>";
		}
		else {
			StringBuilder str = new StringBuilder();
			for (Point iter = first; iter != null; iter = iter.next) {
				str.append("(" + iter.posX + ", " + iter.posY + ")");
				if (iter.next != null) {
					str.append(", ");
				}
			}
			String returnstr = str.toString();
			return returnstr;
		}
	}
	public static void main(String[] args) {

	}
}
