package controller;

import driver.UserInput.InputType;
import model.gridmap.Grid;

/**
 * Controls the entities to perform actions in combat
 */
public class BattlefieldController {
    public static BattlefieldController instance = new BattlefieldController();

    public enum BattlefieldState {
//        ALLY_INFO,
//        ENEMY_INFO,
        ALLY_TURN,
        ENEMY_TURN
    }

    BattlefieldState currentState = BattlefieldState.ALLY_TURN;

    public Grid battlefield = new Grid();

    /**
     * Creates a testing battlefield with Tutorial unit and 1 dummy enemy.
     */
    public void initTutorialDummy() {
//        battlefield.placeEntity
    }

    /**
     * Returns whether the provided InputType is a valid action to take at the current time step.
     * Assumes this is for user-provided input (i.e. SETUP is an invalid command type)
     */
    public boolean isValidCommandType(InputType input) {
        if (input == InputType.INVALID) {
            return false;
        }
        if (currentState == BattlefieldState.ENEMY_TURN) {
            return input == InputType.CONTINUE;
        }
        // TODO check if command is valid at this turn
        return true;
    }
}
