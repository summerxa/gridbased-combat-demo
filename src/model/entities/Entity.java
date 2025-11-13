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
        this.attributes = new EntityAttributes(baseAttributes);
    }

    /**
     * Take some amount of incoming damage
     * @param baseDamage base damage before DEF and other modifiers applied
     */
    public void takeDamage(int baseDamage) {
        // TODO incorporate DEF
        attributes.hp -= MiscUtil.awayFromZero(attributes.defenseMult * baseDamage);
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

    public abstract void onTurnStart();
    public abstract void onTurnEnd();

    public abstract void processEvent(TileListener.TileEvent event, ArrayList<Object> actor);
    public abstract void processActionResult(UserInput.InputType action, ArrayList<EntityActionResult> results);
}
