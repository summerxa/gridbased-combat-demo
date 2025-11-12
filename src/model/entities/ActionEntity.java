package model.entities;

import util.MiscUtil;

/**
 * An entity that can take action (i.e. an Ally or Enemy)
 */
public abstract class ActionEntity extends Entity {
    public double SELF_DAMAGED_MP_MULTIPLIER = 50.0;
    public double SELF_HEALED_MP_MULTIPLIER = 50.0;
    public int ALLY_DOWNED_MP_LOSS = 20;

    protected ActionEntity(EntityAttributes baseAttributes) {
        super(baseAttributes);
    }

    public void onBattleStart() {}

    public void onHpChanged(double coeff, int amount, double multiplier, int denom_additive) {
        double mp_change = coeff * multiplier * amount / (attributes.maxHP + denom_additive);
        updateMP(MiscUtil.awayFromZero(mp_change));
    }

    /**
     * Must call this BEFORE subtracting HP from shield/hp bar in order to calculate the shield amount correctly!
     */
    public void onDamageTaken(int damage) {
        onHpChanged(-1.0, damage, SELF_DAMAGED_MP_MULTIPLIER, 2 * attributes.shield);
    }

    public void onHpRecovered(int heal) {
        onHpChanged(1.0, heal, SELF_HEALED_MP_MULTIPLIER, 0);
    }

    public void onAllyDowned() {
        updateMP(-ALLY_DOWNED_MP_LOSS);
    }

    public abstract void act();
}
