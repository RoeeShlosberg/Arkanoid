// Roee Shlosberg 211600812
package gamePlay.removing;

import gamePlay.levels.GameLevel;
import generators.Counter;
import geometry.objects.Ball;
import geometry.objects.Block;

/**
 * The type Block remover - in charge of removing blocks from the game,
 * as well as keeping count of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game that the blocks belong to.
     * @param removedBlocks the counter type in which the number of blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    // Blocks that are hit should be removed from the game.
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
        beingHit.removeHitListener(this); // remove BlockRemove listener after the block is out of the game.
    }
}