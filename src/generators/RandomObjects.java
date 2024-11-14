// Roee Shlosberg 211600812
package generators;

import geometry.axis.Velocity;

import java.awt.Color;
import java.util.Random;

/**
 * The type Random objects - generate random units for different objects.
 */
public class RandomObjects {
    private static final Random RAND = new Random(); // random initializer.

    /**
     * Generate random velocity - return velocity with random angle and given speed.
     *
     * @param speed the speed given.
     * @return the velocity.
     */
    public static Velocity generateRandomVelocity(int speed) {
        int angle = RAND.nextInt(360);
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Generate random color - return color with random values of base colors.
     *
     * @return the color.
     */
    public static Color generateRandomColor() {
        int colorRange = 256;
        int a = RAND.nextInt(colorRange);
        int b = RAND.nextInt(colorRange);
        int c = RAND.nextInt(colorRange);
        return new Color(a, b, c);
    }

    /**
     * Chances boolean - returning boolean of true for a given number (odds).
     *
     * @param num the number x of chances of 1-x.
     * @return the boolean true/false.
     */
    public static boolean chances(int num) {
        int a = RAND.nextInt(num);
        return a == 0;
    }
}
