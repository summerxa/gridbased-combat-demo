package model.entities;

import model.gridmap.TilePos;

/**
 * An entity that can take action (i.e. an Ally or Enemy)
 */
public abstract class ActionEntity extends Entity {
    protected ActionEntity(EntityAttributes baseAttributes) {
        super(baseAttributes);
    }

    public abstract void act();
}
