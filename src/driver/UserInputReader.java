package driver;

import java.util.Scanner;

import controller.BattlefieldController;
import driver.UserInput.InputType;

/**
 * Use to read user inputs from console.
 */
public class UserInputReader {
    Scanner scanner;
    BattlefieldController battlefield;

    public UserInputReader(BattlefieldController battlefield) {
        scanner = new Scanner(System.in);
        this.battlefield = battlefield;
    }

    public UserInput readCommand() {
        UserInput userInput = new UserInput(readCommandType());
        // TODO
        return userInput;
    }

    public InputType readCommandType() {
        InputType input;
        do {
            System.out.println("Enter a command: ");
            input = switch (scanner.nextLine()) {
                case "x" -> InputType.QUIT;
                case "1" -> InputType.SKILL1;
                case "2" -> InputType.SKILL2;
                case "3" -> InputType.MP_AOE;
                case "q" -> InputType.BASIC_ATK;
                case "w" -> InputType.MOVEMENT;
                case "e" -> InputType.FORFEIT;
                case "r" -> InputType.CHANGE_DEPLOY_STATE;
                case "i" -> InputType.INFO;
                case "" -> InputType.CONTINUE;
                default -> InputType.INVALID;
            };
        } while (!battlefield.isValidCommandType(input));
        return input;
    }
}
