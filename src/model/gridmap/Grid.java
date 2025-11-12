package model.gridmap;

import model.entities.Entity;

public class Grid {
    public Tile[][] grid = new Tile[8][8];

    public Grid() {
        // initialize grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = new Tile();
            }
        }
    }

    public void placeEntity(Entity entity, TilePos pos) {
        grid[pos.row][pos.col].entity = entity;
    }
}
