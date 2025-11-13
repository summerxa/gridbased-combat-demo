package model.gridmap;

import model.entities.ActionEntity;

import java.util.ArrayList;

/**
 * Listens for an event that happens on a certain fixed tile.
 */
public class TileListener {
    public enum TileEvent {         // params passed to callback:
        ENTITY_TAKES_DMG,           // attacker, defender, actual dmg received by defender
        ENTITY_ENTERS,              // actor
        ENTITY_EXITS                // actor
    }

    /**
     * Entity to notify of the event when it occurs
     */
    public ActionEntity callback;

    /**
     * Which event to listen for
     */
    public TileEvent listenFor;

    /**
     * Notifies callback entity of the event if-and-only-if it's the same event this object is listening for.
     */
    // not a big fan of using a list of objects, but this works for now
    public void processEvent(TileEvent event, ArrayList<Object> params) {
        if (event == listenFor) {
            callback.processEvent(event, params);
        }
    }
}
