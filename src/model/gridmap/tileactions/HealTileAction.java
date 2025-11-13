package model.gridmap.tileactions;

import model.entities.Entity;
import model.gridmap.Tile;

public class HealTileAction extends TileAction {
    int baseHeal;

    public HealTileAction(int baseHeal) {
        this.baseHeal = baseHeal;
    }

    @Override
    public void applyAction(Tile tile) {
        Entity entity = tile.entity;
        if (entity != null) {
            entity.heal(baseHeal);
        }
    }
}
