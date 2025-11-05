package controller;

import driver.UserInput.InputType;

/**
 * Controls the entities to perform actions in combat
 */
public class BattlefieldController {
    public enum BattlefieldState {
        ALLY_INFO,
        ENEMY_INFO,
        ALLY_TURN,
        ENEMY_TURN
    }

    BattlefieldState currentState;

    public BattlefieldController() {
        currentState = BattlefieldState.ALLY_TURN;
    }

    /**
     * Returns whether the provided InputType is a valid action to take at the current time step.
     * Assumes this is for user-provided input (i.e. SETUP is an invalid command type)
     */
    public boolean isValidCommandType(InputType input) {
        switch (input) {
            case InputType.SKILL1, InputType.SKILL2:
                // TODO check if on cooldown
                return currentState == BattlefieldState.ALLY_TURN;
        }
        return false;
    }
}
