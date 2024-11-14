package runningTests;

import gamePlay.removing.HitListener;
import geometry.objects.Ball;
import geometry.objects.Block;

public class PrintingHitListener implements HitListener {
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block was hit.");
    }
}