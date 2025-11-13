package driver;

import controller.BattlefieldController;
import controller.LevelBuilder;

/**
 * Driver for gameplay loop
 */
public class Driver {
    public static void main(String[] args) {
        UserInputReader reader = new UserInputReader();
        UserInput input;

        LevelBuilder.initTutorialDummy();

        do {
            BattlefieldController.instance.printState();
            // TODO handle input (update battlefield, print changes, etc)

            // read the next command
            input = reader.readCommand();
            System.out.println("Read valid input, continuing to next step");
        } while (input.inputType != UserInput.InputType.QUIT);

        // TODO print out player stats?
        System.out.println("Thank u for playing :)");
    }
}
