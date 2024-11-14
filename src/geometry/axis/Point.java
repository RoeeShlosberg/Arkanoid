// Roee Shlosberg 211600812
package geometry.axis;

/**
 * Point is the class of a point object.
 */
public class Point {
    private double x;
    private double y;
    /**
     * The Threshold.
     */
    static final double THRESHOLD = 0.0001; // the value of minimum distance between two identical numbers.

    /**
     * Instantiates a new Point.
     *
     * @param x the x value.
     * @param y the y value.
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Instantiates a new Point by integers.
     *
     * @param x the x value - get an integer
     * @param y the y value - get an integer
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double - measure the distance between two points.
     *
     * @param other the other point.
     * @return the double value of the distance.
     */
    public double distance(final Point other) {
        double xDistance = this.x - other.x;
        double yDistance = this.y - other.y;
        return Math.sqrt(yDistance * yDistance + xDistance * xDistance);
    }

    /**
     * Equals - return if the points are the same or not - measured with threshold.
     *
     * @param other the other point.
     * @return the boolean if they're the same.
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Math.abs(this.x - other.x) < THRESHOLD) && (Math.abs(this.y - other.y) < THRESHOLD);
    }

    /**
     * Gets x.
     *
     * @return x value.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return y value.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Is in line boolean - checks if the point is in a specific line.
     *
     * @param l the line
     * @return the boolean - if it's in or not.
     */
    public boolean isInLine(final Line l) {
        if (l == null) {
            return false;
        }
        // if the point is the start or the end of the line:
        if (this.equals(l.getStart()) || this.equals(l.getEnd())) {
            return true;
        }
        // if the point is after or before the line it cannot be in it
        if (this.x > Math.max(l.getStart().x, l.getEnd().x)
                || this.x < Math.min(l.getStart().x, l.getEnd().x)) {
            return false;
        }

        if (Math.abs(this.x - l.getStart().x) < THRESHOLD || Math.abs(this.x - l.getEnd().x) < THRESHOLD) {
            return false;
        }
        // if the incline between the start and end and this point is the same - then it's must be in it.
        return Math.abs(this.findIncline(l.getStart()) - l.getEnd().findIncline(this)) < THRESHOLD;
    }

    /**
     * Find inter double - finds the interaction of a function with the Y axis.
     * the 'b' value of y = ax + b.
     *
     * @param m the incline ('a' value)
     * @return the double value of the interaction.
     */
    public double findInter(final double m) {
        return (this.y - this.x * m);
    }

    /**
     * Is in parallel boolean - checks if the point is in a line in the shape of x = a.
     *
     * @param l the line.
     * @return the boolean - if it's on it or not.
     */
    public boolean isInParallel(final Line l) {
        // first - checks if the x value is the same.
        if (Math.abs(this.x - l.getStart().x) > THRESHOLD) {
            return false;
        } else {
            // checks if the y value is in the line limits.
            return !(this.y > Math.max(l.getStart().y, l.getEnd().y))
                    && !(this.y < Math.min(l.getStart().y, l.getEnd().y));
        }
    }

    /**
     * Checks whether a point is between two other point by x-axis.
     *
     * @param a the first point.
     * @param b the second point.
     * @return the boolean if is between (or if it's the same as them)
     */
    public boolean haveDifferentDirections(Point a, Point b) {
        if (this.equals(a) || this.equals(b)) { // is in the limit.
            return true;
        }
        if (this.x < a.x) {
            return b.x < this.x;
        } else {
            return this.x < b.x;
        }
    }

    /**
     * Apply to close collision point - finds a close point to another point in the same path.
     *
     * @param other the other point.
     * @return the point close to it.
     */
    public Point applyToCloseCollisionPoint(Point other) {
        double xDistance = other.x - this.x;
        double yDistance = other.y - this.y;
        return new Point(this.x + (0.7 * xDistance), this.y + (0.7 * yDistance));
    }
    /**
     * Find closest point - find the point which is closer to given point.
     *
     * @param p1 the first point.
     * @param p2 the second point.
     * @return the point closer, or null in case one of them is null.
     */
    public Point findClosestPoint(Point p1, Point p2) {
        if (p1 == null || p2 == null) {
            return null;
        }
        if (this.distance(p1) < this.distance(p2)) {
            return p1;
        } else {
            return p2;
        }
    }

    /**
     * Sets x value.
     *
     * @param x the required value.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets y value.
     *
     * @param y the required value
     */
    public void setY(double y) {
        this.y = y;
    }

    // Find incline double - finds the incline between two points.
    private double findIncline(final Point other) {
        return (this.y - other.getY()) / (this.x - other.getX());
    }
}
