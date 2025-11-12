package driver;

import java.util.Scanner;

import controller.BattlefieldController;
import driver.UserInput.InputType;

/**
 * Use to read user inputs from console.
 */
public class UserInputReader {
    Scanner scanner;

    public UserInputReader() {
        scanner = new Scanner(System.in);
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
                case "3" -> InputType.ULTIMATE;
                case "q" -> InputType.BASIC_ATK;
                case "w" -> InputType.MOVEMENT;
                case "e" -> InputType.FORFEIT;
                case "r" -> InputType.MP_AOE;
//                case "i" -> InputType.INFO;
                default -> InputType.INVALID;
            };
        } while (!BattlefieldController.instance.isValidCommandType(input));
        return input;
    }
}
