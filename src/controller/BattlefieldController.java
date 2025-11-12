package controller;

import driver.UserInput.InputType;
import model.entities.Ally;
import model.entities.Enemy;
import model.gridmap.Grid;
import model.gridmap.TilePos;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * Controls the entities to perform actions in combat
 */
public class BattlefieldController {
    // Singleton instance yay
    public static BattlefieldController instance = new BattlefieldController();

    public enum BattlefieldState {
//        ALLY_INFO,
//        ENEMY_INFO,
        ALLY_TURN,
        ENEMY_TURN
    }

    BattlefieldState currentState = BattlefieldState.ALLY_TURN;
    int currentActor = 0;

    public Grid grid = new Grid();

    public ArrayList<Ally> allies = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();

    /**
     * Creates a testing battlefield with Tutorial unit and 1 dummy enemy.
     */
    public void initTutorialDummy() {
    }

    /**
     * Returns whether the provided InputType is a valid action to take at the current time step.
     * Assumes this is for user-provided input (i.e. SETUP is an invalid command type)
     */
    public boolean isValidCommandType(InputType input) {
        if (currentState == BattlefieldState.ENEMY_TURN) {
            return true;
        }
        return allies.get(currentActor).isValidMove(input);
    }
}
