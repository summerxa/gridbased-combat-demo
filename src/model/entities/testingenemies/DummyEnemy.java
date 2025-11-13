package model.entities.testingenemies;

import model.entities.Enemy;
import model.entities.EntityAttributes;
import model.gridmap.TilePos;

public class DummyEnemy extends Enemy {
    public DummyEnemy(char displayID, TilePos pos) {
        super(new EntityAttributes("Dummy", displayID, 1000, 50, 50, pos));
    }
}
