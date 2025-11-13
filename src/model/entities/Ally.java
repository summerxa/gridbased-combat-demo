package model.entities;

import driver.UserInput.InputType;
import model.Constants;
import model.gridmap.TilePos;
import model.gridmap.tileactions.AddMpTileAction;
import model.gridmap.tileactions.DealDamageTileAction;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a playable ally unit.
 */
public abstract class Ally extends ActionEntity {
    protected int lifeThreshold;
    protected int deathThreshold;

    protected int[] skillTimer = new int[] {0,0};
    /**
     * Equal to the cooldown of the corresponding skill PLUS ONE
     */
    protected int[] skillCD = new int[] {1,1};
    /**
     * Equal to the MP change from using the corresponding skill
     */
    protected int[] skillMP = new int[] {0,0};

    protected Ally(EntityAttributes baseAttributes) {
        super(baseAttributes);
    }

    /**
     * Basic impl that just checks if skills are usable (off cooldown and within MP threshold)
     * i.e. ignores whether there are actually valid target-able entities within range
     */
    public boolean isValidMove(InputType move) {
        if (move == InputType.ULTIMATE) {
            return attributes.mp <= deathThreshold || attributes.mp >= lifeThreshold;
        } else if (move == InputType.MP_AOE) {
            return Math.abs(attributes.mp) >= Constants.ALLY_MP_AOE_THRESHOLD;
        } else if (move == InputType.SKILL1 || move == InputType.SKILL2) {
            return skillTimer[move == InputType.SKILL1 ? 0 : 1] == 0;
        }
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
            case ULTIMATE -> ultRange();
            case MP_AOE -> new ArrayList<>(Collections.singletonList(attributes.pos));
            default -> new ArrayList<>();
        };
    }

    // return a list of all valid tiles that you can target w/ this ability
    public abstract ArrayList<TilePos> basicAtkRange();
    public abstract ArrayList<TilePos> moveRange();
    public abstract ArrayList<TilePos> skill1Range();
    public abstract ArrayList<TilePos> skill2Range();

    public ArrayList<TilePos> ultRange() {
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
            case ULTIMATE -> ultAction(pos);
            case MP_AOE -> mpAoeAction();
            default -> null; // hopefully this case never happens
        };
    }

    // these methods don't *perform* the action
    // only return a list of which tiles will be affected by a skill use at the selected location "pos"
    public EntityActionChanges basicAtkAction(TilePos pos) {
        EntityActionChanges actions = new EntityActionChanges();
        actions.addAction(pos, new DealDamageTileAction(Constants.ALLY_BASIC_ATK_DMG));
        return actions;
    }
    public abstract EntityActionChanges skill1Action(TilePos pos);
    public abstract EntityActionChanges skill2Action(TilePos pos);

    public EntityActionChanges ultAction(TilePos pos) {
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

    public void performAndProcessAction(InputType move, ArrayList<EntityActionResult> results) {
        if (move == InputType.SKILL1) {
            skillTimer[0] = skillCD[0];
            attributes.mp += skillMP[0];
        } else if (move == InputType.SKILL2) {
            skillTimer[1] = skillCD[1];
            attributes.mp += skillMP[1];
        } else if (move == InputType.ULTIMATE) {
            attributes.mp = 0;
        }
        // TODO if needed, add MP change from basic atk, movement, forfeit
        processActionResult(move, results);
    }

    public void onTurnStart() {
        // TODO trigger status effects and decrement their timers
    }

    public void onTurnEnd() {
        // decrement skill cooldowns
        for (int i = 0; i < 2; ++i)
            if (skillTimer[i] > 0)
                --skillTimer[i];
    }
}
