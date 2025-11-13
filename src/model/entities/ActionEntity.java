package model.entities;

import driver.UserInput;
import model.Constants;
import model.gridmap.TileListener;
import util.MiscUtil;

import java.util.ArrayList;

/**
 * An entity that can take action (i.e. an Ally or Enemy)
 */
public abstract class ActionEntity extends Entity {
    protected ActionEntity(EntityAttributes baseAttributes) {
        super(baseAttributes);
    }

    public void onBattleStart() {}

    public void onHpChanged(double coeff, int amount, double multiplier, double denom_additive) {
        double mp_change = coeff * multiplier * amount / (attributes.maxHP + denom_additive);
        updateMP(MiscUtil.awayFromZero(mp_change));
    }


    // optional, override if needed
    public void processEvent(TileListener.TileEvent event, ArrayList<Object> actor) {

    }

    protected void processActionResult(UserInput.InputType action, ArrayList<EntityActionResult> results) {

    }

    /**
     * Must call this BEFORE subtracting HP from shield/hp bar in order to calculate the shield amount correctly!
     */
    public void onDamageTaken(int damage) {
        onHpChanged(-1.0, damage, Constants.ENTITY_SELF_DAMAGED_MP_MULTIPLIER,
                Constants.ENTITY_SELF_DAMAGED_SHIELDVALUE_MULTIPLIER * attributes.shield);
    }

    public void onHpRecovered(int heal) {
        onHpChanged(1.0, heal, Constants.ENTITY_SELF_HEALED_MP_MULTIPLIER, 0);
    }

    public void onTeammateDowned() {
        updateMP(-Constants.ENTITY_TEAMMATE_DOWNED_MP_LOSS);
    }
}
