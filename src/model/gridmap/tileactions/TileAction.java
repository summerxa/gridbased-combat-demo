package model.gridmap.tileactions;

import model.gridmap.Tile;

/**
 * Represents an action to apply onto a tile
 */
public abstract class TileAction {
    public abstract void applyAction(Tile tile);
}
