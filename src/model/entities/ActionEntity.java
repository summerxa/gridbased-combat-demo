package model.entities;

import model.Constants;
import util.MiscUtil;

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

    public abstract void act();
}
