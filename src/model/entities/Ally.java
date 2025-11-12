package model.entities;

import driver.UserInput;
import model.gridmap.TilePos;

/**
 * Represents a playable ally unit.
 */
public abstract class Ally extends ActionEntity {
    protected Ally(EntityAttributes baseAttributes) {
        super(baseAttributes);
    }

    // TODO some of these prob don't need to be abstract (move & basicAtk maybe)
    // need to make more classes/data structures to store movement and attack ranges etc
    public abstract void basicAtk(TilePos pos);
    public abstract void move(TilePos pos);
    public abstract void skill1(TilePos pos);
    public abstract void skill2(TilePos pos);
    public abstract void lifeUlt(TilePos pos);
    public abstract void deathUlt(TilePos pos);

    // TODO ult shouldn't be abstract - check MP value and either carry out Life or Death depending on state
    public abstract void ult(TilePos pos);
}
