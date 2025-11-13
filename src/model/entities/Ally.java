package model.entities;

import driver.UserInput.InputType;
import model.Constants;
import model.gridmap.TilePos;
import model.gridmap.tileactions.AddMpTileAction;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a playable ally unit.
 */
public abstract class Ally extends ActionEntity {
    protected Ally(EntityAttributes baseAttributes) {
        super(baseAttributes);
    }

    /**
     * Basic impl that just checks if skills are off cooldown.
     * i.e. ignores whether there are actually valid target-able entities within range
     */
    public boolean isValidMove(InputType move) {
        // TODO check for cooldowns and available targets and stuff
        return move == InputType.BASIC_ATK || move == InputType.FORFEIT || move == InputType.MOVEMENT;
    }

    /**
     * Returns all possible tiles that user can select as a target for this ability.
     */
    public ArrayList<TilePos> getAbilityRange(InputType ability) {
        return switch (ability) {
            case BASIC_ATK -> basicAtkRange();
            case MOVEMENT -> moveRange();
            case SKILL1 -> skill1Range();
            case SKILL2 -> skill2Range();
            case ULTIMATE -> ultimateRange();
            case MP_AOE -> new ArrayList<>(Collections.singletonList(attributes.pos));
            default -> new ArrayList<>();
        };
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

    /**
     * Returns the list of all tiles affected by a given "ability" applied at a location "pos"
     */
    public EntityActionChanges getAbilityAction(InputType ability, TilePos pos) {
        return switch (ability) {
            case BASIC_ATK -> basicAtkAction(pos);
            case SKILL1 -> skill1Action(pos);
            case SKILL2 -> skill2Action(pos);
            case ULTIMATE -> ultimateAction(pos);
            case MP_AOE -> mpAoeAction();
            default -> null; // hopefully this case never happens
        };
    }

    // these methods don't *perform* the action
    // only return a list of which tiles will be affected by a skill use at the selected location "pos"
    public abstract EntityActionChanges basicAtkAction(TilePos pos);
    public abstract EntityActionChanges skill1Action(TilePos pos);
    public abstract EntityActionChanges skill2Action(TilePos pos);

    public EntityActionChanges ultimateAction(TilePos pos) {
        return (attributes.mp > 0) ? lifeUltAction(pos) : deathUltAction(pos);
    }

    protected abstract EntityActionChanges lifeUltAction(TilePos pos);
    protected abstract EntityActionChanges deathUltAction(TilePos pos);

    public EntityActionChanges mpAoeAction() {
        EntityActionChanges result = new EntityActionChanges();
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
