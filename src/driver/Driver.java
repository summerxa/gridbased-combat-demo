package driver;

import controller.BattlefieldController;

/**
 * Driver for gameplay loop
 */
public class Driver {
    public static void main(String[] args) {

        BattlefieldController battlefield = new BattlefieldController();

        UserInputReader reader = new UserInputReader(battlefield);
        UserInput input = new UserInput();

        do {
            // TODO handle input (update battlefield, print changes, etc)

            // read the next command
            input = reader.readCommand();
            System.out.println("Read valid input, continuing to next step");
        } while (input.inputType != UserInput.InputType.QUIT);

        // TODO print out player stats?
        System.out.println("Thank u for playing :)");
    }
}
