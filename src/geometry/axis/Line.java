// Roee Shlosberg 211600812
package geometry.axis;

import java.util.List;

/**
 * The type Line - between two points.
 */
public class Line {
    private Point start;
    private Point end;
    /**
     * The Threshold.
     */
    static final double THRESHOLD = 0.0001; // the value of minimum distance between two identical numbers.


    /**
     * Instantiates a new Line - by a settings start and end value.
     *
     * @param start the start point
     * @param end   the end point
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line - by x's and y's values.
     *
     * @param x1 the x value of the start
     * @param y1 the y value of the start
     * @param x2 the x value of the end
     * @param y2 the y value of the end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Gets the start point.
     *
     * @return the start.
     */
    public Point getStart() {
        return this.start;
    }

    /**
     * Gets the end point.
     *
     * @return the end.
     */
    public Point getEnd() {
        return this.end;
    }

    /**
     * Length double - the size of the line.
     *
     * @return the double value of the length.
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Middle point of the line.
     *
     * @return the point of the middle.
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }

    /**
     * Is intersecting boolean - checks if two lines are interacting (may have infinite points).
     *
     * @param other the other line.
     * @return the boolean - if they're interacting or not if they don't have any crossing.
     */
    public boolean isIntersecting(Line other) {
        // in case one of them is 'a point' - start = end - checks if it in the other line.
        if (this.isPoint() && !other.isPoint()) {
            return this.start.isInLine(other);
        } else if (other.isPoint()) {
            return other.start.isInLine(this);
            // in case one of them is a line of "x = a" kind:
        } else if (this.isParallelY() && other.isParallelY()) {
            // the method for two parallel functions.
            return (Line.crossWithTwoParallels(this, other));
        } else if (this.isParallelY()) {
            double a = other.findIncline();
            Point p = this.interWithOneParallel(a, other.start.findInter(a)); // the point of interaction.
            return (p.isInLine(other) && p.isInParallel(this)); // point on the normal and parallel functions
        } else if (other.isParallelY()) {
            double a = this.findIncline();
            Point p = other.interWithOneParallel(a, this.start.findInter(a)); // the point of interaction
            return (p.isInLine(this) && p.isInParallel(other)); // point on the normal and parallel functions.
        }

        // both the lines are "y = ax + b" kind - finding the a's and b's:
        double a1 = this.findIncline();
        double a2 = other.findIncline();
        double b1 = this.start.findInter(a1);
        double b2 = other.start.findInter(a2);
        // in case they are parallel to each other:
        if (Math.abs(a1 - a2) < THRESHOLD) {
            if (Math.abs(b1 - b2) < THRESHOLD) { //same a&b - same function.
                // if there is a sharing points:
                return this.interWithNormalParallel(other);
            }
            return false; // will never interact
        }
        Point p = this.findCrossNormal(a1, b1, a2, b2); // crossing point.
        if (p == null) {
            return false;
        }
        // checks if p is both lines.
        return (p.isInLine(this) && p.isInLine(other));
    }

    /**
     * Intersection with point - find the crossing point (in case is there only one).
     *
     * @param other the other line
     * @return the point of cross, or null if isn't one.
     */
    public Point intersectionWith(final Line other) {
        // in case one of them is 'a point' - start = end:
        if (this.isPoint() && other.isPoint()) {  // in case they're both points.
            if (this.start.equals(other.start)) {
                return this.start;
            }
            return null;
        }
        // in case of only one - checks if it in the other line.
        if (this.isPoint() && !other.isPoint()) {
            if (this.start.isInLine(other)) {
                return this.start;
            }
            return null;
        } else if (other.isPoint()) {
            if (other.start.isInLine(this)) {
                return other.start;
            }
            return null;
            // in case one of them is a line of "x = a" kind:
        } else if (this.isParallelY() && other.isParallelY()) { // both are parallel to the y-axis.
            if (Line.crossWithTwoParallels(this, other)) {
                return this.findSharingPoint(other);
            } else {
                return null; // will never interact
            }
            // in case only one of them is parallel to the y-axis.
        } else if (this.isParallelY()) {
            double a = other.findIncline(); // finds the a and b in the equation.
            Point p = this.interWithOneParallel(a, other.start.findInter(a)); // crossing point.
            if (p.isInLine(other) && p.isInParallel(this)) { // if the point is both the functions.
                return p;
            }
            return null;
        } else if (other.isParallelY()) {
            double a = this.findIncline(); // finds the a and b in the equation
            Point p = other.interWithOneParallel(a, this.start.findInter(a)); // crossing point.
            if (p.isInLine(this) && p.isInParallel(other)) { // if the point is both the functions.
                return p;
            }
            return null;
        }
        // in the case of both are "y = ax + b" kind - finds the a's and b's:
        double a1 = this.findIncline();
        double a2 = other.findIncline();
        double b1 = this.start.findInter(a1);
        double b2 = other.start.findInter(a2);
        if (Math.abs(a1 - a2) < THRESHOLD) { // they are parallel to each other.
            // they are the same function with crossing points
            if (Math.abs(b1 - b2) < THRESHOLD
                    && this.interWithNormalParallel(other)) {
                // the points shared (if it just one):
                return this.findSharingPoint(other);
            }
            return null; // will never interact.
        }
        Point p = this.findCrossNormal(a1, b1, a2, b2); // crossing point of function.
        if (p.isInLine(this) && p.isInLine(other)) { // in both lines.
            return p;
        }
        return null;
    }

