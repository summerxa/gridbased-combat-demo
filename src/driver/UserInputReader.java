package driver;

import java.util.InputMismatchException;
import java.util.Scanner;

import controller.BattlefieldController;
import driver.UserInput.InputType;
import model.gridmap.TilePos;

/**
 * Use to read user inputs from console.
 */
public class UserInputReader {
    Scanner scanner;

    public UserInputReader() {
        scanner = new Scanner(System.in);
    }

    public InputType readCommand() {
        InputType input;
        do {
            System.out.print("Enter a command: ");
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

    // TODO fix this
    public TilePos readCoords() {
        TilePos pos = TilePos.outOfBounds();
        do {
            System.out.print("Enter coordinates in row column format (ex. \"4 5\"): ");
            int r, c;
            try {
                r = scanner.nextInt();
                c = scanner.nextInt();
                pos = new TilePos(r, c);
            } catch (InputMismatchException e) {
                System.out.println("Please give me 2 number :(");
            }
            // TODO: also check if the inputted pos is target-able by the current char's skill
        } while (!TilePos.isInBounds(pos));
        return pos;
    }
}
