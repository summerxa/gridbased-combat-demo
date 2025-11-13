package controller;

import model.entities.Ally;
import model.entities.Enemy;
import model.entities.basekits.PBase;
import model.entities.testingenemies.DummyEnemy;
import model.gridmap.Grid;
import model.gridmap.TilePos;

public class LevelBuilder {
    public static void initTutorialDummy() {
        Grid grid = new Grid();

        Ally pBase = new PBase(new TilePos(6, 3));
        grid.placeEntity(pBase);
        BattlefieldController.instance.allies.add(pBase);

        Enemy dummy1 = new DummyEnemy('1', new TilePos(2, 6));
        grid.placeEntity(dummy1);
        BattlefieldController.instance.enemies.add(dummy1);

        BattlefieldController.instance.grid = grid;
    }
}
