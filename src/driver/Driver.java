package driver;

import controller.BattlefieldController;
import controller.LevelBuilder;
import model.gridmap.TilePos;

/**
 * Driver for gameplay loop
 */
public class Driver {
    public static void main(String[] args) {
        UserInputReader reader = new UserInputReader();
        UserInput.InputType input;

        LevelBuilder.initTutorialDummy();

        do {
            BattlefieldController.instance.printState();
            // TODO handle input (update battlefield, print changes, etc)

            // read the next command
            input = reader.readCommand();
            System.out.println("Received a valid input command");
//            TilePos pos = reader.readCoords();
//            System.out.println("Received a valid coordinate");
        } while (input != UserInput.InputType.QUIT);

        // TODO print out player stats?
        System.out.println("Thank u for playing :)");
    }
}
