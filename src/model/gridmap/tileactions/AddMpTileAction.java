package model.gridmap.tileactions;

import model.entities.Entity;
import model.gridmap.Tile;

public class AddMpTileAction extends TileAction {
    int mpChange;

    public AddMpTileAction(int mpChange) {
        this.mpChange = mpChange;
    }

    @Override
    public void applyAction(Tile tile) {
        Entity entity = tile.entity;
        if (entity != null) {
            entity.updateMP(mpChange);
        }
    }
}
