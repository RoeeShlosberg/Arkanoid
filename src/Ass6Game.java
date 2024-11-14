// Roee Shlosberg 211600812

import animationRun.AnimationRunner;
import gamePlay.GameFlow;
import gamePlay.levels.LevelInformation;
import gamePlay.levels.LevelOne;
import gamePlay.levels.LevelThree;
import gamePlay.levels.LevelTwo;
import generators.RandomObjects;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ass 6 game - starting a new game.
 */
public class Ass6Game {
    /**
     * The entry point of application.
     *
     * @param args the input arguments - the order of the levels playing.
     */
    public static void main(String[] args) {
        biuoop.GUI gui = new biuoop.GUI("game", 800, 600);

        GameFlow game = new GameFlow(new AnimationRunner(gui, 60), gui.getKeyboardSensor());
        List<LevelInformation> list = createLevelList(args);
        game.runLevels(list);
        gui.close();
    }
    // return the list of level - after assuring legality.
    private static List<LevelInformation> createLevelList(String[] strings) {
        List<LevelInformation> list = new ArrayList<>();
        for (String s : strings) { // for every string adding the relevant level.
            switch (s) {
                case "1" -> list.add(new LevelOne());
                case "2" -> list.add(new LevelTwo());
                case "3" -> list.add(new LevelThree());
                default -> {
                }
            }
        }
        // no arguments or no valid argument - three random levels:
        if (list.size() == 0) {
            for (int i = 0; i < 3; i++) {
                if (RandomObjects.chances(3)) {
                    list.add(new LevelOne());
                } else if (RandomObjects.chances(3)) {
                    list.add(new LevelTwo());
                } else {
                    list.add(new LevelThree());
                }
            }
        }
        return list;
    }
}
