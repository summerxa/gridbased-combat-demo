package model.entities;

/**
 * Represents an enemy.
 */
public abstract class Enemy extends ActionEntity {
    protected Enemy(EntityAttributes baseAttributes) {
        super(baseAttributes);
    }
}
