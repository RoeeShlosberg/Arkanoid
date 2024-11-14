// Roee Shlosberg 211600812
package gamePlay.levels;

import animationRun.Animation;
import animationRun.AnimationRunner;
import animationRun.CountdownAnimation;
import animationRun.KeyPressStoppableAnimation;
import animationRun.pause.PauseScreen;

import java.awt.Color;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import gamePlay.screenDraw.GameText;
import gamePlay.screenDraw.Screen;
import gamePlay.collision.Collidable;
import gamePlay.collision.GameEnvironment;
import gamePlay.removing.BallRemover;
import gamePlay.removing.BlockRemover;
import gamePlay.score.BallAdding;
import gamePlay.score.ScoreIndicator;
import gamePlay.score.ScoreTrackingListener;
import gamePlay.time.Sprite;
import gamePlay.time.SpriteCollection;
import generators.Counter;
import generators.RandomObjects;
import geometry.axis.Velocity;
import geometry.objects.Ball;
import geometry.objects.Block;
import geometry.objects.DeathBlock;
import geometry.objects.Paddle;
import geometry.axis.Point;
import geometry.axis.Rectangle;


/**
 * The type Game - initialize and running of a game with balls, collidable and sprite objects.
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites; // list of sprites.
    private GameEnvironment environment; // list of collidable objects.
    private KeyboardSensor keyboard;
    private Counter numOfBlocks;
    private Counter numOfBalls;
    private Counter score;
    private AnimationRunner runner; // the runner of the current level.
    private boolean running;
    private LevelInformation levelInfo; // inforamtion about paddle, balls etc.


    /**
     * Instantiates a new Game - initialize new lists off sprites and collidable objects and the new animation runner.
     *
     * @param levelInfo the level info
     * @param keyboard  the keyboard
     * @param runner    the runner
     * @param score     the score
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboard, AnimationRunner runner, Counter score) {
        this.levelInfo = levelInfo;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.runner = runner;
        // initialization of score, blocks and balls from the level information:
        this.numOfBlocks = new Counter(levelInfo.numberOfBlocksToRemove());
        this.numOfBalls = new Counter(levelInfo.numberOfBalls());
        this.score = score;
        this.keyboard = keyboard;
    }

    /**
     * Gets environment - return the GameEnvironment object.
     *
     * @return the game environment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }

    /**
     * Add collidable object to the game list.
     *
     * @param c the object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite object to the game list.
     *
     * @param s the object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove collidable from the game.
     *
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Remove sprite from the game.
     *
     * @param s the sprite.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize - preparing new game - balls, blocks, paddle, screen and keyboard.
     */
    public void initialize() {
        // setter of game's background:
        int width = 800;
        int height = 600;
        Screen screen = levelInfo.getBackground();
        screen.addToGame(this); // adding a screen first to draw first in the game.

        // adding "walls" - collidable blocks at the edges of the frame.
        int wallSize = 20;
        Block up = new Block(new Rectangle(new Point(0, wallSize), width, wallSize), Color.BLACK);
        Block left = new Block(new Rectangle(new Point(0, 0), wallSize, height), Color.BLACK);
        Block right = new Block(new Rectangle(new Point(width - wallSize, 0), wallSize, height), Color.BLACK);
        up.addToGame(this);
        left.addToGame(this);
        right.addToGame(this);

        // adding new paddle with the new keyboard.
        Paddle paddle = new Paddle(this.keyboard, Color.ORANGE,
                new Rectangle(new Point(400 - (this.levelInfo.paddleWidth() / 2), 570),
                        levelInfo.paddleWidth(), 15), levelInfo.paddleSpeed());
        paddle.addToGame(this);

        // adding balls at the middle of the game with the information about their velocity and amount.
        for (int i = 0; i < this.numOfBalls.getValue(); i++) {
            Velocity v = this.levelInfo.initialBallVelocities().get(i);
            Ball ball = new Ball(400, 550, 4, Color.WHITE, v);
            ball.addToGame(this);
            paddle.addGameBall(ball); // add to paddle.
        }

        // adding ending block at the bottom - losing the ball.
        DeathBlock endBlock = new DeathBlock(new Rectangle(new Point(0, height), width, wallSize));
        endBlock.addToGame(this);
        BallRemover ballRemover = new BallRemover(this, this.numOfBalls); // removes balls when they hit floor.
        endBlock.addHitListener(ballRemover);

        // score and level text and score settings:
        ScoreTrackingListener scoreRun = new ScoreTrackingListener(this.score); // adding score when needs to.
        ScoreIndicator scoreDraw = new ScoreIndicator(this.score); // drawing the score.
        scoreDraw.addToGame(this);
        GameText text = new GameText(this.levelInfo.levelName()); // drawing name.
        text.addToGame(this);

        // adding all blocks from level info to the game, a remover and score indicator will "watch" them:
        BlockRemover blockRemover = new BlockRemover(this, this.numOfBlocks);
        BallAdding ballAdding = new BallAdding(this, this.numOfBalls, paddle); // adding new ball when relevant.
        for (Block b : this.levelInfo.blocks()) {
            // odds of 1 to 10 - new price block!
            if (RandomObjects.chances(10)) {
                b.setColor(new Color(220, 220, 50)); // Golden block - receive one ball when hit!
                b.addHitListener(ballAdding);
            }
            b.addToGame(this);
            b.addHitListener(blockRemover);
            b.addHitListener(scoreRun);
        }
    }

    /**
     * Run - starting the game after initialized, doing the animation.
     */
    public void run() {
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, this.sprites)); // runs a new count-down.
        this.runner.run(this); // runs the animation.
        this.running = false;
    }

    /**
     * Is lost boolean - returns whether the player's lost (no more balls) or not.
     *
     * @return the boolean - true/false.
     */
    public boolean isLost() {
        return this.numOfBalls.getValue() <= 0;
    }
    // should stop when the blocks or balls are over.
    @Override
    public boolean shouldStop() {
        return (this.numOfBalls.getValue() <= 0 || this.numOfBlocks.getValue() <= 0);
    }
    // each frame - all the sprites continue and draw, stop when the 'p' key is pressed.
    @Override
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) { // start an animation of pause.
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen()));
        }
        this.sprites.notifyAllTimePassed();
        this.sprites.drawAllOn(d);
    }
}