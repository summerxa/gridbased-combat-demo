package model.entities;

import model.gridmap.TilePos;

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
     * @return true if entity died, otherwise false
     */
    public boolean takeDamage(int baseDamage) {
        // TODO incorporate DEF
        attributes.hp -= baseDamage;
        return attributes.hp <= 0;
    }

    /**
     * Regen hp
     * @param healAmount amount to regen before modifiers applied
     */
    public void heal(int healAmount) {
        attributes.hp += healAmount;
    }
}
