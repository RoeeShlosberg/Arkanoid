// Roee Shlosberg 211600812
package gamePlay;

import animationRun.AnimationRunner;
import animationRun.pause.GameOverScreen;
import animationRun.KeyPressStoppableAnimation;
import animationRun.pause.WinScreen;
import biuoop.KeyboardSensor;
import gamePlay.levels.GameLevel;
import gamePlay.levels.LevelInformation;
import generators.Counter;

import java.util.List;

/**
 * The type Game flow - runs a series of game levels one after another and ends the game with a relevant screen.
 */
public class GameFlow {
    private KeyboardSensor keyboard;
    private AnimationRunner runner;
    private Counter score; // the score will keep track every level.
    private boolean winning; // for the ending screen.

    /**
     * Instantiates a new Game flow.
     *
     * @param ar the animation runner.
     * @param ks the keyboard.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        this.keyboard = ks;
        this.runner = ar;
        this.score = new Counter(0); // score = 0.
        this.winning = true; // not losing for now.
    }

    /**
     * Run levels - runs the series of level from a list of level information.
     *
     * @param levels the levels
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) { // each level - from the information of it.
            GameLevel level = new GameLevel(levelInfo, this.keyboard, this.runner, this.score);
            level.initialize();
            while (!level.shouldStop()) { // keep going until win/lose.
                level.run();
            }
            if (level.isLost()) { // lost.
                this.winning = false;
                break;
            }
            this.score.increase(100);
        }
        if (this.winning) { // winning screen.
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new WinScreen(this.score)));
        } else { // game over screen.
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY,
                    new GameOverScreen(this.score)));
        }
    }
}