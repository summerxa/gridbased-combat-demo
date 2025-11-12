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

    public void onHpChanged(double coeff, int amount, double multiplier) {
        double mp_change = coeff * multiplier * amount / attributes.maxHP;
        updateMP(MiscUtil.awayFromZero(mp_change));
    }

    public void onDamageTaken(int damage) {
        onHpChanged(-1.0, damage, SELF_DAMAGED_MP_MULTIPLIER);
    }

    public void onHpRecovered(int heal) {
        onHpChanged(1.0, heal, SELF_HEALED_MP_MULTIPLIER);
    }

    public void onAllyDowned() {
        updateMP(-ALLY_DOWNED_MP_LOSS);
    }

    public abstract void act();
}
