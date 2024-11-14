// Roee Shlosberg 211600812
package geometry.axis;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Rectangle - a square object with height and width.
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private static final double THRESHOLD = 0.0001; // the minimum value difference between the same number.

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.height = height;
        this.width = width;
    }

    /**
     * Intersection points list - return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line the line
     * @return the list of the points if there are any, null if not.
     */
    public List<Point> intersectionPoints(Line line) {
        List list = new ArrayList<Point>();
        Point p1 = line.intersectionWith(this.lineUp()); // checks crossing with line up (if only one).
        if (p1 != null) {
            list.add(p1);
        } else if (line.isIntersecting(this.lineUp())) {
            return null;
        }
        Point p2 = line.intersectionWith(this.lineLeft()); // checks crossing with line up (if only one).
        if (p2 != null && !p2.equals(p1)) { // assure not the same point.
            list.add(p2);
        } else if (line.isIntersecting(this.lineLeft())) {
            return null;
        }
        Point p3 = line.intersectionWith(this.lineRight()); // checks crossing with line up (if only one).
        if (p3 != null && !p3.equals(p1)) { // assure not the same point.
            list.add(p3);
        } else if (line.isIntersecting(this.lineRight())) {
            return null;
        }
        Point p4 = line.intersectionWith(this.lineDown()); // checks crossing with line up (if only one).
        if (p4 != null && !p4.equals(p2) && !p4.equals(p3)) { // assure not the same point.
            list.add(p4);
        } else if (line.isIntersecting(this.lineDown())) {
            return null;
        }
        if (list.size() == 0) {
            return null;
        }
        return list;
    }

    /**
     * Find over lapping line - return the rectangle edge the is overlapping a given line, in case there is one.
     *
     * @param line the given line.
     * @return the edge line that is overlapping, or null if there isn't one.
     */
    public Line findOverLappingLine(Line line) {
        // check with every edge if is intersecting but don't have a single crossing point:
        if (this.lineUp().intersectionWith(line) == null && this.lineUp().isIntersecting(line)) {
            return this.lineUp();
        }
        if (this.lineLeft().intersectionWith(line) == null && this.lineLeft().isIntersecting(line)) {
            return this.lineLeft();
        }
        if (this.lineDown().intersectionWith(line) == null && this.lineDown().isIntersecting(line)) {
            return this.lineDown();
        }
        if (this.lineRight().intersectionWith(line) == null && this.lineRight().isIntersecting(line)) {
            return this.lineRight();
        }
        return null; // no match.
    }

    /**
     * Gets width - return the width of the rectangle.
     *
     * @return the width
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height - return the height of the rectangle.
     *
     * @return the height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left - return the edge point of the rectangle.
     *
     * @return the upper left point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Gets center.
     *
     * @return the point of the center of the rectangle.
     */
    public Point getCenter() {
        double x = this.upperLeft.getX() + (this.width / 2);
        double y = this.upperLeft.getY() + (this.height / 2);
        return new Point(x, y);
    }

    /**
     * Line up - return the up vertical line.
     *
     * @return the line-up.
     */
    public Line lineUp() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());
    }

    /**
     * Line down - return the down vertical line.
     *
     * @return the line-down.
     */
    public Line lineDown() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Line left - return the left horizontal line.
     *
     * @return the left line.
     */
    public Line lineLeft() {
        return new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.height);
    }

    /**
     * Line right - return the right horizontal line.
     *
     * @return the right line.
     */
    public Line lineRight() {
        return new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Is point inside boolean - returns true if a point is "inside" the rectangle, else otherwise.
     *
     * @param p the point searched.
     * @return the boolean - true if it does or false.
     */
    public boolean isPointInside(Point p) {
        // checks if the point is between the x's and y's values including the tips.
        if (((Math.abs(p.getX() - this.upperLeft.getX()) < THRESHOLD) || p.getX() > this.upperLeft.getX())
                && ((Math.abs(p.getX() - this.upperLeft.getX() + this.width) < THRESHOLD)
                || (p.getX() < this.upperLeft.getX() + this.width))) {
            if (((Math.abs(p.getY() - this.upperLeft.getY()) < THRESHOLD) || p.getY() > this.upperLeft.getY())
                    && ((Math.abs(p.getY() - this.upperLeft.getY() + this.height) < THRESHOLD)
                    || (p.getY() < this.upperLeft.getY() + this.height))) {
                return true;
            }
        }
        return false; // isn't inside.
    }

    /**
     * Sets upper left - change the place point of the rectangle.
     *
     * @param p the new point of the edge.
     */
    public void setUpperLeft(Point p) {
        this.upperLeft = p;
    }
}