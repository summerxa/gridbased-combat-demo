package model.entities;

import model.gridmap.TilePos;
import model.gridmap.tileactions.TileAction;

import java.util.*;

/**
 * Represents all the tiles that receive a change due to an entity's action
 */
public class EntityActionChanges {
    HashMap<TilePos, ArrayList<TileAction>> actions = new HashMap<>();

    public void addAction(TilePos pos, TileAction action) {
        if (!actions.containsKey(pos)) {
            actions.put(pos, new ArrayList<>(Collections.singletonList(action)));
        }
    }

    public ArrayList<Map.Entry<TilePos, ArrayList<TileAction>>> toList() {
        ArrayList<Map.Entry<TilePos, ArrayList<TileAction>>> result = new ArrayList<>();
        for (TilePos pos : actions.keySet()) {
            result.add(new AbstractMap.SimpleEntry<>(pos, actions.get(pos)));
        }
        return result;
    }
}
