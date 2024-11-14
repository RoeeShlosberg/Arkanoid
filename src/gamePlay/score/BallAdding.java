package gamePlay.score;

import gamePlay.levels.GameLevel;
import gamePlay.removing.HitListener;
import generators.Counter;
import generators.RandomObjects;
import geometry.axis.Point;
import geometry.objects.Ball;
import geometry.objects.Block;
import geometry.objects.Paddle;

import java.awt.Color;

/**
 * The type Ball Adding - in charge of adding new ball in case of hitting a Golden block.
 */
public class BallAdding implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;
    private Paddle paddle; // game's paddle for adding new balls.

    /**
     * Instantiates a new Ball Adding.
     *
     * @param game          the game that the ball belongs to.
     * @param remainingBalls the counter type in which the number of Balls in the game.
     * @param paddle the paddle of the game for adding new balls for him to "push".
     */
    public BallAdding(GameLevel game, Counter remainingBalls, Paddle paddle) {
        this.game = game;
        this.remainingBalls = remainingBalls;
        this.paddle = paddle;
    }

    // GoldenBlocks that are hit adding new ball.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        Point p = beingHit.getCollisionRectangle().getCenter(); // center of the block rectangle.
        Ball b = new Ball(p, 4, Color.WHITE, RandomObjects.generateRandomVelocity(4)); // new ball from center.
        b.addToGame(this.game);
        paddle.addGameBall(b);
        this.remainingBalls.increase(1);
        beingHit.removeHitListener(this); // remove BlockRemove listener after the block is out of the game.
    }
}
