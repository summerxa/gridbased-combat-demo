package model.gridmap;

/**
 * Represents a position on the 8x8 grid.
 * I'm getting CritterWorld flashbacks.
 */
public class TilePos {
    int row;
    int col;

    public static boolean isInBounds(int row, int col) {
        return row >= 0 && row < 8 && col >= 0 && col < 8;
    }

    public static boolean isInBounds(TilePos pos) {
        return isInBounds(pos.row, pos.col);
    }
}
