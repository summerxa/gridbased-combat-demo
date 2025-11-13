package model.entities;

import driver.UserInput;
import model.gridmap.TileListener;
import util.MiscUtil;

import java.util.ArrayList;

public abstract class Entity {
    public final EntityAttributes baseAttributes;
    public EntityAttributes attributes;

    protected Entity(EntityAttributes baseAttributes) {
        this.baseAttributes = baseAttributes;
        attributes = new EntityAttributes(baseAttributes);
    }

    /**
     * Take some amount of incoming damage
     * @param baseDamage base damage before DEF and other modifiers applied
     */
    public void takeDamage(int baseDamage) {
        attributes.hp -= computeDefense(baseDamage);
    }

    /**
     * Regen hp
     * @param healAmount amount to regen before modifiers applied
     */
    public void heal(int healAmount) {
        // sadly we cannot do necromancy and revive dead entities
        if (attributes.isAlive()) {
            attributes.hp += healAmount;
        }
    }

    /**
     * Update MP value but clamp it between -MAX_MP and +MAX_MP.
     */
    public void updateMP(int amount) {
        attributes.mp = Math.min(Math.max(attributes.mp + amount, -EntityAttributes.MAX_MP), EntityAttributes.MAX_MP);
    }

    public String toString() {
        return attributes.name;
    }

    public String toGridString() {
        return Character.toString(attributes.gridRep);
    }

    // override if needed
    public void onTurnStart() {
        // TODO trigger status effects and decrement their timers
    }
    public void onTurnEnd() {
    }

    public int computeOffense(int input) {
        // TODO add atk and other buffs
        // also want to consider buffs that only affect healing/dmg/etc
        // might need to pass in another argument representing whether input is a heal, damage, or status effect?
        double updInput = attributes.offenseMult * input;
        if (attributes.mp >= 0) {
            return MiscUtil.awayFromZero(updInput * (100 + 2 * attributes.mp) / 100.0);
        }
        return MiscUtil.awayFromZero(updInput * (100 + attributes.mp) / 100.0);
    }

    public int computeDefense(int input) {
        // TODO add def and other buffs
        return MiscUtil.awayFromZero(attributes.defenseMult * input);
    }
}
