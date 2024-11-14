// Roee Shlosberg 211600812
package geometry.axis;

/**
 * geometry.axis.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new Velocity.
     *
     * @param dx the dx.
     * @param dy the dy.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * From angle and speed velocity - sets a new velocity by angle on total speed regardless of dx and dy.
     *
     * @param angle the angle.
     * @param speed the speed.
     * @return the new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx;
        double dy;
        angle %= 360; // find the angle between 0-360
        // checking for each kind of angle 0 -> 90 -> 180 -> 270 -> 360, each one calculating the dx&dy:
        if (angle >= 0 && angle <= 90) {
            dx = Math.sin(Math.PI / (180 / angle)) * speed;
            dy = -Math.sin(Math.PI / (180 / (90 - angle))) * speed;
        } else if (angle > 90 && angle <= 180) {
            angle -= 90;
            dy = Math.sin(Math.PI / (180 / angle)) * speed;
            dx = Math.sin(Math.PI / (180 / (90 - angle))) * speed;
        } else if (angle > 180 && angle <= 270) {
            angle -= 180;
            dx = -Math.sin(Math.PI / (180 / angle)) * speed;
            dy = Math.sin(Math.PI / (180 / (90 - angle))) * speed;
        } else { // angle is between 270-360
            angle -= 270;
            dy = -Math.sin(Math.PI / (180 / angle)) * speed;
            dx = -Math.sin(Math.PI / (180 / (90 - angle))) * speed;
        }
        return new Velocity(dx, dy);
    }

    /**
     * Gets dx.
     *
     * @return the dx of the velocity
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy of the velocity
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Apply to point - Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the original point.
     * @return the new point of the object.
     */
    public Point applyToPoint(final Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * Sets dx.
     *
     * @param dx the new dx
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Sets dy.
     *
     * @param dy the new dy
     */
    public void setDy(double dy) {
        this.dy = dy;
    }
}