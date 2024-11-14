// Roee Shlosberg 211600812
package gamePlay.screenDraw;

import biuoop.DrawSurface;
import gamePlay.levels.GameLevel;
import gamePlay.time.Sprite;

import java.awt.Color;

/**
 * The type Game text - draws the level's name.
 */
public class GameText implements Sprite {
    private String name;

    /**
     * Instantiates a new Game text.
     *
     * @param name  the name of level.
     */
    public GameText(String name) {
        this.name = name;
    }

    /**
     * Add to game - adding to the sprites list.
     *
     * @param game the game level.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    // draw only text - name of level.
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(600, 17, ("Level Name: " + this.name), 17);
    }
    // does nothing.
    @Override
    public void timePassed() {
    }
}
