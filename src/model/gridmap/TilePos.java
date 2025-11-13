package model.gridmap;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a position on the 8x8 grid.
 * I'm getting CritterWorld flashbacks.
 * Also includes a ton of helpers that get different AOEs
 */
public class TilePos {
    int row;
    int col;

    public TilePos(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public TilePos(TilePos pos) {
        this.row = pos.row;
        this.col = pos.col;
    }

    /**
     * Build a dummy "invalid"/out of bounds position.
     */
    public static TilePos outOfBounds() {
        return new TilePos(-1, -1);
    }

    public static boolean isInBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public static boolean isInBounds(TilePos pos) {
        return isInBounds(pos.row, pos.col);
    }

    public static ArrayList<TilePos> getSquareAOE(TilePos center, int size, boolean inclSelf) {
        ArrayList<TilePos> tiles = new ArrayList<>();
        for (int r = center.row - size; r <= center.row + size; r++) {
            for (int c = center.col - size; c <= center.col + size; c++) {
                if (!inclSelf && center.row == r && center.col == c) {
                    continue;
                }
                if (isInBounds(r, c)) {
                    tiles.add(new TilePos(r, c));
                }
            }
        }
        return tiles;
    }

    public static ArrayList<TilePos> getDiamondAOE(TilePos center, int size, boolean inclSelf) {
        ArrayList<TilePos> sqTiles = getSquareAOE(center, size, inclSelf);
        ArrayList<TilePos> tiles = new ArrayList<>();
        for (TilePos tile : sqTiles) {
            int manhattanDistance = Math.abs(center.row - tile.row) + Math.abs(center.col - tile.col);
            if (manhattanDistance <= size) {
                tiles.add(tile);
            }
        }
        return tiles;
    }

    public static ArrayList<TilePos> getAllTiles(TilePos center, boolean inclSelf) {
        ArrayList<TilePos> tiles = new ArrayList<>();
        for (int r = 0; r < 8; ++r) {
            for (int c = 0; c < 8; ++c) {
                if (!inclSelf && center.row == r && center.col == c) {
                    continue;
                }
                tiles.add(new TilePos(r, c));
            }
        }
        return tiles;
    }

    public static ArrayList<TilePos> singleTile(TilePos center) {
        return new ArrayList<>(Collections.singletonList(center));
    }

    public static ArrayList<TilePos> getBishopAOE(TilePos center, int size, boolean inclSelf) {
        ArrayList<TilePos> tiles = new ArrayList<>();
        for (int i = 1; i <= size; ++i) {
            TilePos[] corners = new TilePos[] {
                    new TilePos(center.row-i, center.col-i),
                    new TilePos(center.row-i, center.col+i),
                    new TilePos(center.row+i, center.col-i),
                    new TilePos(center.row+i, center.col+i),
            };
            for (TilePos corner : corners) {
                if (isInBounds(corner)) {
                    tiles.add(corner);
                }
            }
        }
        if (inclSelf) {
            tiles.add(center);
        }
        return tiles;
    }

    public static ArrayList<TilePos> getRookAOE(TilePos center, int size, boolean inclSelf) {
        ArrayList<TilePos> tiles = new ArrayList<>();
        for (int i = 1; i <= size; ++i) {
            TilePos[] corners = new TilePos[] {
                    new TilePos(center.row-i, center.col),
                    new TilePos(center.row+i, center.col),
                    new TilePos(center.row, center.col-i),
                    new TilePos(center.row, center.col+i),
            };
            for (TilePos corner : corners) {
                if (isInBounds(corner)) {
                    tiles.add(corner);
                }
            }
        }
        if (inclSelf) {
            tiles.add(center);
        }
        return tiles;
    }

    public static ArrayList<TilePos> getQueenAOE(TilePos center, int size, boolean inclSelf) {
        ArrayList<TilePos> tiles = getRookAOE(center, size, inclSelf);
        tiles.addAll(getBishopAOE(center, size, inclSelf));
        return tiles;
    }

    public boolean equals(TilePos pos) {
        return row == pos.row && col == pos.col;
    }
}
