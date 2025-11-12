package model.entities;

import driver.UserInput.InputType;
import model.gridmap.TilePos;
import model.gridmap.tileactions.TileAction;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * Represents a playable ally unit.
 */
public abstract class Ally extends ActionEntity {
    protected Ally(EntityAttributes baseAttributes) {
        super(baseAttributes);
    }

    public boolean isValidMove(InputType move) {
        // TODO check for cooldowns and available targets and stuff
        return move == InputType.BASIC_ATK || move == InputType.FORFEIT || move == InputType.MOVEMENT;
    }

    // return a list of all valid tiles that you can target w/ this ability
    public abstract ArrayList<TilePos> basicAtkRange();
    public abstract ArrayList<TilePos> moveRange();
    public abstract ArrayList<TilePos> skill1Range();
    public abstract ArrayList<TilePos> skill2Range();

    public ArrayList<TilePos> ultimateRange() {
        return (attributes.mp > 0) ? lifeUltRange() : deathUltRange();
    }

    public abstract ArrayList<TilePos> lifeUltRange();
    public abstract ArrayList<TilePos> deathUltRange();

    // these methods don't *perform* the action
    // only return a list of which tiles will be affected by a skill use at the selected location "pos"
    public abstract Entry<TilePos, TileAction> basicAtkAction(TilePos pos);
    public abstract ArrayList<Entry<TilePos, TileAction>> skill1Action(TilePos pos);
    public abstract ArrayList<Entry<TilePos, TileAction>> skill2Action(TilePos pos);

    public ArrayList<Entry<TilePos, TileAction>> ultimateAction(TilePos pos) {
        return (attributes.mp > 0) ? lifeUltAction(pos) : deathUltAction(pos);
    }

    protected abstract ArrayList<Entry<TilePos, TileAction>> lifeUltAction(TilePos pos);
    protected abstract ArrayList<Entry<TilePos, TileAction>> deathUltAction(TilePos pos);
}
