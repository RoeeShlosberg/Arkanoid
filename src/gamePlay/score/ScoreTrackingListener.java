// Roee Shlosberg 211600812
package gamePlay.score;

import gamePlay.removing.HitListener;
import generators.Counter;
import geometry.objects.Ball;
import geometry.objects.Block;

/**
 * The type Score tracking listener - is notified whenever a hit, updates the game's score.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter of the game.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    // hit-event - adding 5 points to the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
       currentScore.increase(5);
    }
}
