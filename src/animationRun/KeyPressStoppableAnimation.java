// Roee Shlosberg 211600812
package animationRun;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import gamePlay.screenDraw.Screen;

/**
 * The type Key press stoppable animation - animation that wraps a pausing animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor board;
    private String key;
    private boolean pressed; // current press.
    private boolean isAlreadyPressed; // from the start - default of true.

    /**
     * Instantiates a new Key press stoppable animation.
     *
     * @param sensor    the keyboard that will end the animation.
     * @param key       the key that will stop (usually space key).
     * @param animation the pause animation ("win screen"/"game over" screen...)
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.board = sensor;
        this.key = key;
        this.pressed = false;
        this.isAlreadyPressed = true;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        new Screen(800, 600).drawOn(d); // default background.
        animation.doOneFrame(d); // the pausing animation.
        if (this.board.isPressed(this.key)) { // if that key is pressed - should stop*.
            this.pressed = true;
        } else {
            this.isAlreadyPressed = false; // key haven't pressed - than it might be a new press later.
            this.pressed = false;
        }
    }
    // should stop only if it pressed now & haven't pressed from the beginning.
    @Override
    public boolean shouldStop() {
        return this.pressed && !this.isAlreadyPressed;
    }
}
