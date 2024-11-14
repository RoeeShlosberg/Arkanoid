// Roee Shlosberg 211600812
package gamePlay.gameSettings;

import generators.RandomObjects;
import geometry.axis.Point;
import geometry.objects.Block;
import geometry.axis.Rectangle;

import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


/**
 * The type Blocks builder - builds blocks from the right corner of a frame with given rows and settings.
 */
public class BlocksBuilder {
    private int rows;
    private int numOfFirstLine; // how many blocks in the first line.
    private int blockHeight;
    private int blockWidth;
    private int wallSize;
    private int frameSize;
    private List<Block> blocks; // contains all the blocks built.

    /**
     * Instantiates a new Blocks builder.
     *
     * @param rows           the rows
     * @param numOfFirstLine the num of first line
     * @param blockHeight    the block height
     * @param blockWidth     the block width
     * @param wallSize       the wall size
     * @param frameSize      the frame size
     */
    public BlocksBuilder(int rows, int numOfFirstLine, int blockHeight, int blockWidth, int wallSize, int frameSize) {
        this.rows = rows;
        this.numOfFirstLine = numOfFirstLine;
        this.blockHeight = blockHeight;
        this.blockWidth = blockWidth;
        this.wallSize = wallSize;
        this.frameSize = frameSize;
        this.blocks = new LinkedList<>();
    }

    /**
     * Block list - list of all the blocks that the object has.
     *
     * @return the list
     */
    public List<Block> blockList() {
        return this.blocks;
    }

    /**
     * Build - building the blocks with the settings given.
     */
    public void build() {
        for (int i = 0; i < this.rows; i++) {
            Color col = RandomObjects.generateRandomColor(); // random color.
            for (int j = 0; j < this.numOfFirstLine - i; j++) {
                // from right -> left along the row
                double x = this.frameSize - this.wallSize - this.blockWidth - (j * this.blockWidth);
                double y = i * this.blockHeight + 100; // from up -> down every row
                Rectangle r = new Rectangle(new Point(x, y), blockWidth, blockHeight);
                Block b = new Block(r, col);
                this.blocks.add(b);
            }
        }
    }
}
