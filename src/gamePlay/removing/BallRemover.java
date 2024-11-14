// Roee Shlosberg 211600812
package gamePlay.removing;

import gamePlay.levels.GameLevel;
import generators.Counter;
import geometry.objects.Ball;
import geometry.objects.Block;

/**
 * The type Ball remover - is called by a hit-notifier, removes a ball from a game.
 * Keeps track of the number of balls left.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game         the game that the balls belong to.
     * @param removedBalls the counter type, in which the number of balls in the game.
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    // Balls that hit a "death-block" are removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1); // updating number of balls left.
    }
}