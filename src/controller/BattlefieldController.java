package controller;

import driver.UserInput.InputType;
import model.entities.Ally;
import model.entities.Enemy;
import model.entities.Entity;
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

    private BattlefieldController() {
        if (instance != null) {
            throw new RuntimeException("BattlefieldController should be a singleton");
        }
    }

    public enum BattlefieldState {
//        ALLY_INFO,
//        ENEMY_INFO,
        ALLY_TURN,
        ENEMY_TURN
    }

    BattlefieldState currentState = BattlefieldState.ALLY_TURN;
    int currentActor = 0;

    public Grid grid;

    public ArrayList<Ally> allies = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();

    public void printState() {
        System.out.println("\n    0  1  2  3  4  5  6  7");
        for (int i = 0; i < 8; ++i) {
            System.out.print(" " + i);
            for (int j = 0; j < 8; ++j) {
                System.out.print("  " + grid.get(i, j));
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Returns whether the provided InputType is a valid action to take at the current time step.
     * Assumes this is for user-provided input (i.e. SETUP is an invalid command type)
     */
    public boolean isValidCommandType(InputType input) {
        if (currentState == BattlefieldState.ENEMY_TURN) {
            return true;
        }
        return input != InputType.INVALID;
        // TODO uncomment this line (just for testing)
//        return input != InputType.INVALID && allies.get(currentActor).isValidMove(input);
    }

    private void incrActor(ArrayList entityList, ArrayList otherList, BattlefieldState switchToState) {
        do {
            ++currentActor;
        } while (currentActor < entityList.size() && !((Entity)entityList.get(currentActor)).attributes.isAlive());

        if (currentActor == entityList.size()) {
            currentState = switchToState;
            currentActor = 0;
            while (currentActor < otherList.size() && !((Entity)otherList.get(currentActor)).attributes.isAlive()) {
                ++currentActor;
            }
        }
    }

    /**
     * Requires: at the time of calling, there is at least one unit on each side
     * that is still alive
     * <br><br>
     * Goes to next battle step. This does NOT include triggering onTurnEnd() for the previous actor
     * and onTurnStart() for the next actor.
     */
    public void nextStep() {
        if (currentState == BattlefieldState.ALLY_TURN) {
            incrActor(allies, enemies, BattlefieldState.ENEMY_TURN);
        } else {
            incrActor(enemies, allies, BattlefieldState.ALLY_TURN);
        }
    }
}
