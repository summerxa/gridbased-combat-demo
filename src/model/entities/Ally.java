package model.entities;

import driver.UserInput.InputType;
import model.Constants;
import model.gridmap.TilePos;
import model.gridmap.tileactions.AddMpTileAction;

import java.util.ArrayList;

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
    public abstract EntityActionResult basicAtkAction(TilePos pos);
    public abstract EntityActionResult skill1Action(TilePos pos);
    public abstract EntityActionResult skill2Action(TilePos pos);

    public EntityActionResult ultimateAction(TilePos pos) {
        return (attributes.mp > 0) ? lifeUltAction(pos) : deathUltAction(pos);
    }

    protected abstract EntityActionResult lifeUltAction(TilePos pos);
    protected abstract EntityActionResult deathUltAction(TilePos pos);

    public EntityActionResult mpAOE() {
        EntityActionResult result = new EntityActionResult();
        int level = (int)Math.floor(Math.abs(attributes.mp) / 10.0);
        if (level == 0) {
            return result;
        }
        ArrayList<TilePos> aoe = TilePos.getDiamondAOE(attributes.pos, level, false);
        int coeff = (attributes.mp < 0) ? -1 : 1;
        for (TilePos pos : aoe) {
            result.addAction(pos, new AddMpTileAction(
                    coeff * level * Constants.ALLY_MP_AOE_PERLEVEL_MULTIPLIER));
        }
        return result;
    }
}