    /**
     * Equals boolean - checks if the lines are the same - by comparing both couples by Point method.
     *
     * @param other the other line.
     * @return the boolean - true if it is the same, false otherwise.
     */
    public boolean equals(final Line other) {
        // start = start, end = end.
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        // if start = end and end = start
        return this.start.equals(other.end) && this.end.equals(other.start);
    }

    /**
     * Closest intersection to start of line point -checks what is the crossing point between line and rectangle
     * to the start of the line.
     *
     * @param rect the rectangle.
     * @return the point of the closest cross, null if there isn't one.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this); // gets all hits points.
        if (list == null || list.size() == 0) { // no hits/infinite hits.
            Line cross = rect.findOverLappingLine(this); // in case there are infinite.
            if (cross == null) { // no hits
                return null;
            } else {
                // start point is outside the rect, so find the closest edge point:
                return this.start.findClosestPoint(cross.start, cross.end);
            }
        }
        Point p = list.get(0);
        double length = p.distance(this.start);
        for (Point point : list) {
            double temp = this.start.distance(point);
            if (temp < length) { // closest hit until now.
                p = point;
                length = temp;
            }
        }
        return p;
    }

    /**
     * Is from center boolean - checks whether a line that cross a rectangle is from the direction of the
     * rectangle center or not.
     *
     * @param rec the rectangle.
     * @return the boolean - true if it comes from it's center direction, else if not or if it's not crossing it.
     */
    public boolean isFromCenter(Rectangle rec) {
        Point start = this.getStart();
        Point end = this.getEnd();
        if (end.isInLine(rec.lineLeft()) && end.isInLine(rec.lineDown())) {
            return (start.getX() > end.getX());
        } else if (end.isInLine(rec.lineLeft()) && end.isInLine(rec.lineUp())) {
            return (start.getY() > end.getY());
        } else if (end.isInLine(rec.lineRight()) && end.isInLine(rec.lineDown())) {
            return (start.getX() < end.getX());
        } else if (end.isInLine(rec.lineRight()) && end.isInLine(rec.lineUp())) {
            return (start.getY() < end.getY());
        }
        return false;
    }
    ///// interacting methods ///////
    // Find incline double - find the 'a' in the "y = ax + b" equation.
    private double findIncline() {
        if (this.end.getX() > this.start.getX()) { // end > start
            return (this.end.getY() - this.start.getY()) // y2-y1 / x2-x1
                    / (this.end.getX() - this.start.getX());
        }
        return (this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX());
    }

    // Is parallel y boolean - checks if the line is parallel to the y-axis.
    private boolean isParallelY() {
        // checks if the x values are identical:
        return Math.abs(start.getX() - end.getX()) < THRESHOLD;
    }

    // Two parallels y inter boolean - checks if two parallel to y functions have sharing points.
    private static boolean crossWithTwoParallels(final Line l1, final Line l2) {
        if (Math.abs(l1.start.getX() - l2.start.getX()) > THRESHOLD) { // the x values are different - no match.
            return false;
        }
        // finds the x and y value of lines limits:
        double a1 = l1.start.getY();
        double a2 = l1.end.getY();
        double b1 = l2.start.getY();
        double b2 = l2.end.getY();
        // if there is a point between limits (there's space):
        return !(Math.min(a1, a2) > Math.max(b1, b2))
                && !(Math.max(a1, a2) < Math.min(b1, b2));
    }

    // Is intersecting with same incline boolean - checks if two normal identical functions have sharing points.
    private boolean interWithNormalParallel(final Line other) {
        double a1 = this.start.getX();
        double a2 = this.end.getX();
        double b1 = other.start.getX();
        double b2 = other.end.getX();
        // if there is a point between limits:
        return !(Math.min(a1, a2) > Math.max(b1, b2))
                && !(Math.max(a1, a2) < Math.min(b1, b2));
    }

    // Intersecting parallel y point - finds the point of collision between function and a paralleled line.
    private Point interWithOneParallel(final double a, final double b) {
        double y = this.start.getX() * a + b; // finding the y value
        return new Point(this.start.getX(), y);
    }

    /*
     * Find sharing point - finds the shared point between identical function (start or end).
     * return the point of crossing (only if it's the tip! - null otherwise)
     */
    private Point findSharingPoint(Line l) {
        // checking each option of limits shared
        if (this.start.equals(l.start) && this.start.haveDifferentDirections(this.end, l.end)) {
            return this.start;
        } else if (this.start.equals(l.end) && this.start.haveDifferentDirections(this.end, l.start)) {
            return this.start;
        } else if (this.end.equals(l.start) && this.end.haveDifferentDirections(this.start, l.end)) {
            return this.end;
        } else if (this.end.equals(l.end) && this.end.haveDifferentDirections(this.start, l.start)) {
            return this.end;
        }
        return null; // there are infinite points - then null
    }

    // Find cross point - of two functions by their a's and b's of equations.
    private Point findCrossNormal(final double a1, final double b1,
                                  final double a2, final double b2) {
        if (Math.abs(a1 - a2) < THRESHOLD) { // they're parallel to each other
            if (Math.abs(b1 - b2) < THRESHOLD) { // same function
                return new Point(1, a1 * 1 + b1); // a random point shared
            }
            return null;
        }
        double x = (b2 - b1) / (a1 - a2); //finds the x of the point
        double y = a1 * x + b1; //finds the y
        return new Point(x, y);
    }

    // Is point boolean - checks if the line is actually a point.
    private boolean isPoint() {
        return this.start.equals(this.end); // checks points values.
    }
}
