package controller;

import model.entities.Ally;
import model.entities.basekits.PBase;
import model.gridmap.Grid;
import model.gridmap.TilePos;

public class LevelBuilder {
    public static void initTutorialDummy() {
        Grid grid = new Grid();

        Ally pBase = new PBase(new TilePos(6, 3));
        grid.placeEntity(pBase);
        BattlefieldController.instance.allies.add(pBase);

        // TODO add a dummy enemy
        BattlefieldController.instance.grid = grid;
    }
}
