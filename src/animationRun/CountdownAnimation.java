// Roee Shlosberg 211600812
package animationRun;

import biuoop.DrawSurface;
import gamePlay.time.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation - animation for any new level that counts the time for the beginning (3, 2, 1, 0...).
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private long timeZero;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds in total.
     * @param countFrom    the number that's start the counting.
     * @param gameScreen   the sprites that'll will show at the screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.timeZero = System.currentTimeMillis();
    }
    // in one frame - all the sprites of the new level + the number of the counting.
    @Override
    public void doOneFrame(DrawSurface d) {
        gameScreen.drawAllOn(d); // draw all the level (without going forward).
        d.setColor(Color.GRAY);
        double secondsForNum = numOfSeconds / countFrom; // time length of every digit.
        long millisSecondsForNum = (long) (secondsForNum * 1000);
        long timePassed = System.currentTimeMillis() - timeZero;
        int digit = (int) (timePassed / (millisSecondsForNum)); // the digit of current time.
        String letter = "" + (countFrom - digit);
        if (letter.equals("0")) { // in case time passed (in milliseconds) - suspend in "1".
            letter = "1";
        }
        d.drawText(360, 300, letter, 100);
    }
    // should stop when the number of seconds of the animation passed.
    @Override
    public boolean shouldStop() {
        return System.currentTimeMillis() - this.timeZero > this.numOfSeconds * 1000;
    }
}